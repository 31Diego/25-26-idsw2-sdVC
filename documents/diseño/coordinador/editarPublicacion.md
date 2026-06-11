# editarPublicacion — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: editarPublicacion()

## Propósito

Cargar una publicación existente en un formulario pre-rellenado, aplicar las modificaciones del coordinador y persistir el resultado. Solo el coordinador puede editar publicaciones generales.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/editarPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/editarPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarPublicacionView | `PublicacionController` `@Controller` | GET carga el formulario pre-rellenado; POST aplica los cambios y persiste |
| PublicacionService | `PublicacionService` `@Service` | `obtenerPorId(id)` para la carga; `actualizar(id, titulo, contenido)` para el guardado |
| PublicacionRepository | `PublicacionRepository` JpaRepository | SELECT (carga) + UPDATE (guardado) |
| Publicacion | `Publicacion` `@Entity` | Tabla publicaciones en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /publicaciones/{id}/editar | Muestra el formulario pre-rellenado |
| POST | /publicaciones/{id}/editar | Aplica los cambios y persiste |

## Decisiones de diseño

- Ambos endpoints protegidos con `@PreAuthorize("hasRole('COORDINADOR')")` — el investigador recibe 403 si intenta acceder directamente por URL.
- El GET hace `findById(id)` y pasa la publicación al template con `th:object`.
- El `actualizar()` del servicio carga el objeto de BD y copia campo a campo desde el formulario, evitando que el POST sobrescriba campos no editables (autor, fecha, id).
- Tras guardar, redirige a `/publicaciones/{id}` (PRG pattern).
- Los enlaces Editar/Eliminar en los templates se envuelven con `sec:authorize="hasRole('COORDINADOR')"` para no mostrarlos al investigador.
