package com.funiber.gipf.services;

import com.funiber.gipf.models.Convocatoria;
import com.funiber.gipf.repositories.ConvocatoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConvocatoriaService {

    private final ConvocatoriaRepository convocatoriaRepository;

    public ConvocatoriaService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public List<Convocatoria> buscarPorCriterios(String q, String area, String estado) {
        return convocatoriaRepository.findAll().stream()
                .filter(c -> q == null || q.isBlank()
                        || c.getTitulo().toLowerCase().contains(q.toLowerCase()))
                .filter(c -> area == null || area.isBlank()
                        || area.equals(c.getArea()))
                .filter(c -> estado == null || estado.isBlank()
                        || estado.equals(c.getEstado()))
                .toList();
    }

    public Convocatoria obtenerPorId(Long id) {
        return convocatoriaRepository.findById(id).orElseThrow();
    }

    public void eliminar(Long id) {
        convocatoriaRepository.deleteById(id);
    }

    public Convocatoria guardar(String titulo, String area, String estado,
            LocalDate fechaApertura, LocalDate fechaCierre,
            String descripcion, String requisitos,
            String criteriosEvaluacion, String dotacion,
            String documentacion, String contacto) {
        Convocatoria c = new Convocatoria();
        c.setTitulo(titulo);
        c.setArea(area);
        c.setEstado(estado);
        c.setFechaApertura(fechaApertura);
        c.setFechaCierre(fechaCierre);
        c.setDescripcion(descripcion);
        c.setRequisitos(requisitos);
        c.setCriteriosEvaluacion(criteriosEvaluacion);
        c.setDotacion(dotacion);
        c.setDocumentacion(documentacion);
        c.setContacto(contacto);
        return convocatoriaRepository.save(c);
    }
}
