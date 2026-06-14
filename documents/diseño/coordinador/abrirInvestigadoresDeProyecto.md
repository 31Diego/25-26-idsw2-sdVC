# abrirInvestigadoresDeProyecto — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirInvestigadoresDeProyecto()

## Propósito

Recuperar y mostrar la lista de investigadores asignados a un proyecto concreto.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirInvestigadoresDeProyecto-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirInvestigadoresDeProyecto.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Controlador de proyectos | ProyectoController @Controller | Atiende GET /proyectos/{id}/investigadores y prepara el modelo |
| Servicio de proyectos | ProyectoService @Service | `obtenerProyecto(id)` recupera el proyecto con su colección de investigadores |
| Repositorio de proyectos | ProyectoRepository JpaRepository | Ejecuta SELECT * FROM proyectos WHERE id = ? incluyendo investigadores por @ManyToMany |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{id}/investigadores | Muestra los investigadores del proyecto |

## Decisiones de diseño

- El id del proyecto llega como `@PathVariable`.
- Se añaden al modelo `"proyecto"` y `"investigadores"` (obtenidos de `proyecto.getInvestigadores()`).
- No se necesita un método adicional en el repositorio: la relación `@ManyToMany` ya carga la colección de investigadores al obtener el proyecto.
- La vista `investigadores-proyecto.html` muestra la lista con enlace a cada perfil de investigador.
