package com.funiber.gipf.controllers;

import com.funiber.gipf.services.InvestigadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investigadores/{id}")
public class InvestigadorController {

    private final InvestigadorService investigadorService;

    public InvestigadorController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping
    public String abrirInvestigador(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        return "investigador";
    }
}
