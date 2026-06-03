# abrirInvestigador — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirInvestigador()

## Propósito

Recuperar y mostrar el perfil completo de un investigador dado su identificador.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirInvestigador-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirInvestigador.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| InvestigadorView (azul) | `InvestigadorController` `@Controller` | Recibe GET /investigadores/{id}; carga el investigador y devuelve investigador.html |
| InvestigadorController (amarillo) | `InvestigadorService` `@Service` | Recupera el investigador por id con orElseThrow() |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | Ejecuta SELECT por id |
| Investigador (naranja) | `Investigador` `@Entity` | Tabla investigadores en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/{id} | Muestra el perfil del investigador |

## Rutas adicionales — cambio de rol

| Método | URL | Acción | Restricción |
|---|---|---|---|
| POST | /investigadores/{id}/cambiar-rol | Alterna el rol entre COORDINADOR e INVESTIGADOR | Solo COORDINADOR |

El endpoint lo gestiona `CambiarRolController`, protegido con `@PreAuthorize("hasRole('COORDINADOR')")`. El botón solo se muestra en la vista con `sec:authorize="hasRole('COORDINADOR')"`.

## Decisiones de diseño

- Si el id no existe, `orElseThrow()` lanza excepción y Spring devuelve 404.
- El perfil muestra: id, nombre, apellidos, campo, carrera, master y rol.
- La vista ofrece enlace de vuelta a la lista `/investigadores`.
- El cambio de rol es un POST que alterna el valor: COORDINADOR → INVESTIGADOR y viceversa. La restricción se aplica en dos capas: UI (`sec:authorize`) y backend (`@PreAuthorize`), habilitado con `@EnableMethodSecurity` en `SecurityConfig`.
