package com.rednorte.ms_pacientes.controller;

import com.rednorte.ms_pacientes.dto.PacienteRequest;
import com.rednorte.ms_pacientes.dto.PacienteResponse;
import com.rednorte.ms_pacientes.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PacienteResponse> crearPaciente(
            @Valid @RequestBody PacienteRequest request) {

        return ResponseEntity.ok(service.crearPaciente(request));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> obtenerPacientes() {

        return ResponseEntity.ok(service.obtenerPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> obtenerPaciente(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.obtenerPacientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> actualizarPaciente(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequest request) {

        return ResponseEntity.ok(
                service.actualizarPaciente(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(
            @PathVariable Long id) {

        service.eliminarPaciente(id);

        return ResponseEntity.noContent().build();
    }
}