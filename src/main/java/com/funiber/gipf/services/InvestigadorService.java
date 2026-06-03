package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.repositories.InvestigadorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InvestigadorService {

    private final InvestigadorRepository investigadorRepository;
    private final PasswordEncoder passwordEncoder;

    public InvestigadorService(InvestigadorRepository investigadorRepository, PasswordEncoder passwordEncoder) {
        this.investigadorRepository = investigadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Investigador obtenerInvestigador(Long id) {
        return investigadorRepository.findById(id).orElseThrow();
    }

    public Investigador guardarInvestigador(Investigador investigador) {
        investigador.setPassword(passwordEncoder.encode(investigador.getPassword()));
        investigador.setRol("INVESTIGADOR");
        return investigadorRepository.save(investigador);
    }

    public void cambiarRol(Long id) {
        Investigador inv = investigadorRepository.findById(id).orElseThrow();
        inv.setRol("COORDINADOR".equals(inv.getRol()) ? "INVESTIGADOR" : "COORDINADOR");
        investigadorRepository.save(inv);
    }
}
