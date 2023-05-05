package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.repository.TipoVehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TipoVehiculoServiceTest {

    @Mock
    private TipoVehiculoRepository tipoVehiculoRepository;
    @InjectMocks
    private TipoVehiculoService tipoVehiculoServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTipoVehiculos() {
        // when
        tipoVehiculoServiceTest.buscarTipoVehiculos();
        // then
        verify(tipoVehiculoRepository).findAll();
    }

    @Test
    void buscarTipoVehiculoPorId() {
        // given
        Long id = 1L;
        // when
        tipoVehiculoServiceTest.buscarTipoVehiculoPorId(id);
        // then
        verify(tipoVehiculoRepository).findById(id);
    }

    @Test
    void crearTipoVehiculo() {
        // given
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        // when
        tipoVehiculoServiceTest.crearTipoVehiculo(tipoVehiculo);
        // then
        verify(tipoVehiculoRepository).save(tipoVehiculo);
    }

    @Test
    void actualizarTipoVehiculoPorId() {
        // given
        Long id = 1L;
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setNombre("Nuevo tipo de vehiculo");
        // when
        when(tipoVehiculoRepository.findById(id)).thenReturn(Optional.of(new TipoVehiculo()));
        when(tipoVehiculoRepository.saveAndFlush(any(TipoVehiculo.class))).thenReturn(tipoVehiculo);
        tipoVehiculoServiceTest.actualizarTipoVehiculoPorId(id, tipoVehiculo);
        // then
        verify(tipoVehiculoRepository).saveAndFlush(any(TipoVehiculo.class));
        assertEquals(tipoVehiculoServiceTest.actualizarTipoVehiculoPorId(id, tipoVehiculo).getNombre(), tipoVehiculo.getNombre());
    }

    @Test
    void eliminarTipoVehiculoPorId() {
        // given
        Long id = 1L;
        // when
        tipoVehiculoServiceTest.eliminarTipoVehiculoPorId(id);
        // then
        verify(tipoVehiculoRepository).deleteById(id);
    }
}