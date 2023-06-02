package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.service.LocalidadService;
import com.autos.concesionaria.service.ProvinciaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ProvinciaController.class)
public class ProvinciaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProvinciaService provinciaService;

    @MockBean
    private LocalidadService localidadService;

    @Test
    public void testGetProvincias() throws Exception {
        // Mockear el servicio para devolver una lista de provincias
        List<Provincia> provincias = new ArrayList<>();
        provincias.add(new Provincia(1L, "Buenos Aires", null));
        provincias.add(new Provincia(2L, "Córdoba", null));
        when(provinciaService.buscarProvincias()).thenReturn(provincias);

        // Realizar la solicitud GET y verificar los resultados esperados
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/provincia")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(provincias)));
    }

    @Test
    public void testGetProvinciasPorPais() throws Exception {
        // Mockear el servicio para devolver una lista de provincias
        List<Provincia> provincias = new ArrayList<>();
        Pais argentina = new Pais(1L, "Argentina", "AR");
        provincias.add(new Provincia(1L, "Buenos Aires", argentina));
        provincias.add(new Provincia(2L, "Córdoba", argentina));
        when(provinciaService.buscarProvincias("Argentina")).thenReturn(provincias);

        // Realizar la solicitud GET y verificar los resultados esperados
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/provincia?pais=Argentina")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(provincias)));
    }

    @Test
    public void testGetProvincia() throws Exception {
        // Mockear el servicio para devolver una provincia
        Provincia provincia = new Provincia(1L, "Buenos Aires", null);
        when(provinciaService.buscarProvincia(1L)).thenReturn(provincia);

        // Realizar la solicitud GET y verificar los resultados esperados
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/provincia/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(provincia)));
    }



}