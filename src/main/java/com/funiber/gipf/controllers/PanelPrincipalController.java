package com.funiber.gipf.controllers;

import com.funiber.gipf.services.PanelPrincipalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanelPrincipalController {

    private final PanelPrincipalService panelPrincipalService;

    public PanelPrincipalController(PanelPrincipalService panelPrincipalService) {
        this.panelPrincipalService = panelPrincipalService;
    }

    @GetMapping("/panel")
    public String abrirPanelPrincipal() {
        panelPrincipalService.cargarPanel();
        return "panel";
    }
}
