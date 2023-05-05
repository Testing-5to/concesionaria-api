package com.autos.concesionaria.service;


import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.repository.MarcaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;
    private AutoCloseable autoCloseable;
    private MarcaService marcaServiceTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        marcaServiceTest = new MarcaService(marcaRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void crearMarca() {
        // given
        Marca marca = new Marca();
        // when
        marcaServiceTest.crearMarca(marca);
        // then
        verify(marcaRepository).save(marca);
    }

    @Test
    void buscarMarcas() {
        // when
        marcaServiceTest.getMarcas();
        // then
        verify(marcaRepository).findAll();
    }

    @Test
    void buscarMarcaPorId() {
        // given
        Long id = 1L;
        // when
        marcaServiceTest.getMarca(id);
        // then
        verify(marcaRepository).findById(id);
    }

    @Test
    void buscarMarcaPorPais() {
        // given
        String pais = "Argentina";
        // when
        marcaServiceTest.getMarcasByPais(pais);
        // then
        verify(marcaRepository).findAllByPais_Nombre(pais);
    }

    @Test
    void actualizarMarca() {
        // given
        Long id = 1L;
        Marca marca = new Marca();
        marca.setNombre("Nueva marca");
        // when
        when(marcaRepository.findById(id)).thenReturn(Optional.of(new Marca()));
        when(marcaRepository.save(any(Marca.class))).thenReturn(marca);
        marcaServiceTest.actualizarMarca(id, marca);
        // then
        verify(marcaRepository).save(any(Marca.class));
        assertEquals(marcaServiceTest.actualizarMarca(id, marca).getNombre(), marca.getNombre());
    }

    @Test
    void eliminarMarca() {
        // given
        Long id = 1L;
        // when
        marcaServiceTest.borrarMarca(id);
        // then
        verify(marcaRepository).deleteById(id);
    }

    @Test
    void contarMarcasPorPais() {
        // given
        Long rolID = 1L;
        // when
        marcaServiceTest.contarMarcasPorPais(rolID);
        // then
        verify(marcaRepository).countByPais_Id(rolID);
    }
}
