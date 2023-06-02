package com.autos.concesionaria.service;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.repository.RolRepository;
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

class RolServiceTest {

    @Mock
    private RolRepository rolRepository;
    @InjectMocks
    private RolService rolServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarRoles() {
        // when
        rolServiceTest.buscarRoles();
        // then
        verify(rolRepository).findAll();
    }

    @Test
    void buscarRolPorId() {
        // given
        Long id = 1L;
        // when
        rolServiceTest.buscarRolPorId(id);
        // then
        verify(rolRepository).findById(id);
    }

    @Test
    void crearRol() {
        // given
        Rol rol = new Rol();
        // when
        rolServiceTest.crearRol(rol);
        // then
        verify(rolRepository).save(rol);
    }

    @Test
    void actualizarRolPorId() {
        // given
        Long id = 1L;
        Rol rol = new Rol();
        rol.setNombre("Nuevo rol");
        // when
        when(rolRepository.findById(id)).thenReturn(Optional.of(new Rol()));
        when(rolRepository.saveAndFlush(any(Rol.class))).thenReturn(rol);
        rolServiceTest.actualizarRolPorId(id, rol);
        // then
        verify(rolRepository).saveAndFlush(any(Rol.class));
        assertEquals(rolServiceTest.actualizarRolPorId(id, rol).getNombre(), rol.getNombre());
    }

    @Test
    void eliminarRolPorId() {
        // given
        Long id = 1L;
        // when
        rolServiceTest.eliminarRolPorId(id);
        // then
        verify(rolRepository).deleteById(id);
    }
}