package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.repositories.EntregableRepository;
import com.funiber.gipf.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EntregableRepository entregableRepository;

    public ProyectoService(ProyectoRepository proyectoRepository,
                           EntregableRepository entregableRepository) {
        this.proyectoRepository = proyectoRepository;
        this.entregableRepository = entregableRepository;
    }

    public Proyecto obtenerProyecto(Long id) {
        return proyectoRepository.findById(id).orElseThrow();
    }

    public Proyecto guardarProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto actualizarProyecto(Long id, Proyecto datos) {
        Proyecto proyecto = proyectoRepository.findById(id).orElseThrow();
        proyecto.setTitulo(datos.getTitulo());
        proyecto.setDescripcion(datos.getDescripcion());
        proyecto.setObjetivos(datos.getObjetivos());
        proyecto.setEstado(datos.getEstado());
        proyecto.setFechaInicio(datos.getFechaInicio());
        proyecto.setFechaFin(datos.getFechaFin());
        proyecto.setDocumentacion(datos.getDocumentacion());
        return proyectoRepository.save(proyecto);
    }

    @Transactional
    public void eliminarProyecto(Long id) {
        entregableRepository.deleteByProyectoId(id);
        proyectoRepository.deleteById(id);
    }

    public void agregarInvestigador(Proyecto proyecto, Investigador investigador) {
        proyecto.getInvestigadores().add(investigador);
        proyectoRepository.save(proyecto);
    }

    public void eliminarInvestigador(Proyecto proyecto, Investigador investigador) {
        proyecto.getInvestigadores().remove(investigador);
        proyectoRepository.save(proyecto);
    }
}
