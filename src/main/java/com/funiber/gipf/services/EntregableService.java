package com.funiber.gipf.services;

import com.funiber.gipf.models.Entregable;
import com.funiber.gipf.repositories.EntregableRepository;
import com.funiber.gipf.repositories.ProyectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EntregableService {

    private final EntregableRepository entregableRepository;
    private final ProyectoRepository proyectoRepository;
    private final ArchivoService archivoService;

    public EntregableService(EntregableRepository entregableRepository,
                             ProyectoRepository proyectoRepository,
                             ArchivoService archivoService) {
        this.entregableRepository = entregableRepository;
        this.proyectoRepository = proyectoRepository;
        this.archivoService = archivoService;
    }

    public List<Entregable> obtenerEntregablesDeProyecto(Long proyectoId) {
        return entregableRepository.findByProyectoId(proyectoId);
    }

    public Entregable obtenerEntregable(Long id) {
        return entregableRepository.findById(id).orElseThrow();
    }

    public Entregable guardarEntregable(Entregable entregable, MultipartFile archivo, Long proyectoId) throws IOException {
        entregable.setProyecto(proyectoRepository.findById(proyectoId).orElseThrow());
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
