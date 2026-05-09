package com.rednorte.ms_pacientes.controller;

import com.rednorte.ms_pacientes.dto.PacienteRequest;
import com.rednorte.ms_pacientes.dto.PacienteResponse;
import com.rednorte.ms_pacientes.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public PacienteResponse crearPaciente(@RequestBody PacienteRequest request) {
        return service.crearPaciente(request);
    }

    @GetMapping
    public List<PacienteResponse> obtenerPacientes() {
        return service.obtenerPacientes();
    }

    @GetMapping("/{id}")
    public PacienteResponse obtenerPaciente(@PathVariable Long id) {
        return service.obtenerPacientePorId(id);
    }

    @PutMapping("/{id}")
    public PacienteResponse actualizarPaciente(
            @PathVariable Long id,
            @RequestBody PacienteRequest request) {

        return service.actualizarPaciente(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) {
        service.eliminarPaciente(id);
    }
}