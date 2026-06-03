package com.rednorte.ms_pacientes.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacienteRequestTest {

    @Test
    void deberiaCrearPacienteRequest() {
        PacienteRequest request = new PacienteRequest();

        request.setRut("20879762-k");
        request.setNombre("Pedro");
        request.setContacto("pedro@gmail.com");
        request.setHistorial("Sin antecedentes");

        assertEquals("20879762-k", request.getRut());
        assertEquals("Pedro", request.getNombre());
        assertEquals("pedro@gmail.com", request.getContacto());
        assertEquals("Sin antecedentes", request.getHistorial());
    }
}