# abrirSolicitudEliminacionPerfil — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirSolicitudEliminacionPerfil(id)

## Propósito

Recuperar y mostrar el detalle de una solicitud de eliminación de perfil concreta, permitiendo al coordinador navegar al perfil del investigador implicado para resolver la solicitud.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirSolicitudEliminacionPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirSolicitudEliminacionPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| SolicitudEliminacionView (azul) | `SolicitudEliminacionController` `@Controller` | Recibe GET /solicitudes-eliminacion/{id}; pone la solicitud en el Model y devuelve solicitud-eliminacion.html |
| EliminacionController (amarillo) | `SolicitudEliminacionService` `@Service` | Llama a findById(id) |
| SolicitudEliminacionRepository (naranja) | `SolicitudEliminacionRepository` JpaRepository | Ejecuta SELECT WHERE id = ? |
| SolicitudEliminacion (naranja) | `SolicitudEliminacion` `@Entity` | Entidad con investigador, motivo, fecha, estado |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /solicitudes-eliminacion/{id} | Muestra el detalle de una solicitud |

## Decisiones de diseño

- Acceso restringido a `COORDINADOR` mediante `@PreAuthorize("hasRole('COORDINADOR')")`.
- La vista enlaza a `/investigadores/{investigador.id}/opciones` para que el coordinador pueda resolver la solicitud accediendo al perfil.
- El servicio `obtenerSolicitud(id)` ya existía de la implementación de `abrirSolicitudesEliminacionPerfil`.
