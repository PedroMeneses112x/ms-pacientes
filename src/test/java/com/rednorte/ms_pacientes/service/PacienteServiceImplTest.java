package com.rednorte.ms_pacientes.service;

import com.rednorte.ms_pacientes.dto.PacienteRequest;
import com.rednorte.ms_pacientes.model.Paciente;
import com.rednorte.ms_pacientes.repository.PacienteRepository;
import com.rednorte.ms_pacientes.service.PacienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PacienteServiceImplTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deberiaCrearPacienteCorrectamente() {

        PacienteRequest request = new PacienteRequest();
        request.setRut("20879762-k");
        request.setNombre("Pedro");
        request.setContacto("pedro@gmail.com");
        request.setHistorial("Sin antecedentes");

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setRut(request.getRut());
        paciente.setNombre(request.getNombre());
        paciente.setContacto(request.getContacto());
        paciente.setHistorial(request.getHistorial());

        when(repository.save(any(Paciente.class)))
                .thenReturn(paciente);

        var response = service.crearPaciente(request);

        assertNotNull(response);
        assertEquals("Pedro", response.getNombre());

        verify(repository, times(1))
                .save(any(Paciente.class));
    }

    @Test
    void deberiaObtenerPacientes() {

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Pedro");

        when(repository.findAll())
                .thenReturn(List.of(paciente));

        var pacientes = service.obtenerPacientes();

        assertFalse(pacientes.isEmpty());
        assertEquals(1, pacientes.size());
    }

    @Test
    void deberiaObtenerPacientePorId() {

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Pedro");

        when(repository.findById(1L))
                .thenReturn(Optional.of(paciente));

        var response = service.obtenerPacientePorId(1L);

        assertEquals("Pedro", response.getNombre());
    }

    @Test
    void deberiaActualizarPaciente() {

        Paciente existente = new Paciente();
        existente.setId(1L);
        existente.setNombre("Pedro");

        PacienteRequest request = new PacienteRequest();
        request.setNombre("Pedro Actualizado");

        when(repository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(repository.save(any(Paciente.class)))
                .thenReturn(existente);

        var response = service.actualizarPaciente(1L, request);

        assertNotNull(response);
    }

    @Test
    void deberiaEliminarPaciente() {

        doNothing().when(repository).deleteById(1L);

        service.eliminarPaciente(1L);

        verify(repository, times(1))
                .deleteById(1L);
    }
}