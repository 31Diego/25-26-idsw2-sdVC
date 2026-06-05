# abrirOpcionesPerfil — Diseño (Coordinador)

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirOpcionesPerfil()

## Propósito

Mostrar las opciones disponibles sobre un perfil de investigador: editar, cambiar rol y solicitar eliminación. También accesible sobre el propio perfil del coordinador desde el panel principal.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirOpcionesPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirOpcionesPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| OpcionesPerfilView (azul) | `OpcionesPerfilController` `@Controller` | Recibe GET; carga el investigador y devuelve opciones-perfil.html |
| OpcionesPerfilController (amarillo) | `InvestigadorService` `@Service` | Recupera el investigador por id u obtenerInvestigadorPorUsername |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | Ejecuta SELECT por id o por username |
| Investigador (naranja) | `Investigador` `@Entity` | Tabla investigadores en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/{id}/opciones | Muestra opciones del perfil de un investigador específico |
| GET | /perfil/opciones | Muestra opciones del propio perfil del coordinador |

## Decisiones de diseño

- El modelo incluye el atributo `esPropioPeril` (boolean): `false` cuando se accede vía `/investigadores/{id}/opciones`, `true` vía `/perfil/opciones`.
- El botón de cambiar rol (`POST /investigadores/{id}/cambiar-rol`) solo se muestra cuando `esPropioPeril = false` y el actor tiene rol COORDINADOR (`sec:authorize` + `th:if`).
- Para `/perfil/opciones`, el investigador se obtiene por username desde el `Authentication` (evita usar datos de sesión desactualizados).
- La vista muestra enlace de vuelta al investigador (`/investigadores/{id}`) solo cuando `esPropioPeril = false`.
