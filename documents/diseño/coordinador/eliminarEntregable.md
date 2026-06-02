# eliminarEntregable — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: eliminarEntregable()

## Propósito

Mostrar la confirmación de eliminación y borrar el entregable (y su archivo adjunto si existe) tras la confirmación.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/eliminarEntregable-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/eliminarEntregable.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EliminarEntregableView (azul) | `EliminarEntregableController` `@Controller` | GET muestra confirmación; POST elimina |
| EntregableController (amarillo) | `EntregableService` `@Service` | findById para mostrar datos; elimina registro y archivo |
| EntregableRepository (naranja) | `EntregableRepository` JpaRepository | SELECT (GET) y DELETE (POST) |
| Sistema de ficheros | `./archivos/` | Borra el archivo adjunto si existe |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{proyectoId}/entregables/{id}/eliminar | Muestra la pantalla de confirmación |
| POST | /proyectos/{proyectoId}/entregables/{id}/eliminar | Elimina el entregable |

## Decisiones de diseño

- El GET carga el entregable para mostrar título, tipo y estado en la pantalla de confirmación.
- El servicio borra el archivo físico con `Files.deleteIfExists()` antes de llamar a `deleteById`.
- Si el archivo ya no existe en disco, `deleteIfExists` no lanza excepción (operación segura).
- Tras eliminar, redirige a `/proyectos/{proyectoId}/entregables` (PRG).
