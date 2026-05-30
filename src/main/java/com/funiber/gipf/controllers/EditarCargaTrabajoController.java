package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.CargaTrabajoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carga-trabajo")
public class EditarCargaTrabajoController {

    private final CargaTrabajoService cargaTrabajoService;

    public EditarCargaTrabajoController(CargaTrabajoService cargaTrabajoService) {
        this.cargaTrabajoService = cargaTrabajoService;
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormulario(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", cargaTrabajoService.obtenerCargaTrabajo(id));
        return "editar-carga-trabajo";
    }

    @PostMapping("/{id}")
    public String guardarCargaTrabajo(@PathVariable Long id,
                                      @ModelAttribute Investigador investigador) {
        cargaTrabajoService.guardarCargaTrabajo(id, investigador);
        return "redirect:/carga-trabajo";
    }
}
