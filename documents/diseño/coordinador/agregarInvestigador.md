# agregarInvestigador — Diseño (Coordinador)

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: agregarInvestigador()

## Propósito

Mostrar la lista de investigadores que aún no pertenecen al proyecto y permitir al coordinador añadir uno de ellos a la relación `proyecto_investigador`.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/agregarInvestigador-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/agregarInvestigador.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| AgregarInvestigadorView (azul) | `AgregarInvestigadorController` `@Controller` | Recibe GET y POST; orquesta la carga y el guardado |
| AgregarInvestigadorController (amarillo) | `ProyectoService` + `InvestigadorService` `@Service` | Obtiene el proyecto y la lista filtrada de disponibles |
| ProyectoRepository (naranja) | `ProyectoRepository` JpaRepository | SELECT y UPDATE del proyecto |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | SELECT de todos los investigadores |
| Proyecto / Investigador (naranja) | `Proyecto` / `Investigador` `@Entity` | Relación `@ManyToMany` en tabla `proyecto_investigador` |

## Rutas

| Método | URL | Acción | Restricción |
|---|---|---|---|
| GET | /proyectos/{id}/investigadores/agregar | Muestra investigadores disponibles | Solo COORDINADOR |
| POST | /proyectos/{id}/investigadores/agregar | Añade el investigador seleccionado al proyecto | Solo COORDINADOR |

## Decisiones de diseño

- El GET filtra en memoria los investigadores que ya pertenecen al proyecto, usando `proyecto.getInvestigadores().contains(inv)`. Evita mostrar duplicados sin consulta adicional.
- El POST recibe `investigadorId` como `@RequestParam` y delega la operación a `ProyectoService.agregarInvestigador(proyecto, investigador)`, que hace `add` a la lista y llama a `save`.
- Ambos endpoints están protegidos con `@PreAuthorize("hasRole('COORDINADOR')")`.
- Tras el POST redirige al proyecto (`/proyectos/{id}`) para confirmar visualmente el cambio.
