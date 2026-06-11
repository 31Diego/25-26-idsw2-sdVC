package com.funiber.gipf.repositories;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
    List<Recompensa> findByDestinatario(Investigador destinatario);
}
