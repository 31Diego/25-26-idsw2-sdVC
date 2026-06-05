# solicitarEliminacionPerfil — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: solicitarEliminacionPerfil()

## Propósito

Permitir al investigador solicitar la eliminación de su propia cuenta desde sus opciones de perfil. Si el id de la ruta no coincide con el investigador autenticado, se redirige a sus propias opciones.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/solicitarEliminacionPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/solicitarEliminacionPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| SolicitarEliminacionView (azul) | `SolicitarEliminacionController` `@Controller` | Mismo controlador que el coordinador; verifica que el id coincida con el investigador autenticado |
| EliminacionController (amarillo) | `SolicitudEliminacionService` `@Service` | Llama a crearSolicitud(investigador, motivo) |
| SolicitudEliminacionRepository (naranja) | `SolicitudEliminacionRepository` JpaRepository | Ejecuta INSERT |
| SolicitudEliminacion (naranja) | `SolicitudEliminacion` `@Entity` | Se crea con estado=PENDIENTE y fecha=hoy |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/{id}/solicitar-eliminacion | Muestra el formulario (solo si id == propio) |
| POST | /investigadores/{id}/solicitar-eliminacion | Persiste la solicitud |

## Decisiones de diseño

- El id en la URL corresponde al propio investigador; se obtiene del modelo `opciones-perfil` que ya tiene `investigador.id`.
- El controlador comprueba `id == investigador.getId()` para el rol INVESTIGADOR y redirige a `/perfil/opciones` si no coincide.
- Tras el POST, redirige a `/perfil/opciones`.
