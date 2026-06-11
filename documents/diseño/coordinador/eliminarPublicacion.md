# eliminarPublicacion — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: eliminarPublicacion()

## Propósito

Mostrar los datos de la publicación como pantalla de confirmación y borrarla definitivamente tras la acción del coordinador. Solo el coordinador puede eliminar publicaciones generales.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/eliminarPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/eliminarPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EliminarPublicacionView | `PublicacionController` `@Controller` | GET muestra la confirmación; POST ejecuta el borrado |
| PublicacionService | `PublicacionService` `@Service` | `obtenerPorId(id)` para mostrar qué se elimina; `eliminar(id)` para el borrado |
| PublicacionRepository | `PublicacionRepository` JpaRepository | SELECT (carga confirmación) + DELETE |
| Publicacion | `Publicacion` `@Entity` | Tabla publicaciones en H2; cascada ALL sobre respuestas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /publicaciones/{id}/eliminar | Muestra la página de confirmación con los datos de la publicación |
| POST | /publicaciones/{id}/eliminar | Ejecuta el DELETE |

## Decisiones de diseño

- Ambos endpoints protegidos con `@PreAuthorize("hasRole('COORDINADOR')")`.
- El GET carga la publicación y la muestra para que el coordinador confirme que es la correcta.
- El POST llama a `deleteById(id)` y redirige a `/publicaciones` (PRG pattern).
- La entidad `Publicacion` tiene `cascade = CascadeType.ALL, orphanRemoval = true` sobre las respuestas; al eliminar la publicación, sus respuestas se borran automáticamente en cascada — no hace falta lógica adicional.
- Thymeleaf genera un `<form method="post">` con botón "Confirmar eliminación" y un enlace "Cancelar" que vuelve a `/publicaciones/{id}`.
