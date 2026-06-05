package com.funiber.gipf.controllers;

import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditarPerfilController {

    private final InvestigadorService investigadorService;

    public EditarPerfilController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping("/investigadores/{id}/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String getEditarPerfilInvestigador(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        model.addAttribute("esPropioPeril", false);
        return "editar-perfil";
    }

    @PostMapping("/investigadores/{id}/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String postEditarPerfilInvestigador(
            @PathVariable Long id,
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

    @GetMapping("/perfil/editar")
    public String getEditarPerfilPropio(Authentication authentication, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigadorPorUsername(authentication.getName()));
        model.addAttribute("esPropioPeril", true);
        return "editar-perfil";
    }

    @PostMapping("/perfil/editar")
    public String postEditarPerfilPropio(
            Authentication authentication,
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
}
