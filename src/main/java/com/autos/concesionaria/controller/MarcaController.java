package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.service.MarcaService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marca")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // GET
    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas() {
        return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.getMarca(id), HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<Marca> guardarMarca(@RequestBody Marca marca) {
        return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarMarca(@PathVariable Long id) {
        marcaService.borrarMarca(id);
        return new ResponseEntity<>("Marca borrada: " + id, HttpStatus.NO_CONTENT);
    }

}