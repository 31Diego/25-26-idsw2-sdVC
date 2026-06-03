package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    @Query("SELECT p FROM Proyecto p WHERE p.titulo LIKE %:criterio% OR p.descripcion LIKE %:criterio%")
    List<Proyecto> buscarPorCriterio(@Param("criterio") String criterio);

    List<Proyecto> findByInvestigadoresContaining(Investigador investigador);

    @Query("SELECT p FROM Proyecto p JOIN p.investigadores i WHERE i = :investigador AND (p.titulo LIKE %:criterio% OR p.descripcion LIKE %:criterio%)")
    List<Proyecto> buscarPorCriterioEInvestigador(@Param("investigador") Investigador investigador, @Param("criterio") String criterio);
}
