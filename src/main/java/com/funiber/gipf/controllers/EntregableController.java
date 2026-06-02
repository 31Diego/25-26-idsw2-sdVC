package com.funiber.gipf.controllers;

import com.funiber.gipf.services.EntregableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/{proyectoId}/entregables/{id}")
public class EntregableController {

    private final EntregableService entregableService;

    public EntregableController(EntregableService entregableService) {
        this.entregableService = entregableService;
    }

    @GetMapping
    public String abrirEntregable(@PathVariable Long proyectoId, @PathVariable Long id, Model model) {
        model.addAttribute("entregable", entregableService.obtenerEntregable(id));
        model.addAttribute("proyectoId", proyectoId);
        return "entregable";
    }
}
