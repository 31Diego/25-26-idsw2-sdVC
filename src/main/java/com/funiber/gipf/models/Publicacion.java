package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publicaciones")
@Getter @Setter @NoArgsConstructor
public class Publicacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Investigador autor;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("fecha ASC")
    private List<Respuesta> respuestas = new ArrayList<>();
}
