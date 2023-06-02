package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Localidad;
import com.autos.concesionaria.entity.Provincia;
import com.autos.concesionaria.service.DireccionService;
import com.autos.concesionaria.service.LocalidadService;
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
@WebMvcTest(LocalidadController.class)
public class LocalidadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private LocalidadService localidadService;

    @MockBean
    private DireccionService direccionService;

    @Test
    public void testGetLocalidades() throws Exception {
        // Mockear el servicio para devolver una lista de países
        List<Localidad> localidades = new ArrayList<>();
        localidades.add(new Localidad(1L, "Villa María", 5900, null));
        localidades.add(new Localidad(2L, "Córdoba", 5000, null));
        when(localidadService.buscarLocalidades()).thenReturn(localidades);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/localidad")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(localidades)));

        // Mockear el servicio para devolver una lista de países de una provincia
        List<Localidad> localidadesProvincia = new ArrayList<>();
        Provincia cordoba = new Provincia(1L, "Córdoba", null);
        localidadesProvincia.add(new Localidad(1L, "Villa María", 5900, cordoba));
        localidadesProvincia.add(new Localidad(2L, "Córdoba", 5000, cordoba));
        when(localidadService.buscarLocalidades(eq(cordoba.getNombre()))).thenReturn(localidadesProvincia);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/localidad?provincia={provincia}", cordoba.getNombre())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(localidadesProvincia)));
    }

    @Test
    public void testGetLocalidad() throws Exception {
        // Mockear el servicio para devolver un rol
        Localidad localidad = new Localidad(1L, "Villa María", 5900, null);
        when(localidadService.buscarLocalidad(1L)).thenReturn(localidad);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/localidad/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(localidad)));
    }

    @Test
    public void testCrearLocalidad() throws Exception {
        // Mockear el servicio para devolver un rol
        Localidad localidad = new Localidad(1L, "Villa María", 5900, null);
        when(localidadService.crearLocalidad(any(Localidad.class))).thenReturn(localidad);

        // Realizar la solicitud POST y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/localidad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(localidad)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(localidad)));

    }

    @Test
    public void testActualizarLocalidad() throws Exception {
        // Mockear el servicio para devolver un rol
        Localidad localidad = new Localidad(1L, "Villa María", 5900, null);
        when(localidadService.actualizarLocalidad(eq(1L), any(Localidad.class))).thenReturn(localidad);

        // Realizar la solicitud PUT y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/localidad/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(localidad)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(localidad)));
    }

    @Test
    public void testEliminarLocalidad() throws Exception {
        // Mockear el servicio para devolver un rol
        Long localidadId = 1L;

        // Mockear el servicio para devolver 0 direcciones asociadas a la localidad
        when(direccionService.contarDireccionesPorLocalidad(eq(localidadId))).thenReturn(0);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/localidad/{id}", localidadId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Localidad eliminada: " + localidadId));

        // Mockear el servicio para devolver 1 dirección asociada a la localidad
        when(direccionService.contarDireccionesPorLocalidad(eq(localidadId))).thenReturn(1);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/localidad/{id}", localidadId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede eliminar la localidad con id " + localidadId + " porque tiene direcciones asociadas"));
    }

}
