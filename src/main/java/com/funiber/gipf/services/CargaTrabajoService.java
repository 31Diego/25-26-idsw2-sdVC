package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.repositories.InvestigadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CargaTrabajoService {

    private final InvestigadorRepository investigadorRepository;

    public CargaTrabajoService(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    public List<Investigador> obtenerResumenCargaTrabajo() {
        return investigadorRepository.findAll();
    }

    public List<Investigador> filtrarCargaTrabajo(String criterio) {
        return investigadorRepository.buscarPorCriterio(criterio);
    }

    public Investigador obtenerCargaTrabajo(Long id) {
        return investigadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investigador no encontrado: " + id));
    }

    public Investigador guardarCargaTrabajo(Long id, Investigador datos) {
        Investigador investigador = obtenerCargaTrabajo(id);
        investigador.setDisponibilidad(datos.getDisponibilidad());
        investigador.setCargaTrabajo(datos.getCargaTrabajo());
        return investigadorRepository.save(investigador);
    }
}
