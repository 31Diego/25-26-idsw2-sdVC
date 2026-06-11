# editarRecompensa — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: editarRecompensa()

## Propósito

Presentar un formulario pre-rellenado con los datos actuales de una recompensa para que el coordinador los modifique y los guarde.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/editarRecompensa-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/editarRecompensa.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarRecompensaView | `RecompensaController` `@Controller` | GET carga datos actuales; POST persiste y redirige |
| RecompensaController | `RecompensaService` `@Service` | `obtenerPorId(id)` y `actualizar(id, datos)` |
| RecompensaRepository | `RecompensaRepository` JpaRepository | SELECT … WHERE id = ? / UPDATE |
| InvestigadorRepository | `InvestigadorRepository` JpaRepository | SELECT para poblar el selector de destinatario |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas/{id}/editar | Muestra el formulario pre-rellenado |
| POST | /recompensas/{id}/editar | Persiste los cambios y redirige al detalle |

## Decisiones de diseño

- Solo accesible para el rol `COORDINADOR` (`@PreAuthorize("hasRole('COORDINADOR')")`).
- El formulario también permite cambiar el investigador destinatario.
- Tras guardar, redirige a `/recompensas/{id}` (PRG pattern).
