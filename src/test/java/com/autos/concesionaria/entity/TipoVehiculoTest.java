package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TipoVehiculoTest {

    TipoVehiculo tipoVehiculo = TipoVehiculo.builder().id(1L).nombre("Auto").build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idObtenido = tipoVehiculo.getId();
        // then
        assertEquals(id, idObtenido);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "Auto";
        // when
        String nombreObtenido = tipoVehiculo.getNombre();
        // then
        assertEquals(nombre, nombreObtenido);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        tipoVehiculo.setId(id);
        // then
        assertEquals(id, tipoVehiculo.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Camioneta";
        // when
        tipoVehiculo.setNombre(nombre);
        // then
        assertEquals(nombre, tipoVehiculo.getNombre());
    }

    @Test
    void builder() {
        // given
        Long id = 1L;
        String nombre = "Auto";
        // when
        TipoVehiculo tipoVehiculo = TipoVehiculo.builder().id(id).nombre(nombre).build();
        // then
        assertEquals(id, tipoVehiculo.getId());
        assertEquals(nombre, tipoVehiculo.getNombre());
    }

    @Test
    void noArgsConstructor() {
        // given
        TipoVehiculo tipoVehiculo;
        // when
        tipoVehiculo = new TipoVehiculo();
        // then
        assertNotNull(tipoVehiculo);
    }

}