package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Pais;
import com.autos.concesionaria.service.MarcaService;
import com.autos.concesionaria.service.PaisService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(PaisController.class)
public class PaisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PaisService paisService;

    @MockBean
    private ProvinciaService provinciaService;

    @MockBean
    private MarcaService marcaService;

    @Test
    public void testGetPaises() throws Exception {
        // Mockear el servicio para devolver una lista de países
        List<Pais> paises = new ArrayList<>();
        paises.add(new Pais(1L, "Argentina", "AR"));
        paises.add(new Pais(2L, "Brasil", "BR"));
        when(paisService.buscarPaises()).thenReturn(paises);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pais")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(paises)));
    }

    @Test
    public void testGetPaisPorId() throws Exception {
        // Mockear el servicio para devolver un país
        Pais pais = new Pais(1L, "Argentina", "AR");
        when(paisService.buscarPaisPorId(1L)).thenReturn(pais);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pais/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(pais)));
    }

    @Test
    public void testGuardarPais() throws Exception {
        // Datos de ejemplo para el nuevo país
        Pais nuevoPais = new Pais(1L, "Argentina", "AR");

        // Mockear el servicio para devolver el nuevo país guardado
        when(paisService.crearPais(any(Pais.class))).thenReturn(nuevoPais);

        // Realizar la solicitud POST con el nuevo país y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(nuevoPais)))
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(nuevoPais)));
    }

    @Test
    public void testActualizarPais() throws Exception {
        // Datos de ejemplo para el país a actualizar
        Long paisId = 1L;
        Pais paisActualizado = new Pais(paisId, "Argentina", "AR");

        // Mockear el servicio para devolver el país actualizado
        when(paisService.actualizarPaisPorId(eq(paisId), any(Pais.class))).thenReturn(paisActualizado);

        // Realizar la solicitud PUT con el país actualizado y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/pais/{id}", paisId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(paisActualizado)))
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(paisActualizado)));
    }

    @Test
    public void testBorrarPais() throws Exception {
        // Datos de ejemplo para el país a borrar
        Long paisId = 1L;

        // Mockear el servicio para devolver 0 provincias y 0 marcas asociadas al país
        when(provinciaService.contarProvincias(eq(paisId))).thenReturn(0);
        when(marcaService.contarMarcasPorPais(eq(paisId))).thenReturn(0);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pais/{id}", paisId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Pais borrado: " + paisId));

        // Verificar que el método para borrar el país se haya llamado una vez
        verify(paisService, times(1)).borrarPaisPorId(eq(paisId));
    }

    @Test
    public void testBorrarPaisConProvincias() throws Exception {
        // Datos de ejemplo para el país a borrar
        Long paisId = 1L;

        // Mockear el servicio para devolver 1 provincia asociada al país
        when(provinciaService.contarProvincias(eq(paisId))).thenReturn(1);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pais/{id}", paisId))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede borrar el pais con id: " + paisId + " porque tiene provincias asociadas"));

        // Verificar que el método para borrar el país no se haya llamado
        verify(paisService, times(0)).borrarPaisPorId(eq(paisId));
    }

    @Test
    public void testBorrarPaisConMarcas() throws Exception {
        // Datos de ejemplo para el país a borrar
        Long paisId = 1L;

        // Mockear el servicio para devolver 1 marca asociada al país
        when(marcaService.contarMarcasPorPais(eq(paisId))).thenReturn(1);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pais/{id}", paisId))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede borrar el pais con id: " + paisId + " porque tiene marcas asociadas"));

        // Verificar que el método para borrar el país no se haya llamado
        verify(paisService, times(0)).borrarPaisPorId(eq(paisId));
    }

    @Test
    public void testBorrarPaisConMarcasYProvincias() throws Exception {
        // Datos de ejemplo para el país a borrar
        Long paisId = 1L;

        // Mockear el servicio para devolver 1 provincia y 1 marca asociadas al país
        when(provinciaService.contarProvincias(eq(paisId))).thenReturn(1);
        when(marcaService.contarMarcasPorPais(eq(paisId))).thenReturn(1);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pais/{id}", paisId))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede borrar el pais con id: " + paisId + " porque tiene provincias y marcas asociadas"));

        // Verificar que el método para borrar el país no se haya llamado
        verify(paisService, times(0)).borrarPaisPorId(eq(paisId));
    }

}
