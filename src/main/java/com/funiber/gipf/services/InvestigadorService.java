package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.repositories.InvestigadorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvestigadorService {

    private final InvestigadorRepository investigadorRepository;
    private final ProyectoService proyectoService;
    private final SolicitudEliminacionService solicitudEliminacionService;
    private final PasswordEncoder passwordEncoder;

    public InvestigadorService(InvestigadorRepository investigadorRepository,
            ProyectoService proyectoService,
            SolicitudEliminacionService solicitudEliminacionService,
            PasswordEncoder passwordEncoder) {
        this.investigadorRepository = investigadorRepository;
        this.proyectoService = proyectoService;
        this.solicitudEliminacionService = solicitudEliminacionService;
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
            Rol rol, String username, String password) {
        Investigador inv = investigadorRepository.findById(id).orElseThrow();
        inv.setNombre(nombre);
        inv.setApellidos(apellidos);
        inv.setCampo(campo);
        inv.setCarrera(carrera);
        inv.setMaster(master);
        if (rol != null) {
            inv.setRol(rol);
        }
        inv.setUsername(username);
        if (password != null && !password.isBlank()) {
            inv.setPassword(passwordEncoder.encode(password));
        }
        investigadorRepository.save(inv);
    }

    public List<Investigador> obtenerTodos() {
        return investigadorRepository.findAll();
    }

    public List<Investigador> obtenerInvestigadores(String criterio) {
        if (criterio == null || criterio.isBlank()) {
            return investigadorRepository.findAll();
        }
        return investigadorRepository.buscarPorCriterio(criterio);
    }

    public List<Investigador> obtenerNoMiembros(Proyecto proyecto) {
        List<Long> miembroIds = proyecto.getInvestigadores().stream()
                .map(Investigador::getId)
                .toList();
        return investigadorRepository.findAll().stream()
                .filter(inv -> !miembroIds.contains(inv.getId()))
                .toList();
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
    public boolean eliminarPerfil(Long actorId, Long targetId) {
        if (actorId.equals(targetId)) {
            return false;
        }
        proyectoService.eliminarInvestigadorDeTodosLosProyectos(targetId);
        solicitudEliminacionService.eliminarPorInvestigador(targetId);
        investigadorRepository.deleteById(targetId);
        return true;
    }
}
