# abrirRecompensa — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirRecompensa()

## Propósito

Mostrar el detalle completo de una recompensa concreta. El coordinador puede acceder al detalle de cualquier recompensa del sistema.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirRecompensa-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirRecompensa.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| RecompensaView | `RecompensaController` `@Controller` | GET /recompensas/{id} — carga el detalle |
| RecompensaController | `RecompensaService` `@Service` | `obtenerPorId(id)` |
| RecompensaRepository | `RecompensaRepository` JpaRepository | SELECT … WHERE id = ? |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas/{id} | Muestra el detalle de la recompensa |

## Decisiones de diseño

- El coordinador puede acceder al detalle de cualquier recompensa sin restricción adicional.
- El detalle muestra todos los campos: título, tipo, valor, descripción, condiciones, fecha de creación y destinatario.
- Desde el detalle el coordinador accede a editar y eliminar.
