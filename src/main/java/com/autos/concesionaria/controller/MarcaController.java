package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.service.MarcaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marca")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
public class MarcaController {

    private MarcaService marcaService;

    @PostMapping()
    public ResponseEntity<Marca> guardarMarca(@RequestBody Marca marca) {
        return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Marca>> getMarcas() {
        return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarca(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.getMarca(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Marca> borrarMarca(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.borrarMarca(id), HttpStatus.OK);
    }

}