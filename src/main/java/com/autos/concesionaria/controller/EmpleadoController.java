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

    // Service injected by constructor
    @Autowired
    private final EmpleadoService empleadoService;
    @Autowired
    private final DireccionService direccionService;
    @Autowired
    private final VentaService ventaService;

    // GET
    // Get mapping to get all the employees
    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados(@RequestParam(required = false) String rol) {
        if (rol == null) {
            return new ResponseEntity<>(empleadoService.buscarEmpleados(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(empleadoService.buscarEmpleadosByRol(rol), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get an employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(empleadoService.buscarEmpleadoPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create an employee
    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        // if the direccion doesn't exist, create it
        if (empleado.getDireccion() != null && empleado.getDireccion().getId() == null) {
            // check if the direccion exists in the database
            if (direccionService.existeDireccion(empleado.getDireccion())) {
                // if the direccion exists, get it from the database and set it to the empleado
                Direccion direccion = direccionService.buscarDireccion(empleado.getDireccion());
                empleado.setDireccion(direccion);
            } else {
                // if the direccion doesn't exist, create it
                empleado.setDireccion(direccionService.crearDireccion(empleado.getDireccion()));
            }
        }
        logger.info("Guardando empleado: " + empleado);
        return new ResponseEntity<>(empleadoService.crearEmpleado(empleado), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado empleadoActual = empleadoService.buscarEmpleadoPorId(id);
        Boolean direccionCambio = false;
        Long direccionId = null;
        if (empleadoActual == null) {
            logger.error("No se puede actualizar. El empleado con id: " + id + " no existe");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // if the direccion changed, check if the new direccion exists in the database and create it if it doesn't
            if (empleadoActual.getDireccion() != empleado.getDireccion()) {
                // if the direccion doesn't exist, create it
                if (empleado.getDireccion() != null && empleado.getDireccion().getId() == null) {
                    // check if the direccion exists in the database
                    if (direccionService.existeDireccion(empleado.getDireccion())) {
                        // if the direccion exists, get it from the database and set it to the empleado
                        Direccion direccion = direccionService.buscarDireccion(empleado.getDireccion());
                        empleado.setDireccion(direccion);
                    } else {
                        // if the direccion doesn't exist, create it
                        empleado.setDireccion(direccionService.crearDireccion(empleado.getDireccion()));
                    }
                }
                direccionCambio = true;
                direccionId = empleadoActual.getDireccion().getId();
            }
            // update the employee
            Empleado empleadoActualizado = empleadoService.actualizarEmpleadoPorId(id, empleado);
            // if the direccion changed delete the old direccion and the previous direccion is not used by any other employee, delete it
            if (direccionCambio && empleadoService.contarEmpleadosPorDireccion(direccionId) == 0)
                direccionService.eliminarDireccionPorId(direccionId);
            // return the updated employee
            logger.info("Actualizando empleado con id: " + id + " a: " + empleadoActualizado);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        }
    }

    // DELETE
    // Delete mapping to delete an employee
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
