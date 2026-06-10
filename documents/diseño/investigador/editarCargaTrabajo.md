# editarCargaTrabajo — Diseño · Investigador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: editarCargaTrabajo()

## Propósito

Mostrar el formulario de edición de la carga de trabajo propia del investigador autenticado y persistir los cambios.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/editarCargaTrabajo-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/editarCargaTrabajo.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarCargaTrabajoView (GET) | `CargaTrabajoController` `@Controller` | Recibe GET /carga-trabajo/editar; carga la CargaTrabajo del investigador autenticado |
| EditarCargaTrabajoView (POST) | `CargaTrabajoController` `@Controller` | Recibe POST con los tres campos; delega en el service y redirige a /carga-trabajo |
| CargaTrabajoService | `CargaTrabajoService` `@Service` | `obtenerOCrearPorInvestigador` + `actualizar(id, horas…)` |
| CargaTrabajoRepository | `CargaTrabajoRepository` JpaRepository | `findByInvestigadorId` para GET; `findById` + `save` para UPDATE |
| CargaTrabajo | `CargaTrabajo` `@Entity` | Tabla cargas_trabajo |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /carga-trabajo/editar | Muestra el formulario de edición con los datos actuales del investigador |
| POST | /carga-trabajo/editar | Persiste los cambios y redirige a /carga-trabajo |

## Decisiones de diseño

- La URL `/carga-trabajo/editar` es exclusiva del investigador para su propia carga; el coordinador usa `/investigadores/{id}/carga-trabajo/editar`.
- El investigador llega aquí desde el enlace "Editar carga de trabajo" en la vista `/carga-trabajo`.
- El template `editar-carga-trabajo.html` es compartido: si `${investigador}` está en el model (coordinador), muestra el nombre del investigador y apunta el POST a `/investigadores/{id}/…`; si no, apunta a `/carga-trabajo/editar`.
- Tras guardar, redirige a `/carga-trabajo` (resumen personal).
