package com.funiber.gipf.services;

import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Publicacion;
import com.funiber.gipf.models.Respuesta;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.repositories.PublicacionRepository;
import com.funiber.gipf.repositories.RespuestaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final RespuestaRepository respuestaRepository;

    public PublicacionService(PublicacionRepository publicacionRepository,
            RespuestaRepository respuestaRepository) {
        this.publicacionRepository = publicacionRepository;
        this.respuestaRepository = respuestaRepository;
    }

    public List<Publicacion> obtenerTodas() {
        return publicacionRepository.findAll();
    }

    public Publicacion obtenerPorId(Long id) {
        return publicacionRepository.findById(id).orElseThrow();
    }

    public void responder(Long publicacionId, String contenido, Investigador autor) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow();
        Respuesta respuesta = new Respuesta();
        respuesta.setContenido(contenido);
        respuesta.setFecha(LocalDate.now());
        respuesta.setAutor(autor);
        respuesta.setPublicacion(publicacion);
        respuestaRepository.save(respuesta);
    }

    public List<Publicacion> obtenerPorAutor(Investigador autor) {
        return publicacionRepository.findByAutor(autor);
    }

    public Publicacion crear(String titulo, String contenido, Investigador autor) {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setFecha(LocalDate.now());
        publicacion.setAutor(autor);
        return publicacionRepository.save(publicacion);
    }

    public void actualizar(Long id, String titulo, String contenido) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow();
        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacionRepository.save(publicacion);
    }

    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }

    public boolean puedeEditarOEliminar(Investigador usuario, Publicacion publicacion) {
        return usuario.getRol().equals(Rol.COORDINADOR)
                || publicacion.getAutor().getId().equals(usuario.getId());
    }
}
