package com.autos.concesionaria.service;

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

    // Repositorio de ventas
    @Autowired
    private final VentaRepository ventaRepository;

    // Servicio de vehiculos
    @Autowired
    private final VehiculoService vehiculoService;

    // Servicio de impuestos
    @Autowired
    private final ImpuestoService impuestoService;

    // Servicio de clientes
    @Autowired
    private final ClienteService clienteService;


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
        venta.setCliente(clienteService.buscarClientePorId(venta.getCliente().getId()));

        // Restamos la cantidad de vehiculos vendidos al stock si no queda en negativo
        if (venta.getVehiculo().getCantidad() - venta.getCantidadVehiculos() >= 0) {
            venta.getVehiculo().setCantidad(venta.getVehiculo().getCantidad() - venta.getCantidadVehiculos());
        } else {
            // Raise exception
            throw new RuntimeException("No hay suficientes vehiculos en stock");
        }

        // Si el cliente nunca compro un vehiculo, se cambia su atributo esCliente a true y lo actualizamos en la base de datos
        if (!venta.getCliente().getEsCliente()) {
            venta.getCliente().setEsCliente(true);
            clienteService.actualizarClientePorId(venta.getCliente().getId(), venta.getCliente());
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

    /**
     * Cuenta las ventas para un empleado
     *
     * @param Long id El ID del empleado
     * @return int La cantidad de ventas para el empleado
     */
    public int contarVentasPorEmpleado(Long id) {
        return ventaRepository.countAllByVendedor_Id(id);
    }

    /**
     * Cuenta las ventas para un cliente
     *
     * @param Long id El ID del cliente
     * @return int La cantidad de ventas para el cliente
     */
    public int contarVentasPorCliente(Long id) {
        return ventaRepository.countAllByCliente_Id(id);
    }

}
