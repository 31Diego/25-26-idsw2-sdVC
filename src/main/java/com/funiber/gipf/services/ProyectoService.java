package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.repositories.EntregableRepository;
import com.funiber.gipf.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Proyecto> obtenerProyectos() {
        return proyectoRepository.findAll();
    }

    public List<Proyecto> filtrarProyectos(String criterio) {
        return proyectoRepository.buscarPorCriterio(criterio);
    }

    public List<Proyecto> obtenerProyectosDeInvestigador(Investigador investigador) {
        return proyectoRepository.findByInvestigadoresContaining(investigador);
    }

    public List<Proyecto> filtrarProyectosDeInvestigador(Investigador investigador, String criterio) {
        return proyectoRepository.buscarPorCriterioEInvestigador(investigador, criterio);
    }

    public List<Proyecto> obtenerProyectosParaUsuario(Investigador investigador, String criterio) {
        boolean esCoordinador = investigador.getRol() == Rol.COORDINADOR;
        if (criterio != null && !criterio.isBlank()) {
            return esCoordinador
                    ? filtrarProyectos(criterio)
                    : filtrarProyectosDeInvestigador(investigador, criterio);
        }
        return esCoordinador
                ? obtenerProyectos()
                : obtenerProyectosDeInvestigador(investigador);
    }

    public boolean tieneAcceso(Proyecto proyecto, Investigador investigador) {
        if (investigador.getRol() == Rol.COORDINADOR) return true;
        return proyecto.getInvestigadores().stream()
                .anyMatch(inv -> inv.getId().equals(investigador.getId()));
    }
}
