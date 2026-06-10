package com.funiber.gipf.controllers;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.models.CargaTrabajo;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.services.CargaTrabajoService;
import com.funiber.gipf.services.InvestigadorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CargaTrabajoController {

    private final CargaTrabajoService cargaTrabajoService;
    private final InvestigadorService investigadorService;

    public CargaTrabajoController(CargaTrabajoService cargaTrabajoService,
            InvestigadorService investigadorService) {
        this.cargaTrabajoService = cargaTrabajoService;
        this.investigadorService = investigadorService;
    }

    @GetMapping("/carga-trabajo")
    public String abrirCargaTrabajo(@AuthenticationPrincipal InvestigadorUserDetails userDetails,
            Model model) {
        Investigador investigador = userDetails.getInvestigador();
        if (investigador.getRol() == Rol.COORDINADOR) {
            model.addAttribute("investigadores", investigadorService.obtenerInvestigadores(null));
        } else {
            model.addAttribute("carga", cargaTrabajoService.obtenerOCrearPorInvestigador(investigador));
        }
        return "carga-trabajo";
    }

    @GetMapping("/carga-trabajo/editar")
    public String mostrarFormularioPropio(@AuthenticationPrincipal InvestigadorUserDetails userDetails,
            Model model) {
        Investigador investigador = userDetails.getInvestigador();
        CargaTrabajo carga = cargaTrabajoService.obtenerOCrearPorInvestigador(investigador);
        model.addAttribute("carga", carga);
        return "editar-carga-trabajo";
    }

    @PostMapping("/carga-trabajo/editar")
    public String guardarPropio(@AuthenticationPrincipal InvestigadorUserDetails userDetails,
            @RequestParam double horasDocencia,
            @RequestParam double horasInvestigacion,
            @RequestParam double horasActividades) {
        Investigador investigador = userDetails.getInvestigador();
        CargaTrabajo carga = cargaTrabajoService.obtenerOCrearPorInvestigador(investigador);
        cargaTrabajoService.actualizar(carga.getId(), horasDocencia, horasInvestigacion, horasActividades);
        return "redirect:/carga-trabajo";
    }

    @GetMapping("/investigadores/{id}/carga-trabajo/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarFormularioInvestigador(@PathVariable Long id, Model model) {
        Investigador investigador = investigadorService.obtenerInvestigador(id);
        CargaTrabajo carga = cargaTrabajoService.obtenerOCrearPorInvestigador(investigador);
        model.addAttribute("carga", carga);
        model.addAttribute("investigador", investigador);
        return "editar-carga-trabajo";
    }

    @PostMapping("/investigadores/{id}/carga-trabajo/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String guardarInvestigador(@PathVariable Long id,
            @RequestParam double horasDocencia,
            @RequestParam double horasInvestigacion,
            @RequestParam double horasActividades) {
        Investigador investigador = investigadorService.obtenerInvestigador(id);
        CargaTrabajo carga = cargaTrabajoService.obtenerOCrearPorInvestigador(investigador);
        cargaTrabajoService.actualizar(carga.getId(), horasDocencia, horasInvestigacion, horasActividades);
        return "redirect:/carga-trabajo";
    }
}
