# abrirEntregables — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirEntregables()

## Propósito

Recuperar y mostrar el listado de entregables asociados a un proyecto concreto.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/abrirEntregables-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirEntregables.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| AbrirEntregablesView (azul) | `EntregablesController` `@Controller` | GET devuelve el listado de entregables del proyecto |
| EntregableController (amarillo) | `EntregableService` `@Service` | Llama a findByProyectoId |
| EntregableRepository (naranja) | `EntregableRepository` JpaRepository | Ejecuta SELECT filtrado por proyectoId |
| ProyectoRepository (naranja) | `ProyectoRepository` JpaRepository | Obtiene el proyecto para el encabezado |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{proyectoId}/entregables | Muestra el listado de entregables del proyecto |

## Decisiones de diseño

- Los entregables siempre se muestran en el contexto de un proyecto; el `proyectoId` viaja en la URL.
- `EntregableRepository` expone `findByProyectoId(Long proyectoId)` que Spring Data deriva automáticamente.
- El proyecto se carga también para mostrar su título en el encabezado de la vista.
