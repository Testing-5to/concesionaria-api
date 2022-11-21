package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VehiculoTest {
    Modelo modeloTest = Modelo.builder().id(1L).nombre("Modelo Test").build();

    Vehiculo vehiculoTest = Vehiculo.builder()
            .id(1L)
            .anio(2020)
            .cantidad(1)
            .importado(false)
            .imagen("imagen")
            .precioVenta(100000.0)
            .precioCompra(90000.0)
            .modelo(modeloTest)
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = vehiculoTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getAnio() {
        // given
        Integer anio = 2020;
        // when
        Integer anioTest = vehiculoTest.getAnio();
        // then
        assertEquals(anio, anioTest);
    }

    @Test
    void getCantidad() {
        // given
        Integer cantidad = 1;
        // when
        Integer cantidadTest = vehiculoTest.getCantidad();
        // then
        assertEquals(cantidad, cantidadTest);
    }

    @Test
    void getImportado() {
        // given
        Boolean importado = false;
        // when
        Boolean importadoTest = vehiculoTest.getImportado();
        // then
        assertEquals(importado, importadoTest);
    }

    @Test
    void getImagen() {
        // given
        String imagen = "imagen";
        // when
        String imagenTest = vehiculoTest.getImagen();
        // then
        assertEquals(imagen, imagenTest);
    }

    @Test
    void getPrecioVenta() {
        // given
        Double precioVenta = 100000.0;
        // when
        Double precioVentaTest = vehiculoTest.getPrecioVenta();
        // then
        assertEquals(precioVenta, precioVentaTest);
    }

    @Test
    void getPrecioCompra() {
        // given
        Double precioCompra = 90000.0;
        // when
        Double precioCompraTest = vehiculoTest.getPrecioCompra();
        // then
        assertEquals(precioCompra, precioCompraTest);
    }

    @Test
    void getModelo() {
        // given
        Modelo modelo = modeloTest;
        // when
        Modelo modeloTest = vehiculoTest.getModelo();
        // then
        assertEquals(modelo.getId(), modeloTest.getId());
        assertEquals(modelo.getNombre(), modeloTest.getNombre());
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        vehiculoTest.setId(id);
        // then
        assertEquals(id, vehiculoTest.getId());
    }

    @Test
    void setAnio() {
        // given
        Integer anio = 2021;
        // when
        vehiculoTest.setAnio(anio);
        // then
        assertEquals(anio, vehiculoTest.getAnio());
    }

    @Test
    void setCantidad() {
        // given
        Integer cantidad = 2;
        // when
        vehiculoTest.setCantidad(cantidad);
        // then
        assertEquals(cantidad, vehiculoTest.getCantidad());
    }

    @Test
    void setImportado() {
        // given
        Boolean importado = true;
        // when
        vehiculoTest.setImportado(importado);
        // then
        assertEquals(importado, vehiculoTest.getImportado());
    }

    @Test
    void setImagen() {
        // given
        String imagen = "imagen2";
        // when
        vehiculoTest.setImagen(imagen);
        // then
        assertEquals(imagen, vehiculoTest.getImagen());
    }

    @Test
    void setPrecioVenta() {
        // given
        Double precioVenta = 110000.0;
        // when
        vehiculoTest.setPrecioVenta(precioVenta);
        // then
        assertEquals(precioVenta, vehiculoTest.getPrecioVenta());
    }

    @Test
    void setPrecioCompra() {
        // given
        Double precioCompra = 100000.0;
        // when
        vehiculoTest.setPrecioCompra(precioCompra);
        // then
        assertEquals(precioCompra, vehiculoTest.getPrecioCompra());
    }

    @Test
    void setModelo() {
        // given
        Modelo modelo = Modelo.builder().id(2L).nombre("Modelo Test 2").build();
        // when
        vehiculoTest.setModelo(modelo);
        // then
        assertEquals(modelo.getId(), vehiculoTest.getModelo().getId());
        assertEquals(modelo.getNombre(), vehiculoTest.getModelo().getNombre());
    }

    @Test
    void builder() {
        // given
        Long id = 1L;
        Integer anio = 2020;
        Integer cantidad = 1;
        Boolean importado = false;
        String imagen = "imagen";
        Double precioVenta = 100000.0;
        Double precioCompra = 90000.0;
        Modelo modelo = modeloTest;
        // when
        Vehiculo vehiculo = Vehiculo.builder()
                .id(id)
                .anio(anio)
                .cantidad(cantidad)
                .importado(importado)
                .imagen(imagen)
                .precioVenta(precioVenta)
                .precioCompra(precioCompra)
                .modelo(modelo)
                .build();
        // then
        assertEquals(id, vehiculo.getId());
        assertEquals(anio, vehiculo.getAnio());
        assertEquals(cantidad, vehiculo.getCantidad());
        assertEquals(importado, vehiculo.getImportado());
        assertEquals(imagen, vehiculo.getImagen());
        assertEquals(precioVenta, vehiculo.getPrecioVenta());
        assertEquals(precioCompra, vehiculo.getPrecioCompra());
        assertEquals(modelo.getId(), vehiculo.getModelo().getId());
        assertEquals(modelo.getNombre(), vehiculo.getModelo().getNombre());
    }

    @Test
    void noArgsConstructor() {
        // given
        Vehiculo vehiculo;
        // when
        vehiculo = new Vehiculo();
        // then
        assertNotNull(vehiculo);
    }

}