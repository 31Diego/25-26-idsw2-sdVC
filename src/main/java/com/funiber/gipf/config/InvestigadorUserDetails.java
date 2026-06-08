package com.funiber.gipf.config;

import com.funiber.gipf.models.Investigador;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class InvestigadorUserDetails implements UserDetails {

    private final Investigador investigador;

    public InvestigadorUserDetails(Investigador investigador) {
        this.investigador = investigador;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + investigador.getRol().name()));
    }

    @Override public String getPassword()              { return investigador.getPassword(); }
    @Override public String getUsername()              { return investigador.getUsername(); }
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }
}
