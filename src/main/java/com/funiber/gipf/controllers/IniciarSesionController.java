package com.funiber.gipf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IniciarSesionController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
}
