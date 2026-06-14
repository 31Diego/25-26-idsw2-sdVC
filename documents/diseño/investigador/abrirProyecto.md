# abrirProyecto — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirProyecto()

## Propósito

Mostrar el detalle de un proyecto al investigador autenticado, verificando que pertenece al proyecto antes de mostrarlo.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirProyecto-investigador-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirProyecto.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Controlador de proyectos | ProyectoController @Controller | Atiende GET /proyectos/{id} con @AuthenticationPrincipal investigador |
| Servicio de proyectos | ProyectoService @Service | `obtenerProyecto(id)` recupera el proyecto |
| Repositorio de proyectos | ProyectoRepository JpaRepository | Ejecuta SELECT * FROM proyectos WHERE id = ? |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{id} | Muestra el detalle del proyecto si el investigador es miembro |

## Decisiones de diseño

- Tras cargar el proyecto, el controlador evalúa `sinAcceso(proyecto, investigador)`.
- Flujo alternativo `alt`: si `sinAcceso` → 302 redirect a `/proyectos`; si `!sinAcceso` → `model.addAttribute("proyecto", proyecto)` y vista `proyecto.html` sin botones de gestión (Editar, Eliminar, Agregar investigador).
- La vista usa `sec:authorize="hasRole('COORDINADOR')"` para ocultar las acciones de gestión al investigador.
