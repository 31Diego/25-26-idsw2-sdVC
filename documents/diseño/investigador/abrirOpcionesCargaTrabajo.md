# abrirOpcionesCargaTrabajo — Diseño · Investigador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirOpcionesCargaTrabajo()

## Propósito

Recuperar y mostrar el resumen personal de carga de trabajo del investigador autenticado: horas semanales de docencia, investigación y actividades académicas.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirOpcionesCargaTrabajo-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirOpcionesCargaTrabajo.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| CargaTrabajoView | `CargaTrabajoController` `@Controller` | Recibe GET /carga-trabajo; detecta rol INVESTIGADOR; pone la CargaTrabajo en el Model y devuelve carga-trabajo.html |
| CargaTrabajoService | `CargaTrabajoService` `@Service` | Busca la CargaTrabajo del investigador autenticado; la crea con 0.0 en todos los campos si no existe |
| CargaTrabajoRepository | `CargaTrabajoRepository` JpaRepository | `findByInvestigadorId` + `save` si no existe |
| CargaTrabajo | `CargaTrabajo` `@Entity` | Tabla cargas_trabajo: `horasDocencia`, `horasInvestigacion`, `horasActividades` |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /carga-trabajo | Resumen personal del investigador (o tabla global si es coordinador) |

## Decisiones de diseño

- `CargaTrabajoService.obtenerOCrearPorInvestigador` usa `orElseGet` para crear la entrada con 0.0 si el investigador aún no tiene CargaTrabajo asignada; garantiza que el template nunca recibe null en `${carga}`.
- El total semanal se calcula directamente en el template con SpEL: `${carga.horasDocencia + carga.horasInvestigacion + carga.horasActividades}`.
- La URL es la misma que la del coordinador (`/carga-trabajo`); la bifurcación ocurre en el controller comparando `investigador.getRol()`.
