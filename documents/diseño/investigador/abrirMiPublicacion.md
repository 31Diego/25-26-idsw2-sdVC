# abrirMiPublicacion — Diseño · Investigador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirMiPublicacion()

## Propósito

Mostrar el detalle de una publicación propia del investigador, con acceso directo a las acciones de editar y eliminar.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirMiPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirMiPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| MiPublicacionView | `PublicacionController` `@Controller` | Recibe GET /mis-publicaciones/{id}; verifica propiedad y devuelve mi-publicacion.html |
| PublicacionService | `PublicacionService` `@Service` | `obtenerPorId(id)` para cargar la publicación |
| PublicacionRepository | `PublicacionRepository` JpaRepository | SELECT por id |
| Publicacion | `Publicacion` `@Entity` | Tabla publicaciones |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /mis-publicaciones/{id} | Muestra el detalle de la publicación propia |

## Decisiones de diseño

- El endpoint verifica que la publicación pertenece al usuario autenticado; si no es así, redirige a `/mis-publicaciones`.
- Los enlaces Editar y Eliminar apuntan a `/publicaciones/{id}/editar` y `/publicaciones/{id}/eliminar`, reutilizando la lógica ya construida.
- El control de acceso en esos endpoints permite al autor editar/eliminar su propia publicación.
