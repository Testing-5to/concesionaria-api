package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarcaTest {
    Pais paisTest = Pais.builder().id(1L).nombre("Argentina").abreviatura("AR").build();
    Marca marcaTest = Marca.builder()
            .id(1L)
            .nombre("Fiat")
            .pais(paisTest)
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = marcaTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "Fiat";
        // when
        String nombreTest = marcaTest.getNombre();
        // then
        assertEquals(nombre, nombreTest);
    }

    @Test
    void getPais() {
        // given
        Pais pais = paisTest;
        // when
        Pais paisTest = marcaTest.getPais();
        // then
        assertEquals(pais, paisTest);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        marcaTest.setId(id);
        // then
        assertEquals(id, marcaTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Ford";
        // when
        marcaTest.setNombre(nombre);
        // then
        assertEquals(nombre, marcaTest.getNombre());
    }

    @Test
    void setPais() {
        // given
        Pais pais = Pais.builder().id(2L).nombre("Brasil").abreviatura("BR").build();
        // when
        marcaTest.setPais(pais);
        // then
        assertEquals(pais.getId(), marcaTest.getPais().getId());
        assertEquals(pais.getNombre(), marcaTest.getPais().getNombre());
        assertEquals(pais.getAbreviatura(), marcaTest.getPais().getAbreviatura());
    }

    @Test
    void builder() {
        // given
        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("Fiat");
        marca.setPais(paisTest);
        // when
        Marca marcaTest = Marca.builder()
                .id(1L)
                .nombre("Fiat")
                .pais(paisTest)
                .build();
        // then
        assertEquals(marca.getId(), marcaTest.getId());
        assertEquals(marca.getNombre(), marcaTest.getNombre());
        assertEquals(marca.getPais().getId(), marcaTest.getPais().getId());
        assertEquals(marca.getPais().getNombre(), marcaTest.getPais().getNombre());
        assertEquals(marca.getPais().getAbreviatura(), marcaTest.getPais().getAbreviatura());
    }
}