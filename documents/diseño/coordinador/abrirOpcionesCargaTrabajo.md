# abrirOpcionesCargaTrabajo — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirOpcionesCargaTrabajo()

## Propósito

Recuperar y mostrar la tabla global de carga de trabajo de todos los usuarios del sistema (horas semanales por categoría).

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirOpcionesCargaTrabajo-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirOpcionesCargaTrabajo.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| CargaTrabajoView | `CargaTrabajoController` `@Controller` | Recibe GET /carga-trabajo; detecta rol COORDINADOR; pone la lista en el Model y devuelve carga-trabajo.html |
| InvestigadorService | `InvestigadorService` `@Service` | Devuelve todos los investigadores vía `obtenerInvestigadores(null)` |
| InvestigadorRepository | `InvestigadorRepository` JpaRepository | Ejecuta SELECT * FROM investigadores |
| CargaTrabajo | `CargaTrabajo` `@Entity` | Tabla cargas_trabajo; cargada en EAGER desde `Investigador.cargaTrabajo` |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /carga-trabajo | Tabla global (coordinador) o resumen personal (investigador) |

## Decisiones de diseño

- La URL `/carga-trabajo` es compartida entre actores; el controller bifurca por `investigador.getRol()`.
- `Investigador` declara `@OneToOne(mappedBy = "investigador", cascade = CascadeType.ALL)` hacia `CargaTrabajo`. Al ser `@OneToOne`, JPA carga la relación en EAGER por defecto; el template accede a `inv.cargaTrabajo` directamente sin consulta adicional.
- Si un usuario aún no tiene `CargaTrabajo` asignada, el template muestra 0.0 con expresión condicional `th:text`.
- El DataLoader inicializa entradas de `CargaTrabajo` para todos los usuarios al arrancar la aplicación por primera vez.
