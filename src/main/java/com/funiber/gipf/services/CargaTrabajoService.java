package com.funiber.gipf.services;

import com.funiber.gipf.models.CargaTrabajo;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.repositories.CargaTrabajoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargaTrabajoService {

    private final CargaTrabajoRepository cargaTrabajoRepository;

    public CargaTrabajoService(CargaTrabajoRepository cargaTrabajoRepository) {
        this.cargaTrabajoRepository = cargaTrabajoRepository;
    }

    public CargaTrabajo obtenerOCrearPorInvestigador(Investigador investigador) {
        return cargaTrabajoRepository.findByInvestigadorId(investigador.getId())
                .orElseGet(() -> {
                    CargaTrabajo nueva = new CargaTrabajo();
                    nueva.setInvestigador(investigador);
                    return cargaTrabajoRepository.save(nueva);
                });
    }

    public void actualizar(Long id, double horasDocencia, double horasInvestigacion, double horasActividades) {
        CargaTrabajo carga = cargaTrabajoRepository.findById(id).orElseThrow();
        carga.setHorasDocencia(horasDocencia);
        carga.setHorasInvestigacion(horasInvestigacion);
        carga.setHorasActividades(horasActividades);
        cargaTrabajoRepository.save(carga);
    }

    public boolean excedeLimite(Investigador investigador) {
        return cargaTrabajoRepository.findByInvestigadorId(investigador.getId())
                .map(c -> c.getHorasDocencia() + c.getHorasInvestigacion() + c.getHorasActividades() >= 40.0)
                .orElse(false);
    }
}
