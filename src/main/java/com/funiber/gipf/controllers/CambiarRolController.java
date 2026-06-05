package com.funiber.gipf.controllers;

import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investigadores/{id}/cambiar-rol")
public class CambiarRolController {

    private final InvestigadorService investigadorService;

    public CambiarRolController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String cambiarRol(@PathVariable Long id) {
        investigadorService.cambiarRol(id);
        return "redirect:/investigadores/" + id + "/opciones";
    }
}
