# abrirProyecto — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirProyecto(id)

## Propósito

Recuperar y mostrar los datos de un proyecto concreto identificado por su id.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/abrirProyecto-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirProyecto.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| ProyectoView (azul) | `ProyectoController` `@Controller` | Recibe GET /proyectos/{id}; pone el proyecto en el Model y devuelve proyecto.html |
| ProyectoController (amarillo) | `ProyectoService` `@Service` | Llama a findById(id); lanza excepción si no existe |
| ProyectoRepository (naranja) | `ProyectoRepository` JpaRepository | Ejecuta SELECT WHERE id = ? |
| Proyecto (naranja) | `Proyecto` `@Entity` | Tabla proyectos en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{id} | Muestra un proyecto concreto |

## Decisiones de diseño

- El id llega como `@PathVariable Long id`.
- El servicio usa `findById(id).orElseThrow()` — si el id no existe Spring devuelve 500 (se manejará con `@ControllerAdvice` en el futuro).
- La vista incluye botones de navegación a: editarProyecto, eliminarProyecto, abrirEntregables, agregarInvestigador, abrirInvestigadoresDeProyecto. Los tres últimos se implementarán en sus propios casos de uso.
