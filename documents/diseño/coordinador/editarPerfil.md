# editarPerfil — Diseño (Coordinador)

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: editarPerfil()

## Propósito

Presentar un formulario editable con los datos de un perfil de investigador, incluyendo el campo `rol`. El coordinador puede editar cualquier investigador (vía `/investigadores/{id}/editar`) o su propio perfil (vía `/perfil/editar`, sin campo `rol`).

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/editarPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/editarPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarPerfilView (azul) | `EditarPerfilController` `@Controller` | Recibe GET y POST; carga/guarda el investigador |
| EditarPerfilController (amarillo) | `InvestigadorService` `@Service` | Recupera y actualiza el investigador |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | Ejecuta SELECT y UPDATE por id |
| Investigador (naranja) | `Investigador` `@Entity` | Tabla investigadores en H2 |

## Rutas

| Método | URL | Acción | Restricción |
|---|---|---|---|
| GET | /investigadores/{id}/editar | Muestra el formulario del investigador | Solo COORDINADOR |
| POST | /investigadores/{id}/editar | Guarda los cambios incluyendo rol | Solo COORDINADOR |
| GET | /perfil/editar | Muestra el formulario del propio perfil | Autenticado |
| POST | /perfil/editar | Guarda cambios sin modificar rol | Autenticado |

## Decisiones de diseño

- El campo `rol` (select entre INVESTIGADOR/COORDINADOR) solo se muestra en el formulario cuando `esPropioPeril = false` y el actor tiene rol COORDINADOR (`sec:authorize` + `th:if`). Así el cambio de rol queda integrado en la edición del perfil, sin endpoint separado.
- Los endpoints `/investigadores/{id}/editar` están protegidos con `@PreAuthorize("hasRole('COORDINADOR')")`.
- El formulario usa `@RequestParam` individuales (no binding directo a `Investigador`) para evitar que campos sensibles como `password` sean sobreescritos.
- Tras guardar, redirige a `/investigadores/{id}/opciones` o `/perfil/opciones` según el contexto.
