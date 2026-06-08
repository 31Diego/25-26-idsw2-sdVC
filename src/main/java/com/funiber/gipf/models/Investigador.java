package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investigadores")
@Getter @Setter @NoArgsConstructor
public class Investigador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String institucion;

    @Column(unique = true)
    private String username;
    private String password;

    private String campo;
    private String carrera;
    private String master;

    @Enumerated(EnumType.STRING)
    private Rol rol;


}
