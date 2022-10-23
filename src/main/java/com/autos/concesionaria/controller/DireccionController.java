package com.autos.concesionaria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.service.DireccionService;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/direccion")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class DireccionController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(DireccionController.class);

    // Service injected by constructor
    @Autowired
    private final DireccionService direccionService;

    // GET
    // Get mapping to get all the direcciones
    @GetMapping
    public ResponseEntity<List<Direccion>> getDirecciones(@RequestParam(required = false) String localidad) {
        if (localidad == null) {
            logger.info("Obteniendo todas las direcciones");
            return new ResponseEntity<List<Direccion>>(direccionService.buscarDirecciones(), HttpStatus.OK);
        } else {
            logger.info("Obteniendo todas las direcciones de la localidad: " + localidad);
            return new ResponseEntity<List<Direccion>>(direccionService.buscarDireccionesPorLocalidad(localidad), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a direccion by id
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionPorId(@PathVariable Long id) {
        logger.info("Obteniendo la direccion con id: " + id);
        return new ResponseEntity<Direccion>(direccionService.buscarDireccionPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a direccion
    @PostMapping
    public ResponseEntity<Direccion> guardarDireccion(@RequestBody Direccion direccion) {
        logger.info("Guardando la direccion: " + direccion);
        return new ResponseEntity<Direccion>(direccionService.crearDireccion(direccion), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a direccion
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        logger.info("Actualizando la direccion con id: " + id);
        return new ResponseEntity<Direccion>(direccionService.actualizarDireccionPorId(id, direccion), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a direccion
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDireccion(@PathVariable Long id) {
        logger.info("Eliminando la direccion con id: " + id);
        direccionService.eliminarDireccionPorId(id);
        return new ResponseEntity<String>("Direccion eliminada", HttpStatus.NO_CONTENT);
    }

}
