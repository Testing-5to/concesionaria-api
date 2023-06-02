package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaisTest {

    Pais paisTest = Pais.builder()
            .id(1L)
            .nombre("Argentina")
            .abreviatura("AR")
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = paisTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "Argentina";
        // when
        String nombreTest = paisTest.getNombre();
        // then
        assertEquals(nombre, nombreTest);
    }

    @Test
    void getAbreviatura() {
        // given
        String abreviatura = "AR";
        // when
        String abreviaturaTest = paisTest.getAbreviatura();
        // then
        assertEquals(abreviatura, abreviaturaTest);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        paisTest.setId(id);
        // then
        assertEquals(id, paisTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "Brasil";
        // when
        paisTest.setNombre(nombre);
        // then
        assertEquals(nombre, paisTest.getNombre());
    }

    @Test
    void setAbreviatura() {
        // given
        String abreviatura = "BR";
        // when
        paisTest.setAbreviatura(abreviatura);
        // then
        assertEquals(abreviatura, paisTest.getAbreviatura());
    }

    @Test
    void builder() {
        // given
        Pais pais = new Pais();
        pais.setId(1L);
        pais.setNombre("Argentina");
        pais.setAbreviatura("AR");
        // when
        Pais paisTest = Pais.builder()
                .id(1L)
                .nombre("Argentina")
                .abreviatura("AR")
                .build();
        // then
        assertEquals(pais.getId(), paisTest.getId());
        assertEquals(pais.getNombre(), paisTest.getNombre());
        assertEquals(pais.getAbreviatura(), paisTest.getAbreviatura());
    }

    @Test
    void noArgsConstructor() {
        // given
        Pais pais;
        // when
        pais = new Pais();
        // then
        assertNotNull(pais);
    }

}