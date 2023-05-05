package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Impuesto;
import com.autos.concesionaria.repository.ImpuestoRepository;
import com.autos.concesionaria.repository.PaisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ImpuestoServiceTest {

    @Mock
    private ImpuestoRepository impuestoRepository;
    @Mock
    private PaisRepository paisRepository;
    @InjectMocks
    private ImpuestoService impuestoServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarImpuestos() {
        // when
        impuestoServiceTest.buscarImpuestos();
        // then
        verify(impuestoRepository).findAll();
    }

    @Test
    void getImpuesto() {
        // given
        Long id = 1L;
        // when
        impuestoServiceTest.getImpuesto(id);
        // then
        verify(impuestoRepository).findById(id);
    }

    @Test
    @DisplayName("buscarImpuestoPorPaisYPrecioVenta para pais argentina")
    void buscarImpuestoPorPaisYPrecioVenta() {
        // given
        List<Impuesto> listaImpuestos = new ArrayList<>();
        Impuesto impuesto = new Impuesto(1L, "Nacional", 10d, 0d, 10000d);
        listaImpuestos.add(impuesto);

        String pais = "Argentina";
        Double precioVenta = 1000d;
        String region = "Nacional";
        // when
        when(impuestoRepository.findAllByRegion(region)).thenReturn(listaImpuestos);
        impuestoServiceTest.buscarImpuestoPorPaisYPrecioVenta(pais, precioVenta);
        // then
        verify(impuestoRepository).findAllByRegion(region);
        assertEquals(impuestoServiceTest.buscarImpuestoPorPaisYPrecioVenta(pais, precioVenta), impuesto);
    }

    @Test
    @DisplayName("buscarImpuestoPorPaisYPrecioVenta para pais de America")
    void buscarImpuestoPorPaisYPrecioVenta2() {
        // given
        Impuesto impuesto = new Impuesto(1L, "America", 10d, 0d, 10000d);

        String pais = "Brasil";
        Double precioVenta = 1000d;
        String region = "America";
        // when
        when(impuestoRepository.findByRegion(region)).thenReturn(impuesto);
        impuestoServiceTest.buscarImpuestoPorPaisYPrecioVenta(pais, precioVenta);
        // then
        verify(impuestoRepository).findByRegion(region);
        assertEquals(impuestoServiceTest.buscarImpuestoPorPaisYPrecioVenta(pais, precioVenta), impuesto);
    }

    @Test
    @DisplayName("buscarImpuestoPorPaisYPrecioVenta para pais fuera de America")
    void buscarImpuestoPorPaisYPrecioVenta3() {
        // given
        Impuesto impuesto = new Impuesto(1L, "Mundo", 10d, 0d, 10000d);

        String pais = "Italia";
        Double precioVenta = 1000d;
        String region = "Mundo";
        // when
        when(paisRepository.existsByNombre(pais)).thenReturn(true);
        when(impuestoRepository.findByRegion(region)).thenReturn(impuesto);
        impuestoServiceTest.buscarImpuestoPorPaisYPrecioVenta(pais, precioVenta);
        // then
        //verify(impuestoRepository).findByRegion("Mundo");
        verify(impuestoRepository).findByRegion(region);
        assertEquals(impuestoServiceTest.buscarImpuestoPorPaisYPrecioVenta(pais, precioVenta), impuesto);
    }
}
