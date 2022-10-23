package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Vehiculo;
import com.autos.concesionaria.entity.Venta;
import com.autos.concesionaria.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    @Autowired
    // Inyección de dependencia por constructor
    private final VentaRepository ventaRepository;
    @Autowired
    private final VehiculoService vehiculoService;
    @Autowired
    private final ImpuestoService impuestoService;

    /**
     * Obtener todas las ventas
     *
     * @return List<Venta> Lista de ventas
     */
    public List<Venta> buscarVentas() {
        return ventaRepository.findAll();
    }

    /**
     * Obtener una venta por su ID
     *
     * @param id El ID de la venta
     * @return Venta La venta
     */
    public Venta buscarVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    /**
     * Guardar una venta nueva
     *
     * @param venta La venta
     * @return Venta La venta guardada
     */
    public Venta guardarVenta(Venta venta) {

        // Si la fecha es null, se le asigna la fecha actual
        if (venta.getFecha() == null) venta.setFecha(LocalDateTime.now());

        // Obtenemos el vehiculo y el impuesto de la venta desde la base de datos
        venta.setVehiculo(vehiculoService.getVehiculo(venta.getVehiculo().getId()));
        venta.setImpuesto(impuestoService.getImpuesto(venta.getImpuesto().getId()));

        // Restamos la cantidad de vehiculos vendidos al stock si no queda en negativo
        if (venta.getVehiculo().getCantidad() - venta.getCantidadVehiculos() >= 0) {
            venta.getVehiculo().setCantidad(venta.getVehiculo().getCantidad() - venta.getCantidadVehiculos());
        } else {
            // Raise exception
            throw new RuntimeException("No hay suficientes vehiculos en stock");
        }

        // Guardamos el porcentaje del impuesto en la venta
        venta.setImpuestoPorcentaje(venta.getImpuesto().getPorcentaje());

        // Guardamos el impuesto en la venta
        venta.setImpuestoPesos(venta.calcularImpuestos());

        return ventaRepository.save(venta);
    }

    /**
     * Busca las ventas para un vehiculo
     *
     * @param id El ID del vehiculo
     * @return List<Venta> Lista de ventas para el vehiculo
     */
    public List<Venta> getVentasByVehiculo(Long id) {
        return ventaRepository.findAllByVehiculo_Id(id);
    }

    public int contarVentasPorEmpleado(Long id) {
        return ventaRepository.countAllByEmpleado_Id(id);
    }
}
