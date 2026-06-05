# eliminarInvestigador — Diseño (Coordinador)

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: eliminarInvestigador()

## Propósito

Mostrar una página de confirmación antes de retirar a un investigador del equipo de un proyecto, y ejecutar la operación tras la confirmación. No elimina la entidad `Investigador`, solo rompe la relación `@ManyToMany` en `proyecto_investigador`.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/eliminarInvestigador-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/eliminarInvestigador.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EliminarInvestigadorView (azul) | `EliminarInvestigadorController` `@Controller` | GET muestra la confirmación; POST ejecuta la retirada |
| EliminarInvestigadorController (amarillo) | `ProyectoService` + `InvestigadorService` `@Service` | Carga proyecto e investigador; delega la operación |
| ProyectoRepository (naranja) | `ProyectoRepository` JpaRepository | SELECT del proyecto + UPDATE de la tabla de unión |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | SELECT del investigador |
| Proyecto / Investigador (naranja) | `Proyecto` / `Investigador` `@Entity` | Relación `@ManyToMany` en tabla `proyecto_investigador` |

## Rutas

| Método | URL | Acción | Restricción |
|---|---|---|---|
| GET | /proyectos/{pId}/investigadores/{iId}/eliminar | Muestra confirmación con datos del proyecto e investigador | Solo COORDINADOR |
| POST | /proyectos/{pId}/investigadores/{iId}/eliminar | Retira al investigador del proyecto | Solo COORDINADOR |

## Decisiones de diseño

- El GET carga tanto el proyecto como el investigador para que la confirmación muestre los nombres y no solo los IDs.
- El POST llama a `ProyectoService.eliminarInvestigador(proyecto, investigador)`, que hace `remove` de la lista y `save`. JPA se encarga de borrar la fila de `proyecto_investigador`.
- Ambos endpoints están protegidos con `@PreAuthorize("hasRole('COORDINADOR')")`.
- Tras el POST redirige a `/proyectos/{pId}` (PRG pattern) para reflejar el equipo actualizado.
