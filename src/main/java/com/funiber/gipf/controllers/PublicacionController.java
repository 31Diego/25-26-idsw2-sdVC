package com.funiber.gipf.controllers;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.services.PublicacionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping("/publicaciones")
    public String abrirPublicaciones(Model model) {
        model.addAttribute("publicaciones", publicacionService.obtenerTodas());
        return "publicaciones";
    }

    @GetMapping("/publicaciones/{id}")
    public String abrirPublicacion(@PathVariable Long id, Model model) {
        model.addAttribute("publicacion", publicacionService.obtenerPorId(id));
        return "publicacion";
    }

    @PostMapping("/publicaciones/{id}/responder")
    public String responderPublicacion(@PathVariable Long id,
            @RequestParam String contenido,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        publicacionService.responder(id, contenido, userDetails.getInvestigador());
        return "redirect:/publicaciones/" + id;
    }
}
