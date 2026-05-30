package com.funiber.gipf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CerrarSesionController {

    @GetMapping("/cerrar-sesion")
    public String mostrarConfirmacion() {
        return "cerrar-sesion";
    }
}
