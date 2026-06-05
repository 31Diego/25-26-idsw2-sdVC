package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/investigadores/{id}/eliminar-perfil")
public class EliminarPerfilController {

    private final InvestigadorService investigadorService;

    public EliminarPerfilController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarConfirmacion(@PathVariable Long id,
                                      @AuthenticationPrincipal Investigador coordinador,
                                      Model model) {
        if (coordinador.getId().equals(id)) {
            return "redirect:/investigadores/" + id + "/opciones";
        }
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        return "eliminar-perfil";
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String eliminarPerfil(@PathVariable Long id,
                                 @AuthenticationPrincipal Investigador coordinador) {
        if (coordinador.getId().equals(id)) {
            return "redirect:/investigadores/" + id + "/opciones";
        }
        investigadorService.eliminarPerfil(id);
        return "redirect:/investigadores";
    }
}
