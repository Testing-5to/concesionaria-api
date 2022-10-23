package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.entity.Empleado;
import com.autos.concesionaria.service.DireccionService;
import com.autos.concesionaria.service.EmpleadoService;
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
@RequestMapping("/api/v1/empleado")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class EmpleadoController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    // Inyección de dependencias de los servicios
    @Autowired
    private final EmpleadoService empleadoService;
    @Autowired
    private final DireccionService direccionService;
    @Autowired
    private final VentaService ventaService;

    // GET
    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados(@RequestParam(required = false) String rol) {
        if (rol == null) {
            return new ResponseEntity<>(empleadoService.buscarEmpleados(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(empleadoService.buscarEmpleados(rol), HttpStatus.OK);
        }
    }

    // GET by ID
    // Obtener un empleado por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(empleadoService.buscarEmpleadoPorId(id), HttpStatus.OK);
    }

    // POST
    // Crear un empleado
    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        // Si la dirección no existe, se crea una nueva
        if (empleado.getDireccion() != null && empleado.getDireccion().getId() == null) {
            // Comprobamos si la dirección ya existe en la base de datos
            if (direccionService.existeDireccion(empleado.getDireccion())) {
                // Si existe, se obtiene la dirección de la base de datos y se asigna al empleado
                Direccion direccion = direccionService.buscarDireccion(empleado.getDireccion());
                empleado.setDireccion(direccion);
            } else {
                // Si no existe, se crea una nueva dirección y se asigna al empleado
                empleado.setDireccion(direccionService.crearDireccion(empleado.getDireccion()));
            }
        }
        // Se crea el empleado
        logger.info("Guardando empleado: " + empleado);
        return new ResponseEntity<>(empleadoService.crearEmpleado(empleado), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado empleadoActual = empleadoService.buscarEmpleadoPorId(id);
        Boolean direccionCambio = false;
        Long direccionId = null;
        if (empleadoActual == null) {
            logger.error("No se puede actualizar. El empleado con id: " + id + " no existe");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Si la dirección cambió, se comprueba si la nueva dirección ya existe en la base de datos y se crea si no existe
            if (empleadoActual.getDireccion() != empleado.getDireccion()) {
                // Si la dirección no existe, se crea una nueva
                if (empleado.getDireccion() != null && empleado.getDireccion().getId() == null) {
                    // Compobamos si la dirección ya existe en la base de datos
                    if (direccionService.existeDireccion(empleado.getDireccion())) {
                        // Si la dirección ya existe, se obtiene la dirección de la base de datos y se asigna al empleado
                        Direccion direccion = direccionService.buscarDireccion(empleado.getDireccion());
                        empleado.setDireccion(direccion);
                    } else {
                        // Si la dirección no existe, se crea una nueva dirección y se asigna al empleado
                        empleado.setDireccion(direccionService.crearDireccion(empleado.getDireccion()));
                    }
                }
                direccionCambio = true;
                direccionId = empleadoActual.getDireccion().getId();
            }
            // Actualizamos el empleado
            Empleado empleadoActualizado = empleadoService.actualizarEmpleadoPorId(id, empleado);
            // Si la dirección cambió, se elimina la dirección anterior si no está asociada a ningún otro empleado
            if (direccionCambio && empleadoService.contarEmpleadosPorDireccion(direccionId) == 0)
                direccionService.eliminarDireccionPorId(direccionId);
            // Se devuelve el empleado actualizado
            logger.info("Actualizando empleado con id: " + id + " a: " + empleadoActualizado);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        }
    }

    // DELETE
    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        // Buscamos si no existen ventas asociadas al empleado
        if (ventaService.contarVentasPorEmpleado(id) > 0) {
            logger.error("No se puede eliminar. El empleado con id: " + id + " tiene ventas asociadas");
            return new ResponseEntity<>("No se puede eliminar. El empleado con id: " + id + " tiene ventas asociadas", HttpStatus.BAD_REQUEST);
        } else {
            empleadoService.eliminarEmpleadoPorId(id);
            logger.info("Eliminando empleado con id: " + id);
            return new ResponseEntity<String>("Empleado eliminado", HttpStatus.OK);
        }
    }

}
