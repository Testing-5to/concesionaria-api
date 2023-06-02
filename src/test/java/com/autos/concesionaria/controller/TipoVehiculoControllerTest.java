package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.TipoVehiculo;
import com.autos.concesionaria.service.ModeloService;
import com.autos.concesionaria.service.TipoVehiculoService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@WebMvcTest(TipoVehiculoController.class)
class TipoVehiculoControllerTest {

    @MockBean
    private TipoVehiculoService tipoVehiculoService;
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
    void getTiposVehiculos() throws Exception {
        // given
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setNombre("Nuevo tipo de vehiculo");
        List<TipoVehiculo> tiposVehiculos = new ArrayList<>();
        tiposVehiculos.add(tipoVehiculo);

        // when
        when(tipoVehiculoService.buscarTipoVehiculos()).thenReturn(tiposVehiculos);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/tipoVehiculo")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(tiposVehiculos)));
        // Con mapper.writeValueAsString(tiposVehiculos), se tranforma el objeto tiposVehiculos a un json.
        // Entonces, con la linea .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(tiposVehiculos)));
        // se espera que, el contenido de la respuesta del get, sea de tipo JSON y que ese JSON contenga la información de tiposVehiculos.
        }

    @Test
    void getTipoVehiculoPorId() throws Exception {
        // given
        Long id = 1L;
        TipoVehiculo tipoVehiculo = new TipoVehiculo(id,"Automovil");

        // when
        when(tipoVehiculoService.buscarTipoVehiculoPorId(id)).thenReturn(tipoVehiculo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tipoVehiculo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(tipoVehiculo)));

    }

    @Test
    void guardarTipoVehiculo() throws Exception {
        // given
        TipoVehiculo tipoVehiculo = new TipoVehiculo(1L,"Automovil");

        // when
        when(tipoVehiculoService.crearTipoVehiculo(any(TipoVehiculo.class))).thenReturn(tipoVehiculo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tipoVehiculo")
                        .content(mapper.writeValueAsString(tipoVehiculo))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(tipoVehiculo)));
    }

    @Test
    void actualizarTipoVehiculo() throws Exception {
        // given
        Long id = 1L;
        TipoVehiculo tipoVehiculo = new TipoVehiculo(id,"Automovil");

        // when
        when(tipoVehiculoService.actualizarTipoVehiculoPorId(eq(id), any(TipoVehiculo.class))).thenReturn(tipoVehiculo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tipoVehiculo/1")
                        .content(mapper.writeValueAsString(tipoVehiculo))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(tipoVehiculo)));
    }

    @Test
    void eliminarTipoVehiculo() throws Exception {
        // given
        Long id = 1L;

        // when modelo service devuelve 0 modelos asociadas al tipo de vehiculo
        when(modeloService.contarModelosPorTipoVehiculo(eq(id))).thenReturn(0);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tipoVehiculo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNoContent());

        // when modelo service devuelve 1 modelo asociado al tipo de vehiculo
        when(modeloService.contarModelosPorTipoVehiculo(eq(id))).thenReturn(1);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tipoVehiculo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}