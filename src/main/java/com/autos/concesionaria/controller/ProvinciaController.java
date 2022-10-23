package com.autos.concesionaria.controller;

import com.autos.concesionaria.service.LocalidadService;
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

    // Service injected by constructor
    @Autowired
    private final ProvinciaService provinciaService;
    @Autowired
    private final LocalidadService localidadService;

    // GET
    // Get mapping to get all the provincias
    @GetMapping
    public ResponseEntity<List<Provincia>> getProvincias(@RequestParam(required = false) String pais) {
        if (pais == null) {
            return new ResponseEntity<>(provinciaService.buscarProvincias(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(provinciaService.buscarProvinciasByPais(pais), HttpStatus.OK);
        }
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
        // Verifico que no haya localidades asociadas a la provincia
        if (localidadService.contarLocalidadesPorProvincia(id) == 0) {
            provinciaService.eliminarProvinciaPorId(id);
            logger.info("Eliminando la provincia con id: " + id);
            return new ResponseEntity<>("Provincia eliminada", HttpStatus.OK);
        } else {
            logger.info("No se puede eliminar la provincia con id: " + id + " porque tiene localidades asociadas");
            return new ResponseEntity<>("No se puede eliminar la provincia porque tiene localidades asociadas", HttpStatus.BAD_REQUEST);
        }
    }

}