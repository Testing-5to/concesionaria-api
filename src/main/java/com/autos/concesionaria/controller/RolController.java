package com.autos.concesionaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.service.RolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rol")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class RolController {

    // Service injected by constructor
    @Autowired
    private final RolService rolService;

    // GET
    // Get mapping to get all the roles
    @GetMapping
    public ResponseEntity<List<Rol>> getRoles() {
        return new ResponseEntity<>(rolService.buscarRoles(), HttpStatus.OK);
    }

    // GET by ID
    // Get mapping to get a rol by id
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolPorId(@PathVariable Long id) {
        return new ResponseEntity<>(rolService.buscarRolPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a rol
    @PostMapping
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol) {
        return new ResponseEntity<>(rolService.crearRol(rol), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a rol
    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        return new ResponseEntity<>(rolService.actualizarRolPorId(id, rol), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a rol
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Long id) {
        rolService.eliminarRolPorId(id);
        return new ResponseEntity<String>("Rol borrado: " + id, HttpStatus.NO_CONTENT);
    }

}