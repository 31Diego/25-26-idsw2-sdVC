package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Entregable;
import com.funiber.gipf.services.EntregableService;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EntregableController {

    private final EntregableService entregableService;
    private final ProyectoService proyectoService;

    public EntregableController(EntregableService entregableService,
            ProyectoService proyectoService) {
        this.entregableService = entregableService;
        this.proyectoService = proyectoService;
    }

    @GetMapping("/proyectos/{proyectoId}/entregables")
    public String abrirEntregables(@PathVariable Long proyectoId, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(proyectoId));
        model.addAttribute("entregables", entregableService.obtenerEntregablesDeProyecto(proyectoId));
        return "entregables";
    }

    @GetMapping("/proyectos/{proyectoId}/entregables/nuevo")
    public String mostrarFormularioCrear(@PathVariable Long proyectoId, Model model) {
        model.addAttribute("entregable", new Entregable());
        model.addAttribute("proyectoId", proyectoId);
        return "crear-entregable";
    }

    @PostMapping("/proyectos/{proyectoId}/entregables/nuevo")
    public String guardarEntregable(@PathVariable Long proyectoId,
            @ModelAttribute Entregable entregable,
            @RequestParam("archivo") MultipartFile archivo) throws Exception {
        entregableService.guardarEntregable(entregable, archivo, proyectoService.obtenerProyecto(proyectoId));
        return "redirect:/proyectos/" + proyectoId + "/entregables";
    }

    @GetMapping("/proyectos/{proyectoId}/entregables/{id}")
    public String abrirEntregable(@PathVariable Long proyectoId, @PathVariable Long id, Model model) {
        model.addAttribute("entregable", entregableService.obtenerEntregable(id));
        model.addAttribute("proyectoId", proyectoId);
        return "entregable";
    }

    @GetMapping("/proyectos/{proyectoId}/entregables/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long proyectoId, @PathVariable Long id, Model model) {
        model.addAttribute("entregable", entregableService.obtenerEntregable(id));
        model.addAttribute("proyectoId", proyectoId);
        return "editar-entregable";
    }

    @PostMapping("/proyectos/{proyectoId}/entregables/{id}/editar")
    public String guardarCambios(@PathVariable Long proyectoId,
            @PathVariable Long id,
            @ModelAttribute Entregable datos,
            @RequestParam("archivo") MultipartFile archivo) throws Exception {
        entregableService.actualizarEntregable(id, datos, archivo);
        return "redirect:/proyectos/" + proyectoId + "/entregables/" + id;
    }

    @GetMapping("/proyectos/{proyectoId}/entregables/{id}/eliminar")
    public String mostrarConfirmacion(@PathVariable Long proyectoId, @PathVariable Long id, Model model) {
        model.addAttribute("entregable", entregableService.obtenerEntregable(id));
        model.addAttribute("proyectoId", proyectoId);
        return "eliminar-entregable";
    }

    @PostMapping("/proyectos/{proyectoId}/entregables/{id}/eliminar")
    public String eliminarEntregable(@PathVariable Long proyectoId, @PathVariable Long id) throws Exception {
        entregableService.eliminarEntregable(id);
        return "redirect:/proyectos/" + proyectoId + "/entregables";
    }
}
