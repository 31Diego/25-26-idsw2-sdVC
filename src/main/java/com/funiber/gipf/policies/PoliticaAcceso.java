package com.funiber.gipf.policies;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;

public interface PoliticaAcceso {
    boolean tieneAcceso(Proyecto proyecto, Investigador investigador);
}
