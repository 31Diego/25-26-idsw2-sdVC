# abrirConvocatorias — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirConvocatorias()

## Propósito

Recuperar y mostrar el listado de convocatorias registradas en el sistema, con soporte de filtrado por texto, área y estado.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirConvocatorias-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirConvocatorias.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| ListarConvocatoriasView | `ConvocatoriaController` `@Controller` | Recibe GET /convocatorias; añade la lista al Model y devuelve convocatorias.html |
| ConvocatoriasController | `ConvocatoriaService` `@Service` | `obtenerTodas()` y `buscarPorCriterios(q, area, estado)` |
| ConvocatoriaRepository | `ConvocatoriaRepository` JpaRepository | SELECT completo o filtrado sobre la tabla convocatorias |
| Convocatoria | `Convocatoria` `@Entity` | Tabla convocatorias |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /convocatorias | Lista todas las convocatorias |
| GET | /convocatorias?q=...&area=...&estado=... | Lista convocatorias con filtros aplicados |

## Decisiones de diseño

- El filtrado se delega a `ConvocatoriaService`; si los parámetros están vacíos o ausentes, se devuelve el listado completo.
- La URL `/convocatorias` está restringida a `COORDINADOR` mediante `@PreAuthorize("hasRole('COORDINADOR')")`.
- El template muestra un botón "Importar convocatoria" que navega a `/convocatorias/importar`.
