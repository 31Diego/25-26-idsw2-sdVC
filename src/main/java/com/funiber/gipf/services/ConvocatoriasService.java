package com.funiber.gipf.services;

import com.funiber.gipf.models.Convocatoria;
import com.funiber.gipf.repositories.ConvocatoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConvocatoriasService {

    private final ConvocatoriaRepository convocatoriaRepository;

    public ConvocatoriasService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public List<Convocatoria> listarConvocatorias() {
        return convocatoriaRepository.findAll();
    }

    public List<Convocatoria> filtrarConvocatorias(String texto, String area, String estado) {
        return convocatoriaRepository.buscarPorCriterio(texto, area, estado);
    }

    public Convocatoria obtenerConvocatoria(Long id) {
        return convocatoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convocatoria no encontrada: " + id));
    }
}
