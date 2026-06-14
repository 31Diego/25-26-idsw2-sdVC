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
| Controlador de investigadores | InvestigadorController @Controller | Atiende GET /investigadores/{id} y prepara el modelo |
| Servicio de investigador | InvestigadorService @Service | `obtenerInvestigador(id)` recupera el investigador por id |
| Repositorio de investigadores | InvestigadorRepository JpaRepository | Ejecuta SELECT * FROM investigadores WHERE id = ? vía findById(id) |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/{id} | Muestra el perfil completo del investigador |

## Decisiones de diseño

- El id llega como `@PathVariable Long id`.
- El investigador se añade al modelo con `model.addAttribute("investigador", investigador)`.
- La vista `investigador.html` muestra el perfil y enlaza a las opciones del perfil (`/investigadores/{id}/opciones`).
