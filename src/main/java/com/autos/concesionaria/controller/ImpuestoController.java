package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Impuesto;
import com.autos.concesionaria.service.ImpuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/impuesto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ImpuestoController {

    // Inyección de dependencia por constructor
    private final ImpuestoService impuestoService;

    // GET
    // Obtene todos los impuestos
    @GetMapping
    public List<Impuesto> buscarImpuestos() {
        return impuestoService.buscarImpuestos();
    }

    // GET by pais y precio de venta
    // Obtener un impuesto por su pais y precio de venta
    @GetMapping("/consultar")
    public ResponseEntity<Impuesto> buscarImpuestoPorPaisYPrecioDeVenta(@RequestParam String pais, @RequestParam Double precioDeVenta) {
        Impuesto impuesto = impuestoService.buscarImpuestoPorPaisYPrecioVenta(pais, precioDeVenta);
        if (impuesto != null) {
            return ResponseEntity.ok(impuesto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
