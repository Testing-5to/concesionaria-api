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

import com.autos.concesionaria.entity.Calle;
import com.autos.concesionaria.service.CalleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/localidad")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class CalleController {

    // Service injected by constructor
    @Autowired
    private final CalleService calleService;

    // GET
    // Get mapping to get all the calles
    @GetMapping
    public ResponseEntity<List<Calle>> getCalles() {
        return new ResponseEntity<>(calleService.buscarCalles(), HttpStatus.OK);
    }

    // GET by ID
    // Get mapping to get a calle by id
    @GetMapping("/{id}")
    public ResponseEntity<Calle> getCallePorId(@PathVariable Long id) {
        return new ResponseEntity<>(calleService.buscarCallePorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a calle
    @PostMapping
    public ResponseEntity<Calle> guardarCalle(@RequestBody Calle calle) {
        return new ResponseEntity<>(calleService.crearCalle(calle), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a calle
    @PutMapping("/{id}")
    public ResponseEntity<Calle> actualizarCalle(@PathVariable Long id, @RequestBody Calle calle) {
        return new ResponseEntity<>(calleService.actualizarCallePorId(id, calle), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a calle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCalle(@PathVariable Long id) {
        calleService.eliminarCallePorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
