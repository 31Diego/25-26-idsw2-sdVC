package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "solicitudes_eliminacion")
@Getter @Setter @NoArgsConstructor
public class SolicitudEliminacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "investigador_id")
    private Investigador investigador;

    private String motivo;
    private LocalDate fecha;
    private String estado; // PENDIENTE | ACEPTADA | RECHAZADA
}
