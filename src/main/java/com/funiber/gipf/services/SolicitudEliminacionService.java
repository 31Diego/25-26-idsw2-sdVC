package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.SolicitudEliminacion;
import com.funiber.gipf.repositories.SolicitudEliminacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SolicitudEliminacionService {

    private final SolicitudEliminacionRepository solicitudEliminacionRepository;

    public SolicitudEliminacionService(SolicitudEliminacionRepository solicitudEliminacionRepository) {
        this.solicitudEliminacionRepository = solicitudEliminacionRepository;
    }

    public List<SolicitudEliminacion> obtenerSolicitudes() {
        return solicitudEliminacionRepository.findAll();
    }

    public SolicitudEliminacion obtenerSolicitud(Long id) {
        return solicitudEliminacionRepository.findById(id).orElseThrow();
    }

    public SolicitudEliminacion crearSolicitud(Investigador investigador, String motivo) {
        SolicitudEliminacion s = new SolicitudEliminacion();
        s.setInvestigador(investigador);
        s.setMotivo(motivo);
        s.setFecha(LocalDate.now());
        s.setEstado("PENDIENTE");
        return solicitudEliminacionRepository.save(s);
    }

    public void denegarSolicitud(Long id) {
        solicitudEliminacionRepository.deleteById(id);
    }
}
