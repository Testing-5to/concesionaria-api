package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Vehiculo;
import com.autos.concesionaria.service.VehiculoService;

import com.autos.concesionaria.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class VehiculoController {

    // Logger
    private static final Logger logger = LogManager.getLogger(VehiculoController.class);

    // Service injected by constructor
    @Autowired
    private final VehiculoService vehiculoService;
    @Autowired
    private final VentaService ventaService;

    // GET
    // Get mapping to get all the vehiculo
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getVehiculos(@RequestParam(required = false) String modelo) {
        if (modelo == null) {
            logger.info("Getting all the vehiculos");
            return new ResponseEntity<>(vehiculoService.getVehiculos(), HttpStatus.OK);
        } else {
            logger.info("Getting all the vehiculos by modelo");
            return new ResponseEntity<>(vehiculoService.getVehiculosByModelo(modelo), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a vehiculo by id
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoPorId(@PathVariable Long id) {
        logger.info("Getting the vehiculo with id: " + id);
        return new ResponseEntity<>(vehiculoService.getVehiculo(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a vehiculo
    @PostMapping
    public ResponseEntity<Vehiculo> guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        logger.info("Creating a new vehiculo");
        return new ResponseEntity<>(vehiculoService.crearVehiculo(vehiculo), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a vehiculo
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        logger.info("Updating the vehiculo with id: " + id);
        return new ResponseEntity<>(vehiculoService.actualizarVehiculo(id, vehiculo), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a vehiculo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarVehiculo(@PathVariable Long id) {
        // Buscamos si no existen ventas asociadas al vehiculo
        if (ventaService.getVentasByVehiculo(id).isEmpty()) {
            // Si no existen ventas asociadas al vehiculo, lo borramos
            vehiculoService.borrarVehiculo(id);
            logger.info("Deleting the vehiculo with id: " + id);
            return new ResponseEntity<>("Vehiculo borrado", HttpStatus.OK);
        } else {
            // Si existen ventas asociadas al vehiculo, no lo borramos
            logger.info("The vehiculo with id: " + id + " can't be deleted because it has sales associated");
            return new ResponseEntity<>("No se puede borrar el vehiculo porque tiene ventas asociadas", HttpStatus.BAD_REQUEST);
        }
    }

}