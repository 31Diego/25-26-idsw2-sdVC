package com.funiber.gipf;

import com.funiber.gipf.models.Convocatoria;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Usuario;
import com.funiber.gipf.repositories.ConvocatoriaRepository;
import com.funiber.gipf.repositories.InvestigadorRepository;
import com.funiber.gipf.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final InvestigadorRepository investigadorRepository;
    private final ConvocatoriaRepository convocatoriaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UsuarioRepository usuarioRepository,
                      InvestigadorRepository investigadorRepository,
                      ConvocatoriaRepository convocatoriaRepository,
                      PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.investigadorRepository = investigadorRepository;
        this.convocatoriaRepository = convocatoriaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        cargarUsuarios();
        cargarConvocatorias();
    }

    private void cargarUsuarios() {
        if (usuarioRepository.count() > 0) return;

        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRol("COORDINADOR");
        usuarioRepository.save(admin);

        Investigador perfil = new Investigador();
        perfil.setNombre("Admin");
        perfil.setApellidos("Coordinador");
        perfil.setEmail("admin@funiber.org");
        perfil.setInstitucion("FUNIBER");
        perfil.setEspecializacion("Coordinación de investigación");
        perfil.setIntereses("Gestión de proyectos, financiación europea");
        perfil.setExperiencia("10 años coordinando proyectos en la red FUNIBER");
        perfil.setDisponibilidad("Disponible");
        perfil.setCargaTrabajo("Supervisión general de convocatorias activas");
        perfil.setUsuario(admin);
        investigadorRepository.save(perfil);
    }

    private void cargarConvocatorias() {
        if (convocatoriaRepository.count() > 0) return;

        convocatoriaRepository.saveAll(List.of(
            convocatoria("Horizonte Europa 2026",
                "Ciencias", "Abierta",
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 12, 31),
                "Programa marco de la UE para financiación de investigación e innovación.",
                "Doctorado en área relacionada. Publicaciones previas valoradas.",
                "Impacto científico, viabilidad, colaboración internacional.",
                "Hasta 2.000.000 €", "convocatoria-horizonte-2026.pdf",
                "horizonte@europa.eu"),

            convocatoria("Convocatoria Salud FUNIBER 2026",
                "Salud", "Abierta",
                LocalDate.of(2026, 3, 1), LocalDate.of(2026, 9, 30),
                "Investigación aplicada en ciencias de la salud en la red FUNIBER.",
                "Investigador activo en la red FUNIBER. Experiencia en ensayos clínicos.",
                "Relevancia clínica, metodología, equipo investigador.",
                "Hasta 150.000 €", "bases-salud-funiber.pdf",
                "investigacion@funiber.org"),

            convocatoria("Innovación Tecnológica 2026",
                "Tecnología", "Abierta",
                LocalDate.of(2026, 2, 1), LocalDate.of(2026, 8, 31),
                "Proyectos de I+D en inteligencia artificial, robótica y transformación digital.",
                "Grado en Ingeniería o similar. Experiencia en desarrollo de software.",
                "Innovación, aplicabilidad, potencial de transferencia tecnológica.",
                "Hasta 500.000 €", "convocatoria-tecnologia-2026.pdf",
                "tecnologia@funiber.org"),

            convocatoria("Investigación Educativa 2025",
                "Educación", "Cerrada",
                LocalDate.of(2025, 1, 1), LocalDate.of(2025, 6, 30),
                "Estudio de metodologías pedagógicas innovadoras en entornos virtuales.",
                "Titulación en Ciencias de la Educación. Experiencia docente.",
                "Impacto pedagógico, rigor metodológico, alcance de la muestra.",
                "Hasta 80.000 €", "convocatoria-educacion-2025.pdf",
                "educacion@funiber.org"),

            convocatoria("Medio Ambiente FUNIBER 2025",
                "Medio Ambiente", "Cerrada",
                LocalDate.of(2025, 4, 1), LocalDate.of(2025, 10, 31),
                "Investigación en cambio climático, biodiversidad y sostenibilidad.",
                "Formación en Ciencias Ambientales o similar.",
                "Impacto ambiental, sostenibilidad del proyecto, difusión de resultados.",
                "Hasta 200.000 €", "convocatoria-medioambiente-2025.pdf",
                "medioambiente@funiber.org")
        ));
    }

    private Convocatoria convocatoria(String titulo, String area, String estado,
                                      LocalDate fechaInicio, LocalDate fechaFin,
                                      String descripcion, String requisitos,
                                      String criterios, String dotacion,
                                      String documentacion, String contacto) {
        Convocatoria c = new Convocatoria();
        c.setTitulo(titulo);
        c.setArea(area);
        c.setEstado(estado);
        c.setFechaInicio(fechaInicio);
        c.setFechaFin(fechaFin);
        c.setDescripcion(descripcion);
        c.setRequisitos(requisitos);
        c.setCriterios(criterios);
        c.setDotacion(dotacion);
        c.setDocumentacion(documentacion);
        c.setContacto(contacto);
        return c;
    }
}
