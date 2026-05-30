package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.PerfilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EditarPerfilController {

    private final PerfilService perfilService;

    public EditarPerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/perfil/editar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("investigador", perfilService.obtenerPerfil());
        return "editar-perfil";
    }

    @PostMapping("/perfil")
    public String guardarPerfil(@ModelAttribute Investigador investigador) {
        perfilService.guardarPerfil(investigador);
        return "redirect:/perfil";
    }
}
