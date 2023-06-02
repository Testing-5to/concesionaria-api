package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ModeloTest {
    Marca marcaTest = Marca.builder().id(1L).nombre("Marca Test").build();
    TipoVehiculo tipoVehiculoTest = TipoVehiculo.builder().id(1L).nombre("Tipo Vehiculo Test").build();
    Modelo modeloTest = Modelo.builder()
            .id(1L)
            .nombre("Modelo Test")
            .marca(marcaTest)
            .tipoVehiculo(tipoVehiculoTest)
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = modeloTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "Modelo Test";
        // when
        String nombreTest = modeloTest.getNombre();
        // then
        assertEquals(nombre, nombreTest);
    }

    @Test
    void getMarca() {
        // given
        Marca marca = marcaTest;
        // when
        Marca marcaTest = modeloTest.getMarca();
        // then
        assertEquals(marca, marcaTest);
    }

    @Test
    void getTipoVehiculo() {
        // given
        TipoVehiculo tipoVehiculo = tipoVehiculoTest;
        // when
        TipoVehiculo tipoVehiculoTest = modeloTest.getTipoVehiculo();
        // then
        assertEquals(tipoVehiculo, tipoVehiculoTest);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        modeloTest.setId(id);
        // then
        assertEquals(id, modeloTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Modelo Test 2";
        // when
        modeloTest.setNombre(nombre);
        // then
        assertEquals(nombre, modeloTest.getNombre());
    }

    @Test
    void setMarca() {
        // given
        Marca marca = Marca.builder().id(2L).nombre("Marca Test 2").build();
        // when
        modeloTest.setMarca(marca);
        // then
        assertEquals(marca, modeloTest.getMarca());
    }

    @Test
    void setTipoVehiculo() {
        // given
        TipoVehiculo tipoVehiculo = TipoVehiculo.builder().id(2L).nombre("Tipo Vehiculo Test 2").build();
        // when
        modeloTest.setTipoVehiculo(tipoVehiculo);
        // then
        assertEquals(tipoVehiculo, modeloTest.getTipoVehiculo());
    }

    @Test
    void builder() {
        // given
        Modelo modelo = new Modelo();
        modelo.setId(1L);
        modelo.setNombre("Modelo Test");
        modelo.setMarca(marcaTest);
        modelo.setTipoVehiculo(tipoVehiculoTest);
        // when
        Modelo modeloTest = Modelo.builder()
                .id(1L)
                .nombre("Modelo Test")
                .marca(marcaTest)
                .tipoVehiculo(tipoVehiculoTest)
                .build();
        // then
        assertEquals(modelo.getId(), modeloTest.getId());
        assertEquals(modelo.getNombre(), modeloTest.getNombre());
        assertEquals(modelo.getMarca(), modeloTest.getMarca());
        assertEquals(modelo.getTipoVehiculo(), modeloTest.getTipoVehiculo());
    }

    @Test
    void noArgsConstructor() {
        // given
        Modelo modelo;
        // when
        modelo = new Modelo();
        // then
        assertNotNull(modelo);
    }

}