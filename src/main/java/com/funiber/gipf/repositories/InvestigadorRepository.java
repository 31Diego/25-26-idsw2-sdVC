package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Investigador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {
    Optional<Investigador> findByUsername(String username);
}
