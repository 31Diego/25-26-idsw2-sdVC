package com.funiber.gipf.controllers;

import com.funiber.gipf.services.EntregableService;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/{proyectoId}/entregables")
public class EntregablesController {

    private final EntregableService entregableService;
    private final ProyectoService proyectoService;

    public EntregablesController(EntregableService entregableService, ProyectoService proyectoService) {
        this.entregableService = entregableService;
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String abrirEntregables(@PathVariable Long proyectoId, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(proyectoId));
        model.addAttribute("entregables", entregableService.obtenerEntregablesDeProyecto(proyectoId));
        return "entregables";
    }
}
