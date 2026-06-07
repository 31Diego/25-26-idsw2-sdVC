package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.InvestigadoresService;
import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InvestigadorController {

    private final InvestigadorService investigadorService;
    private final InvestigadoresService investigadoresService;

    public InvestigadorController(InvestigadorService investigadorService,
                                   InvestigadoresService investigadoresService) {
        this.investigadorService = investigadorService;
        this.investigadoresService = investigadoresService;
    }

    @GetMapping("/investigadores")
    public String abrirInvestigadores(@RequestParam(required = false) String criterio, Model model) {
        if (criterio != null && !criterio.isBlank()) {
            model.addAttribute("investigadores", investigadoresService.filtrarInvestigadores(criterio));
            model.addAttribute("criterio", criterio);
        } else {
            model.addAttribute("investigadores", investigadoresService.obtenerInvestigadores());
        }
        return "investigadores";
    }

    @GetMapping("/investigadores/nuevo")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("investigador", new Investigador());
        return "crear-investigador";
    }

    @PostMapping("/investigadores/nuevo")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String guardarInvestigador(@ModelAttribute Investigador investigador) {
        Investigador guardado = investigadorService.guardarInvestigador(investigador);
        return "redirect:/investigadores/" + guardado.getId();
    }

    @GetMapping("/investigadores/{id}")
    public String abrirInvestigador(@PathVariable Long id, Model model) {
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(id));
        return "investigador";
    }
}
