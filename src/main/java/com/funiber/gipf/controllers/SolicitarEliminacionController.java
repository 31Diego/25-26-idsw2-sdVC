package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.InvestigadorService;
import com.funiber.gipf.services.SolicitudEliminacionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/investigadores/{id}/solicitar-eliminacion")
public class SolicitarEliminacionController {

    private final InvestigadorService investigadorService;
    private final SolicitudEliminacionService solicitudEliminacionService;

    public SolicitarEliminacionController(InvestigadorService investigadorService,
                                          SolicitudEliminacionService solicitudEliminacionService) {
        this.investigadorService = investigadorService;
        this.solicitudEliminacionService = solicitudEliminacionService;
    }

    @GetMapping
    public String mostrarFormulario(@PathVariable Long id,
                                    @AuthenticationPrincipal Investigador investigador,
                                    Model model) {
        if ("INVESTIGADOR".equals(investigador.getRol()) && !investigador.getId().equals(id)) {
            return "redirect:/perfil/opciones";
        }
        model.addAttribute("investigadorDestino", investigadorService.obtenerInvestigador(id));
        return "solicitar-eliminacion";
    }

    @PostMapping
    public String enviarSolicitud(@PathVariable Long id,
                                  @AuthenticationPrincipal Investigador investigador,
                                  @RequestParam String motivo) {
        if ("INVESTIGADOR".equals(investigador.getRol()) && !investigador.getId().equals(id)) {
            return "redirect:/perfil/opciones";
        }
        Investigador objetivo = investigadorService.obtenerInvestigador(id);
        solicitudEliminacionService.crearSolicitud(objetivo, motivo);
        if ("INVESTIGADOR".equals(investigador.getRol())) {
            return "redirect:/perfil/opciones";
        }
        return "redirect:/investigadores/" + id + "/opciones";
    }
}
