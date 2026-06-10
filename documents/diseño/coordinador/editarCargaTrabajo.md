# editarCargaTrabajo — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: editarCargaTrabajo()

## Propósito

Mostrar el formulario de edición de carga de trabajo de un investigador concreto y persistir los cambios introducidos por el coordinador.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/editarCargaTrabajo-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/editarCargaTrabajo.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarCargaTrabajoView (GET) | `CargaTrabajoController` `@Controller` | Recibe GET /investigadores/{id}/carga-trabajo/editar; carga el formulario con los valores actuales |
| EditarCargaTrabajoView (POST) | `CargaTrabajoController` `@Controller` | Recibe POST con los tres campos; delega en el service y redirige a /carga-trabajo |
| CargaTrabajoService | `CargaTrabajoService` `@Service` | `obtenerOCrearPorInvestigador` + `actualizar(id, horas…)` |
| CargaTrabajoRepository | `CargaTrabajoRepository` JpaRepository | `findByInvestigadorId` para GET; `findById` + `save` para UPDATE |
| CargaTrabajo | `CargaTrabajo` `@Entity` | Tabla cargas_trabajo |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/{id}/carga-trabajo/editar | Muestra el formulario de edición con datos actuales |
| POST | /investigadores/{id}/carga-trabajo/editar | Persiste los cambios y redirige a /carga-trabajo |

## Decisiones de diseño

- Protegido con `@PreAuthorize("hasRole('COORDINADOR')")` en ambos métodos del controller.
- El coordinador llega aquí desde el enlace "Editar" de la columna Acciones en la tabla global de `/carga-trabajo`.
- `CargaTrabajoService.actualizar` recibe el `id` de la `CargaTrabajo` (no el del `Investigador`); el controller obtiene ese id llamando primero a `obtenerOCrearPorInvestigador`.
- Tras guardar, redirige siempre a `/carga-trabajo` (vista global del coordinador).
