package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Empleado;
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

    // GET
    // Get mapping to get all the employees
    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return new ResponseEntity<>(empleadoService.buscarEmpleados(), HttpStatus.OK);
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
        return new ResponseEntity<>(empleadoService.crearEmpleado(empleado), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        return new ResponseEntity<>(empleadoService.actualizarEmpleadoPorId(id, empleado), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleadoPorId(id);
        return new ResponseEntity<String>("Empleado eliminado", HttpStatus.OK);
    }

}
