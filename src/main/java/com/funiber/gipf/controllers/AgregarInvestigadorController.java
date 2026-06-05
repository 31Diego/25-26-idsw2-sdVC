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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/proyectos/{id}/investigadores/agregar")
public class AgregarInvestigadorController {

    private final ProyectoService proyectoService;
    private final InvestigadorService investigadorService;

    public AgregarInvestigadorController(ProyectoService proyectoService, InvestigadorService investigadorService) {
        this.proyectoService = proyectoService;
        this.investigadorService = investigadorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarDisponibles(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        List<Investigador> disponibles = investigadorService.obtenerTodosLosInvestigadores().stream()
                .filter(inv -> !proyecto.getInvestigadores().contains(inv))
                .toList();
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("disponibles", disponibles);
        return "agregar-investigador";
    }

    @PostMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String agregarInvestigador(@PathVariable Long id, @RequestParam Long investigadorId) {
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        Investigador investigador = investigadorService.obtenerInvestigador(investigadorId);
        proyectoService.agregarInvestigador(proyecto, investigador);
        return "redirect:/proyectos/" + id;
    }
}
