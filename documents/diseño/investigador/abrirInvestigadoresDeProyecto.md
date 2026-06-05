# abrirInvestigadoresDeProyecto — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirInvestigadoresDeProyecto(id)

## Propósito

Recuperar y mostrar la lista de investigadores asignados a un proyecto concreto al investigador autenticado. Solo puede acceder si pertenece al proyecto; en caso contrario es redirigido a su lista de proyectos.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirInvestigadoresDeProyecto-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirInvestigadoresDeProyecto.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| InvestigadoresProyectoView (azul) | `InvestigadoresProyectoController` `@Controller` | Recibe GET /proyectos/{id}/investigadores; verifica membresía y devuelve investigadores-proyecto.html |
| InvestigadorController (amarillo) | `ProyectoService` `@Service` | Llama a obtenerProyecto(id); los investigadores se obtienen de la relación @ManyToMany ya cargada |
| ProyectoRepository (naranja) | `ProyectoRepository` JpaRepository | Ejecuta SELECT WHERE id = ? |
| Proyecto + Investigador (naranja) | `Proyecto` + `Investigador` `@Entity` | Relación ManyToMany; proyecto.getInvestigadores() devuelve la lista |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{id}/investigadores | Muestra los investigadores del proyecto (si el investigador es miembro) |

## Decisiones de diseño

- El id llega como `@PathVariable Long id`; el investigador autenticado llega como `@AuthenticationPrincipal`.
- Si el investigador no pertenece al proyecto, se redirige a `/proyectos` (mismo comportamiento que `abrirProyecto`).
- No se necesita nuevo método en el servicio: `proyectoService.obtenerProyecto(id)` ya devuelve la entidad con su colección `investigadores`.
- La vista es idéntica a la del coordinador (`investigadores-proyecto.html`) pero sin acceso a acciones de gestión — el template es compartido.
- El controlador es el mismo que el del coordinador: un único `InvestigadoresProyectoController` con lógica de rol.
