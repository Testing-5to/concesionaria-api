package com.autos.concesionaria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.service.ProvinciaService;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/provincia")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProvinciaController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

    @Autowired
    // Service injected by constructor
    private final ProvinciaService provinciaService;

    // GET
    // Get mapping to get all the provincias
    @GetMapping
    public ResponseEntity<List<Provincia>> getProvincias(@RequestParam(required = false) String pais) {
        if (pais == null) {
            logger.info("Obteniendo todas las provincias");
            return new ResponseEntity<>(provinciaService.buscarProvincias(), HttpStatus.OK);
        } else {
            logger.info("Obteniendo todas las provincias para el pais: " + pais);
            return new ResponseEntity<>(provinciaService.buscarProvinciasByPais(pais), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a provincia by id
    @GetMapping("/{id}")
    public ResponseEntity<Provincia> getProvinciaPorId(@PathVariable Long id) {
        logger.info("Obteniendo la provincia con id: " + id);
        return new ResponseEntity<>(provinciaService.buscarProvinciaPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a provincia
    @PostMapping
    public ResponseEntity<Provincia> guardarProvincia(@RequestBody Provincia provincia) {
        logger.info("Guardando la provincia: " + provincia.getNombre());
        return new ResponseEntity<>(provinciaService.crearProvincia(provincia), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a provincia
    @PutMapping("/{id}")
    public ResponseEntity<Provincia> actualizarProvincia(@PathVariable Long id, @RequestBody Provincia provincia) {
        logger.info("Actualizando la provincia con id: " + id);
        return new ResponseEntity<>(provinciaService.actualizarProvinciaPorId(id, provincia), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a provincia
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProvincia(@PathVariable Long id) {
        provinciaService.eliminarProvinciaPorId(id);
        logger.info("Eliminando la provincia con id: " + id);
        return new ResponseEntity<>("Provincia eliminada", HttpStatus.OK);
    }

}