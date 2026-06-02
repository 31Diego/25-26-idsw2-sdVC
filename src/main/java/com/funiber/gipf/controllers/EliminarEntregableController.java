package com.funiber.gipf.controllers;

import com.funiber.gipf.services.EntregableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proyectos/{proyectoId}/entregables/{id}/eliminar")
public class EliminarEntregableController {

    private final EntregableService entregableService;

    public EliminarEntregableController(EntregableService entregableService) {
        this.entregableService = entregableService;
    }

    @GetMapping
    public String mostrarConfirmacion(@PathVariable Long proyectoId, @PathVariable Long id, Model model) {
        model.addAttribute("entregable", entregableService.obtenerEntregable(id));
        model.addAttribute("proyectoId", proyectoId);
        return "eliminar-entregable";
    }

    @PostMapping
    public String eliminarEntregable(@PathVariable Long proyectoId, @PathVariable Long id) throws Exception {
        entregableService.eliminarEntregable(id);
        return "redirect:/proyectos/" + proyectoId + "/entregables";
    }
}
