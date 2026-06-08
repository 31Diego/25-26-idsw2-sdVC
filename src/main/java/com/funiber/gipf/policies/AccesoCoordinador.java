package com.funiber.gipf.policies;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;

public class AccesoCoordinador implements PoliticaAcceso {

    @Override
    public boolean tieneAcceso(Proyecto proyecto, Investigador investigador) {
        return true;
    }
}
