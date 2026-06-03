# crearInvestigador — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: crearInvestigador()

## Propósito

Mostrar el formulario de creación de un nuevo investigador y persistirlo con sus datos mínimos obligatorios.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/crearInvestigador-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/crearInvestigador.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| CrearInvestigadorView (azul) | `CrearInvestigadorController` `@Controller` | GET devuelve el formulario vacío; POST recibe los datos y guarda |
| InvestigadorController (amarillo) | `InvestigadorService` `@Service` | Llama a guardarInvestigador(), que codifica la contraseña antes de persistir |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | Ejecuta INSERT INTO investigadores |
| Investigador (naranja) | `Investigador` `@Entity` | Tabla investigadores en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/nuevo | Muestra el formulario vacío |
| POST | /investigadores/nuevo | Guarda el nuevo investigador |

## Campos del formulario

| Campo | Tipo | Requerido |
|---|---|---|
| nombre | String | Sí |
| apellidos | String | No |
| username | String | Sí |
| password | String | Sí |
| campo | String | Sí |
| carrera | String | No |
| master | String | No |
| email | String | No |
| institucion | String | No |

## Decisiones de diseño

- El rol se fija en "INVESTIGADOR" en el controller; el coordinador no puede elegirlo desde el formulario.
- El coordinador fija la contraseña inicial en el formulario; el servicio la codifica con BCrypt antes de persistir.
- El formulario usa `th:object="${investigador}"` con binding automático de Spring.
- Tras guardar, redirige a `/investigadores/{id}` del nuevo investigador (PRG pattern).
