package com.funiber.gipf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelPrincipalController {

    @GetMapping
    public String abrirPanelPrincipal() {
        return "panel";
    }
}
