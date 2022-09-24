package com.autos.concesionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autos.concesionaria.entity.Localidad;
import com.autos.concesionaria.service.LocalidadService;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/localidad")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class LocalidadController {

    // Service injected by constructor
    @Autowired
    private final LocalidadService localidadService;

    // GET
    // Get mapping to get all the localidades
    @GetMapping
    public ResponseEntity<List<Localidad>> getLocalidades(@RequestParam(required = false) String provincia) {
        if (provincia == null) {
            return new ResponseEntity<List<Localidad>>(localidadService.buscarLocalidades(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Localidad>>(localidadService.buscarLocalidadesPorProvincia(provincia), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a localidad by id
    @GetMapping("/{id}")
    public ResponseEntity<Localidad> getLocalidadPorId(@PathVariable Long id) {
        return new ResponseEntity<>(localidadService.buscarLocalidadPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a localidad
    @PostMapping
    public ResponseEntity<Localidad> guardarLocalidad(@RequestBody Localidad localidad) {
        return new ResponseEntity<>(localidadService.crearLocalidad(localidad), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a localidad
    @PutMapping("/{id}")
    public ResponseEntity<Localidad> actualizarLocalidad(@PathVariable Long id, @RequestBody Localidad localidad) {
        return new ResponseEntity<>(localidadService.actualizarLocalidadPorId(id, localidad), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a localidad
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLocalidad(@PathVariable Long id) {
        localidadService.eliminarLocalidadPorId(id);
        return new ResponseEntity<String>("Localidad eliminada: " + id, HttpStatus.NO_CONTENT);
    }

}
