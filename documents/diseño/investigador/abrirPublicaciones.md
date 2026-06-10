# abrirPublicaciones — Diseño · Investigador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirPublicaciones()

## Propósito

Recuperar y mostrar el listado completo de publicaciones del sistema (título, autor, fecha). Comportamiento idéntico al del coordinador; la URL es compartida.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirPublicaciones-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirPublicaciones.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| PublicacionesView | `PublicacionController` `@Controller` | Recibe GET /publicaciones; añade la lista al Model y devuelve publicaciones.html |
| PublicacionService | `PublicacionService` `@Service` | Devuelve todas las publicaciones vía `obtenerTodas()` |
| PublicacionRepository | `PublicacionRepository` JpaRepository | Ejecuta SELECT * FROM publicaciones |
| Publicacion | `Publicacion` `@Entity` | Tabla publicaciones; relación `@ManyToOne` hacia `Investigador` (autor) |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /publicaciones | Lista todas las publicaciones del sistema |

## Decisiones de diseño

- La URL `/publicaciones` es compartida entre ambos actores; no hay bifurcación por rol.
- La relación `@ManyToOne` entre `Publicacion` e `Investigador` se carga por defecto en EAGER en JPA, por lo que el template accede a `pub.autor.nombre` directamente sin consulta adicional.
- El DataLoader inicializa tres publicaciones de prueba al arrancar la aplicación por primera vez.
