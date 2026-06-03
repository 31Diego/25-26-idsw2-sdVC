package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/{id}")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String abrirProyecto(@PathVariable Long id,
                                 @AuthenticationPrincipal Investigador investigador,
                                 Model model) {
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        if ("INVESTIGADOR".equals(investigador.getRol()) && !proyecto.getInvestigadores().contains(investigador)) {
            return "redirect:/proyectos";
        }
        model.addAttribute("proyecto", proyecto);
        return "proyecto";
    }
}
