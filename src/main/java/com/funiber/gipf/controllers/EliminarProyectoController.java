package com.funiber.gipf.controllers;

import com.funiber.gipf.services.ProyectoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/{id}/eliminar")
public class EliminarProyectoController {

    private final ProyectoService proyectoService;

    public EliminarProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String mostrarConfirmacion(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(id));
        return "eliminar-proyecto";
    }

    @PostMapping
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos";
    }
}
