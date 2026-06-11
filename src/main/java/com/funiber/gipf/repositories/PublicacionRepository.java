package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByAutor(Investigador autor);
}
