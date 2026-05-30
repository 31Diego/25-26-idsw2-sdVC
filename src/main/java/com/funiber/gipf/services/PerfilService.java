package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Usuario;
import com.funiber.gipf.repositories.InvestigadorRepository;
import com.funiber.gipf.repositories.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final InvestigadorRepository investigadorRepository;
    private final UsuarioRepository usuarioRepository;

    public PerfilService(InvestigadorRepository investigadorRepository,
                         UsuarioRepository usuarioRepository) {
        this.investigadorRepository = investigadorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Investigador obtenerPerfil() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        return investigadorRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para: " + username));
    }

    public Investigador guardarPerfil(Investigador datos) {
        Investigador perfil = obtenerPerfil();
        perfil.setNombre(datos.getNombre());
        perfil.setApellidos(datos.getApellidos());
        perfil.setEmail(datos.getEmail());
        perfil.setEspecializacion(datos.getEspecializacion());
        perfil.setInstitucion(datos.getInstitucion());
        perfil.setIntereses(datos.getIntereses());
        perfil.setExperiencia(datos.getExperiencia());
        return investigadorRepository.save(perfil);
    }
}
