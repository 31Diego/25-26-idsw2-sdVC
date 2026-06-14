# abrirPublicacion — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirPublicacion()

## Propósito

Recuperar y mostrar el detalle de una publicación: título, contenido, autor, fecha y lista de respuestas asociadas.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Controlador de publicaciones | PublicacionController @Controller | Atiende GET /publicaciones/{id} y prepara el modelo |
| Servicio de publicaciones | PublicacionService @Service | `obtenerPorId(id)` recupera la publicación con sus respuestas LAZY |
| Repositorio de publicaciones | PublicacionRepository JpaRepository | Ejecuta SELECT * FROM publicaciones WHERE id=? |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /publicaciones/{id} | Muestra el detalle de la publicación con su lista de respuestas |

## Decisiones de diseño

- El repositorio devuelve la `Publicacion` con las respuestas cargadas en LAZY; el acceso en el template se resuelve por open-in-view.
- La publicación se añade al modelo con `model.addAttribute("publicacion", pub)`.
- La vista `publicacion.html` muestra título, contenido, autor, fecha y lista de respuestas.
- El formulario de `responderPublicacion` está embebido en la misma vista.
