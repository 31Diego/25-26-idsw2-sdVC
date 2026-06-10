# responderPublicacion — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: responderPublicacion()

## Propósito

Registrar una respuesta de texto a una publicación existente. El formulario está embebido en la vista de la publicación. Comportamiento idéntico al del investigador.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/responderPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/responderPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| PublicacionView | `PublicacionController` `@Controller` | Recibe POST /publicaciones/{id}/responder; delega en el service y redirige |
| PublicacionService | `PublicacionService` `@Service` | Crea y guarda la `Respuesta` via `responder(id, contenido, autor)` |
| RespuestaRepository | `RespuestaRepository` JpaRepository | Ejecuta INSERT INTO respuestas |
| Respuesta | `Respuesta` `@Entity` | Entidad con `contenido`, `fecha` (LocalDate.now()), `autor` y `publicacion` |

## Rutas

| Método | URL | Acción |
|---|---|---|
| POST | /publicaciones/{id}/responder | Guarda la respuesta y redirige a GET /publicaciones/{id} |

## Decisiones de diseño

- El autor de la respuesta se obtiene del `@AuthenticationPrincipal` — el controller no recibe el autor como parámetro de formulario.
- La fecha se asigna en el service con `LocalDate.now()`, no viene del formulario.
- Tras el POST se redirige al GET de la misma publicación (patrón Post/Redirect/Get), evitando reenvío accidental del formulario.
