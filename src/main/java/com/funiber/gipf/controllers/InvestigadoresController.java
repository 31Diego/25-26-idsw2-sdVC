package com.funiber.gipf.controllers;

import com.funiber.gipf.services.InvestigadoresService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/investigadores")
public class InvestigadoresController {

    private final InvestigadoresService investigadoresService;

    public InvestigadoresController(InvestigadoresService investigadoresService) {
        this.investigadoresService = investigadoresService;
    }

    @GetMapping
    public String abrirInvestigadores(@RequestParam(required = false) String criterio, Model model) {
        if (criterio != null && !criterio.isBlank()) {
            model.addAttribute("investigadores", investigadoresService.filtrarInvestigadores(criterio));
            model.addAttribute("criterio", criterio);
        } else {
            model.addAttribute("investigadores", investigadoresService.obtenerInvestigadores());
        }
        return "investigadores";
    }
}
