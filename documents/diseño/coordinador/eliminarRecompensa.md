# eliminarRecompensa — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: eliminarRecompensa()

## Propósito

Mostrar una pantalla de confirmación y, si el coordinador confirma, eliminar la recompensa del sistema.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/eliminarRecompensa-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/eliminarRecompensa.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EliminarRecompensaView | `RecompensaController` `@Controller` | GET muestra confirmación; POST elimina y redirige |
| RecompensaController | `RecompensaService` `@Service` | `obtenerPorId(id)` y `eliminar(id)` |
| RecompensaRepository | `RecompensaRepository` JpaRepository | DELETE FROM recompensas WHERE id = ? |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas/{id}/eliminar | Muestra la pantalla de confirmación |
| POST | /recompensas/{id}/eliminar | Elimina la recompensa y redirige al listado |

## Decisiones de diseño

- Solo accesible para el rol `COORDINADOR` (`@PreAuthorize("hasRole('COORDINADOR')")`).
- La pantalla de confirmación muestra: título, tipo, valor y destinatario.
- Tras eliminar, redirige a `/recompensas` (PRG pattern).
