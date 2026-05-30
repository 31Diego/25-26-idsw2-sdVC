package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvestigadorRepository extends JpaRepository<Investigador, Long> {
    Optional<Investigador> findByUsuario(Usuario usuario);

    @Query("SELECT i FROM Investigador i WHERE " +
           "(:criterio IS NULL OR i.nombre LIKE %:criterio% OR i.apellidos LIKE %:criterio% OR i.disponibilidad = :criterio)")
    List<Investigador> buscarPorCriterio(@Param("criterio") String criterio);
}
