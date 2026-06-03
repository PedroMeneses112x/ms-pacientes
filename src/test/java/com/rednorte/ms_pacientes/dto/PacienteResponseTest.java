package com.rednorte.ms_pacientes.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacienteResponseTest {

    @Test
    void deberiaCrearPacienteResponse() {
        PacienteResponse response = new PacienteResponse();

        response.setId(1L);
        response.setRut("20879762-k");
        response.setNombre("Pedro");
        response.setContacto("pedro@gmail.com");
        response.setHistorial("Sin antecedentes");

        assertEquals(1L, response.getId());
        assertEquals("20879762-k", response.getRut());
        assertEquals("Pedro", response.getNombre());
        assertEquals("pedro@gmail.com", response.getContacto());
        assertEquals("Sin antecedentes", response.getHistorial());
    }
}