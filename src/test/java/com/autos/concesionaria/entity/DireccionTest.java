package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DireccionTest {

    Localidad localidadTest = Localidad.builder()
            .id(1L)
            .nombre("CABA")
            .build();
    Direccion direccionTest = Direccion.builder()
            .id(1L)
            .calle("Calle Falsa")
            .numero(123)
            .piso("1")
            .departamento("A")
            .localidad(localidadTest)
            .build();

    @Test
    void getId() {
        // when
        Long id = direccionTest.getId();
        // then
        assertEquals(1L, id);
    }

    @Test
    void getCalle() {
        // when
        String calle = direccionTest.getCalle();
        // then
        assertEquals("Calle Falsa", calle);
    }

    @Test
    void getNumero() {
        // when
        int numero = direccionTest.getNumero();
        // then
        assertEquals(123, numero);
    }

    @Test
    void getPiso() {
        // when
        String piso = direccionTest.getPiso();
        // then
        assertEquals("1", piso);
    }

    @Test
    void getDepartamento() {
        // when
        String departamento = direccionTest.getDepartamento();
        // then
        assertEquals("A", departamento);
    }

    @Test
    void getLocalidad() {
        // when
        Localidad localidad = direccionTest.getLocalidad();
        // then
        assertEquals(localidadTest, localidad);
    }

    @Test
    void setId() {
        // when
        direccionTest.setId(2L);
        // then
        assertEquals(2L, direccionTest.getId());
    }

    @Test
    void setCalle() {
        // when
        direccionTest.setCalle("Calle Falsa 123");
        // then
        assertEquals("Calle Falsa 123", direccionTest.getCalle());
    }

    @Test
    void setNumero() {
        // when
        direccionTest.setNumero(321);
        // then
        assertEquals(321, direccionTest.getNumero());
    }

    @Test
    void setPiso() {
        // when
        direccionTest.setPiso("2");
        // then
        assertEquals("2", direccionTest.getPiso());
    }

    @Test
    void setDepartamento() {
        // when
        direccionTest.setDepartamento("B");
        // then
        assertEquals("B", direccionTest.getDepartamento());
    }

    @Test
    void setLocalidad() {
        // given
        Localidad localidadTest2 = Localidad.builder()
                .id(2L)
                .nombre("San Isidro")
                .build();
        // when
        direccionTest.setLocalidad(localidadTest2);
        // then
        assertEquals(localidadTest2, direccionTest.getLocalidad());
    }

    @Test
    void builder() {
        // when
        Direccion direccion = Direccion.builder()
                .id(1L)
                .calle("Calle Falsa")
                .numero(123)
                .piso("1")
                .departamento("A")
                .localidad(localidadTest)
                .build();
        // then
        assertEquals(direccion.getId(), 1L);
        assertEquals(direccion.getCalle(), "Calle Falsa");
        assertEquals(direccion.getNumero(), 123);
        assertEquals(direccion.getPiso(), "1");
        assertEquals(direccion.getDepartamento(), "A");
        assertEquals(direccion.getLocalidad(), localidadTest);
    }
}