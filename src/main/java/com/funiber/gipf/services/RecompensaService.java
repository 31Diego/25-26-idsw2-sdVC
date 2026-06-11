package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Recompensa;
import com.funiber.gipf.repositories.InvestigadorRepository;
import com.funiber.gipf.repositories.RecompensaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecompensaService {

    private final RecompensaRepository recompensaRepository;
    private final InvestigadorRepository investigadorRepository;

    public RecompensaService(RecompensaRepository recompensaRepository,
                             InvestigadorRepository investigadorRepository) {
        this.recompensaRepository = recompensaRepository;
        this.investigadorRepository = investigadorRepository;
    }

    public List<Recompensa> obtenerTodas() {
        return recompensaRepository.findAll();
    }

    public List<Recompensa> obtenerPorDestinatario(Investigador destinatario) {
        return recompensaRepository.findByDestinatario(destinatario);
    }

    public Recompensa obtenerPorId(Long id) {
        return recompensaRepository.findById(id).orElseThrow();
    }

    public Recompensa crear(String titulo, String tipo, String valor,
                            String descripcion, String condiciones, Long destinatarioId) {
        Recompensa r = new Recompensa();
        r.setTitulo(titulo);
        r.setTipo(tipo);
        r.setValor(valor);
        r.setDescripcion(descripcion);
        r.setCondiciones(condiciones);
        r.setFechaCreacion(LocalDate.now());
        r.setDestinatario(investigadorRepository.findById(destinatarioId).orElseThrow());
        return recompensaRepository.save(r);
    }

    public void actualizar(Long id, String titulo, String tipo, String valor,
                           String descripcion, String condiciones, Long destinatarioId) {
        Recompensa r = recompensaRepository.findById(id).orElseThrow();
        r.setTitulo(titulo);
        r.setTipo(tipo);
        r.setValor(valor);
        r.setDescripcion(descripcion);
        r.setCondiciones(condiciones);
        r.setDestinatario(investigadorRepository.findById(destinatarioId).orElseThrow());
        recompensaRepository.save(r);
    }

    public void eliminar(Long id) {
        recompensaRepository.deleteById(id);
    }
}
