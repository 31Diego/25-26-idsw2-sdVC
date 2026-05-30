package com.funiber.gipf.controllers;

import com.funiber.gipf.services.PerfilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpcionesPerfilController {

    private final PerfilService perfilService;

    public OpcionesPerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/perfil")
    public String abrirOpcionesPerfil(Model model) {
        model.addAttribute("investigador", perfilService.obtenerPerfil());
        return "opciones-perfil";
    }
}
