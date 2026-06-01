package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos/{id}/editar")
public class EditarProyectoController {

    private final ProyectoService proyectoService;

    public EditarProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String mostrarFormulario(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(id));
        return "editar-proyecto";
    }

    @PostMapping
    public String guardarCambios(@PathVariable Long id, @ModelAttribute Proyecto datos) {
        proyectoService.actualizarProyecto(id, datos);
        return "redirect:/proyectos/" + id;
    }
}
