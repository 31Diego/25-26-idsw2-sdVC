package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investigadores/nuevo")
public class CrearInvestigadorController {

    private final InvestigadorService investigadorService;

    public CrearInvestigadorController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarFormulario(Model model) {
        model.addAttribute("investigador", new Investigador());
        return "crear-investigador";
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String guardarInvestigador(@ModelAttribute Investigador investigador) {
        Investigador guardado = investigadorService.guardarInvestigador(investigador);
        return "redirect:/investigadores/" + guardado.getId();
    }
}
