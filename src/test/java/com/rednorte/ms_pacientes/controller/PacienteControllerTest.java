package com.rednorte.ms_pacientes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rednorte.ms_pacientes.dto.PacienteRequest;
import com.rednorte.ms_pacientes.dto.PacienteResponse;
import com.rednorte.ms_pacientes.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deberiaCrearPaciente() throws Exception {

        PacienteRequest request = new PacienteRequest();
        request.setRut("20879762-k");
        request.setNombre("Pedro");
        request.setContacto("pedro@gmail.com");
        request.setHistorial("Sin antecedentes");

        PacienteResponse response = new PacienteResponse();
        response.setId(1L);
        response.setRut("20879762-k");
        response.setNombre("Pedro");
        response.setContacto("pedro@gmail.com");
        response.setHistorial("Sin antecedentes");

        when(service.crearPaciente(any(PacienteRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }

    @Test
    void deberiaObtenerPacientes() throws Exception {

        PacienteResponse response = new PacienteResponse();
        response.setId(1L);
        response.setRut("20879762-k");
        response.setNombre("Pedro");
        response.setContacto("pedro@gmail.com");
        response.setHistorial("Sin antecedentes");

        when(service.obtenerPacientes())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"));
    }

    @Test
    void deberiaObtenerPacientePorId() throws Exception {

        PacienteResponse response = new PacienteResponse();
        response.setId(1L);
        response.setRut("20879762-k");
        response.setNombre("Pedro");
        response.setContacto("pedro@gmail.com");
        response.setHistorial("Sin antecedentes");

        when(service.obtenerPacientePorId(1L))
                .thenReturn(response);

        mockMvc.perform(get("/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }

    @Test
    void deberiaActualizarPaciente() throws Exception {

        PacienteRequest request = new PacienteRequest();
        request.setRut("20879762-k");
        request.setNombre("Pedro Actualizado");
        request.setContacto("pedroactualizado@gmail.com");
        request.setHistorial("Sin antecedentes actualizados");

        PacienteResponse response = new PacienteResponse();
        response.setId(1L);
        response.setRut("20879762-k");
        response.setNombre("Pedro Actualizado");
        response.setContacto("pedroactualizado@gmail.com");
        response.setHistorial("Sin antecedentes actualizados");

        when(service.actualizarPaciente(any(Long.class), any(PacienteRequest.class)))
                .thenReturn(response);

        mockMvc.perform(put("/pacientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro Actualizado"));
    }

    @Test
    void deberiaEliminarPaciente() throws Exception {

        doNothing().when(service).eliminarPaciente(1L);

        mockMvc.perform(delete("/pacientes/1"))
                .andExpect(status().isNoContent());
    }
}