# importarConvocatoria — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: importarConvocatoria()

## Propósito

Presentar un formulario vacío para que el coordinador registre manualmente una nueva convocatoria en el sistema y persistirla. Funciona como un CREATE estándar (título, área, estado, fechas, descripción, requisitos, criterios, dotación, contacto).

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/importarConvocatoria-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/importarConvocatoria.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| ImportarConvocatoriaView | `ConvocatoriaController` `@Controller` | GET muestra el formulario vacío; POST persiste y redirige |
| ConvocatoriaController | `ConvocatoriaService` `@Service` | `guardar(datos)` — instancia la entidad, aplica los datos y llama al repositorio |
| ConvocatoriaRepository | `ConvocatoriaRepository` JpaRepository | INSERT INTO convocatorias |
| Convocatoria | `Convocatoria` `@Entity` | Tabla convocatorias |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /convocatorias/importar | Muestra el formulario de importación vacío |
| POST | /convocatorias/importar | Persiste la nueva convocatoria y redirige al detalle |

## Decisiones de diseño

- Tras guardar, redirige a `/convocatorias/{id}` para mostrar la convocatoria recién creada (PRG pattern).
- La ruta está restringida a `COORDINADOR` mediante `@PreAuthorize("hasRole('COORDINADOR')")`.
- El estado inicial lo fija el coordinador en el formulario (campo enum: ABIERTA, CERRADA, PENDIENTE).
- Los campos de texto extenso usan `<textarea>` en el template Thymeleaf.
