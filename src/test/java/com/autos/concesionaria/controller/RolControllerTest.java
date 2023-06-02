package com.autos.concesionaria.controller;

import com.autos.concesionaria.entity.Rol;
import com.autos.concesionaria.service.RolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RolControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RolService rolService;


    @Test
    public void getRolesSucces() throws Exception {

        //create mocked entity
        List<Rol> testResponse = new ArrayList<>();
        testResponse.add(new Rol(1L, "vendedor"));


        //mock the response of the method in the service
        when(rolService.buscarRoles()).thenReturn(testResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/rol")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void guardarRol() throws Exception {
        //Create a mocked entity
        Rol rolResponse = new Rol(1L, "vendedor");

        //mock the response of the method in the service
        when(rolService.crearRol(rolResponse)).thenReturn(rolResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/rol").content(toJson(rolResponse))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private String toJson(Rol rolResponse) throws JsonProcessingException {
        return mapper.writeValueAsString(rolResponse);
    }

}
