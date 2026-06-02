package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Entregable;
import com.funiber.gipf.services.EntregableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/proyectos/{proyectoId}/entregables/nuevo")
public class CrearEntregableController {

    private final EntregableService entregableService;

    public CrearEntregableController(EntregableService entregableService) {
        this.entregableService = entregableService;
    }

    @GetMapping
    public String mostrarFormulario(@PathVariable Long proyectoId, Model model) {
        model.addAttribute("entregable", new Entregable());
        model.addAttribute("proyectoId", proyectoId);
        return "crear-entregable";
    }

    @PostMapping
    public String guardarEntregable(@PathVariable Long proyectoId,
                                    @ModelAttribute Entregable entregable,
                                    @RequestParam("archivo") MultipartFile archivo) throws Exception {
        entregableService.guardarEntregable(entregable, archivo, proyectoId);
        return "redirect:/proyectos/" + proyectoId + "/entregables";
    }
}
