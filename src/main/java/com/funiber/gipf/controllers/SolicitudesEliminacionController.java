package com.funiber.gipf.controllers;

import com.funiber.gipf.services.SolicitudEliminacionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitudes-eliminacion")
public class SolicitudesEliminacionController {

    private final SolicitudEliminacionService solicitudEliminacionService;

    public SolicitudesEliminacionController(SolicitudEliminacionService solicitudEliminacionService) {
        this.solicitudEliminacionService = solicitudEliminacionService;
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String abrirSolicitudesEliminacion(Model model) {
        model.addAttribute("solicitudes", solicitudEliminacionService.obtenerSolicitudes());
        return "solicitudes-eliminacion";
    }
}
