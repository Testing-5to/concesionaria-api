package com.autos.concesionaria.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RolTest {

    Rol rolTest = Rol.builder().id(1L).nombre("test").build();

    @Test
    void getId() {
        // given
        Long id = 1L;
        // when
        Long idTest = rolTest.getId();
        // then
        assertEquals(id, idTest);
    }

    @Test
    void getNombre() {
        // given
        String nombre = "test";
        // when
        String nombreTest = rolTest.getNombre();
        // then
        assertEquals(nombre, nombreTest);
    }

    @Test
    void setId() {
        // given
        Long id = 2L;
        // when
        rolTest.setId(id);
        // then
        assertEquals(id, rolTest.getId());
    }

    @Test
    void setNombre() {
        // given
        String nombre = "test2";
        // when
        rolTest.setNombre(nombre);
        // then
        assertEquals(nombre, rolTest.getNombre());
    }

    @Test
    void builder() {
        // given
        Long id = 1L;
        String nombre = "test";
        // when
        Rol rol = Rol.builder().id(id).nombre(nombre).build();
        // then
        assertEquals(id, rol.getId());
        assertEquals(nombre, rol.getNombre());
    }

    @Test
    void noArgsConstructor() {
        // given
        Rol rol;
        // when
        rol = new Rol();
        // then
        assertNotNull(rol);
    }

}