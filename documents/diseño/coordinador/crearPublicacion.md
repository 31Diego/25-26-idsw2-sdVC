# crearPublicacion — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: crearPublicacion()

## Propósito

Presentar un formulario vacío, recoger los datos introducidos por el coordinador y persistir la nueva publicación con él como autor.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/crearPublicacion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/crearPublicacion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| CrearPublicacionView | `PublicacionController` `@Controller` | GET muestra el formulario vacío; POST persiste y redirige |
| PublicacionService | `PublicacionService` `@Service` | `crear(titulo, contenido, autor)` — instancia, fija fecha actual y persiste |
| PublicacionRepository | `PublicacionRepository` JpaRepository | INSERT INTO publicaciones |
| Publicacion | `Publicacion` `@Entity` | Tabla publicaciones |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /mis-publicaciones/crear | Muestra el formulario de creación |
| POST | /mis-publicaciones/crear | Persiste la publicación y redirige al detalle |

## Decisiones de diseño

- El autor se obtiene de `@AuthenticationPrincipal`, no del formulario — el usuario no puede falsificar la autoría.
- La fecha se fija en el servicio con `LocalDate.now()`.
- Tras guardar, redirige a `/mis-publicaciones/{id}` para mostrar la publicación recién creada (PRG pattern).
- Comportamiento idéntico para coordinador e investigador; la URL es compartida.
