package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "entregables")
@Getter @Setter @NoArgsConstructor
public class Entregable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String tipo;

    private LocalDate fechaLimite;

    private String estado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;
}
