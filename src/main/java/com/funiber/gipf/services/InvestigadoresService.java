package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.repositories.InvestigadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestigadoresService {

    private final InvestigadorRepository investigadorRepository;

    public InvestigadoresService(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    public List<Investigador> obtenerInvestigadores() {
        return investigadorRepository.findAll();
    }

    public List<Investigador> filtrarInvestigadores(String criterio) {
        return investigadorRepository.buscarPorCriterio(criterio);
    }
}
