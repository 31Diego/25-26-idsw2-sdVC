# abrirMisPublicaciones — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirMisPublicaciones()

## Propósito

Recuperar y mostrar el listado de publicaciones cuyo autor es el investigador autenticado.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirMisPublicaciones.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirMisPublicaciones.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Controlador de publicaciones | PublicacionController @Controller | Atiende GET /mis-publicaciones; filtra por autor y devuelve mis-publicaciones.html |
| Servicio de publicaciones | PublicacionService @Service | `obtenerPorAutor(investigador)` devuelve solo las publicaciones del autor |
| Repositorio de publicaciones | PublicacionRepository JpaRepository | Ejecuta SELECT * FROM publicaciones WHERE autor_id=? vía findByAutor(investigador) |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /mis-publicaciones | Lista las publicaciones del investigador autenticado |

## Decisiones de diseño

- El autor se obtiene del `@AuthenticationPrincipal`; el filtrado se delega al servicio con `obtenerPorAutor(investigador)`.
- La URL `/mis-publicaciones` es compartida entre ambos actores.
- Desde la lista se puede navegar al detalle de cada publicación y crear una nueva.
