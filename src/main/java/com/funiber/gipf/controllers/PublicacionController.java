package com.funiber.gipf.controllers;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.models.Publicacion;
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

    // ── Publicaciones (listado general) ──────────────────────────────────────

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

    @GetMapping("/publicaciones/{id}/editar")
    public String editarPublicacionForm(@PathVariable Long id, Model model,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Publicacion pub = publicacionService.obtenerPorId(id);
        if (!publicacionService.puedeEditarOEliminar(userDetails.getInvestigador(), pub)) {
            return "redirect:/publicaciones";
        }
        model.addAttribute("publicacion", pub);
        return "editar-publicacion";
    }

    @PostMapping("/publicaciones/{id}/editar")
    public String editarPublicacion(@PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String contenido,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Publicacion pub = publicacionService.obtenerPorId(id);
        if (!publicacionService.puedeEditarOEliminar(userDetails.getInvestigador(), pub)) {
            return "redirect:/publicaciones";
        }
        publicacionService.actualizar(id, titulo, contenido);
        return "redirect:/publicaciones/" + id;
    }

    @GetMapping("/publicaciones/{id}/eliminar")
    public String eliminarPublicacionConfirm(@PathVariable Long id, Model model,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Publicacion pub = publicacionService.obtenerPorId(id);
        if (!publicacionService.puedeEditarOEliminar(userDetails.getInvestigador(), pub)) {
            return "redirect:/publicaciones";
        }
        model.addAttribute("publicacion", pub);
        return "eliminar-publicacion";
    }

    @PostMapping("/publicaciones/{id}/eliminar")
    public String eliminarPublicacion(@PathVariable Long id,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Publicacion pub = publicacionService.obtenerPorId(id);
        if (!publicacionService.puedeEditarOEliminar(userDetails.getInvestigador(), pub)) {
            return "redirect:/publicaciones";
        }
        publicacionService.eliminar(id);
        return "redirect:/publicaciones";
    }

    // ── Mis publicaciones ────────────────────────────────────────────────────

    @GetMapping("/mis-publicaciones")
    public String abrirMisPublicaciones(Model model,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        model.addAttribute("publicaciones", publicacionService.obtenerPorAutor(userDetails.getInvestigador()));
        return "mis-publicaciones";
    }

    @GetMapping("/mis-publicaciones/{id}")
    public String abrirMiPublicacion(@PathVariable Long id, Model model,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Publicacion pub = publicacionService.obtenerPorId(id);
        if (!pub.getAutor().getId().equals(userDetails.getInvestigador().getId())) {
            return "redirect:/mis-publicaciones";
        }
        model.addAttribute("publicacion", pub);
        return "mi-publicacion";
    }

    @GetMapping("/mis-publicaciones/crear")
    public String crearPublicacionForm() {
        return "crear-publicacion";
    }

    @PostMapping("/mis-publicaciones/crear")
    public String crearPublicacion(@RequestParam String titulo,
            @RequestParam String contenido,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails) {
        Publicacion nueva = publicacionService.crear(titulo, contenido, userDetails.getInvestigador());
        return "redirect:/mis-publicaciones/" + nueva.getId();
    }

}
