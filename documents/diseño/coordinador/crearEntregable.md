# crearEntregable — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: crearEntregable()

## Propósito

Mostrar el formulario de creación, persistir el nuevo entregable y guardar el archivo adjunto si se proporciona.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/crearEntregable-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/crearEntregable.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| CrearEntregableView (azul) | `CrearEntregableController` `@Controller` | GET devuelve formulario vacío; POST guarda el entregable |
| EntregableController (amarillo) | `EntregableService` `@Service` | Asocia el proyecto, gestiona el archivo y llama a save |
| ProyectoRepository (naranja) | `ProyectoRepository` JpaRepository | Recupera el proyecto para asociarlo al entregable |
| EntregableRepository (naranja) | `EntregableRepository` JpaRepository | Ejecuta INSERT INTO entregables |
| Sistema de ficheros | `./archivos/` | Carpeta donde se almacena el archivo adjunto |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{proyectoId}/entregables/nuevo | Muestra el formulario vacío |
| POST | /proyectos/{proyectoId}/entregables/nuevo | Guarda el nuevo entregable |

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

- El formulario usa `enctype="multipart/form-data"` para permitir la subida de archivo.
- Si `archivo` no está vacío, el servicio crea el directorio `./archivos/` (si no existe) y copia el fichero con `Files.copy()`.
- El nombre original del archivo (`getOriginalFilename()`) se persiste en `entregable.rutaArchivo`.
- La asociación proyecto–entregable se resuelve en el servicio con `proyectoRepository.findById(proyectoId)`.
- Tras guardar, redirige a `/proyectos/{proyectoId}/entregables` (patrón PRG).
- Tipos disponibles: INFORME, DATASET, PRESENTACION, OTRO.
- Estados disponibles: PENDIENTE, EN_CURSO, ENTREGADO.
