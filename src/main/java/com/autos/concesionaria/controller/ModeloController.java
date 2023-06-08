package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.service.ModeloService;
import com.autos.concesionaria.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Inyección de dependencias
    private final ModeloService modeloService;
    private final VehiculoService vehiculoService;

    // GET
    // Obtener todos los modelos
    @GetMapping
    public ResponseEntity<List<Modelo>> getModelos(@RequestParam(required = false) String marca, @RequestParam(required = false) String tipoVehiculo) {
        if (marca != null && tipoVehiculo != null) {
            return ResponseEntity.ok(modeloService.getModelosByMarcaAndTipoVehiculo(marca, tipoVehiculo));
        } else if (marca != null) {
            return ResponseEntity.ok(modeloService.getModelosByMarca(marca));
        } else if (tipoVehiculo != null) {
            return ResponseEntity.ok(modeloService.getModelosByTipoVehiculo(tipoVehiculo));
        } else {
            return ResponseEntity.ok(modeloService.getModelos());
        }
    }

    // GET by ID
    // Obtener un modelo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getModeloPorId(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.getModelo(id));
    }

    // POST
    // Crear un modelo
    @PostMapping
    public ResponseEntity<Modelo> guardarModelo(@RequestBody Modelo modelo) {
        logger.info("Guardando modelo con nombre: " + modelo.getNombre());
        return new ResponseEntity<>(modeloService.crearModelo(modelo), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar un modelo
    @PutMapping("/{id}")
    public ResponseEntity<Modelo> actualizarModelo(@PathVariable Long id, @RequestBody Modelo modelo) {
        logger.info("Actualizando modelo con id: " + id);
        return ResponseEntity.ok(modeloService.actualizarModelo(id, modelo));
    }

    // DELETE
    // Eliminar un modelo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarModelo(@PathVariable Long id) {
        // Buscamos si el modelo no tiene vehiculos asociados
        if (vehiculoService.countVehiculosByModelo(id) == 0) {
            modeloService.borrarModelo(id);
            logger.info("Borrando modelo con id: " + id);
            return ResponseEntity.noContent().build();
        } else {
            logger.info("No se puede borrar el modelo con id: " + id + " porque tiene vehiculos asociados");
            return ResponseEntity.badRequest().body("No se puede borrar el modelo porque tiene vehiculos asociados");
        }
    }

}
