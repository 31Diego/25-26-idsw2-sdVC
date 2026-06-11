# abrirRecompensa — Diseño · Investigador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirRecompensa()

## Propósito

Mostrar al investigador el detalle completo de una recompensa que le ha sido asignada. Un investigador no puede acceder al detalle de recompensas de otros usuarios.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirRecompensa-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirRecompensa.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| RecompensaView | `RecompensaController` `@Controller` | GET /recompensas/{id} — verifica que el destinatario coincide |
| RecompensaController | `RecompensaService` `@Service` | `obtenerPorId(id)` |
| RecompensaRepository | `RecompensaRepository` JpaRepository | SELECT … WHERE id = ? |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas/{id} | Muestra el detalle si el investigador es el destinatario |

## Decisiones de diseño

- El controlador comprueba que `recompensa.getDestinatario().getId()` coincide con el id del usuario autenticado; si no, redirige a `/recompensas`.
- El detalle no muestra los botones de editar ni eliminar para el investigador.
