package com.autos.concesionaria.controller;


import com.autos.concesionaria.dto.MarcaDTO;
import com.autos.concesionaria.repository.MarcaRepository;
import com.autos.concesionaria.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ConcesionariaEndPointResource {

    @Autowired
    MarcaService marcaService;


    @PostMapping("/marca")
    public ResponseEntity<MarcaDTO> guardarMarca(@RequestBody MarcaDTO marca){

        return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
    }

    @GetMapping("/marca")
    public ResponseEntity<List<MarcaDTO>> getMarcas(){
        return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);

    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<MarcaDTO> getMarca(@PathVariable Long id){
        return new ResponseEntity<>(marcaService.getMarca(id), HttpStatus.OK);

    }

    @PutMapping("/marca/{id}")
    public ResponseEntity<MarcaDTO> actualizarMarca(@PathVariable Long id, @RequestBody MarcaDTO marca){
        return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);

    }

    @DeleteMapping("/marca/{id}")
    public ResponseEntity<MarcaDTO> borrarMarca(@PathVariable Long id){
        return new ResponseEntity<>(marcaService.borrarMarca(id), HttpStatus.OK);

    }



}
