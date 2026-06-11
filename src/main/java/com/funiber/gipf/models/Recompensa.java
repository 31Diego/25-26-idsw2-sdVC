package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "recompensas")
@Getter @Setter @NoArgsConstructor
public class Recompensa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String tipo;
    private String valor;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String condiciones;

    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Investigador destinatario;
}
