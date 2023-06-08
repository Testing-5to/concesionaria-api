package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Marca;
import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.service.ModeloService;
import com.autos.concesionaria.service.VehiculoService;
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
@WebMvcTest(ModeloController.class)
public class ModeloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ModeloService modeloService;

    @MockBean
    private VehiculoService vehiculoService;

    @Test
    public void testGetModelos() throws Exception {
        // given
        List<Modelo> modelos = new ArrayList<>();
        modelos.add(new Modelo(1L, "Modelo 1", null, null));
        modelos.add(new Modelo(2L, "Modelo 2", null, null));

        // when
        when(modeloService.getModelos()).thenReturn(modelos);

        // then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/modelo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelos)));
    }

    @Test
    public void testGetModelosByMarca() throws Exception {
        // given
        Marca marca = new Marca(1L, "Marca 1", null);
        List<Modelo> modelos = new ArrayList<>();
        modelos.add(new Modelo(1L, "Modelo 1", marca, null));
        modelos.add(new Modelo(2L, "Modelo 2", marca, null));


        // when
        when(modeloService.getModelosByMarca(eq("Marca 1"))).thenReturn(modelos);

        // then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/modelo?marca={nombre}", "Marca 1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelos)));
    }

    @Test
    public void testGetModelosByTipoVehiculo() throws Exception {
        // given
        TipoVehiculo tipoVehiculo = new TipoVehiculo(1L, "Tipo 1");
        List<Modelo> modelos = new ArrayList<>();
        modelos.add(new Modelo(1L, "Modelo 1", null, tipoVehiculo));
        modelos.add(new Modelo(2L, "Modelo 2", null, tipoVehiculo));

        // when
        when(modeloService.getModelosByTipoVehiculo(eq("Tipo 1"))).thenReturn(modelos);

        // then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/modelo?tipoVehiculo={nombre}", "Tipo 1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelos)));
    }

    @Test
    public void testGetModelosByMarcaAndTipoVehiculo() throws Exception {
        // given
        Marca marca = new Marca(1L, "Marca 1", null);
        TipoVehiculo tipoVehiculo = new TipoVehiculo(1L, "Tipo 1");
        List<Modelo> modelos = new ArrayList<>();
        modelos.add(new Modelo(1L, "Modelo 1", marca, tipoVehiculo));
        modelos.add(new Modelo(2L, "Modelo 2", marca, tipoVehiculo));

        // when
        when(modeloService.getModelosByMarcaAndTipoVehiculo(eq("Marca 1"), eq("Tipo 1"))).thenReturn(modelos);

        // then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/modelo?marca={marca}&tipoVehiculo={tipoVehiculo}", "Marca 1", "Tipo 1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelos)));
    }

    @Test
    public void testGetModeloById() throws Exception {
        // given
        Modelo modelo = new Modelo(1L, "Modelo 1", null, null);

        // when
        when(modeloService.getModelo(eq(1L))).thenReturn(modelo);

        // then
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/modelo/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelo)));
    }

    @Test
    public void testCreateModelo() throws Exception {
        // given
        Modelo modelo = new Modelo(1L, "Modelo 1", null, null);

        // when
        when(modeloService.crearModelo(any(Modelo.class))).thenReturn(modelo);

        // then
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/modelo")
                .content(mapper.writeValueAsString(modelo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelo)));
    }

    @Test
    public void testUpdateModelo() throws Exception {
        // given
        Long id = 1L;
        Modelo modelo = new Modelo(id, "Modelo 1", null, null);

        // when
        when(modeloService.actualizarModelo(eq(id), any(Modelo.class))).thenReturn(modelo);

        // then
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/modelo/{id}", id)
                        .content(mapper.writeValueAsString(modelo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(modelo)));
    }

    @Test
    public void testDeleteModelo() throws Exception {
        // given
        Long id = 1L;

        // when
        when(vehiculoService.countVehiculosByModelo(eq(id))).thenReturn(0).thenReturn(1);

        // then
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/modelo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/modelo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
