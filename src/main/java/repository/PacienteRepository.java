package com.rednorte.ms_pacientes.repository;

import com.rednorte.ms_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}