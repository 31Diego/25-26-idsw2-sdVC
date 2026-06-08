package com.funiber.gipf.policies;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.repositories.ProyectoRepository;

import java.util.List;

public class ConsultaCoordinador implements PoliticaConsulta {

    private final ProyectoRepository proyectoRepository;

    public ConsultaCoordinador(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<Proyecto> obtener(Investigador investigador, String criterio) {
        if (criterio != null && !criterio.isBlank()) {
            return proyectoRepository.buscarPorCriterio(criterio);
        }
        return proyectoRepository.findAll();
    }
}
