package com.funiber.gipf.services;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.repositories.InvestigadorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private final InvestigadorRepository investigadorRepository;

    public AutenticacionService(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Investigador investigador = investigadorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return new InvestigadorUserDetails(investigador);
    }
}
