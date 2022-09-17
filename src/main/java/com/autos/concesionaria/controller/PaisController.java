package com.autos.concesionaria.controller;

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

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.service.PaisService;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pais")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class PaisController {

    @Autowired
    // Service injected by constructor
    private final PaisService paisService;

    // GET
    // Get mapping to get all the paises
    @GetMapping
    public ResponseEntity<List<Pais>> getPaises() {
        return new ResponseEntity<>(paisService.buscarPaises(), HttpStatus.OK);
    }

    // GET by ID
    // Get mapping to get a pais by id
    @GetMapping("/{id}")
    public ResponseEntity<Pais> getPaisPorId(@PathVariable Long id) {
        return new ResponseEntity<>(paisService.buscarPaisPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a pais
    @PostMapping
    public ResponseEntity<Pais> guardarPais(@RequestBody Pais pais) {
        return new ResponseEntity<>(paisService.crearPais(pais), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a pais
    @PutMapping("/{id}")
    public ResponseEntity<Pais> actualizarPais(@PathVariable Long id, @RequestBody Pais pais) {
        return new ResponseEntity<>(paisService.actualizarPaisPorId(id, pais), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a pais
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPais(@PathVariable Long id) {
        paisService.borrarPaisPorId(id);
        return new ResponseEntity<>("Pais borrado: " + id, HttpStatus.OK);
    }

}