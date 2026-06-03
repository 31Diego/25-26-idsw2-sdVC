package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Investigador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {
    Optional<Investigador> findByUsername(String username);

    @Query("SELECT i FROM Investigador i WHERE i.nombre LIKE %:criterio% OR i.apellidos LIKE %:criterio% OR i.campo LIKE %:criterio%")
    List<Investigador> buscarPorCriterio(@Param("criterio") String criterio);
}
