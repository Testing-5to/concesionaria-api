package com.autos.concesionaria.controller;

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

    // Service injected by constructor
    @Autowired
    private final DireccionService direccionService;

    // GET
    // Get mapping to get all the direcciones
    @GetMapping
    public ResponseEntity<List<Direccion>> getDirecciones(@RequestParam(required = false) String localidad) {
        if (localidad == null) {
            return new ResponseEntity<List<Direccion>>(direccionService.buscarDirecciones(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Direccion>>(direccionService.buscarDireccionesPorLocalidad(localidad), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a direccion by id
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionPorId(@PathVariable Long id) {
        return new ResponseEntity<Direccion>(direccionService.buscarDireccionPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a direccion
    @PostMapping
    public ResponseEntity<Direccion> guardarDireccion(@RequestBody Direccion direccion) {
        return new ResponseEntity<Direccion>(direccionService.crearDireccion(direccion), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a direccion
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        return new ResponseEntity<Direccion>(direccionService.actualizarDireccionPorId(id, direccion), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a direccion
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDireccion(@PathVariable Long id) {
        direccionService.eliminarDireccionPorId(id);
        return new ResponseEntity<String>("Direccion eliminada", HttpStatus.NO_CONTENT);
    }

}
