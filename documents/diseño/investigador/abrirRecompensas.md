# abrirRecompensas — Diseño · Investigador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirRecompensas()

## Propósito

Mostrar al investigador autenticado únicamente las recompensas que le han sido asignadas por el coordinador, ocultando las del resto de usuarios.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirRecompensas-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirRecompensas.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| RecompensasView | `RecompensaController` `@Controller` | GET /recompensas — detecta rol INVESTIGADOR y filtra por destinatario |
| RecompensaController | `RecompensaService` `@Service` | `obtenerPorDestinatario(investigador)` |
| RecompensaRepository | `RecompensaRepository` JpaRepository | SELECT … WHERE destinatario_id = ? |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas | Muestra las recompensas del investigador autenticado |

## Decisiones de diseño

- Mismo endpoint `/recompensas` que el coordinador; la diferenciación ocurre en el controlador al comprobar el rol del usuario autenticado (`@AuthenticationPrincipal`).
- El investigador no ve el campo "destinatario" en el listado (es siempre él mismo).
- El investigador no tiene acceso a crear, editar ni eliminar recompensas.
