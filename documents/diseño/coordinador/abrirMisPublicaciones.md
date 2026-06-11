# abrirMisPublicaciones — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirMisPublicaciones()

## Propósito

Recuperar y mostrar el listado de publicaciones cuyo autor es el coordinador autenticado. Comportamiento idéntico al del investigador; la URL es compartida.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirMisPublicaciones-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirMisPublicaciones.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| MisPublicacionesView | `PublicacionController` `@Controller` | Recibe GET /mis-publicaciones; filtra por autor y devuelve mis-publicaciones.html |
| PublicacionService | `PublicacionService` `@Service` | Devuelve las publicaciones del autor vía `obtenerPorAutor(investigador)` |
| PublicacionRepository | `PublicacionRepository` JpaRepository | Ejecuta `findByAutor(investigador)` |
| Publicacion | `Publicacion` `@Entity` | Tabla publicaciones; relación `@ManyToOne` hacia `Investigador` (autor) |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /mis-publicaciones | Lista las publicaciones del usuario autenticado |

## Decisiones de diseño

- La URL `/mis-publicaciones` es compartida entre ambos actores; el filtrado se hace por el autor obtenido de `@AuthenticationPrincipal`.
- `PublicacionRepository` añade `findByAutor(Investigador autor)` para la consulta filtrada.
- Desde la lista se puede navegar a cada publicación propia y crear una nueva.
