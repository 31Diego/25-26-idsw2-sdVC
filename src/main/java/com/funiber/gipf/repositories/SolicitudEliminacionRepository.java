package com.funiber.gipf.repositories;

import com.funiber.gipf.models.SolicitudEliminacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudEliminacionRepository extends JpaRepository<SolicitudEliminacion, Long> {
    List<SolicitudEliminacion> findByInvestigadorId(Long investigadorId);
}
