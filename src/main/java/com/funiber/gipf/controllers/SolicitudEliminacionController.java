package com.funiber.gipf.controllers;

import com.funiber.gipf.services.SolicitudEliminacionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitudes-eliminacion/{id}")
public class SolicitudEliminacionController {

    private final SolicitudEliminacionService solicitudEliminacionService;

    public SolicitudEliminacionController(SolicitudEliminacionService solicitudEliminacionService) {
        this.solicitudEliminacionService = solicitudEliminacionService;
    }

    @GetMapping
    @PreAuthorize("hasRole('COORDINADOR')")
    public String abrirSolicitudEliminacion(@PathVariable Long id, Model model) {
        model.addAttribute("solicitud", solicitudEliminacionService.obtenerSolicitud(id));
        return "solicitud-eliminacion";
    }
}
