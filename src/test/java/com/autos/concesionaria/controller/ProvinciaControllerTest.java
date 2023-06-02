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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    public void testCrearProvincia() throws Exception {
        // Mockear el servicio para devolver una provincia
        Provincia provincia = new Provincia(1L, "Buenos Aires", null);
        when(provinciaService.crearProvincia(any(Provincia.class))).thenReturn(provincia);

        // Realizar la solicitud POST y verificar los resultados esperados
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/provincia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(provincia)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(provincia)));
    }

    @Test
    public void testActualizarProvincia() throws Exception {
        // Mockear el servicio para devolver una provincia
        Long id = 1L;
        Provincia provincia = new Provincia(id, "Buenos Aires", null);
        when(provinciaService.actualizarProvincia(eq(id), any(Provincia.class))).thenReturn(provincia);

        // Realizar la solicitud PUT y verificar los resultados esperados
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/provincia/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(provincia)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(provincia)));
    }

    @Test
    public void testEliminarProvincia() throws Exception {
        // Mockear el servicio para devolver una provincia
        Long id = 1L;
        when(localidadService.contarLocalidadesPorProvincia(id)).thenReturn(0).thenReturn(1);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/provincia/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Provincia eliminada"));

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/provincia/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede eliminar la provincia porque tiene localidades asociadas"));
    }

}