package com.autos.concesionaria.controller;

import com.autos.concesionaria.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/reportes")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ReportController {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    // Service
    @Autowired
    private final VentaService ventaService;

    // GET utilidades
    // Obtener utilidades por fecha y por vendedor/es
    @GetMapping("/utilidades")
    public ResponseEntity<?> getUtilidades(
            @RequestParam(required = false, name = "fechaInicio", defaultValue = "") String fechaInicio,
            @RequestParam(required = false, name = "fechaFin", defaultValue = "") String fechaFin,
            @RequestParam(required = false, name = "vendedores", defaultValue = "") String vendedores) {

        // Si la fecha incio está vacía, se le asigna el primer día del año
        if (fechaInicio.isEmpty()) fechaInicio = "01-01-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));

        // Si la fecha fin está vacía, se le asigna la fecha de hoy
        if (fechaFin.equals("")) fechaFin = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Parseo de fechas
        LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate fechaFinDate = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Si la fecha de inicio es mayor a la fecha de fin, se intercambian
        if (fechaInicioDate.isAfter(fechaFinDate)) {
            LocalDate aux = fechaInicioDate;
            fechaInicioDate = fechaFinDate;
            fechaFinDate = aux;
        }

        // Si no se especifican vendedores, se devuelve el reporte de utilidades de todas las ventas
        if (vendedores.isEmpty()) {
            logger.info("Obteniendo utilidades de todas las ventas entre " + fechaInicioDate + " y " + fechaFinDate);
            return ResponseEntity.ok(ventaService.getUtilidades(fechaInicioDate, fechaFinDate, vendedores));
        } else {
            logger.info("Obteniendo utilidades de las ventas de los vendedores " + vendedores + " entre " + fechaInicioDate + " y " + fechaFinDate);
            return ResponseEntity.ok(ventaService.getUtilidades(fechaInicioDate, fechaFinDate, vendedores));
        }
    }

    // GET autosVendidos
    // Obtener autos vendidos por fecha y por vendedor/es
    @GetMapping("/autosVendidos")
    public ResponseEntity<?> getAutosVendidos(
            @RequestParam(required = false, name = "fechaInicio", defaultValue = "") String fechaInicio,
            @RequestParam(required = false, name = "fechaFin", defaultValue = "") String fechaFin,
            @RequestParam(required = false, name = "vendedores", defaultValue = "") String vendedores) {

        // Si la fecha incio está vacía, se le asigna el primer día del año
        if (fechaInicio.isEmpty()) fechaInicio = "01-01-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));

        // Si la fecha fin está vacía, se le asigna la fecha de hoy
        if (fechaFin.equals("")) fechaFin = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Parseo de fechas
        LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechaFinDate = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Si la fecha de inicio es mayor a la fecha de fin, se intercambian
        if (fechaInicioDate.isAfter(fechaFinDate)) {
            LocalDate aux = fechaInicioDate;
            fechaInicioDate = fechaFinDate;
            fechaFinDate = aux;
        }

        // Si no se especifican vendedores, se devuelve el reporte de autos vendidos de todas las ventas
        if (vendedores.isEmpty()) {
            logger.info("Obteniendo autos vendidos de todas las ventas entre " + fechaInicioDate + " y " + fechaFinDate);
            return ResponseEntity.ok().body("Reporte de autos vendidos de todas las ventas");
        } else {
            logger.info("Obteniendo autos vendidos de las ventas de los vendedores " + vendedores + " entre " + fechaInicioDate + " y " + fechaFinDate);
            return ResponseEntity.ok().body("Reporte de autos vendidos de las ventas de los vendedores: " + vendedores);
        }
    }

    // GET ventasPorMes
    // Obtener ventas por mes
    @GetMapping("/ventasPorMes")
    public ResponseEntity<?> getVentasPorMes(
            @RequestParam(required = false, name = "periodos", defaultValue = "2022") String periodos
    ) {
        logger.info("Obteniendo ventas por mes");
        return ResponseEntity.ok().body("Reporte de ventas por mes");
    }

}
