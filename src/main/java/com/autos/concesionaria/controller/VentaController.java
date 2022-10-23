package com.autos.concesionaria.controller;


import com.autos.concesionaria.entity.Venta;
import com.autos.concesionaria.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venta")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class VentaController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(VentaController.class);

    @Autowired
    // Inyección de dependencia por constructor
    private final VentaService ventaService;

    // GET
    // Obtene todas las ventas
    @GetMapping
    public ResponseEntity<List<Venta>> buscarVentas() {
        return ResponseEntity.ok(ventaService.buscarVentas());
    }

    // GET by ID
    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscarVentaPorId(id));
    }

    // POST
    // Guardar una venta nueva
    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        logger.info("Guardando venta: " + venta);
        return ResponseEntity.ok(ventaService.guardarVenta(venta));
    }

}
