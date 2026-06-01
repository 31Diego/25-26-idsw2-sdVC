package com.funiber.gipf.services;

import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectosService {

    private final ProyectoRepository proyectoRepository;

    public ProyectosService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> obtenerProyectos() {
        return proyectoRepository.findAll();
    }

    public List<Proyecto> filtrarProyectos(String criterio) {
        return proyectoRepository.buscarPorCriterio(criterio);
    }
}
