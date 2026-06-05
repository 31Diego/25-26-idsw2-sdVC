package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proyectos")
@Getter @Setter @NoArgsConstructor
public class Proyecto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String objetivos;

    private String estado;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String documentacion;
    

    @ManyToMany
    @JoinTable(
        name = "proyecto_investigador",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "investigador_id")
    )
    private List<Investigador> investigadores = new ArrayList<>();
}
