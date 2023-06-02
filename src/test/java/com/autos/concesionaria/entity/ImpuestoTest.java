package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImpuestoTest {

    Impuesto impuestoTest = Impuesto.builder()
            .id(1L)
            .region("Buenos Aires")
            .porcentaje(5.0)
            .rangoMinimo(0.0)
            .rangoMaximo(100000.0)
            .build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = impuestoTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getRegion() {
        // given
        String region = "Buenos Aires";
        // when
        String regionTest = impuestoTest.getRegion();
        // then
        assertEquals(region, regionTest);
    }

    @Test
    void getPorcentaje() {
        // given
        Double porcentaje = 5.0;
        // when
        Double porcentajeTest = impuestoTest.getPorcentaje();
        // then
        assertEquals(porcentaje, porcentajeTest);
    }

    @Test
    void getRangoMinimo() {
        // given
        Double rangoMinimo = 0.0;
        // when
        Double rangoMinimoTest = impuestoTest.getRangoMinimo();
        // then
        assertEquals(rangoMinimo, rangoMinimoTest);
    }

    @Test
    void getRangoMaximo() {
        // given
        Double rangoMaximo = 100000.0;
        // when
        Double rangoMaximoTest = impuestoTest.getRangoMaximo();
        // then
        assertEquals(rangoMaximo, rangoMaximoTest);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        impuestoTest.setId(id);
        // then
        assertEquals(id, impuestoTest.getId());
    }

    @Test
    void setRegion() {
        // given
        String region = "CABA";
        // when
        impuestoTest.setRegion(region);
        // then
        assertEquals(region, impuestoTest.getRegion());
    }

    @Test
    void setPorcentaje() {
        // given
        Double porcentaje = 10.0;
        // when
        impuestoTest.setPorcentaje(porcentaje);
        // then
        assertEquals(porcentaje, impuestoTest.getPorcentaje());
    }

    @Test
    void setRangoMinimo() {
        // given
        Double rangoMinimo = 100000.0;
        // when
        impuestoTest.setRangoMinimo(rangoMinimo);
        // then
        assertEquals(rangoMinimo, impuestoTest.getRangoMinimo());
    }

    @Test
    void setRangoMaximo() {
        // given
        Double rangoMaximo = 200000.0;
        // when
        impuestoTest.setRangoMaximo(rangoMaximo);
        // then
        assertEquals(rangoMaximo, impuestoTest.getRangoMaximo());
    }

    @Test
    void builder() {
        // given
        Impuesto impuesto = new Impuesto();
        impuesto.setId(1L);
        impuesto.setRegion("Buenos Aires");
        impuesto.setPorcentaje(5.0);
        impuesto.setRangoMinimo(0.0);
        impuesto.setRangoMaximo(100000.0);
        // when
        Impuesto impuestoTest = Impuesto.builder()
                .id(1L)
                .region("Buenos Aires")
                .porcentaje(5.0)
                .rangoMinimo(0.0)
                .rangoMaximo(100000.0)
                .build();
        // then
        assertEquals(impuesto.getId(), impuestoTest.getId());
        assertEquals(impuesto.getRegion(), impuestoTest.getRegion());
        assertEquals(impuesto.getPorcentaje(), impuestoTest.getPorcentaje());
        assertEquals(impuesto.getRangoMinimo(), impuestoTest.getRangoMinimo());
        assertEquals(impuesto.getRangoMaximo(), impuestoTest.getRangoMaximo());
    }

    @Test
    void noArgsConstructor() {
        // given
        Impuesto impuesto;
        // when
        impuesto = new Impuesto();
        // then
        assertNotNull(impuesto);
    }
}