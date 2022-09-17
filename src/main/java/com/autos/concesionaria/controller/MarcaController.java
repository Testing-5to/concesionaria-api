package com.autos.concesionaria.controller;


import com.autos.concesionaria.dto.MarcaDTO;
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
    public ResponseEntity<MarcaDTO> guardarMarca(@RequestBody MarcaDTO marca) {
        return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<MarcaDTO>> getMarcas() {
        return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> getMarca(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.getMarca(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> actualizarMarca(@PathVariable Long id, @RequestBody MarcaDTO marca) {
        return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MarcaDTO> borrarMarca(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.borrarMarca(id), HttpStatus.OK);
    }

}