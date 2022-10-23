package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.service.ModeloService;
import com.autos.concesionaria.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modelo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ModeloController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ModeloController.class);

    // Service injected by constructor
    @Autowired
    private final ModeloService modeloService;
    @Autowired
    private final VehiculoService vehiculoService;

    // GET
    // Get mapping to get all the modelos
    @GetMapping
    public ResponseEntity<List<Modelo>> getModelos(@RequestParam(required = false) String marca, @RequestParam(required = false) String tipoVehiculo) {
        if (marca != null && tipoVehiculo != null) {
            return new ResponseEntity<>(modeloService.getModelosByMarcaAndTipoVehiculo(marca, tipoVehiculo), HttpStatus.OK);
        } else if (marca != null) {
            return new ResponseEntity<>(modeloService.getModelosByMarca(marca), HttpStatus.OK);
        } else if (tipoVehiculo != null) {
            return new ResponseEntity<>(modeloService.getModelosByTipoVehiculo(tipoVehiculo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(modeloService.getModelos(), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a modelo by id
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getModeloPorId(@PathVariable Long id) {
        return new ResponseEntity<>(modeloService.getModelo(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a modelo
    @PostMapping
    public ResponseEntity<Modelo> guardarModelo(@RequestBody Modelo modelo) {
        logger.info("Guardando modelo con nombre: " + modelo.getNombre());
        return new ResponseEntity<>(modeloService.crearModelo(modelo), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a modelo
    @PutMapping("/{id}")
    public ResponseEntity<Modelo> actualizarModelo(@PathVariable Long id, @RequestBody Modelo modelo) {
        logger.info("Actualizando modelo con id: " + id);
        return new ResponseEntity<>(modeloService.actualizarModelo(id, modelo), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a modelo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarModelo(@PathVariable Long id) {
        // Buscamos si el modelo no tiene vehiculos asociados
        if (vehiculoService.countVehiculosByModelo(id) == 0) {
            modeloService.borrarModelo(id);
            logger.info("Borrando modelo con id: " + id);
            return new ResponseEntity<String>("Modelo borrado: " + id, HttpStatus.NO_CONTENT);
        } else {
            logger.info("No se puede borrar el modelo con id: " + id + " porque tiene vehiculos asociados");
            return new ResponseEntity<>("No se puede borrar el modelo porque tiene vehiculos asociados", HttpStatus.BAD_REQUEST);
        }
    }

}
