package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Convocatoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, Long> {

    @Query("SELECT c FROM Convocatoria c WHERE " +
           "(:texto IS NULL OR c.titulo LIKE %:texto%) AND " +
           "(:area IS NULL OR c.area = :area) AND " +
           "(:estado IS NULL OR c.estado = :estado)")
    List<Convocatoria> buscarPorCriterio(
            @Param("texto") String texto,
            @Param("area") String area,
            @Param("estado") String estado
    );
}
