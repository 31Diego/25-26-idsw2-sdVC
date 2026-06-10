package com.funiber.gipf.repositories;

import com.funiber.gipf.models.CargaTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargaTrabajoRepository extends JpaRepository<CargaTrabajo, Long> {
    Optional<CargaTrabajo> findByInvestigadorId(Long investigadorId);
}
