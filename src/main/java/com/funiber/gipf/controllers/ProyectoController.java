package com.funiber.gipf.controllers;

import com.funiber.gipf.config.InvestigadorUserDetails;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.services.InvestigadorService;
import com.funiber.gipf.services.ProyectoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProyectoController {

    private final ProyectoService proyectoService;
    private final InvestigadorService investigadorService;

    public ProyectoController(ProyectoService proyectoService,
            InvestigadorService investigadorService) {
        this.proyectoService = proyectoService;
        this.investigadorService = investigadorService;
    }

    @GetMapping("/proyectos")
    public String abrirProyectos(@RequestParam(required = false) String criterio,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails,
            Model model) {
        Investigador investigador = userDetails.getInvestigador();
        model.addAttribute("proyectos", proyectoService.obtenerProyectosParaUsuario(investigador, criterio));
        model.addAttribute("criterio", criterio);
        return "proyectos";
    }

    @GetMapping("/proyectos/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "crear-proyecto";
    }

    @PostMapping("/proyectos/nuevo")
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/proyectos/{id}")
    public String abrirProyecto(@PathVariable Long id,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails,
            Model model) {
        Investigador investigador = userDetails.getInvestigador();
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        if (sinAcceso(proyecto, investigador)) return "redirect:/proyectos";
        model.addAttribute("proyecto", proyecto);
        return "proyecto";
    }

    @GetMapping("/proyectos/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(id));
        return "editar-proyecto";
    }

    @PostMapping("/proyectos/{id}/editar")
    public String guardarCambios(@PathVariable Long id, @ModelAttribute Proyecto datos) {
        proyectoService.actualizarProyecto(id, datos);
        return "redirect:/proyectos/" + id;
    }

    @GetMapping("/proyectos/{id}/eliminar")
    public String mostrarConfirmacionEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(id));
        return "eliminar-proyecto";
    }

    @PostMapping("/proyectos/{id}/eliminar")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos";
    }

    @GetMapping("/proyectos/{id}/investigadores")
    public String abrirInvestigadoresDeProyecto(@PathVariable Long id,
            @AuthenticationPrincipal InvestigadorUserDetails userDetails,
            Model model) {
        Investigador investigador = userDetails.getInvestigador();
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        if (sinAcceso(proyecto, investigador)) return "redirect:/proyectos";
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("investigadores", proyecto.getInvestigadores());
        return "investigadores-proyecto";
    }

    private boolean sinAcceso(Proyecto proyecto, Investigador investigador) {
        return !proyectoService.tieneAcceso(proyecto, investigador);
    }

    @GetMapping("/proyectos/{id}/investigadores/agregar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarDisponibles(@PathVariable Long id, Model model) {
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("disponibles", investigadorService.obtenerNoMiembros(proyecto));
        return "agregar-investigador";
    }

    @PostMapping("/proyectos/{id}/investigadores/agregar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String agregarInvestigador(@PathVariable Long id, @RequestParam Long investigadorId) {
        Proyecto proyecto = proyectoService.obtenerProyecto(id);
        Investigador investigador = investigadorService.obtenerInvestigador(investigadorId);
        proyectoService.agregarInvestigador(proyecto, investigador);
        return "redirect:/proyectos/" + id;
    }

    @GetMapping("/proyectos/{pId}/investigadores/{iId}/eliminar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String mostrarConfirmacionEliminarInvestigador(@PathVariable Long pId,
            @PathVariable Long iId,
            Model model) {
        model.addAttribute("proyecto", proyectoService.obtenerProyecto(pId));
        model.addAttribute("investigador", investigadorService.obtenerInvestigador(iId));
        return "eliminar-investigador";
    }

    @PostMapping("/proyectos/{pId}/investigadores/{iId}/eliminar")
    @PreAuthorize("hasRole('COORDINADOR')")
    public String confirmarEliminacionInvestigador(@PathVariable Long pId, @PathVariable Long iId) {
        Proyecto proyecto = proyectoService.obtenerProyecto(pId);
        Investigador investigador = investigadorService.obtenerInvestigador(iId);
        proyectoService.eliminarInvestigador(proyecto, investigador);
        return "redirect:/proyectos/" + pId;
    }
}
