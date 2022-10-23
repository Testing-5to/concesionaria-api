package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marca")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class MarcaController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    // Service injected by constructor
    private final MarcaService marcaService;

    // GET
    // Get mapping to get all the marcas
    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas(@RequestParam(required = false) String pais) {
        if (pais == null) {
            logger.info("Obteniendo todas las marcas");
            return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);
        } else {
            logger.info("Obteniendo todas las marcas para el pais: " + pais);
            return new ResponseEntity<>(marcaService.getMarcasByPais(pais), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a marca by id
    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaPorId(@PathVariable Long id) {
        logger.info("Obteniendo la marca con id: " + id);
        return new ResponseEntity<>(marcaService.getMarca(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a marca
    @PostMapping
    public ResponseEntity<Marca> guardarMarca(@RequestBody Marca marca) {
        logger.info("Guardando la marca: " + marca.getNombre());
        return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        logger.info("Actualizando la marca con id: " + id);
        return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a marca
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarMarca(@PathVariable Long id) {
        marcaService.borrarMarca(id);
        logger.info("Borrando la marca con id: " + id);
        return new ResponseEntity<>("Marca borrada: " + id, HttpStatus.NO_CONTENT);
    }

}