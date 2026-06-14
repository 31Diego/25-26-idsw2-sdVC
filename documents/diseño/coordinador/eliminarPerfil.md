# eliminarPerfil — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: eliminarPerfil()

## Propósito

Eliminar definitivamente el perfil de un investigador del sistema, incluyendo su eliminación de todos los proyectos y el borrado de sus solicitudes de eliminación pendientes. El coordinador no puede eliminar su propio perfil.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/eliminarPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/eliminarPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Controlador de eliminación | EliminacionController @Controller POST /investigadores/{id}/eliminar-perfil | Recibe @AuthenticationPrincipal coordinador y delega en el servicio |
| Servicio de investigador | InvestigadorService @Service | `eliminarPerfil(coordinador.getId(), id)` orquesta la eliminación en tres pasos |
| Servicio de proyectos | ProyectoService @Service | `eliminarInvestigadorDeTodosLosProyectos(targetId)` retira al investigador de cada proyecto |
| Servicio de solicitudes | SolicitudEliminacionService @Service | `eliminarPorInvestigador(targetId)` borra las solicitudes asociadas |
| Repositorio de proyectos | ProyectoRepository JpaRepository | findAll() y save(proyecto) para actualizar las relaciones |
| Repositorio de solicitudes | SolicitudEliminacionRepository JpaRepository | findByInvestigadorId y deleteAll |
| Repositorio de investigadores | InvestigadorRepository JpaRepository | deleteById(targetId) |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| POST | /investigadores/{id}/eliminar-perfil | Elimina el perfil del investigador en tres pasos y redirige |

## Decisiones de diseño

- Nota anti-autoeliminación en `InvestigadorService`: si `actorId == targetId`, no se ejecuta la eliminación.
- La eliminación se realiza en tres pasos en orden: 1) quitar al investigador de todos los proyectos; 2) eliminar sus solicitudes de eliminación; 3) eliminar el investigador con `deleteById(targetId)`.
- `eliminarInvestigadorDeTodosLosProyectos(targetId)` hace `findAll()` de proyectos y para cada uno que contenga al investigador hace `save(proyecto)` que actualiza la tabla `proyecto_investigador`.
- Tras la eliminación, redirige a `/solicitudes-eliminacion` (302).
