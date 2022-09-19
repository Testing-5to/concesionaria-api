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

import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.service.ProvinciaService;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/provincia")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProvinciaController {

    @Autowired
    // Service injected by constructor
    private final ProvinciaService provinciaService;

    // GET
    // Get mapping to get all the provincias
    @GetMapping
    public ResponseEntity<List<Provincia>> getProvincias() {
        return new ResponseEntity<>(provinciaService.buscarProvincias(), HttpStatus.OK);
    }

    // GET by ID
    // Get mapping to get a provincia by id
    @GetMapping("/{id}")
    public ResponseEntity<Provincia> getProvinciaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(provinciaService.buscarProvinciaPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a provincia
    @PostMapping
    public ResponseEntity<Provincia> guardarProvincia(@RequestBody Provincia provincia) {
        return new ResponseEntity<>(provinciaService.crearProvincia(provincia), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a provincia
    @PutMapping("/{id}")
    public ResponseEntity<Provincia> actualizarProvincia(@PathVariable Long id, @RequestBody Provincia provincia) {
        return new ResponseEntity<>(provinciaService.actualizarProvinciaPorId(id, provincia), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a provincia
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProvincia(@PathVariable Long id) {
        provinciaService.eliminarProvinciaPorId(id);
        return new ResponseEntity<>("Provincia eliminada", HttpStatus.OK);
    }

}