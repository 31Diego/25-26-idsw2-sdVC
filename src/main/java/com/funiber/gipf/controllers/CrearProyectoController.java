package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/nuevo")
public class CrearProyectoController {

    private final ProyectoService proyectoService;

    public CrearProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "crear-proyecto";
    }

    @PostMapping
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }
}
