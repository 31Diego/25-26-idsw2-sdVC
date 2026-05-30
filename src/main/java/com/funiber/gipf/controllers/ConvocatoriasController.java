package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Convocatoria;
import com.funiber.gipf.services.ConvocatoriasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/convocatorias")
public class ConvocatoriasController {

    private final ConvocatoriasService convocatoriasService;

    public ConvocatoriasController(ConvocatoriasService convocatoriasService) {
        this.convocatoriasService = convocatoriasService;
    }

    // abrirConvocatorias — GET /convocatorias
    @GetMapping
    public String abrirConvocatorias(
            @RequestParam(required = false) String texto,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String estado,
            Model model) {

        List<Convocatoria> convocatorias;
        if (texto != null || area != null || estado != null) {
            convocatorias = convocatoriasService.filtrarConvocatorias(texto, area, estado);
        } else {
            convocatorias = convocatoriasService.listarConvocatorias();
        }

        model.addAttribute("convocatorias", convocatorias);
        model.addAttribute("texto", texto);
        model.addAttribute("area", area);
        model.addAttribute("estado", estado);
        return "convocatorias";
    }

    // abrirConvocatoria — GET /convocatorias/{id}
    @GetMapping("/{id}")
    public String abrirConvocatoria(@PathVariable Long id, Model model) {
        Convocatoria convocatoria = convocatoriasService.obtenerConvocatoria(id);
        model.addAttribute("convocatoria", convocatoria);
        return "convocatoria";
    }
}
