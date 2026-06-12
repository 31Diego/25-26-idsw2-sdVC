package com.funiber.gipf.controllers;

import com.funiber.gipf.services.ArchivoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArchivoController {

    private final ArchivoService archivoService;

    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    // el if puede considerarase logica de negocio ? por lo demas bien

    @GetMapping("/archivos/{nombre}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombre) throws Exception {
        return archivoService.obtenerRecurso(nombre)
                .map(r -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombre + "\"")
                        .body(r))
                .orElse(ResponseEntity.notFound().build());
    }
}
