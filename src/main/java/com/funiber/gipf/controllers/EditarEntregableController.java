package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Entregable;
import com.funiber.gipf.services.EntregableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/proyectos/{proyectoId}/entregables/{id}/editar")
public class EditarEntregableController {

    private final EntregableService entregableService;

    public EditarEntregableController(EntregableService entregableService) {
        this.entregableService = entregableService;
    }

    @GetMapping
    public String mostrarFormulario(@PathVariable Long proyectoId, @PathVariable Long id, Model model) {
        model.addAttribute("entregable", entregableService.obtenerEntregable(id));
        model.addAttribute("proyectoId", proyectoId);
        return "editar-entregable";
    }

    @PostMapping
    public String guardarCambios(@PathVariable Long proyectoId,
                                 @PathVariable Long id,
                                 @ModelAttribute Entregable datos,
                                 @RequestParam("archivo") MultipartFile archivo) throws Exception {
        entregableService.actualizarEntregable(id, datos, archivo);
        return "redirect:/proyectos/" + proyectoId + "/entregables/" + id;
    }
}
