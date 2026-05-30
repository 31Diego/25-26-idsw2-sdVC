package com.funiber.gipf.controllers;

import com.funiber.gipf.services.CargaTrabajoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.funiber.gipf.models.Investigador;

@Controller
@RequestMapping("/carga-trabajo")
public class CargaTrabajoController {

    private final CargaTrabajoService cargaTrabajoService;

    public CargaTrabajoController(CargaTrabajoService cargaTrabajoService) {
        this.cargaTrabajoService = cargaTrabajoService;
    }

    @GetMapping
    public String abrirOpcionesCargaTrabajo(
            @RequestParam(required = false) String criterio,
            Model model) {

        List<Investigador> investigadores = (criterio != null)
                ? cargaTrabajoService.filtrarCargaTrabajo(criterio)
                : cargaTrabajoService.obtenerResumenCargaTrabajo();

        model.addAttribute("investigadores", investigadores);
        model.addAttribute("criterio", criterio);
        return "carga-trabajo";
    }
}
