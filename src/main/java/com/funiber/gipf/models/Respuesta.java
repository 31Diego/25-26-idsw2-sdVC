package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "respuestas")
@Getter @Setter @NoArgsConstructor
public class Respuesta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Investigador autor;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
}
