package com.autos.concesionaria.service;



import com.autos.concesionaria.entity.Empleado;
import com.autos.concesionaria.repository.EmpleadoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;
    private AutoCloseable autoCloseable;
    private EmpleadoService empleadoServiceTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        empleadoServiceTest = new EmpleadoService(empleadoRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void crearEmpleado() {
        // given
        Empleado empleado = new Empleado();
        // when
        empleadoServiceTest.crearEmpleado(empleado);
        // then
        verify(empleadoRepository).save(empleado);
    }

    @Test
    void buscarEmpleados() {
        // when
        empleadoServiceTest.buscarEmpleados();
        // then
        verify(empleadoRepository).findAll();
    }

    @Test
    void buscarEmpleadoPorId() {
        // given
        Long id = 1L;
        // when
        empleadoServiceTest.buscarEmpleadoPorId(id);
        // then
        verify(empleadoRepository).findById(id);
    }

    @Test
    void eliminarEmpleadoPorId() {
        // given
        Long id = 1L;
        // when
        empleadoServiceTest.eliminarEmpleadoPorId(id);
        // then
        verify(empleadoRepository).deleteById(id);
    }

    @Test
    void contarEmpleadosPorDireccion() {
        // given
        Long direccionId = 1L;
        // when
        empleadoServiceTest.contarEmpleadosPorDireccion(direccionId);
        // then
        verify(empleadoRepository).countByDireccion_Id(direccionId);
    }

    @Test
    void contarEmpleadosPorRol() {
        // given
        Long rolID = 1L;
        // when
        empleadoServiceTest.contarEmpleadosPorRol(rolID);
        // then
        verify(empleadoRepository).countByRol_Id(rolID);
    }
}
