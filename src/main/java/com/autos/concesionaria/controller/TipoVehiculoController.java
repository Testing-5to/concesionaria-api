package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.service.ModeloService;
import com.autos.concesionaria.service.TipoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipoVehiculo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class TipoVehiculoController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(TipoVehiculoController.class);

    // Inyección de dependencias
    @Autowired
    private final TipoVehiculoService tipoVehiculoService;
    @Autowired
    private final ModeloService modeloService;

    // GET
    // Obtener todos los tipos de vehiculos
    @GetMapping
    public ResponseEntity<List<TipoVehiculo>> getRoles() {
        return new ResponseEntity<>(tipoVehiculoService.buscarTipoVehiculos(), HttpStatus.OK);
    }

    // GET by ID
    // Obtener un tipo de vehiculo por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculo> getTipoVehiculoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(tipoVehiculoService.buscarTipoVehiculoPorId(id), HttpStatus.OK);
    }

    // POST
    // Crear un tipo de vehiculo
    @PostMapping
    public ResponseEntity<TipoVehiculo> guardarTipoVehiculo(@RequestBody TipoVehiculo tipoVehiculo) {
        logger.info("Guardando tipo de vehiculo: " + tipoVehiculo.getNombre());
        return new ResponseEntity<>(tipoVehiculoService.crearTipoVehiculo(tipoVehiculo), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar un tipo de vehiculo
    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculo> actualizarTipoVehiculo(@PathVariable Long id, @RequestBody TipoVehiculo tipoVehiculo) {
logger.info("Actualizando tipo de vehiculo: " + tipoVehiculo.getNombre());
        return new ResponseEntity<>(tipoVehiculoService.actualizarTipoVehiculoPorId(id, tipoVehiculo), HttpStatus.OK);
    }

    // DELETE
    // Eliminar un tipo de vehiculo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoVehiculo(@PathVariable Long id) {
        // Buscamos si existen modelos asociados al tipo de vehiculo
        if (modeloService.contarModelosPorTipoVehiculo(id) > 0) {
            tipoVehiculoService.eliminarTipoVehiculoPorId(id);
            logger.info("Eliminando tipo de vehiculo por id: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.info("No se puede eliminar el tipo de vehiculo por id: " + id + " porque tiene modelos asociados");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
