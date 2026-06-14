# abrirSolicitudesEliminacionPerfil — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirSolicitudesEliminacionPerfil()

## Propósito

Recuperar y mostrar la lista de todas las solicitudes de eliminación de perfil registradas en el sistema.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirSolicitudesEliminacionPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirSolicitudesEliminacionPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Controlador de eliminación | EliminacionController @Controller | Atiende GET /solicitudes-eliminacion y prepara el modelo |
| Servicio de solicitud de eliminación | SolicitudEliminacionService @Service | `obtenerSolicitudes()` recupera todas las solicitudes |
| Repositorio de solicitudes | SolicitudEliminacionRepository JpaRepository | Ejecuta SELECT * FROM solicitudes_eliminacion vía findAll() |
| Base de datos | H2 | Almacén persistente |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /solicitudes-eliminacion | Lista todas las solicitudes de eliminación de perfil |

## Decisiones de diseño

- La lista se añade al modelo con `model.addAttribute("solicitudes", lista)`.
- La vista `solicitudes-eliminacion.html` enlaza a cada solicitud con `/solicitudes-eliminacion/{id}`.
