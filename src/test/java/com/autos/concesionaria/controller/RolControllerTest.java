package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.service.EmpleadoService;
import com.autos.concesionaria.service.RolService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(RolController.class)
public class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RolService rolService;

    @MockBean
    private EmpleadoService empleadoService;

    @Test
    public void testGetRoles() throws Exception {
        // Mockear el servicio para devolver una lista de países
        List<Rol> roles = new ArrayList<>();
        roles.add(new Rol(1L, "Administrador"));
        roles.add(new Rol(2L, "Usuario"));
        when(rolService.buscarRoles()).thenReturn(roles);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rol")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(roles)));
    }

    @Test
    public void testGetRol() throws Exception {
        // Mockear el servicio para devolver un rol
        Rol rol = new Rol(1L, "Administrador");
        when(rolService.buscarRolPorId(1L)).thenReturn(rol);

        // Realizar la solicitud GET y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rol/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Administrador"));
    }

    @Test
    public void testCrearRol() throws Exception {
        // Mockear el servicio para devolver un rol
        Rol rol = new Rol(1L, "Administrador");
        when(rolService.crearRol(any(Rol.class))).thenReturn(rol);

        // Realizar la solicitud POST y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rol")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(rol)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Administrador"));
    }

    @Test
    public void testActualizarRol() throws Exception {
        // Mockear el servicio para devolver un rol
        Rol rol = new Rol(1L, "Administrador");
        when(rolService.actualizarRolPorId(eq(1L), any(Rol.class))).thenReturn(rol);

        // Realizar la solicitud PUT y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/rol/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(rol)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Administrador"));
    }

    @Test
    public void testEliminarRol() throws Exception {
        // Mockear el servicio para devolver un rol
        Long rolId = 1L;

        // Mockear el servicio para devolver 0 empleados asociados al rol
        when(empleadoService.contarEmpleadosPorRol(eq(rolId))).thenReturn(0);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/rol/{id}", rolId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Rol borrado: " + rolId));

        // Mockear el servicio para devolver 1 empleado asociado al rol
        when(empleadoService.contarEmpleadosPorRol(eq(rolId))).thenReturn(1);

        // Realizar la solicitud DELETE y verificar los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/rol/{id}", rolId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("El rol no puede ser eliminado porque esta asignado a un empleado"));
    }

}
