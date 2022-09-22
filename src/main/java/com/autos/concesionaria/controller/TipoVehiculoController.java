package com.autos.concesionaria.controller;

import java.util.List;

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

import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.service.TipoVehiculoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tipoVehiculo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class TipoVehiculoController {

    // Service injected by constructor
    @Autowired
    private final TipoVehiculoService tipoVehiculoService;

    // GET
    // Get mapping to get all the tipoVehiculos
    @GetMapping
    public ResponseEntity<List<TipoVehiculo>> getRoles() {
        return new ResponseEntity<>(tipoVehiculoService.buscarTipoVehiculos(), HttpStatus.OK);
    }

    // GET by ID
    // Get mapping to get a tipoVehiculo by id
    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculo> getTipoVehiculoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tipoVehiculoService.buscarTipoVehiculoPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a tipoVehiculo
    @PostMapping
    public ResponseEntity<TipoVehiculo> guardarTipoVehiculo(@RequestBody TipoVehiculo tipoVehiculo) {
        return new ResponseEntity<>(tipoVehiculoService.crearTipoVehiculo(tipoVehiculo), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a tipoVehiculo
    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculo> actualizarTipoVehiculo(@PathVariable Long id, @RequestBody TipoVehiculo tipoVehiculo) {
        return new ResponseEntity<>(tipoVehiculoService.actualizarTipoVehiculoPorId(id, tipoVehiculo), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a tipoVehiculo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoVehiculo(@PathVariable Long id) {
        tipoVehiculoService.eliminarTipoVehiculoPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
