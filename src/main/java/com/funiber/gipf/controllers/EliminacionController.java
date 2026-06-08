package com.funiber.gipf.controllers;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.InvestigadorService;
import com.funiber.gipf.services.SolicitudEliminacionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EliminacionController {

    private final InvestigadorService investigadorService;
    private final SolicitudEliminacionService solicitudEliminacionService;

    public EliminacionController(InvestigadorService investigadorService,
            SolicitudEliminacionService solicitudEliminacionService) {
        this.investigadorService = investigadorService;
        this.solicitudEliminacionService = solicitudEliminacionService;
    }

    @GetMapping("/investigadores/{id}/solicitar-eliminacion")
    public String mostrarFormularioSolicitud(@PathVariable Long id,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails,
            Model model) {
        Investigador investigador = userDetails.getInvestigador();
        if (!solicitudEliminacionService.puedeGestionar(investigador, id)) {
            return "redirect:/perfil/opciones";
        }
        model.addAttribute("investigadorDestino", investigadorService.obtenerInvestigador(id));
        return "solicitar-eliminacion";
    }

    @PostMapping("/investigadores/{id}/solicitar-eliminacion")
    public String enviarSolicitud(@PathVariable Long id,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails,
            @RequestParam String motivo,
            HttpServletRequest request) {
        Investigador investigador = userDetails.getInvestigador();
        if (!solicitudEliminacionService.puedeGestionar(investigador, id)) {
            return "redirect:/perfil/opciones";
        }
        Investigador objetivo = investigadorService.obtenerInvestigador(id);
        solicitudEliminacionService.crearSolicitud(objetivo, motivo);
        if (solicitudEliminacionService.requiereLogoutTrasEnviar(investigador)) {
            SecurityContextHolder.clearContext();
            HttpSession session = request.getSession(false);
            if (session != null)
                session.invalidate();
            return "redirect:/login?logout";
        }
        return "redirect:/investigadores/" + id + "/opciones";
    }

    @GetMapping("/solicitudes-eliminacion")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String abrirSolicitudesEliminacion(Model model) {
        model.addAttribute("solicitudes", solicitudEliminacionService.obtenerSolicitudes());
        return "solicitudes-eliminacion";
    }

    @GetMapping("/solicitudes-eliminacion/{id}")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String abrirSolicitudEliminacion(@PathVariable Long id, Model model) {
        model.addAttribute("solicitud", solicitudEliminacionService.obtenerSolicitud(id));
        return "solicitud-eliminacion";
    }

    @PostMapping("/solicitudes-eliminacion/{id}/denegar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String denegarSolicitud(@PathVariable Long id) {
        solicitudEliminacionService.denegarSolicitud(id);
        return "redirect:/solicitudes-eliminacion";
    }

    @PostMapping("/investigadores/{id}/eliminar-perfil")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String eliminarPerfil(@PathVariable Long id,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Investigador coordinador = userDetails.getInvestigador();
        if (!investigadorService.eliminarPerfil(coordinador.getId(), id)) {
            return "redirect:/investigadores/" + id + "/opciones";
        }
        return "redirect:/solicitudes-eliminacion";
    }
}
