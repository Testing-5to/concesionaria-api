package com.autos.concesionaria.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente clienteTest;

    @BeforeEach
    void setUp() {
        clienteTest = new Cliente();
        clienteTest.setId(1L);
        clienteTest.setNombre("Juan");
        clienteTest.setApellido("Perez");
        clienteTest.setTelefono("123456789");
        clienteTest.setDni(12345678);
        clienteTest.setEmail("juanperez@gmail.com");
        clienteTest.setEsCliente(true);
        clienteTest.setDireccion(
                Direccion.builder()
                        .calle("Av. Siempreviva")
                        .numero(742)
                        .piso("1")
                        .departamento("A")
                        .localidad(
                                Localidad.builder()
                                        .nombre("Springfield")
                                        .provincia(
                                                Provincia.builder()
                                                        .nombre("Massachusetts")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
    }

    @Test
    void getId() {
        // when
        Long id = clienteTest.getId();
        // then
        assertEquals(1L, id);
    }

    @Test
    void getNombre() {
        // when
        String nombre = clienteTest.getNombre();
        // then
        assertEquals("Juan", nombre);
    }

    @Test
    void getApellido() {
        // when
        String apellido = clienteTest.getApellido();
        // then
        assertEquals("Perez", apellido);
    }

    @Test
    void getTelefono() {
        // when
        String telefono = clienteTest.getTelefono();
        // then
        assertEquals("123456789", telefono);
    }

    @Test
    void getDni() {
        // when
        Integer dni = clienteTest.getDni();
        // then
        assertEquals(12345678, dni);
    }

    @Test
    void getEmail() {
        // when
        String email = clienteTest.getEmail();
        // then
        assertEquals("juanperez@gmail.com", email);
    }

    @Test
    void getEsCliente() {
        // when
        Boolean esCliente = clienteTest.getEsCliente();
        // then
        assertTrue(esCliente);
    }

    @Test
    void getDireccion() {
        // when
        Direccion direccion = clienteTest.getDireccion();
        // then
        assertEquals("Av. Siempreviva", direccion.getCalle());
        assertEquals(742, direccion.getNumero());
        assertEquals("1", direccion.getPiso());
        assertEquals("A", direccion.getDepartamento());
        assertEquals("Springfield", direccion.getLocalidad().getNombre());
        assertEquals("Massachusetts", direccion.getLocalidad().getProvincia().getNombre());
    }

    @Test
    void setId() {
        // when
        clienteTest.setId(2L);
        // then
        assertEquals(2L, clienteTest.getId());
    }

    @Test
    void setNombre() {
        // when
        clienteTest.setNombre("Pedro");
        // then
        assertEquals("Pedro", clienteTest.getNombre());
    }

    @Test
    void setApellido() {
        // when
        clienteTest.setApellido("Gomez");
        // then
        assertEquals("Gomez", clienteTest.getApellido());
    }

    @Test
    void setTelefono() {
        // when
        clienteTest.setTelefono("987654321");
        // then
        assertEquals("987654321", clienteTest.getTelefono());
    }

    @Test
    void setDni() {
        // when
        clienteTest.setDni(87654321);
        // then
        assertEquals(87654321, clienteTest.getDni());
    }

    @Test
    void setEmail() {
        // when
        clienteTest.setEmail("juanperez01@gmail.com");
        // then
        assertEquals("juanperez01@gmail.com", clienteTest.getEmail());
    }

    @Test
    void setEsCliente() {
        // when
        clienteTest.setEsCliente(false);
        // then
        assertFalse(clienteTest.getEsCliente());
    }

    @Test
    void setDireccion() {
        // when
        clienteTest.setDireccion(
                Direccion.builder()
                        .calle("Bv. España")
                        .numero(754)
                        .piso("2")
                        .departamento("B")
                        .localidad(
                                Localidad.builder()
                                        .nombre("Avellaneda")
                                        .provincia(
                                                Provincia.builder()
                                                        .nombre("Buenos Aires")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
        // then
        assertEquals("Bv. España", clienteTest.getDireccion().getCalle());
        assertEquals(754, clienteTest.getDireccion().getNumero());
        assertEquals("2", clienteTest.getDireccion().getPiso());
        assertEquals("B", clienteTest.getDireccion().getDepartamento());
        assertEquals("Avellaneda", clienteTest.getDireccion().getLocalidad().getNombre());
        assertEquals("Buenos Aires", clienteTest.getDireccion().getLocalidad().getProvincia().getNombre());
    }

    @Test
    void builder() {
        // when
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Perez")
                .telefono("123456789")
                .dni(12345678)
                .email("juanperez@gmail.com")
                .esCliente(true)
                .direccion(
                        Direccion.builder()
                                .calle("Av. Siempreviva")
                                .numero(742)
                                .piso("1")
                                .departamento("A")
                                .localidad(
                                        Localidad.builder()
                                                .nombre("Springfield")
                                                .provincia(
                                                        Provincia.builder()
                                                                .nombre("Massachusetts")
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        // then
        assertEquals(1L, cliente.getId());
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApellido());
        assertEquals("123456789", cliente.getTelefono());
        assertEquals(12345678, cliente.getDni());
        assertEquals("juanperez@gmail.com", cliente.getEmail());
        assertTrue(cliente.getEsCliente());
        assertEquals("Av. Siempreviva", cliente.getDireccion().getCalle());
        assertEquals(742, cliente.getDireccion().getNumero());
        assertEquals("1", cliente.getDireccion().getPiso());
        assertEquals("A", cliente.getDireccion().getDepartamento());
        assertEquals("Springfield", cliente.getDireccion().getLocalidad().getNombre());
        assertEquals("Massachusetts", cliente.getDireccion().getLocalidad().getProvincia().getNombre());
    }

}