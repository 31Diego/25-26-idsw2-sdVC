package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.policies.AccesoCoordinador;
import com.funiber.gipf.policies.AccesoInvestigador;
import com.funiber.gipf.policies.ConsultaCoordinador;
import com.funiber.gipf.policies.ConsultaInvestigador;
import com.funiber.gipf.policies.PoliticaAcceso;
import com.funiber.gipf.policies.PoliticaConsulta;
import com.funiber.gipf.repositories.EntregableRepository;
import com.funiber.gipf.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EntregableRepository entregableRepository;
    private final Map<Rol, PoliticaAcceso> politicas;
    private final Map<Rol, PoliticaConsulta> consultas;

    public ProyectoService(ProyectoRepository proyectoRepository,
            EntregableRepository entregableRepository) {
        this.proyectoRepository = proyectoRepository;
        this.entregableRepository = entregableRepository;
        this.politicas = Map.of(
                Rol.COORDINADOR, new AccesoCoordinador(),
                Rol.INVESTIGADOR, new AccesoInvestigador());
        this.consultas = Map.of(
                Rol.COORDINADOR, new ConsultaCoordinador(proyectoRepository),
                Rol.INVESTIGADOR, new ConsultaInvestigador(proyectoRepository));
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

    public List<Proyecto> obtenerProyectosParaUsuario(Investigador investigador, String criterio) {
        return consultas.get(investigador.getRol()).obtener(investigador, criterio);
    }

    public boolean tieneAcceso(Proyecto proyecto, Investigador investigador) {
        return politicas.get(investigador.getRol()).tieneAcceso(proyecto, investigador);
    }
}
