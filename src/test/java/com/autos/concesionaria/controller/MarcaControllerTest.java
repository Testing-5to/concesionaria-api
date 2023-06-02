package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.service.MarcaService;
import com.autos.concesionaria.service.ModeloService;
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
@WebMvcTest(MarcaController.class)
class MarcaControllerTest {

    @MockBean
    private MarcaService marcaService;
    @MockBean
    private ModeloService modeloService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMarcas() throws Exception {
        //given
        Marca marca = new Marca();
        marca.setNombre("Audi");
        List<Marca> marcas = new ArrayList<>();
        marcas.add(marca);
        //when
        when(marcaService.getMarcas()).thenReturn(marcas);
        //then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/marca")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(marcas)));
    }

    @Test
    void getMarcaPorId() throws Exception {
        // given
        Long id = 1L;
        Marca marca = new Marca();
        marca.setId(id);
        marca.setNombre("Audi");
        // when
        when(marcaService.getMarca(id)).thenReturn(marca);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/marca/{id}",id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(marca)));
    }

    @Test
    void guardarMarca() throws Exception {
        // given
        Long id = 1L;
        Marca marca = new Marca();
        marca.setId(id);
        marca.setNombre("Audi");
        // when
        when(marcaService.crearMarca(any(Marca.class))).thenReturn(marca);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/marca")
                        .content(mapper.writeValueAsString(marca))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(marca)));
    }

    @Test
    void actualizarMarca() throws Exception {
        // given
        Long id = 1L;
        Marca marca = new Marca();
        marca.setId(id);
        marca.setNombre("Audi");
        // when
        when(marcaService.actualizarMarca(eq(id), any(Marca.class))).thenReturn(marca);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/marca/{id}", id)
                        .content(mapper.writeValueAsString(marca))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(marca)));
    }

    @Test
    void borrarMarca() throws Exception {
        // given
        Long idMarca = 1L;
        // when modelo service devuelve 0 modelos asociadas a la marca
        when(modeloService.contarModelosPorMarca(eq(idMarca))).thenReturn(0);
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/marca/{id}", idMarca)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNoContent())
                        .andExpect(MockMvcResultMatchers.content().string("Marca borrada: " + idMarca));

        // when modelo service devuelve 1 modelo asociado al tipo de vehiculo
        when(modeloService.contarModelosPorMarca(eq(idMarca))).thenReturn(1);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/marca/{id}", idMarca)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                        .andExpect(MockMvcResultMatchers.content().string("No se puede borrar la marca con id: " + idMarca + " porque tiene modelos asociados"));
    }
}