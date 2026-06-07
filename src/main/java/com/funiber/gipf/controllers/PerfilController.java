package com.funiber.gipf.controllers;

import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PerfilController {

    private final InvestigadorService investigadorService;

    public PerfilController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping("/perfil/opciones")
    public String abrirOpcionesPerfilPropio(Authentication authentication, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigadorPorUsername(authentication.getName()));
        model.addAttribute("esPropioPeril", true);
        return "opciones-perfil";
    }

    @GetMapping("/investigadores/{id}/opciones")
    public String abrirOpcionesPerfilInvestigador(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        model.addAttribute("esPropioPeril", false);
        return "opciones-perfil";
    }

    @GetMapping("/perfil/editar")
    public String mostrarFormularioEditarPropio(Authentication authentication, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigadorPorUsername(authentication.getName()));
        model.addAttribute("esPropioPeril", true);
        return "editar-perfil";
    }

    @PostMapping("/perfil/editar")
    public String guardarEditarPropio(Authentication authentication,
                                      @RequestParam String nombre,
                                      @RequestParam String apellidos,
                                      @RequestParam String campo,
                                      @RequestParam String carrera,
                                      @RequestParam String master,
                                      @RequestParam String username,
                                      @RequestParam(required = false) String password,
                                      @RequestParam(required = false) String rol) {
        Long id = investigadorService.obtenerInvestigadorPorUsername(authentication.getName()).getId();
        if (rol != null && !rol.isBlank()) {
            investigadorService.actualizarPerfilConRol(id, nombre, apellidos, campo, carrera, master, rol, username, password);
        } else {
            investigadorService.actualizarPerfil(id, nombre, apellidos, campo, carrera, master, username, password);
        }
        return "redirect:/perfil/opciones";
    }

    @GetMapping("/investigadores/{id}/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarFormularioEditarInvestigador(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        model.addAttribute("esPropioPeril", false);
        return "editar-perfil";
    }

    @PostMapping("/investigadores/{id}/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String guardarEditarInvestigador(@PathVariable Long id,
                                            @RequestParam String nombre,
                                            @RequestParam String apellidos,
                                            @RequestParam String campo,
                                            @RequestParam String carrera,
                                            @RequestParam String master,
                                            @RequestParam String rol,
                                            @RequestParam String username,
                                            @RequestParam(required = false) String password) {
        investigadorService.actualizarPerfilConRol(id, nombre, apellidos, campo, carrera, master, rol, username, password);
        return "redirect:/investigadores/" + id + "/opciones";
    }

    @PostMapping("/investigadores/{id}/cambiar-rol")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String cambiarRol(@PathVariable Long id) {
        investigadorService.cambiarRol(id);
        return "redirect:/investigadores/" + id + "/opciones";
    }
}
