package com.funiber.gipf.policies;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;

import java.util.List;

public interface PoliticaConsulta {
    List<Proyecto> obtener(Investigador investigador, String criterio);
}
