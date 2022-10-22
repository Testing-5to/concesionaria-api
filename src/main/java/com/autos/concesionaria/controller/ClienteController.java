package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Cliente;
import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.service.ClienteService;
import com.autos.concesionaria.service.DireccionService;
import lombok.RequiredArgsConstructor;
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

    // Inyectamos el servicio de cliente
    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final DireccionService direccionService;

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
        // if the direccion doesn't exist, create it
        if (cliente.getDireccion() != null && cliente.getDireccion().getId() == null) {
            // check if the direccion exists in the database
            if (direccionService.existeDireccion(cliente.getDireccion())) {
                // if the direccion exists, get it from the database and set it to the cliente
                Direccion direccion = direccionService.buscarDireccion(cliente.getDireccion());
                cliente.setDireccion(direccion);
            } else {
                // if the direccion doesn't exist, create it
                cliente.setDireccion(direccionService.crearDireccion(cliente.getDireccion()));
            }
        }
        return new ResponseEntity<>(clienteService.crearCliente(cliente), HttpStatus.CREATED);
    }

    // PUT
    // Actualizamos un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActual = clienteService.buscarClientePorId(id);
        Boolean direccionCambio = false;
        Long direccionId = null;
        if (clienteActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // if the direccion changed, check if the new direccion exists in the database and create it if it doesn't
            if (clienteActual.getDireccion() != cliente.getDireccion()) {
                // if the direccion doesn't exist, create it
                if (cliente.getDireccion() != null && cliente.getDireccion().getId() == null) {
                    // check if the direccion exists in the database
                    if (direccionService.existeDireccion(cliente.getDireccion())) {
                        // if the direccion exists, get it from the database and set it to the cliente
                        Direccion direccion = direccionService.buscarDireccion(cliente.getDireccion());
                        cliente.setDireccion(direccion);
                    } else {
                        // if the direccion doesn't exist, create it
                        cliente.setDireccion(direccionService.crearDireccion(cliente.getDireccion()));
                    }
                }
                direccionCambio = true;
                direccionId = clienteActual.getDireccion().getId();
            }
            // update the cliente
            Cliente clienteActualizado = clienteService.actualizarClientePorId(id, cliente);
            // if the direccion changed delete the old direccion and the previous direccion is not used by any other cliente, delete it
            if (direccionCambio && clienteService.contarClientesPorDireccion(direccionId) == 0)
                direccionService.eliminarDireccionPorId(direccionId);
            // return the updated cliente
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        }
    }

    // DELETE
    // Eliminamos un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarClientePorId(id);
        return new ResponseEntity<String>("Cliente eliminado: " + id, HttpStatus.OK);
    }

}
