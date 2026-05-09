package com.rednorte.ms_pacientes.service;

import com.rednorte.ms_pacientes.dto.PacienteRequest;
import com.rednorte.ms_pacientes.dto.PacienteResponse;
import com.rednorte.ms_pacientes.model.Paciente;
import com.rednorte.ms_pacientes.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;

    public PacienteServiceImpl(PacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public PacienteResponse crearPaciente(PacienteRequest request) {

        Paciente paciente = new Paciente();

        paciente.setRut(request.getRut());
        paciente.setNombre(request.getNombre());
        paciente.setContacto(request.getContacto());
        paciente.setHistorial(request.getHistorial());

        Paciente guardado = repository.save(paciente);

        return convertirResponse(guardado);
    }

    @Override
    public List<PacienteResponse> obtenerPacientes() {
        return repository.findAll()
                .stream()
                .map(this::convertirResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteResponse obtenerPacientePorId(Long id) {

        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return convertirResponse(paciente);
    }

    @Override
    public PacienteResponse actualizarPaciente(Long id, PacienteRequest request) {

        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setRut(request.getRut());
        paciente.setNombre(request.getNombre());
        paciente.setContacto(request.getContacto());
        paciente.setHistorial(request.getHistorial());

        Paciente actualizado = repository.save(paciente);

        return convertirResponse(actualizado);
    }

    @Override
    public void eliminarPaciente(Long id) {
        repository.deleteById(id);
    }

    private PacienteResponse convertirResponse(Paciente paciente) {

        PacienteResponse response = new PacienteResponse();

        response.setId(paciente.getId());
        response.setRut(paciente.getRut());
        response.setNombre(paciente.getNombre());
        response.setContacto(paciente.getContacto());
        response.setHistorial(paciente.getHistorial());

        return response;
    }
}