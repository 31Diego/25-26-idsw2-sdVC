# eliminarConvocatoria — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: eliminarConvocatoria()

## Propósito

Mostrar los datos de la convocatoria como pantalla de confirmación y borrarla definitivamente tras la acción del coordinador.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/eliminarConvocatoria-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/eliminarConvocatoria.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EliminarConvocatoriaView | `ConvocatoriaController` `@Controller` | GET muestra la confirmación; POST ejecuta el borrado |
| ConvocatoriaService | `ConvocatoriaService` `@Service` | `obtenerPorId(id)` para cargar la confirmación; `eliminar(id)` para el borrado |
| ConvocatoriaRepository | `ConvocatoriaRepository` JpaRepository | SELECT (carga confirmación) + DELETE |
| Convocatoria | `Convocatoria` `@Entity` | Tabla convocatorias en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /convocatorias/{id}/eliminar | Muestra la página de confirmación con título, área y estado |
| POST | /convocatorias/{id}/eliminar | Ejecuta el DELETE y redirige al listado |

## Decisiones de diseño

- Todos los endpoints del controlador ya están protegidos con `@PreAuthorize("hasRole('COORDINADOR')")` a nivel de clase.
- El GET carga la convocatoria y la muestra para que el coordinador confirme que es la correcta.
- El POST llama a `deleteById(id)` y redirige a `/convocatorias` (PRG pattern).
- El enlace "Eliminar" aparece en `convocatoria.html` (vista de detalle).
- El enlace "Cancelar" vuelve a `/convocatorias/{id}` sin modificar nada.
