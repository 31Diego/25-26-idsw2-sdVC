# crearRecompensa — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: crearRecompensa()

## Propósito

Presentar un formulario para que el coordinador registre una nueva recompensa asignada a un investigador concreto. El investigador destinatario seleccionado verá esta recompensa en su listado.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/crearRecompensa-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/crearRecompensa.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| CrearRecompensaView | `RecompensaController` `@Controller` | GET muestra el formulario; POST persiste y redirige |
| RecompensaController | `RecompensaService` `@Service` | `crear(datos, destinatario)` — instancia y persiste la entidad |
| RecompensaRepository | `RecompensaRepository` JpaRepository | INSERT INTO recompensas |
| InvestigadorRepository | `InvestigadorRepository` JpaRepository | SELECT para poblar el selector de destinatario |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas/crear | Muestra el formulario vacío con el selector de investigadores |
| POST | /recompensas/crear | Persiste la nueva recompensa y redirige al detalle |

## Decisiones de diseño

- Solo accesible para el rol `COORDINADOR` (`@PreAuthorize("hasRole('COORDINADOR')")`).
- El formulario incluye un `<select>` con todos los investigadores del sistema para elegir el destinatario.
- Los campos de texto extenso (descripción, condiciones) usan `<textarea>`.
- Tras guardar, redirige a `/recompensas/{id}` (PRG pattern).
- El campo `tipo` es un `<select>` con valores: `Económica`, `Reconocimiento`, `Tiempo libre`.
