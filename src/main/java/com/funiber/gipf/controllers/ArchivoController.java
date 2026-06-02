package com.funiber.gipf.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ArchivoController {

    @GetMapping("/archivos/{nombre}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombre) throws Exception {
        Path ruta = Paths.get("archivos").resolve(nombre);
        Resource resource = new UrlResource(ruta.toUri());
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombre + "\"")
                .body(resource);
    }
}
