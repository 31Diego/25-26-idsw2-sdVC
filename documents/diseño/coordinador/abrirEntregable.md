# abrirEntregable — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirEntregable()

## Propósito

Recuperar y mostrar el detalle completo de un entregable concreto.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/abrirEntregable-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirEntregable.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| AbrirEntregableView (azul) | `EntregableController` `@Controller` | GET devuelve el detalle del entregable |
| EntregableController (amarillo) | `EntregableService` `@Service` | Llama a findById |
| EntregableRepository (naranja) | `EntregableRepository` JpaRepository | Ejecuta SELECT por id |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /proyectos/{proyectoId}/entregables/{id} | Muestra el detalle del entregable |

## Decisiones de diseño

- El `proyectoId` en la URL se usa para los enlaces de navegación (volver al listado, editar, eliminar).
- Si el entregable tiene `rutaArchivo`, la vista muestra el nombre del archivo adjunto.
- El `orElseThrow()` de JPA lanza excepción si el id no existe; Spring devuelve 500 (pendiente manejo de errores).
