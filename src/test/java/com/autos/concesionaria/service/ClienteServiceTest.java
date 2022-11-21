package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Cliente;
import com.autos.concesionaria.repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    private AutoCloseable autoCloseable;
    private ClienteService clienteServiceTest;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        clienteServiceTest = new ClienteService(clienteRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void crearCliente() {
        // given
        Cliente cliente = new Cliente();
        // when
        clienteServiceTest.crearCliente(cliente);
        // then
        verify(clienteRepository).save(cliente);
    }

    @Test
    void buscarClientes() {
        // when
        clienteServiceTest.buscarClientes();
        // then
        verify(clienteRepository).findAll();
    }

    @Test
    void buscarClientePorId() {
        // given
        Long id = 1L;
        // when
        clienteServiceTest.buscarClientePorId(id);
        // then
        verify(clienteRepository).findById(id);
    }

    @Test
    void eliminarClientePorId() {
        // given
        Long id = 1L;
        // when
        clienteServiceTest.eliminarClientePorId(id);
        // then
        verify(clienteRepository).deleteById(id);
    }

    @Test
    void contarClientesPorDireccion() {
        // given
        Long direccionId = 1L;
        // when
        clienteServiceTest.contarClientesPorDireccion(direccionId);
        // then
        verify(clienteRepository).countAllByDireccion_Id(direccionId);
    }
}