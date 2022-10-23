package com.autos.concesionaria.controller;

import com.autos.concesionaria.service.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(LocalidadController.class);

    // Service injected by constructor
    @Autowired
    private final LocalidadService localidadService;
    @Autowired
    private final DireccionService direccionService;

    // GET
    // Get mapping to get all the localidades
    @GetMapping
    public ResponseEntity<List<Localidad>> getLocalidades(@RequestParam(required = false) String provincia) {
        if (provincia == null) {
            logger.info("Obteniendo todas las localidades");
            return new ResponseEntity<List<Localidad>>(localidadService.buscarLocalidades(), HttpStatus.OK);
        } else {
            logger.info("Obteniendo todas las localidades de la provincia " + provincia);
            return new ResponseEntity<List<Localidad>>(localidadService.buscarLocalidadesPorProvincia(provincia), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a localidad by id
    @GetMapping("/{id}")
    public ResponseEntity<Localidad> getLocalidadPorId(@PathVariable Long id) {
        logger.info("Obteniendo la localidad con id " + id);
        return new ResponseEntity<>(localidadService.buscarLocalidadPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a localidad
    @PostMapping
    public ResponseEntity<Localidad> guardarLocalidad(@RequestBody Localidad localidad) {
        logger.info("Guardando la localidad " + localidad.getNombre());
        return new ResponseEntity<>(localidadService.crearLocalidad(localidad), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a localidad
    @PutMapping("/{id}")
    public ResponseEntity<Localidad> actualizarLocalidad(@PathVariable Long id, @RequestBody Localidad localidad) {
        logger.info("Actualizando la localidad con id " + id);
        return new ResponseEntity<>(localidadService.actualizarLocalidadPorId(id, localidad), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a localidad
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLocalidad(@PathVariable Long id) {
        // Verifico que no haya direcciones asociadas a la localidad
        if (direccionService.contarDireccionesPorLocalidad(id) == 0) {
            localidadService.eliminarLocalidadPorId(id);
            logger.info("Eliminando la localidad con id " + id);
            return new ResponseEntity<String>("Localidad eliminada: " + id, HttpStatus.NO_CONTENT);
        } else {
            logger.info("No se puede eliminar la localidad con id " + id + " porque tiene direcciones asociadas");
            return new ResponseEntity<>("No se puede eliminar la localidad con id " + id + " porque tiene direcciones asociadas", HttpStatus.BAD_REQUEST);
        }
    }

}
