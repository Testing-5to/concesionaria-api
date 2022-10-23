package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.service.MarcaService;
import com.autos.concesionaria.service.PaisService;
import com.autos.concesionaria.service.ProvinciaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pais")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PaisController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(PaisController.class);

    // Inyección de dependencias
    @Autowired
    private final PaisService paisService;
    @Autowired
    private final ProvinciaService provinciaService;
    @Autowired
    private final MarcaService marcaService;

    // GET
    // Obtener todos los paises
    @GetMapping
    public ResponseEntity<List<Pais>> getPaises() {
        return new ResponseEntity<>(paisService.buscarPaises(), HttpStatus.OK);
    }

    // GET by ID
    // Obtener un pais por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pais> getPaisPorId(@PathVariable Long id) {
        return new ResponseEntity<>(paisService.buscarPaisPorId(id), HttpStatus.OK);
    }

    // POST
    // Crear un pais
    @PostMapping
    public ResponseEntity<Pais> guardarPais(@RequestBody Pais pais) {
        logger.info("Guardando pais: " + pais.getNombre());
        return new ResponseEntity<>(paisService.crearPais(pais), HttpStatus.CREATED);
    }

    // PUT
    // Actualizar un pais
    @PutMapping("/{id}")
    public ResponseEntity<Pais> actualizarPais(@PathVariable Long id, @RequestBody Pais pais) {
        logger.info("Actualizando pais con id: " + id);
        return new ResponseEntity<>(paisService.actualizarPaisPorId(id, pais), HttpStatus.OK);
    }

    // DELETE
    // Eliminar un pais
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPais(@PathVariable Long id) {
        int cantidadProvincias = provinciaService.contarProvinciasPorPais(id);
        int cantidadMarcas = marcaService.contarMarcasPorPais(id);
        // Verifico que no haya provincias o marcas asociadas al pais
        if (cantidadProvincias == 0 && cantidadMarcas == 0) {
            // Elimino el pais
            paisService.borrarPaisPorId(id);
            logger.info("Borrando pais con id: " + id);
            return new ResponseEntity<>("Pais borrado: " + id, HttpStatus.OK);
        } else {
            if (cantidadProvincias > 0 && cantidadMarcas > 0) {
                logger.info("No se puede borrar el pais con id: " + id + " porque tiene provincias y marcas asociadas");
                return new ResponseEntity<>("No se puede borrar el pais con id: " + id + " porque tiene provincias y marcas asociadas", HttpStatus.BAD_REQUEST);
            } else {
                if (cantidadProvincias > 0) {
                    logger.info("No se puede borrar el pais con id: " + id + " porque tiene provincias asociadas");
                    return new ResponseEntity<>("No se puede borrar el pais con id: " + id + " porque tiene provincias asociadas", HttpStatus.BAD_REQUEST);
                } else {
                    logger.info("No se puede borrar el pais con id: " + id + " porque tiene marcas asociadas");
                    return new ResponseEntity<>("No se puede borrar el pais con id: " + id + " porque tiene marcas asociadas", HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

}