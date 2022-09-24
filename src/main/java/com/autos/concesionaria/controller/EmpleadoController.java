package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.entity.Empleado;
import com.autos.concesionaria.service.DireccionService;
import com.autos.concesionaria.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
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

    // Service injected by constructor
    @Autowired
    private final EmpleadoService empleadoService;

    @Autowired
    private final DireccionService direccionService;

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
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        }
    }

    // DELETE
    // Delete mapping to delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleadoPorId(id);
        return new ResponseEntity<String>("Empleado eliminado", HttpStatus.OK);
    }

}
