package com.funiber.gipf.controllers;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.services.ProyectosService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/proyectos")
public class ProyectosController {

    private final ProyectosService proyectosService;

    public ProyectosController(ProyectosService proyectosService) {
        this.proyectosService = proyectosService;
    }

    @GetMapping
    public String abrirProyectos(@RequestParam(required = false) String criterio,
                                  @AuthenticationPrincipal Investigador investigador,
                                  Model model) {
        boolean esCoordinador = "COORDINADOR".equals(investigador.getRol());

        if (criterio != null && !criterio.isBlank()) {
            model.addAttribute("proyectos", esCoordinador
                    ? proyectosService.filtrarProyectos(criterio)
                    : proyectosService.filtrarProyectosDeInvestigador(investigador, criterio));
            model.addAttribute("criterio", criterio);
        } else {
            model.addAttribute("proyectos", esCoordinador
                    ? proyectosService.obtenerProyectos()
                    : proyectosService.obtenerProyectosDeInvestigador(investigador));
        }
        return "proyectos";
    }
}
