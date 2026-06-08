package com.funiber.gipf.services;

import com.funiber.gipf.models.Entregable;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.repositories.EntregableRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EntregableService {

    private final EntregableRepository entregableRepository;
    private final ArchivoService archivoService;

    public EntregableService(EntregableRepository entregableRepository,
                             ArchivoService archivoService) {
        this.entregableRepository = entregableRepository;
        this.archivoService = archivoService;
    }

    public List<Entregable> obtenerEntregablesDeProyecto(Long proyectoId) {
        return entregableRepository.findByProyectoId(proyectoId);
    }

    public Entregable obtenerEntregable(Long id) {
        return entregableRepository.findById(id).orElseThrow();
    }

    public Entregable guardarEntregable(Entregable entregable, MultipartFile archivo, Proyecto proyecto) throws IOException {
        entregable.setProyecto(proyecto);
        if (archivo != null && !archivo.isEmpty()) {
            entregable.setRutaArchivo(archivoService.guardarArchivo(archivo));
        }
        return entregableRepository.save(entregable);
    }

    public Entregable actualizarEntregable(Long id, Entregable datos, MultipartFile archivo) throws IOException {
        Entregable entregable = entregableRepository.findById(id).orElseThrow();
        entregable.setTitulo(datos.getTitulo());
        entregable.setTipo(datos.getTipo());
        entregable.setFechaLimite(datos.getFechaLimite());
        entregable.setEstado(datos.getEstado());
        entregable.setDescripcion(datos.getDescripcion());
        if (archivo != null && !archivo.isEmpty()) {
            entregable.setRutaArchivo(archivoService.guardarArchivo(archivo));
        }
        return entregableRepository.save(entregable);
    }

    public void eliminarEntregable(Long id) throws IOException {
        Entregable entregable = entregableRepository.findById(id).orElseThrow();
        if (entregable.getRutaArchivo() != null) {
            archivoService.eliminarArchivo(entregable.getRutaArchivo());
        }
        entregableRepository.deleteById(id);
    }
}
