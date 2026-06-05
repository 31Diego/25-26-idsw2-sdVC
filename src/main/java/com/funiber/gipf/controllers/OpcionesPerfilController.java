package com.funiber.gipf.controllers;

import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OpcionesPerfilController {

    private final InvestigadorService investigadorService;

    public OpcionesPerfilController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping("/investigadores/{id}/opciones")
    public String abrirOpcionesPerfilInvestigador(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        model.addAttribute("esPropioPeril", false);
        return "opciones-perfil";
    }

    @GetMapping("/perfil/opciones")
    public String abrirOpcionesPerfilPropio(Authentication authentication, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigadorPorUsername(authentication.getName()));
        model.addAttribute("esPropioPeril", true);
        return "opciones-perfil";
    }
}
