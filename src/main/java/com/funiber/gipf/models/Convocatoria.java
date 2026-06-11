package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "convocatorias")
@Getter @Setter @NoArgsConstructor
public class Convocatoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String area;
    private String estado;

    private LocalDate fechaApertura;
    private LocalDate fechaCierre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String requisitos;

    @Column(columnDefinition = "TEXT")
    private String criteriosEvaluacion;

    @Column(columnDefinition = "TEXT")
    private String dotacion;

    @Column(columnDefinition = "TEXT")
    private String documentacion;

    private String contacto;
}
