package com.funiber.gipf.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cargas_trabajo")
@Getter @Setter @NoArgsConstructor
public class CargaTrabajo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "investigador_id", unique = true)
    private Investigador investigador;

    private double horasDocencia = 0.0;
    private double horasInvestigacion = 0.0;
    private double horasActividades = 0.0;
}
