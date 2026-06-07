package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.repositories.InvestigadorRepository;
import com.funiber.gipf.repositories.ProyectoRepository;
import com.funiber.gipf.repositories.SolicitudEliminacionRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvestigadorService {

    private final InvestigadorRepository investigadorRepository;
    private final ProyectoRepository proyectoRepository;
    private final SolicitudEliminacionRepository solicitudEliminacionRepository;
    private final PasswordEncoder passwordEncoder;

    public InvestigadorService(InvestigadorRepository investigadorRepository,
            ProyectoRepository proyectoRepository,
            SolicitudEliminacionRepository solicitudEliminacionRepository,
            PasswordEncoder passwordEncoder) {
        this.investigadorRepository = investigadorRepository;
        this.proyectoRepository = proyectoRepository;
        this.solicitudEliminacionRepository = solicitudEliminacionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Investigador obtenerInvestigador(Long id) {
        return investigadorRepository.findById(id).orElseThrow();
    }

    public Investigador guardarInvestigador(Investigador investigador) {
        investigador.setPassword(passwordEncoder.encode(investigador.getPassword()));
        investigador.setRol(Rol.INVESTIGADOR);
        return investigadorRepository.save(investigador);
    }

    public void actualizarPerfil(Long id, String nombre, String apellidos, String campo, String carrera, String master,
            String username, String password) {
        Investigador inv = investigadorRepository.findById(id).orElseThrow();
        inv.setNombre(nombre);
        inv.setApellidos(apellidos);
        inv.setCampo(campo);
        inv.setCarrera(carrera);
        inv.setMaster(master);
        inv.setUsername(username);
        if (password != null && !password.isBlank()) {
            inv.setPassword(passwordEncoder.encode(password));
        }
        investigadorRepository.save(inv);
    }

    public void actualizarPerfilConRol(Long id, String nombre, String apellidos, String campo, String carrera,
            String master, Rol rol, String username, String password) {
        Investigador inv = investigadorRepository.findById(id).orElseThrow();
        inv.setNombre(nombre);
        inv.setApellidos(apellidos);
        inv.setCampo(campo);
        inv.setCarrera(carrera);
        inv.setMaster(master);
        inv.setRol(rol);
        inv.setUsername(username);
        if (password != null && !password.isBlank()) {
            inv.setPassword(passwordEncoder.encode(password));
        }
        investigadorRepository.save(inv);
    }

    public List<Investigador> obtenerTodosLosInvestigadores() {
        return investigadorRepository.findAll();
    }

    public List<Investigador> filtrarInvestigadores(String criterio) {
        return investigadorRepository.buscarPorCriterio(criterio);
    }

    public Investigador obtenerInvestigadorPorUsername(String username) {
        return investigadorRepository.findByUsername(username).orElseThrow();
    }

    public void cambiarRol(Long id) {
        Investigador inv = investigadorRepository.findById(id).orElseThrow();
        inv.setRol(inv.getRol() == Rol.COORDINADOR ? Rol.INVESTIGADOR : Rol.COORDINADOR);
        investigadorRepository.save(inv);
    }

    @Transactional
    public void eliminarPerfil(Long id) {
        for (Proyecto p : proyectoRepository.findAll()) {
            if (p.getInvestigadores().stream().anyMatch(i -> i.getId().equals(id))) {
                p.getInvestigadores().removeIf(i -> i.getId().equals(id));
                proyectoRepository.save(p);
            }
        }
        solicitudEliminacionRepository.deleteAll(solicitudEliminacionRepository.findByInvestigadorId(id));
        investigadorRepository.deleteById(id);
    }
}
