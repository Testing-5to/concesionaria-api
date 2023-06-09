package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.*;
import com.autos.concesionaria.service.ClienteService;
import com.autos.concesionaria.service.DireccionService;
import com.autos.concesionaria.service.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(DireccionController.class)
class DireccionControllerTest {

    @MockBean
    private DireccionService direccionService;
    @MockBean
    private EmpleadoService empleadoService;
    @MockBean
    private ClienteService clienteService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDirecciones() throws Exception {
        // given
        Localidad localidad = new Localidad();
        localidad.setNombre("Villa Maria");

        Direccion direccion = new Direccion();
        direccion.setLocalidad(localidad);

        List<Direccion> direcciones = new ArrayList<>();
        direcciones.add(direccion);
        // when
        when(direccionService.buscarDirecciones()).thenReturn(direcciones);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/direccion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(direcciones)));

        // when en el request param se envia un string de localidad
        when(direccionService.buscarDireccionesPorLocalidad(any(String.class))).thenReturn(direcciones);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/direccion?localidad={localidad}",direccion.getLocalidad())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(direcciones)));
    }

    @Test
    void getDireccionPorId() throws Exception {
        // given
        Long id = 1L;
        Direccion direccion = new Direccion();
        direccion.setId(id);
        // when
        when(direccionService.buscarDireccionPorId(id)).thenReturn(direccion);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/direccion/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(direccion)));
    }

    @Test
    void guardarDireccion() throws Exception {
        // given
        Long id = 1L;
        Direccion direccion = new Direccion();
        direccion.setId(id);
        // when
        when(direccionService.crearDireccion(any(Direccion.class))).thenReturn(direccion);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/direccion")
                        .content(mapper.writeValueAsString(direccion))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(direccion)));
    }

    @Test
    void actualizarDireccion() throws Exception {
        // given
        Long id = 1L;
        Direccion direccion = new Direccion();
        direccion.setId(id);
        // when
        when(direccionService.actualizarDireccionPorId(eq(id),any(Direccion.class))).thenReturn(direccion);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/direccion/{id}",id)
                        .content(mapper.writeValueAsString(direccion))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(direccion)));
    }


    @Test
    void eliminarDireccion() throws Exception {
        // given
        Long id = 1L;

        // when la direccion esta asociada a un empleado. No deberia eliminarse si esta asociada a un empleado o cliente
        when(empleadoService.contarEmpleadosPorDireccion(eq(id))).thenReturn(1);
        when(clienteService.contarClientesPorDireccion(eq(id))).thenReturn(0);

        // then no se elimina la direccion
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/direccion/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede eliminar la direccion con id: " + id + " porque esta asociada a un empleado o cliente"));;

        // when la direccion no esta asociada a un empleado o cliente
        when(empleadoService.contarEmpleadosPorDireccion(eq(id))).thenReturn(0);
        when(clienteService.contarClientesPorDireccion(eq(id))).thenReturn(0);

        // then se elimina la direccion
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/direccion/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string("Direccion eliminada"));
    }
}