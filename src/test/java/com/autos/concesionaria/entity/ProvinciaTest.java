package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProvinciaTest {

    Pais paisTest = Pais.builder().id(1L).nombre("Argentina").build();
    Provincia provinciaTest = Provincia.builder().id(1L).nombre("Buenos Aires").pais(paisTest).build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idProvincia = provinciaTest.getId();
        // then
        assertEquals(id, idProvincia);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "Buenos Aires";
        // when
        String nombreProvincia = provinciaTest.getNombre();
        // then
        assertEquals(nombre, nombreProvincia);
    }

    @Test
    void getPais() {
        // given
        Pais pais = paisTest;
        // when
        Pais paisProvincia = provinciaTest.getPais();
        // then
        assertEquals(pais, paisProvincia);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        provinciaTest.setId(id);
        // then
        assertEquals(id, provinciaTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Cordoba";
        // when
        provinciaTest.setNombre(nombre);
        // then
        assertEquals(nombre, provinciaTest.getNombre());
    }

    @Test
    void setPais() {
        // given
        Pais pais = Pais.builder().id(2L).nombre("Brasil").build();
        // when
        provinciaTest.setPais(pais);
        // then
        assertEquals(pais.getId(), provinciaTest.getPais().getId());
        assertEquals(pais.getNombre(), provinciaTest.getPais().getNombre());
    }

    @Test
    void builder() {
        // given
        Long id = 1L;
        String nombre = "Buenos Aires";
        Pais pais = paisTest;
        // when
        Provincia provincia = Provincia.builder().id(id).nombre(nombre).pais(pais).build();
        // then
        assertEquals(id, provincia.getId());
        assertEquals(nombre, provincia.getNombre());
        assertEquals(pais, provincia.getPais());
    }

    @Test
    void noArgsConstructor() {
        // given
        Provincia provincia;
        // when
        provincia = new Provincia();
        // then
        assertNull(provincia.getId());
        assertNull(provincia.getNombre());
        assertNull(provincia.getPais());
    }

}