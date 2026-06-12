package com.funiber.gipf;

import com.funiber.gipf.models.*;
import com.funiber.gipf.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final InvestigadorRepository investigadorRepository;
    private final ProyectoRepository proyectoRepository;
    private final EntregableRepository entregableRepository;
    private final CargaTrabajoRepository cargaTrabajoRepository;
    private final PublicacionRepository publicacionRepository;
    private final RespuestaRepository respuestaRepository;
    private final ConvocatoriaRepository convocatoriaRepository;
    private final RecompensaRepository recompensaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(InvestigadorRepository investigadorRepository,
                      ProyectoRepository proyectoRepository,
                      EntregableRepository entregableRepository,
                      CargaTrabajoRepository cargaTrabajoRepository,
                      PublicacionRepository publicacionRepository,
                      RespuestaRepository respuestaRepository,
                      ConvocatoriaRepository convocatoriaRepository,
                      RecompensaRepository recompensaRepository,
                      PasswordEncoder passwordEncoder) {
        this.investigadorRepository = investigadorRepository;
        this.proyectoRepository = proyectoRepository;
        this.entregableRepository = entregableRepository;
        this.cargaTrabajoRepository = cargaTrabajoRepository;
        this.publicacionRepository = publicacionRepository;
        this.respuestaRepository = respuestaRepository;
        this.convocatoriaRepository = convocatoriaRepository;
        this.recompensaRepository = recompensaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (investigadorRepository.count() > 0) return;

        // ── Investigadores ───────────────────────────────────────────────────

        Investigador admin = new Investigador();
        admin.setNombre("Coordinador");
        admin.setApellidos("FUNIBER");
        admin.setEmail("coordinador@funiber.org");
        admin.setInstitucion("FUNIBER");
        admin.setUsername("coordinador");
        admin.setPassword(passwordEncoder.encode("coordinador"));
        admin.setRol(Rol.COORDINADOR);
        investigadorRepository.save(admin);

        Investigador inv = new Investigador();
        inv.setNombre("Investigador");
        inv.setApellidos("FUNIBER");
        inv.setEmail("investigador@funiber.org");
        inv.setInstitucion("FUNIBER");
        inv.setUsername("investigador");
        inv.setPassword(passwordEncoder.encode("investigador"));
        inv.setRol(Rol.INVESTIGADOR);
        inv.setCampo("Energías Renovables");
        investigadorRepository.save(inv);

        // ── Carga de trabajo ─────────────────────────────────────────────────

        CargaTrabajo cargaAdmin = new CargaTrabajo();
        cargaAdmin.setInvestigador(admin);
        cargaAdmin.setHorasDocencia(8.0);
        cargaAdmin.setHorasInvestigacion(12.0);
        cargaAdmin.setHorasActividades(3.0);
        cargaTrabajoRepository.save(cargaAdmin);

        CargaTrabajo cargaMaria = new CargaTrabajo();
        cargaMaria.setInvestigador(inv);
        cargaMaria.setHorasDocencia(15.0);
        cargaMaria.setHorasInvestigacion(20.0);
        cargaMaria.setHorasActividades(5.0);
        cargaTrabajoRepository.save(cargaMaria);

        // ── Proyectos ────────────────────────────────────────────────────────

        Proyecto p1 = new Proyecto();
        p1.setTitulo("Horizonte Europa 2025");
        p1.setDescripcion("Proyecto de investigación en energías renovables en el marco del programa Horizonte Europa.");
        p1.setObjetivos("Reducir emisiones de CO2 en un 30% mediante tecnologías de almacenamiento energético.");
        p1.setEstado("EN_CURSO");
        p1.setFechaInicio(LocalDate.of(2025, 1, 15));
        p1.setFechaFin(LocalDate.of(2027, 12, 31));
        p1.getInvestigadores().add(inv);
        proyectoRepository.save(p1);

        Proyecto p2 = new Proyecto();
        p2.setTitulo("Redes de Investigación LATAM");
        p2.setDescripcion("Fortalecimiento de redes de investigación entre universidades latinoamericanas afiliadas a FUNIBER.");
        p2.setObjetivos("Establecer 5 redes temáticas y publicar 20 artículos en revistas indexadas.");
        p2.setEstado("EN_PROPUESTA");
        p2.setFechaInicio(LocalDate.of(2025, 6, 1));
        p2.setFechaFin(LocalDate.of(2026, 5, 31));
        p2.getInvestigadores().add(inv);
        proyectoRepository.save(p2);

        // ── Entregables ──────────────────────────────────────────────────────

        Entregable e1 = new Entregable();
        e1.setTitulo("Informe de arranque");
        e1.setTipo("INFORME");
        e1.setEstado("ENTREGADO");
        e1.setFechaLimite(LocalDate.of(2025, 3, 31));
        e1.setDescripcion("Informe inicial con el plan de trabajo y los primeros avances del proyecto.");
        e1.setProyecto(p1);
        entregableRepository.save(e1);

        Entregable e2 = new Entregable();
        e2.setTitulo("Memoria final");
        e2.setTipo("MEMORIA");
        e2.setEstado("PENDIENTE");
        e2.setFechaLimite(LocalDate.of(2027, 11, 30));
        e2.setDescripcion("Memoria completa con resultados, conclusiones y publicaciones derivadas.");
        e2.setProyecto(p1);
        entregableRepository.save(e2);

        Entregable e3 = new Entregable();
        e3.setTitulo("Plan de trabajo");
        e3.setTipo("INFORME");
        e3.setEstado("PENDIENTE");
        e3.setFechaLimite(LocalDate.of(2025, 8, 31));
        e3.setDescripcion("Documento con la planificación detallada de actividades y responsables.");
        e3.setProyecto(p2);
        entregableRepository.save(e3);

        // ── Convocatorias ────────────────────────────────────────────────────

        Convocatoria c1 = new Convocatoria();
        c1.setTitulo("Horizonte Europa 2026");
        c1.setArea("Energía y Clima");
        c1.setEstado("ABIERTA");
        c1.setFechaApertura(LocalDate.of(2025, 11, 1));
        c1.setFechaCierre(LocalDate.of(2026, 3, 15));
        c1.setDescripcion("Convocatoria del programa Horizonte Europa para proyectos de investigación en energía y cambio climático.");
        c1.setRequisitos("Consorcio mínimo de 3 entidades de 3 países miembros de la UE.");
        c1.setCriteriosEvaluacion("Impacto científico, viabilidad técnica y potencial de transferencia.");
        c1.setDotacion("Hasta 3.000.000 € por proyecto.");
        c1.setContacto("horizonte2026@ec.europa.eu");
        convocatoriaRepository.save(c1);

        Convocatoria c2 = new Convocatoria();
        c2.setTitulo("ANID Chile — Fondecyt Regular 2025");
        c2.setArea("Ciencias Sociales y Humanidades");
        c2.setEstado("CERRADA");
        c2.setFechaApertura(LocalDate.of(2025, 3, 1));
        c2.setFechaCierre(LocalDate.of(2025, 5, 30));
        c2.setDescripcion("Fondo Nacional de Desarrollo Científico y Tecnológico de Chile para investigación básica.");
        c2.setRequisitos("Investigador responsable con grado de doctor y afiliación a institución chilena.");
        c2.setDotacion("Hasta 50.000.000 CLP anuales por 3 años.");
        c2.setContacto("fondecyt@anid.cl");
        convocatoriaRepository.save(c2);

        Convocatoria c3 = new Convocatoria();
        c3.setTitulo("CYTED — Redes Temáticas 2025");
        c3.setArea("Tecnología e Innovación");
        c3.setEstado("ABIERTA");
        c3.setFechaApertura(LocalDate.of(2025, 9, 1));
        c3.setFechaCierre(LocalDate.of(2026, 1, 31));
        c3.setDescripcion("Programa Iberoamericano de Ciencia y Tecnología para el Desarrollo — convocatoria de redes temáticas.");
        c3.setRequisitos("Mínimo 6 grupos de investigación de al menos 5 países iberoamericanos.");
        c3.setDotacion("60.000 € anuales durante 4 años.");
        c3.setContacto("secretaria@cyted.org");
        convocatoriaRepository.save(c3);

        // ── Recompensas ──────────────────────────────────────────────────────

        Recompensa r1 = new Recompensa();
        r1.setTitulo("Investigadora del trimestre");
        r1.setTipo("Reconocimiento");
        r1.setValor("Diploma + mención en web institucional");
        r1.setDescripcion("Reconocimiento a la investigadora con mayor producción científica del trimestre.");
        r1.setCondiciones("Publicación de al menos 2 artículos en revistas indexadas en el trimestre.");
        r1.setFechaCreacion(LocalDate.of(2025, 10, 1));
        r1.setDestinatario(inv);
        recompensaRepository.save(r1);

        Recompensa r2 = new Recompensa();
        r2.setTitulo("Premio publicación destacada");
        r2.setTipo("Económica");
        r2.setValor("500 €");
        r2.setDescripcion("Premio por la publicación con mayor índice de impacto del semestre.");
        r2.setCondiciones("Artículo publicado en revista Q1 con factor de impacto superior a 3.");
        r2.setFechaCreacion(LocalDate.of(2026, 1, 15));
        r2.setDestinatario(inv);
        recompensaRepository.save(r2);

        // ── Publicaciones y respuestas ───────────────────────────────────────

        Publicacion pub1 = new Publicacion();
        pub1.setTitulo("Convocatoria Horizonte Europa 2026 — primeras noticias");
        pub1.setContenido("Se han publicado los primeros detalles de la próxima convocatoria Horizonte Europa para el año 2026. Os animamos a revisar los requisitos y a contactar con coordinación si queréis formar parte de un consorcio.");
        pub1.setFecha(LocalDate.of(2025, 10, 5));
        pub1.setAutor(admin);
        publicacionRepository.save(pub1);

        Publicacion pub2 = new Publicacion();
        pub2.setTitulo("Resultados preliminares: almacenamiento energético en zonas rurales");
        pub2.setContenido("Compartimos los primeros resultados obtenidos en el marco del proyecto Horizonte Europa 2025. Los datos muestran una reducción del 18% en emisiones en las zonas piloto.");
        pub2.setFecha(LocalDate.of(2025, 11, 14));
        pub2.setAutor(inv);
        publicacionRepository.save(pub2);

        Respuesta resp = new Respuesta();
        resp.setContenido("Excelentes resultados. Estaremos atentos a la evolución del proyecto.");
        resp.setFecha(LocalDate.of(2025, 11, 16));
        resp.setAutor(admin);
        resp.setPublicacion(pub2);
        respuestaRepository.save(resp);
    }
}
