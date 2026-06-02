# editarEntregable — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: editarEntregable()

## Propósito

Mostrar el formulario pre-relleno con los datos del entregable y persistir los cambios, permitiendo reemplazar el archivo adjunto.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/editarEntregable-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/editarEntregable.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarEntregableView (azul) | `EditarEntregableController` `@Controller` | GET carga el formulario pre-relleno; POST actualiza |
| EntregableController (amarillo) | `EntregableService` `@Service` | findById para cargar, actualiza campos y gestiona archivo |
| EntregableRepository (naranja) | `EntregableRepository` JpaRepository | SELECT (GET) y UPDATE (POST) |
| Sistema de ficheros | `./archivos/` | Reemplaza el archivo adjunto si se sube uno nuevo |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{proyectoId}/entregables/{id}/editar | Muestra el formulario pre-relleno |
| POST | /proyectos/{proyectoId}/entregables/{id}/editar | Guarda los cambios |

## Campos del formulario

| Campo | Tipo | Requerido |
|---|---|---|
| titulo | String | Sí |
| tipo | String (selector) | Sí |
| fechaLimite | LocalDate | No |
| estado | String (selector) | Sí |
| descripcion | Texto largo | No |
| archivo | MultipartFile | No |

## Decisiones de diseño

- El GET carga el entregable existente con `findById` y lo pone en el modelo para pre-rellenar el formulario.
- Si se sube un nuevo archivo en el POST, reemplaza el anterior (sobrescribe con `REPLACE_EXISTING`).
- Si no se sube archivo, `rutaArchivo` se mantiene sin cambios.
- Tras guardar, redirige a `/proyectos/{proyectoId}/entregables/{id}` (PRG).
