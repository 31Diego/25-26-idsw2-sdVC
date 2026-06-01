package com.funiber.gipf.controllers;

import com.funiber.gipf.services.ProyectoService;
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
    public String abrirProyecto(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(id));
        return "proyecto";
    }
}
