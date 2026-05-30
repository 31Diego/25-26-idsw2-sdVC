package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investigadores")
@Getter @Setter @NoArgsConstructor
public class Investigador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String especializacion;
    private String institucion;

    @Column(columnDefinition = "TEXT")
    private String intereses;

    @Column(columnDefinition = "TEXT")
    private String experiencia;

    private String disponibilidad;

    @Column(columnDefinition = "TEXT")
    private String cargaTrabajo;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
