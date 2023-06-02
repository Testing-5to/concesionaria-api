package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Direccion;
import com.autos.concesionaria.entity.Localidad;
import com.autos.concesionaria.repository.DireccionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DireccionServiceTest {

    @Mock
    DireccionRepository direccionRepository;
    private AutoCloseable autoCloseable;
    private DireccionService direccionServiceTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        direccionServiceTest = new DireccionService(direccionRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void buscarDirecciones() {
        // when
        direccionServiceTest.buscarDirecciones();
        // then
        verify(direccionRepository).findAll();
    }

    @Test
    void buscarDireccionesPorLocalidad() {
        // given
        String localidad = "localidad";
        // when
        direccionServiceTest.buscarDireccionesPorLocalidad(localidad);
        // then
        verify(direccionRepository).findAllByLocalidad_Nombre(localidad);
    }

    @Test
    void buscarDireccionPorId() {
        // given
        Long id = 1L;
        // when
        direccionServiceTest.buscarDireccionPorId(id);
        // then
        verify(direccionRepository).findById(id);
    }

    @Test
    void crearDireccion() {
        // given
        Direccion direccion = new Direccion();
        // when
        direccionServiceTest.crearDireccion(direccion);
        // then
        verify(direccionRepository).save(direccion);
    }

    @Test
    void actualizarDireccionPorId() {
        // given
        Long id = 1L;
        Direccion direccion = new Direccion();
        direccion.setCalle("Nueva Calle");
        // when
        when(direccionRepository.findById(id)).thenReturn(Optional.of(new Direccion()));
        when(direccionRepository.save(any(Direccion.class))).thenReturn(direccion);
        direccionServiceTest.actualizarDireccionPorId(id, direccion);
        // then
        verify(direccionRepository).save(any(Direccion.class));
        assertEquals(direccionServiceTest.actualizarDireccionPorId(id, direccion).getCalle(), direccion.getCalle());
    }

    @Test
    void eliminarDireccionPorId() {
        // given
        Long id = 1L;
        // when
        direccionServiceTest.eliminarDireccionPorId(id);
        // then
        verify(direccionRepository).deleteById(id);
    }

    @Test
    void existeDireccion() {
        // given
        Direccion direccion = new Direccion();
        direccion.setId(1L);
        direccion.setCalle("calle");
        direccion.setNumero(1);
        direccion.setLocalidad(Localidad.builder().id(1L).nombre("localidad").build());
        // when
        direccionServiceTest.existeDireccion(direccion);
        // then
        verify(direccionRepository).existsByCalleAndNumeroAndPisoAndDepartamentoAndLocalidad_Id(direccion.getCalle(), direccion.getNumero(), direccion.getPiso(), direccion.getDepartamento(), direccion.getLocalidad().getId());
    }

    @Test
    void buscarDireccion() {
        // given
        Direccion direccion = new Direccion();
        direccion.setId(1L);
        direccion.setCalle("calle");
        direccion.setNumero(1);
        direccion.setLocalidad(Localidad.builder().id(1L).nombre("localidad").build());
        // when
        direccionServiceTest.buscarDireccion(direccion);
        // then
        verify(direccionRepository).findByCalleAndNumeroAndPisoAndDepartamentoAndLocalidad_Id(direccion.getCalle(), direccion.getNumero(), direccion.getPiso(), direccion.getDepartamento(), direccion.getLocalidad().getId());
    }

    @Test
    void contarDireccionesPorLocalidad() {
        // given
        Long idLocalidad = 1L;
        // when
        direccionServiceTest.contarDireccionesPorLocalidad(idLocalidad);
        // then
        verify(direccionRepository).countByLocalidad_Id(idLocalidad);
    }

}