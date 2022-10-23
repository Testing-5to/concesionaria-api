package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.service.MarcaService;
import com.autos.concesionaria.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marca")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class MarcaController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    // Inyección de dependencias
    @Autowired
    private final MarcaService marcaService;
    @Autowired
    private final ModeloService modeloService;

    // GET
    // Obtener todas las marcas
    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas(@RequestParam(required = false) String pais) {
        if (pais == null) {
            return new ResponseEntity<>(marcaService.getMarcas(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(marcaService.getMarcasByPais(pais), HttpStatus.OK);
        }
    }

    // GET by ID
    // Obtener una marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.getMarca(id), HttpStatus.OK);
    }

    // POST
    // Crear una marca
    @PostMapping
    public ResponseEntity<Marca> guardarMarca(@RequestBody Marca marca) {
        logger.info("Guardando la marca: " + marca.getNombre());
        return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar una marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        logger.info("Actualizando la marca con id: " + id);
        return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);
    }

    // DELETE
    // Eliminar una marca
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarMarca(@PathVariable Long id) {
        // Verificamos que no existan modelos asociados a la marca
        if (modeloService.contarModelosPorMarca(id) == 0) {
            marcaService.borrarMarca(id);
            logger.info("Borrando la marca con id: " + id);
            return new ResponseEntity<>("Marca borrada: " + id, HttpStatus.NO_CONTENT);
        } else {
            logger.info("No se puede borrar la marca con id: " + id + " porque tiene modelos asociados");
            return new ResponseEntity<>("No se puede borrar la marca con id: " + id + " porque tiene modelos asociados", HttpStatus.BAD_REQUEST);
        }
    }

}