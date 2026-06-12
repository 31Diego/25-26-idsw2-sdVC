package com.funiber.gipf.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class ArchivoService {

    private static final Path CARPETA_ARCHIVOS = Paths.get("archivos");

    public String guardarArchivo(MultipartFile archivo) throws IOException {
        Files.createDirectories(CARPETA_ARCHIVOS);
        String nombre = archivo.getOriginalFilename();
        Files.copy(archivo.getInputStream(), CARPETA_ARCHIVOS.resolve(nombre), StandardCopyOption.REPLACE_EXISTING);
        return nombre;
    }

    public void eliminarArchivo(String nombre) throws IOException {
        Files.deleteIfExists(CARPETA_ARCHIVOS.resolve(nombre));
    }

    public Optional<Resource> obtenerRecurso(String nombre) throws Exception {
        Resource resource = new UrlResource(CARPETA_ARCHIVOS.resolve(nombre).toUri());
        return resource.exists() ? Optional.of(resource) : Optional.empty();
    }
}
