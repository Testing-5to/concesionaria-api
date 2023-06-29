package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.repository.ModeloRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ModeloServiceTest {

    @Mock
    private ModeloRepository modeloRepository;

    private AutoCloseable autoCloseable;
    private ModeloService modeloServiceTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        modeloServiceTest = new ModeloService(modeloRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void crearModelo() {
        // given
        Modelo modelo = new Modelo();
        // when
        modeloServiceTest.crearModelo(modelo);
        // then
        verify(modeloRepository).save(modelo);
    }

    @Test
    void buscarModelos() {
        // when
        modeloServiceTest.getModelos();
        // then
        verify(modeloRepository).findAll();
    }

    @Test
    void buscarModeloPorId() {
        // given
        Long id = 1L;
        // when
        modeloServiceTest.getModelo(id);
        // then
        verify(modeloRepository).findById(id);
    }

    @Test
    void eliminarModelo() {
        // given
        Long id = 1L;
        // when
        modeloServiceTest.borrarModelo(id);
        // then
        verify(modeloRepository).deleteById(id);
    }

    @Test
    void buscarModelosPorMarca() {
        // given
        String marca = "Ford";
        // when
        modeloServiceTest.getModelosByMarca(marca);
        // then
        verify(modeloRepository).findAllByMarca_Nombre(marca);
    }

    @Test
    void buscarModelosPorTipoVehiculo() {
        // given
        String tipoVehiculo = "Auto";
        // when
        modeloServiceTest.getModelosByTipoVehiculo(tipoVehiculo);
        // then
        verify(modeloRepository).findAllByTipoVehiculo_Nombre(tipoVehiculo);
    }

    @Test
    void buscarModelosPorMarcaAndTipoVehiculo() {
        // given
        String marca = "Ford";
        String tipoVehiculo = "Auto";

        // when
        modeloServiceTest.getModelosByMarcaAndTipoVehiculo(marca, tipoVehiculo);
        // then
        verify(modeloRepository)
                .findAllByMarca_NombreAndTipoVehiculo_Nombre(marca, tipoVehiculo);
    }

    @Test
    void contarModelosPorTipoVehiculo() {
        // given
        Long id = 1L;
        // when
        modeloServiceTest.contarModelosPorTipoVehiculo(id);
        // then
        verify(modeloRepository).countByTipoVehiculo_Id(id);
    }

    @Test
    void contarModelosPorMarca() {
        // given
        Long id = 1L;
        // when
        modeloServiceTest.contarModelosPorMarca(id);
        // then
        verify(modeloRepository).countByMarca_Id(id);
    }
}
