package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Cliente;
import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.service.ClienteService;
import com.autos.concesionaria.service.DireccionService;
import com.autos.concesionaria.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ClienteController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteService clienteService;
    private final DireccionService direccionService;
    private final VentaService ventaService;

    // GET
    // Obtenemos todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return new ResponseEntity<>(clienteService.buscarClientes(), HttpStatus.OK);
    }

    // GET by ID
    // Obtenemos un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.buscarClientePorId(id), HttpStatus.OK);
    }

    // POST
    // Creamos un cliente
    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        // Si la dirección no existe, la creamos
        if (cliente.getDireccion() != null && cliente.getDireccion().getId() == null) {
            // Comprobamos si la dirección ya existe en la base de datos
            if (direccionService.existeDireccion(cliente.getDireccion())) {
                // Si existe, la obtenemos de la base de datos y la asignamos al cliente
                Direccion direccion = direccionService.buscarDireccion(cliente.getDireccion());
                cliente.setDireccion(direccion);
            } else {
                // Si no existe, la creamos y la asignamos al cliente
                cliente.setDireccion(direccionService.crearDireccion(cliente.getDireccion()));
            }
        }
        logger.info("Guardando cliente: " + cliente);
        return new ResponseEntity<>(clienteService.crearCliente(cliente), HttpStatus.CREATED);
    }

    // PUT
    // Actualizamos un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActual = clienteService.buscarClientePorId(id);
        boolean direccionCambio = false;
        Long direccionId = null;
        if (clienteActual == null) {
            logger.error("No se puede actualizar. El cliente con ID: " + id + " no existe");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Si la dirección cambio, comprobamos si la nueva dirección ya existe en la base de datos y la creamos si no existe
            if (clienteActual.getDireccion() != cliente.getDireccion()) {
                // Si la dirección no existe, la creamos
                if (cliente.getDireccion() != null && cliente.getDireccion().getId() == null) {
                    // Comprobamos que la dirección no exista en la base de datos
                    if (direccionService.existeDireccion(cliente.getDireccion())) {
                        // Si existe, la obtenemos de la base de datos y la asignamos al cliente
                        Direccion direccion = direccionService.buscarDireccion(cliente.getDireccion());
                        cliente.setDireccion(direccion);
                    } else {
                        // Si no existe, la creamos y la asignamos al cliente
                        cliente.setDireccion(direccionService.crearDireccion(cliente.getDireccion()));
                    }
                }
                direccionCambio = true;
                direccionId = clienteActual.getDireccion().getId();
            }
            // Actualizamos el cliente
            Cliente clienteActualizado = clienteService.actualizarClientePorId(id, cliente);
            // Si la dirección cambio, eliminamos la dirección anterior si no está asociada a ningún cliente
            if (direccionCambio && clienteService.contarClientesPorDireccion(direccionId) == 0)
                direccionService.eliminarDireccionPorId(direccionId);
            // Retornamos el cliente actualizado
            logger.info("Actualizando cliente con ID: " + id);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        }
    }

    // DELETE
    // Eliminamos un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        // Buscamos si no existen ventas asociadas al cliente
        if (ventaService.contarVentasPorCliente(id) > 0) {
            logger.error("No se puede eliminar. El cliente con ID: " + id + " tiene ventas asociadas");
            return new ResponseEntity<>("No se puede eliminar. El cliente con ID: " + id + " tiene ventas asociadas", HttpStatus.BAD_REQUEST);
        } else {
            // Si no existen ventas asociadas al cliente, eliminamos el cliente
            clienteService.eliminarClientePorId(id);
            logger.info("Eliminando cliente con ID: " + id);
            return new ResponseEntity<String>("Cliente eliminado: " + id, HttpStatus.OK);
        }
    }

}
