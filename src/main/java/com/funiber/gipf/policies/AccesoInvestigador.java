package com.funiber.gipf.policies;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;

public class AccesoInvestigador implements PoliticaAcceso {

    @Override
    public boolean tieneAcceso(Proyecto proyecto, Investigador investigador) {
        return proyecto.getInvestigadores().stream()
                .anyMatch(inv -> inv.getId().equals(investigador.getId()));
    }
}
