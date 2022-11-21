package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VentaTest {

    Vehiculo vehiculoTest = Vehiculo.builder()
            .id(1L)
            .anio(2020)
            .cantidad(1)
            .importado(false)
            .imagen("imagen")
            .precioVenta(100000.0)
            .precioCompra(90000.0)
            .build();

    Cliente clienteTest = Cliente.builder()
            .id(1L)
            .nombre("Cliente Test")
            .apellido("Apellido Test")
            .dni(12345678)
            .build();

    LocalDateTime fechaTest = LocalDateTime.now();

    Impuesto impuestoTest = Impuesto.builder()
            .id(1L)
            .region("Region Test")
            .porcentaje(20.0)
            .rangoMaximo(100000.0)
            .rangoMinimo(0.0)
            .build();

    Empleado vendedorTest = Empleado.builder()
            .id(1L)
            .nombre("Juan")
            .apellido("Perez")
            .dni(12345678)
            .build();

    Venta ventaTest = Venta.builder()
            .id(1L)
            .cantidadVehiculos(1)
            .precioUnitario(100000.0)
            .impuestoPesos(10000.0)
            .impuestoPorcentaje(10.0)
            .vehiculo(vehiculoTest)
            .cliente(clienteTest)
            .fecha(fechaTest)
            .impuesto(impuestoTest)
            .vendedor(vendedorTest)
            .build();

    @Test
    void calcularSubtotal() {
        // given subTotal = precioUnitario * cantidadVehiculos
        Double subtotal = 100000.0;
        // when
        Double subtotalTest = ventaTest.calcularSubtotal();
        // then
        assertEquals(subtotal, subtotalTest);
    }

    @Test
    void calcularImpuestos() {
        // given impuestoPesos = impuestoPorcentaje * precioUnitario
        Double impuestoPesos = 10000.0;
        // when
        Double impuestoPesosTest = ventaTest.calcularImpuestos();
        // then
        assertEquals(impuestoPesos, impuestoPesosTest);
    }

    @Test
    void calcularTotal() {
        // given total = subtotal + impuestoPesos
        Double total = 110000.0;
        // when
        Double totalTest = ventaTest.calcularTotal();
        // then
        assertEquals(total, totalTest);
    }

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = ventaTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getFecha() {
        // given
        LocalDateTime fecha = fechaTest;
        // when
        LocalDateTime fechaTest = ventaTest.getFecha();
        // then
        assertEquals(fecha.getYear(), fechaTest.getYear());
        assertEquals(fecha.getMonth(), fechaTest.getMonth());
        assertEquals(fecha.getDayOfMonth(), fechaTest.getDayOfMonth());
        assertEquals(fecha.getHour(), fechaTest.getHour());
        assertEquals(fecha.getMinute(), fechaTest.getMinute());
        assertEquals(fecha.getSecond(), fechaTest.getSecond());
    }

    @Test
    void getCantidadVehiculos() {
        // given
        Integer cantidadVehiculos = 1;
        // when
        Integer cantidadVehiculosTest = ventaTest.getCantidadVehiculos();
        // then
        assertEquals(cantidadVehiculos, cantidadVehiculosTest);
    }

    @Test
    void getPrecioUnitario() {
        // given
        Double precioUnitario = 100000.0;
        // when
        Double precioUnitarioTest = ventaTest.getPrecioUnitario();
        // then
        assertEquals(precioUnitario, precioUnitarioTest);
    }

    @Test
    void getImpuesto() {
        // given
        Impuesto impuesto = impuestoTest;
        // when
        Impuesto impuestoTest = ventaTest.getImpuesto();
        // then
        assertEquals(impuesto.getId(), impuestoTest.getId());
        assertEquals(impuesto.getRegion(), impuestoTest.getRegion());
        assertEquals(impuesto.getPorcentaje(), impuestoTest.getPorcentaje());
        assertEquals(impuesto.getRangoMaximo(), impuestoTest.getRangoMaximo());
        assertEquals(impuesto.getRangoMinimo(), impuestoTest.getRangoMinimo());
    }

    @Test
    void getImpuestoPesos() {
        // given
        Double impuestoPesos = 10000.0;
        // when
        Double impuestoPesosTest = ventaTest.getImpuestoPesos();
        // then
        assertEquals(impuestoPesos, impuestoPesosTest);
    }

    @Test
    void getImpuestoPorcentaje() {
        // given
        Double impuestoPorcentaje = 10.0;
        // when
        Double impuestoPorcentajeTest = ventaTest.getImpuestoPorcentaje();
        // then
        assertEquals(impuestoPorcentaje, impuestoPorcentajeTest);
    }

    @Test
    void getVehiculo() {
        // given
        Vehiculo vehiculo = vehiculoTest;
        // when
        Vehiculo vehiculoTest = ventaTest.getVehiculo();
        // then
        assertEquals(vehiculo.getId(), vehiculoTest.getId());
        assertEquals(vehiculo.getModelo(), vehiculoTest.getModelo());
        assertEquals(vehiculo.getAnio(), vehiculoTest.getAnio());
        assertEquals(vehiculo.getCantidad(), vehiculoTest.getCantidad());
        assertEquals(vehiculo.getImportado(), vehiculoTest.getImportado());
        assertEquals(vehiculo.getImagen(), vehiculoTest.getImagen());
        assertEquals(vehiculo.getPrecioVenta(), vehiculoTest.getPrecioVenta());
        assertEquals(vehiculo.getPrecioCompra(), vehiculoTest.getPrecioCompra());
    }

    @Test
    void getVendedor() {
        // given
        Empleado vendedor = vendedorTest;
        // when
        Empleado vendedorTest = ventaTest.getVendedor();
        // then
        assertEquals(vendedor.getId(), vendedorTest.getId());
        assertEquals(vendedor.getNombre(), vendedorTest.getNombre());
        assertEquals(vendedor.getApellido(), vendedorTest.getApellido());
        assertEquals(vendedor.getDni(), vendedorTest.getDni());
    }

    @Test
    void getCliente() {
        // given
        Cliente cliente = clienteTest;
        // when
        Cliente clienteTest = ventaTest.getCliente();
        // then
        assertEquals(cliente.getId(), clienteTest.getId());
        assertEquals(cliente.getNombre(), clienteTest.getNombre());
        assertEquals(cliente.getApellido(), clienteTest.getApellido());
        assertEquals(cliente.getDni(), clienteTest.getDni());
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        ventaTest.setId(id);
        // then
        assertEquals(id, ventaTest.getId());
    }

    @Test
    void setFecha() {
        // given
        LocalDateTime fecha = LocalDateTime.of(2020, 12, 12, 12, 12, 12);
        // when
        ventaTest.setFecha(fecha);
        // then
        assertEquals(fecha.getYear(), ventaTest.getFecha().getYear());
        assertEquals(fecha.getMonth(), ventaTest.getFecha().getMonth());
        assertEquals(fecha.getDayOfMonth(), ventaTest.getFecha().getDayOfMonth());
        assertEquals(fecha.getHour(), ventaTest.getFecha().getHour());
        assertEquals(fecha.getMinute(), ventaTest.getFecha().getMinute());
        assertEquals(fecha.getSecond(), ventaTest.getFecha().getSecond());
    }

    @Test
    void setCantidadVehiculos() {
        // given
        Integer cantidadVehiculos = 2;
        // when
        ventaTest.setCantidadVehiculos(cantidadVehiculos);
        // then
        assertEquals(cantidadVehiculos, ventaTest.getCantidadVehiculos());
    }

    @Test
    void setPrecioUnitario() {
        // given
        Double precioUnitario = 200000.0;
        // when
        ventaTest.setPrecioUnitario(precioUnitario);
        // then
        assertEquals(precioUnitario, ventaTest.getPrecioUnitario());
    }

    @Test
    void setImpuesto() {
        // given
        Impuesto impuesto = impuestoTest;
        // when
        ventaTest.setImpuesto(impuesto);
        // then
        assertEquals(impuesto.getId(), ventaTest.getImpuesto().getId());
        assertEquals(impuesto.getRegion(), ventaTest.getImpuesto().getRegion());
        assertEquals(impuesto.getPorcentaje(), ventaTest.getImpuesto().getPorcentaje());
        assertEquals(impuesto.getRangoMaximo(), ventaTest.getImpuesto().getRangoMaximo());
        assertEquals(impuesto.getRangoMinimo(), ventaTest.getImpuesto().getRangoMinimo());
    }

    @Test
    void setImpuestoPesos() {
        // given
        Double impuestoPesos = 10000.0;
        // when
        ventaTest.setImpuestoPesos(impuestoPesos);
        // then
        assertEquals(impuestoPesos, ventaTest.getImpuestoPesos());
    }

    @Test
    void setImpuestoPorcentaje() {
        // given
        Double impuestoPorcentaje = 10.0;
        // when
        ventaTest.setImpuestoPorcentaje(impuestoPorcentaje);
        // then
        assertEquals(impuestoPorcentaje, ventaTest.getImpuestoPorcentaje());
    }

    @Test
    void setVehiculo() {
        // given
        Vehiculo vehiculo = vehiculoTest;
        // when
        ventaTest.setVehiculo(vehiculo);
        // then
        assertEquals(vehiculo.getId(), ventaTest.getVehiculo().getId());
        assertEquals(vehiculo.getModelo(), ventaTest.getVehiculo().getModelo());
        assertEquals(vehiculo.getAnio(), ventaTest.getVehiculo().getAnio());
        assertEquals(vehiculo.getCantidad(), ventaTest.getVehiculo().getCantidad());
        assertEquals(vehiculo.getImportado(), ventaTest.getVehiculo().getImportado());
        assertEquals(vehiculo.getImagen(), ventaTest.getVehiculo().getImagen());
        assertEquals(vehiculo.getPrecioVenta(), ventaTest.getVehiculo().getPrecioVenta());
        assertEquals(vehiculo.getPrecioCompra(), ventaTest.getVehiculo().getPrecioCompra());
    }

    @Test
    void setVendedor() {
        // given
        Empleado vendedor = vendedorTest;
        // when
        ventaTest.setVendedor(vendedor);
        // then
        assertEquals(vendedor.getId(), ventaTest.getVendedor().getId());
        assertEquals(vendedor.getNombre(), ventaTest.getVendedor().getNombre());
        assertEquals(vendedor.getApellido(), ventaTest.getVendedor().getApellido());
        assertEquals(vendedor.getDni(), ventaTest.getVendedor().getDni());
    }

    @Test
    void setCliente() {
        // given
        Cliente cliente = clienteTest;
        // when
        ventaTest.setCliente(cliente);
        // then
        assertEquals(cliente.getId(), ventaTest.getCliente().getId());
        assertEquals(cliente.getNombre(), ventaTest.getCliente().getNombre());
        assertEquals(cliente.getApellido(), ventaTest.getCliente().getApellido());
        assertEquals(cliente.getDni(), ventaTest.getCliente().getDni());
    }

    @Test
    void builder() {
        // given
        Long id = 1L;
        LocalDateTime fecha = LocalDateTime.of(2020, 12, 12, 12, 12, 12);
        Integer cantidadVehiculos = 2;
        Double precioUnitario = 200000.0;
        Impuesto impuesto = impuestoTest;
        Double impuestoPesos = 10000.0;
        Double impuestoPorcentaje = 10.0;
        Vehiculo vehiculo = vehiculoTest;
        Empleado vendedor = vendedorTest;
        Cliente cliente = clienteTest;
        // when
        Venta venta = Venta.builder()
                .id(id)
                .fecha(fecha)
                .cantidadVehiculos(cantidadVehiculos)
                .precioUnitario(precioUnitario)
                .impuesto(impuesto)
                .impuestoPesos(impuestoPesos)
                .impuestoPorcentaje(impuestoPorcentaje)
                .vehiculo(vehiculo)
                .vendedor(vendedor)
                .cliente(cliente)
                .build();
        // then
        assertEquals(id, venta.getId());
        assertEquals(fecha, venta.getFecha());
        assertEquals(cantidadVehiculos, venta.getCantidadVehiculos());
        assertEquals(precioUnitario, venta.getPrecioUnitario());
        assertEquals(impuesto.getId(), venta.getImpuesto().getId());
        assertEquals(impuesto.getRegion(), venta.getImpuesto().getRegion());
        assertEquals(impuesto.getPorcentaje(), venta.getImpuesto().getPorcentaje());
        assertEquals(impuesto.getRangoMaximo(), venta.getImpuesto().getRangoMaximo());
        assertEquals(impuesto.getRangoMinimo(), venta.getImpuesto().getRangoMinimo());
        assertEquals(impuestoPesos, venta.getImpuestoPesos());
        assertEquals(impuestoPorcentaje, venta.getImpuestoPorcentaje());
        assertEquals(vehiculo.getId(), venta.getVehiculo().getId());
        assertEquals(vehiculo.getModelo(), venta.getVehiculo().getModelo());
        assertEquals(vehiculo.getAnio(), venta.getVehiculo().getAnio());
        assertEquals(vehiculo.getCantidad(), venta.getVehiculo().getCantidad());
        assertEquals(vehiculo.getImportado(), venta.getVehiculo().getImportado());
        assertEquals(vehiculo.getImagen(), venta.getVehiculo().getImagen());
        assertEquals(vehiculo.getPrecioVenta(), venta.getVehiculo().getPrecioVenta());
        assertEquals(vehiculo.getPrecioCompra(), venta.getVehiculo().getPrecioCompra());
        assertEquals(vendedor.getId(), venta.getVendedor().getId());
        assertEquals(vendedor.getNombre(), venta.getVendedor().getNombre());
        assertEquals(vendedor.getApellido(), venta.getVendedor().getApellido());
        assertEquals(vendedor.getDni(), venta.getVendedor().getDni());
        assertEquals(cliente.getId(), venta.getCliente().getId());
        assertEquals(cliente.getNombre(), venta.getCliente().getNombre());
        assertEquals(cliente.getApellido(), venta.getCliente().getApellido());
        assertEquals(cliente.getDni(), venta.getCliente().getDni());
    }

    @Test
    void noArgsConstructor() {
        // given
        Venta venta;
        // when
        venta = new Venta();
        // then
        assertNotNull(venta);
    }

}