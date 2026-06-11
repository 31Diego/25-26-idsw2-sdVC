package com.funiber.gipf.controllers;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Recompensa;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.repositories.InvestigadorRepository;
import com.funiber.gipf.services.RecompensaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecompensaController {

    private final RecompensaService recompensaService;
    private final InvestigadorRepository investigadorRepository;

    public RecompensaController(RecompensaService recompensaService,
                                InvestigadorRepository investigadorRepository) {
        this.recompensaService = recompensaService;
        this.investigadorRepository = investigadorRepository;
    }

    @GetMapping("/recompensas")
    public String abrirRecompensas(@AuthenticationPrincipal InvestigadorUserDetails userDetails,
                                   Model model) {
        Investigador usuario = userDetails.getInvestigador();
        if (usuario.getRol() == Rol.COORDINADOR) {
            model.addAttribute("recompensas", recompensaService.obtenerTodas());
        } else {
            model.addAttribute("recompensas", recompensaService.obtenerPorDestinatario(usuario));
        }
        return "recompensas";
    }

    @GetMapping("/recompensas/{id}")
    public String abrirRecompensa(@PathVariable Long id,
                                  @AuthenticationPrincipal InvestigadorUserDetails userDetails,
                                  Model model) {
        Recompensa recompensa = recompensaService.obtenerPorId(id);
        Investigador usuario = userDetails.getInvestigador();
        if (usuario.getRol() != Rol.COORDINADOR
                && !recompensa.getDestinatario().getId().equals(usuario.getId())) {
            return "redirect:/recompensas";
        }
        model.addAttribute("recompensa", recompensa);
        return "recompensa";
    }

    @GetMapping("/recompensas/crear")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String crearRecompensaForm(Model model) {
        model.addAttribute("investigadores", investigadorRepository.findAll());
        return "crear-recompensa";
    }

    @PostMapping("/recompensas/crear")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String crearRecompensa(@RequestParam String titulo,
                                  @RequestParam String tipo,
                                  @RequestParam String valor,
                                  @RequestParam(required = false) String descripcion,
                                  @RequestParam(required = false) String condiciones,
                                  @RequestParam Long destinatarioId) {
        Recompensa nueva = recompensaService.crear(titulo, tipo, valor, descripcion, condiciones, destinatarioId);
        return "redirect:/recompensas/" + nueva.getId();
    }

    @GetMapping("/recompensas/{id}/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String editarRecompensaForm(@PathVariable Long id, Model model) {
        model.addAttribute("recompensa", recompensaService.obtenerPorId(id));
        model.addAttribute("investigadores", investigadorRepository.findAll());
        return "editar-recompensa";
    }

    @PostMapping("/recompensas/{id}/editar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String editarRecompensa(@PathVariable Long id,
                                   @RequestParam String titulo,
                                   @RequestParam String tipo,
                                   @RequestParam String valor,
                                   @RequestParam(required = false) String descripcion,
                                   @RequestParam(required = false) String condiciones,
                                   @RequestParam Long destinatarioId) {
        recompensaService.actualizar(id, titulo, tipo, valor, descripcion, condiciones, destinatarioId);
        return "redirect:/recompensas/" + id;
    }

    @GetMapping("/recompensas/{id}/eliminar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String eliminarRecompensaConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("recompensa", recompensaService.obtenerPorId(id));
        return "eliminar-recompensa";
    }

    @PostMapping("/recompensas/{id}/eliminar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String eliminarRecompensa(@PathVariable Long id) {
        recompensaService.eliminar(id);
        return "redirect:/recompensas";
    }
}
