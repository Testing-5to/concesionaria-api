package com.autos.concesionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.service.PaisService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pais")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequiredArgsConstructor
@AllArgsConstructor
public class PaisController {

    @Autowired
    private PaisService paisService;

    // GET
    @GetMapping
    public ResponseEntity<?> getPaises() {
        return new ResponseEntity<>(paisService.buscarPaises(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaisPorId(@PathVariable Long id) {
        return new ResponseEntity<>(paisService.buscarPaisPorId(id), HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<Pais> guardarPais(@RequestBody Pais pais) {
        return new ResponseEntity<>(paisService.crearPais(pais), HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Pais> actualizarPais(@PathVariable Long id, @RequestBody Pais pais) {
        return new ResponseEntity<>(paisService.actualizarPaisPorId(id, pais), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPais(@PathVariable Long id) {
        return new ResponseEntity<>(paisService.borrarPaisPorId(id), HttpStatus.OK);
    }

}