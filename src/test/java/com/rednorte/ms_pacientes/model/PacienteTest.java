package com.rednorte.ms_pacientes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PacienteTest {

    @Test
    void deberiaCrearPaciente() {
        Paciente paciente = new Paciente();

        paciente.setId(1L);
        paciente.setRut("20879762-k");
        paciente.setNombre("Pedro");
        paciente.setContacto("pedro@gmail.com");
        paciente.setHistorial("Sin antecedentes");

        assertEquals(1L, paciente.getId());
        assertEquals("20879762-k", paciente.getRut());
        assertEquals("Pedro", paciente.getNombre());
        assertEquals("pedro@gmail.com", paciente.getContacto());
        assertEquals("Sin antecedentes", paciente.getHistorial());
    }
}