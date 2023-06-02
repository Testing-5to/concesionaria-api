package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.service.LocalidadService;
import com.autos.concesionaria.service.ProvinciaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provincia")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProvinciaController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

    // Inyección de dependencias
    private final ProvinciaService provinciaService;
    private final LocalidadService localidadService;

    // GET
    // Obtener todas las provincias
    @GetMapping
    public ResponseEntity<List<Provincia>> getProvincias(@RequestParam(required = false) String pais) {
        if (pais == null) {
            return ResponseEntity.ok(provinciaService.buscarProvincias());
        } else {
            return ResponseEntity.ok(provinciaService.buscarProvinciasByPais(pais));
        }
    }

    // GET by ID
    // Obtener una provincia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Provincia> getProvinciaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(provinciaService.buscarProvinciaPorId(id));
    }

    // POST
    // Crear una provincia
    @PostMapping
    public ResponseEntity<Provincia> guardarProvincia(@RequestBody Provincia provincia) {
        logger.info("Guardando la provincia: " + provincia.getNombre());
        return new ResponseEntity<>(provinciaService.crearProvincia(provincia), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar una provincia
    @PutMapping("/{id}")
    public ResponseEntity<Provincia> actualizarProvincia(@PathVariable Long id, @RequestBody Provincia provincia) {
        logger.info("Actualizando la provincia con id: " + id);
        return ResponseEntity.ok(provinciaService.actualizarProvinciaPorId(id, provincia));
    }

    // DELETE
    // Eliminar una provincia
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProvincia(@PathVariable Long id) {
        // Verifico que no haya localidades asociadas a la provincia
        if (localidadService.contarLocalidadesPorProvincia(id) == 0) {
            provinciaService.eliminarProvinciaPorId(id);
            logger.info("Eliminando la provincia con id: " + id);
            return ResponseEntity.ok("Provincia eliminada");
        } else {
            logger.info("No se puede eliminar la provincia con id: " + id + " porque tiene localidades asociadas");
            return ResponseEntity.badRequest().body("No se puede eliminar la provincia porque tiene localidades asociadas");
        }
    }

}