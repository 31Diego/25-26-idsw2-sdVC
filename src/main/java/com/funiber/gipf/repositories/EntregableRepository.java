package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Entregable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregableRepository extends JpaRepository<Entregable, Long> {
    List<Entregable> findByProyectoId(Long proyectoId);
}
