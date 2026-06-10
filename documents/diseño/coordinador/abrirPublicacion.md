# abrirPublicacion — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirPublicacion()

## Propósito

Recuperar y mostrar el detalle de una publicación: título, contenido, autor, fecha y lista de respuestas asociadas. Comportamiento idéntico al del investigador; la URL es compartida.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| PublicacionView | `PublicacionController` `@Controller` | Recibe GET /publicaciones/{id}; pone la publicación en el Model y devuelve publicacion.html |
| PublicacionService | `PublicacionService` `@Service` | Recupera la publicación vía `obtenerPorId(id)` |
| PublicacionRepository | `PublicacionRepository` JpaRepository | Ejecuta SELECT * FROM publicaciones WHERE id=? |
| Publicacion | `Publicacion` `@Entity` | Entidad con relación `@OneToMany` hacia `Respuesta` (LAZY por defecto) |
| Respuesta | `Respuesta` `@Entity` | Cargada al acceder a `publicacion.respuestas` en el template (open-in-view activo) |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /publicaciones/{id} | Muestra el detalle de la publicación con sus respuestas |

## Decisiones de diseño

- La relación `@OneToMany` entre `Publicacion` y `Respuesta` es LAZY por defecto. Spring Boot activa `open-in-view=true`, por lo que Thymeleaf puede acceder a `publicacion.respuestas` sin N+1 adicional dentro del mismo request.
- Las respuestas se ordenan por `fecha ASC` mediante `@OrderBy`, sin necesidad de ordenación en el template.
- El formulario de `responderPublicacion` está embebido en la misma vista; no se necesita una pantalla separada.
