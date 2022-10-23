package com.autos.concesionaria.controller;

import com.autos.concesionaria.repository.EmpleadoRepository;
import com.autos.concesionaria.service.ClienteService;
import com.autos.concesionaria.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.service.DireccionService;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/direccion")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class DireccionController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(DireccionController.class);

    // Service injected by constructor
    @Autowired
    private final DireccionService direccionService;
    @Autowired
    private final EmpleadoService empleadoService;
    @Autowired
    private final ClienteService clienteService;

    // GET
    // Get mapping to get all the direcciones
    @GetMapping
    public ResponseEntity<List<Direccion>> getDirecciones(@RequestParam(required = false) String localidad) {
        if (localidad == null) {
            return new ResponseEntity<List<Direccion>>(direccionService.buscarDirecciones(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Direccion>>(direccionService.buscarDireccionesPorLocalidad(localidad), HttpStatus.OK);
        }
    }

    // GET by ID
    // Get mapping to get a direccion by id
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionPorId(@PathVariable Long id) {
        return new ResponseEntity<Direccion>(direccionService.buscarDireccionPorId(id), HttpStatus.OK);
    }

    // POST
    // Post mapping to create a direccion
    @PostMapping
    public ResponseEntity<Direccion> guardarDireccion(@RequestBody Direccion direccion) {
        logger.info("Guardando la direccion: " + direccion);
        return new ResponseEntity<Direccion>(direccionService.crearDireccion(direccion), HttpStatus.CREATED);
    }

    // PUT
    // Put mapping to update a direccion
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        logger.info("Actualizando la direccion con id: " + id);
        return new ResponseEntity<Direccion>(direccionService.actualizarDireccionPorId(id, direccion), HttpStatus.OK);
    }

    // DELETE
    // Delete mapping to delete a direccion
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDireccion(@PathVariable Long id) {
        // Verifico si la direccion esta asociada a un empleado o cliente
        if (empleadoService.contarEmpleadosPorDireccion(id) > 0 || clienteService.contarClientesPorDireccion(id) > 0) {
            logger.info("No se puede eliminar la direccion con id: " + id + " porque esta asociada a un empleado o cliente");
            return new ResponseEntity<String>("No se puede eliminar la direccion con id: " + id + " porque esta asociada a un empleado o cliente", HttpStatus.BAD_REQUEST);
        } else {
            logger.info("Eliminando la direccion con id: " + id);
            direccionService.eliminarDireccionPorId(id);
            return new ResponseEntity<String>("Direccion eliminada", HttpStatus.NO_CONTENT);
        }
    }

}
