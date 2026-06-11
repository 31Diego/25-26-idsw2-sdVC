# abrirRecompensas — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirRecompensas()

## Propósito

Mostrar al coordinador el listado completo de todas las recompensas registradas en el sistema, con acceso directo a cada detalle y a la creación de nuevas recompensas.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirRecompensas-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirRecompensas.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| RecompensasView | `RecompensaController` `@Controller` | GET /recompensas — determina el rol y retorna el listado correspondiente |
| RecompensaController | `RecompensaService` `@Service` | `obtenerTodas()` — devuelve todas las recompensas |
| RecompensaRepository | `RecompensaRepository` JpaRepository | SELECT * FROM recompensas |
| Recompensa | `Recompensa` `@Entity` | Tabla recompensas |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /recompensas | Muestra el listado de todas las recompensas (coordinador ve todas) |

## Decisiones de diseño

- El mismo endpoint `/recompensas` sirve a ambos roles; el controlador comprueba el rol del usuario autenticado para filtrar (`obtenerTodas()` para coordinador, `obtenerPorDestinatario()` para investigador).
- El listado muestra: título, tipo, valor, destinatario (nombre).
- Desde el listado el coordinador puede acceder al detalle de cada recompensa y al formulario de creación.
