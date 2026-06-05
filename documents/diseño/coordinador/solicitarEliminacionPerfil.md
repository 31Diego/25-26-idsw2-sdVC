# solicitarEliminacionPerfil — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: solicitarEliminacionPerfil(id)

## Propósito

Presentar el formulario de solicitud de eliminación de perfil y registrar la solicitud para el investigador indicado. El coordinador accede desde las opciones de perfil de ese investigador.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/solicitarEliminacionPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/solicitarEliminacionPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| SolicitarEliminacionView (azul) | `SolicitarEliminacionController` `@Controller` | GET muestra el formulario; POST persiste la solicitud y redirige |
| EliminacionController (amarillo) | `SolicitudEliminacionService` `@Service` | Llama a crearSolicitud(investigador, motivo) |
| SolicitudEliminacionRepository (naranja) | `SolicitudEliminacionRepository` JpaRepository | Ejecuta INSERT |
| SolicitudEliminacion (naranja) | `SolicitudEliminacion` `@Entity` | Se crea con estado=PENDIENTE y fecha=hoy |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /investigadores/{id}/solicitar-eliminacion | Muestra el formulario de solicitud |
| POST | /investigadores/{id}/solicitar-eliminacion | Persiste la solicitud |

## Decisiones de diseño

- El controlador es compartido con el actor investigador; la lógica de rol se gestiona en el mismo controlador.
- El coordinador puede solicitar la eliminación de cualquier investigador; el investigador solo puede solicitar la propia.
- La solicitud se crea con `estado = PENDIENTE` y `fecha = LocalDate.now()`.
- Tras el POST, redirige a `/investigadores/{id}/opciones`.
