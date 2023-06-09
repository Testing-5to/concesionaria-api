package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Modelo;
import com.autos.concesionaria.entity.Vehiculo;
import com.autos.concesionaria.entity.Venta;
import com.autos.concesionaria.service.VehiculoService;
import com.autos.concesionaria.service.VentaService;
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
@WebMvcTest(VehiculoController.class)
class VehiculoControllerTest {

    @MockBean
    private VehiculoService vehiculoService;
    @MockBean
    private VentaService ventaService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getVehiculos() throws Exception {
        // given
        Modelo modelo = new Modelo();
        modelo.setNombre("Audi");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setModelo(modelo);

        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo);
        // when
        when(vehiculoService.getVehiculos()).thenReturn(vehiculos);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/vehiculo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(vehiculos)));

        // when en el request param se envia un string de modelo
        when(vehiculoService.getVehiculosByModelo(any(String.class))).thenReturn(vehiculos);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/vehiculo?modelo={modelo}",modelo.getNombre())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(vehiculos)));
    }

    @Test
    void getVehiculoPorId() throws Exception {
        // given
        Long id = 1L;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(id);
        // when
        when(vehiculoService.getVehiculo(id)).thenReturn(vehiculo);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/vehiculo/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(vehiculo)));
    }

    @Test
    void guardarVehiculo() throws Exception {
        // given
        Long id = 1L;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(id);
        // when
        when(vehiculoService.crearVehiculo(any(Vehiculo.class))).thenReturn(vehiculo);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/vehiculo")
                        .content(mapper.writeValueAsString(vehiculo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(vehiculo)));
    }

    @Test
    void actualizarVehiculo() throws Exception {
        // given
        Long id = 1L;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(id);
        // when
        when(vehiculoService.actualizarVehiculo(eq(id),any(Vehiculo.class))).thenReturn(vehiculo);
        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/vehiculo/{id}",id)
                        .content(mapper.writeValueAsString(vehiculo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(vehiculo)));
    }

    @Test
    void borrarVehiculo() throws Exception {
        // given
        Long id = 1L;
        Venta venta = new Venta();
        List<Venta> ventas = new ArrayList<>();
        ventas.add(venta);

        // when venta service devuelve 1 vehiculo asociadas a venta
        when(ventaService.getVentasByVehiculo(eq(id))).thenReturn(ventas);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/vehiculo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No se puede borrar el vehiculo porque tiene ventas asociadas"));

        // when venta service devuelve 0 vehiculos asociadas a venta
        when(ventaService.getVentasByVehiculo(eq(id))).thenReturn(new ArrayList<>());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/vehiculo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vehiculo borrado"));

    }
}