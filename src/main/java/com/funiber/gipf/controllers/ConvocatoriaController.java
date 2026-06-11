package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Convocatoria;
import com.funiber.gipf.services.ConvocatoriaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@PreAuthorize("hasRole('COORDINADOR')")
public class ConvocatoriaController {

    private final ConvocatoriaService convocatoriaService;

    public ConvocatoriaController(ConvocatoriaService convocatoriaService) {
        this.convocatoriaService = convocatoriaService;
    }

    @GetMapping("/convocatorias")
    public String abrirConvocatorias(@RequestParam(required = false) String q,
                                     @RequestParam(required = false) String area,
                                     @RequestParam(required = false) String estado,
                                     Model model) {
        model.addAttribute("convocatorias", convocatoriaService.buscarPorCriterios(q, area, estado));
        model.addAttribute("q", q != null ? q : "");
        model.addAttribute("area", area != null ? area : "");
        model.addAttribute("estado", estado != null ? estado : "");
        return "convocatorias";
    }

    @GetMapping("/convocatorias/{id}")
    public String abrirConvocatoria(@PathVariable Long id, Model model) {
        model.addAttribute("convocatoria", convocatoriaService.obtenerPorId(id));
        return "convocatoria";
    }

    @GetMapping("/convocatorias/importar")
    public String importarConvocatoriaForm() {
        return "importar-convocatoria";
    }

    @GetMapping("/convocatorias/{id}/eliminar")
    public String eliminarConvocatoriaConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("convocatoria", convocatoriaService.obtenerPorId(id));
        return "eliminar-convocatoria";
    }

    @PostMapping("/convocatorias/{id}/eliminar")
    public String eliminarConvocatoria(@PathVariable Long id) {
        convocatoriaService.eliminar(id);
        return "redirect:/convocatorias";
    }

    @PostMapping("/convocatorias/importar")
    public String importarConvocatoria(@RequestParam String titulo,
                                       @RequestParam String area,
                                       @RequestParam String estado,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaApertura,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCierre,
                                       @RequestParam(required = false) String descripcion,
                                       @RequestParam(required = false) String requisitos,
                                       @RequestParam(required = false) String criteriosEvaluacion,
                                       @RequestParam(required = false) String dotacion,
                                       @RequestParam(required = false) String documentacion,
                                       @RequestParam(required = false) String contacto) {
        Convocatoria nueva = convocatoriaService.guardar(titulo, area, estado,
                fechaApertura, fechaCierre, descripcion, requisitos,
                criteriosEvaluacion, dotacion, documentacion, contacto);
        return "redirect:/convocatorias/" + nueva.getId();
    }
}
