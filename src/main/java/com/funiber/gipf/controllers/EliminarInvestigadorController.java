package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.services.InvestigadorService;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/{pId}/investigadores/{iId}/eliminar")
public class EliminarInvestigadorController {

    private final ProyectoService proyectoService;
    private final InvestigadorService investigadorService;

    public EliminarInvestigadorController(ProyectoService proyectoService, InvestigadorService investigadorService) {
        this.proyectoService = proyectoService;
        this.investigadorService = investigadorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarConfirmacion(@PathVariable Long pId, @PathVariable Long iId, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(pId));
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(iId));
        return "eliminar-investigador";
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String confirmarEliminacion(@PathVariable Long pId, @PathVariable Long iId) {
        Proyecto proyecto = proyectoService.obtenerProyecto(pId);
        Investigador investigador = investigadorService.obtenerInvestigador(iId);
        proyectoService.eliminarInvestigador(proyecto, investigador);
        return "redirect:/proyectos/" + pId;
    }
}
