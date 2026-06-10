package com.funiber.gipf;

import com.funiber.gipf.models.CargaTrabajo;
import com.funiber.gipf.models.Investigador;
import com.funiber.gipf.models.Proyecto;
import com.funiber.gipf.models.Rol;
import com.funiber.gipf.repositories.CargaTrabajoRepository;
import com.funiber.gipf.repositories.InvestigadorRepository;
import com.funiber.gipf.repositories.ProyectoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final InvestigadorRepository investigadorRepository;
    private final ProyectoRepository proyectoRepository;
    private final CargaTrabajoRepository cargaTrabajoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(InvestigadorRepository investigadorRepository,
                      ProyectoRepository proyectoRepository,
                      CargaTrabajoRepository cargaTrabajoRepository,
                      PasswordEncoder passwordEncoder) {
        this.investigadorRepository = investigadorRepository;
        this.proyectoRepository = proyectoRepository;
        this.cargaTrabajoRepository = cargaTrabajoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (investigadorRepository.count() == 0) {
            Investigador admin = new Investigador();
            admin.setNombre("Admin");
            admin.setApellidos("FUNIBER");
            admin.setEmail("admin@funiber.org");
            admin.setInstitucion("FUNIBER");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRol(Rol.COORDINADOR);
            investigadorRepository.save(admin);
        }

        boolean hayInvestigador = investigadorRepository.findAll().stream()
                .anyMatch(i -> i.getRol() == Rol.INVESTIGADOR);
        if (!hayInvestigador) {
            Investigador maria = new Investigador();
            maria.setNombre("María");
            maria.setApellidos("García López");
            maria.setEmail("maria@funiber.org");
            maria.setInstitucion("FUNIBER");
            maria.setUsername("maria");
            maria.setPassword(passwordEncoder.encode("maria"));
            maria.setRol(Rol.INVESTIGADOR);
            maria.setCampo("Energías Renovables");
            investigadorRepository.save(maria);

            proyectoRepository.findAll().stream().limit(2).forEach(p -> {
                p.getInvestigadores().add(maria);
                proyectoRepository.save(p);
            });
        }

        if (proyectoRepository.count() == 0) {
            Proyecto p1 = new Proyecto();
            p1.setTitulo("Horizonte Europa 2025");
            p1.setDescripcion("Proyecto de investigación en energías renovables en el marco del programa Horizonte Europa.");
            p1.setObjetivos("Reducir emisiones de CO2 en un 30% mediante tecnologías de almacenamiento energético.");
            p1.setEstado("EN_CURSO");
            p1.setFechaInicio(LocalDate.of(2025, 1, 15));
            p1.setFechaFin(LocalDate.of(2027, 12, 31));
            proyectoRepository.save(p1);

            Proyecto p2 = new Proyecto();
            p2.setTitulo("Redes de Investigación LATAM");
            p2.setDescripcion("Fortalecimiento de redes de investigación entre universidades latinoamericanas afiliadas a FUNIBER.");
            p2.setObjetivos("Establecer 5 redes temáticas y publicar 20 artículos en revistas indexadas.");
            p2.setEstado("EN_PROPUESTA");
            p2.setFechaInicio(LocalDate.of(2025, 6, 1));
            p2.setFechaFin(LocalDate.of(2026, 5, 31));
            proyectoRepository.save(p2);

            Proyecto p3 = new Proyecto();
            p3.setTitulo("Plataforma Digital de Formación");
            p3.setDescripcion("Desarrollo de una plataforma digital para formación continua en salud pública.");
            p3.setObjetivos("Llegar a 10.000 profesionales de la salud en 2 años.");
            p3.setEstado("COMPLETADO");
            p3.setFechaInicio(LocalDate.of(2023, 3, 1));
            p3.setFechaFin(LocalDate.of(2024, 12, 31));
            proyectoRepository.save(p3);
        }

        if (cargaTrabajoRepository.count() == 0) {
            investigadorRepository.findAll().forEach(inv -> {
                CargaTrabajo carga = new CargaTrabajo();
                carga.setInvestigador(inv);
                carga.setHorasDocencia(inv.getRol() == Rol.INVESTIGADOR ? 15.0 : 8.0);
                carga.setHorasInvestigacion(inv.getRol() == Rol.INVESTIGADOR ? 20.0 : 12.0);
                carga.setHorasActividades(inv.getRol() == Rol.INVESTIGADOR ? 5.0 : 3.0);
                cargaTrabajoRepository.save(carga);
            });
        }
    }
}
