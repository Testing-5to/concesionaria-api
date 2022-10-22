package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Venta;
import com.autos.concesionaria.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    @Autowired
    // Inyección de dependencia por constructor
    private final VentaRepository ventaRepository;

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
        return ventaRepository.save(venta);
    }

}
