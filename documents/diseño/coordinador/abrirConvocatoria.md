# abrirConvocatoria — Diseño · Coordinador

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirConvocatoria()

## Propósito

Recuperar y mostrar el detalle completo de una convocatoria concreta: título, área, estado, fechas, descripción, requisitos y condiciones, criterios de evaluación, dotación, documentación asociada e información de contacto.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirConvocatoria-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirConvocatoria.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| ConvocatoriaView | `ConvocatoriaController` `@Controller` | Recibe GET /convocatorias/{id}; añade la entidad al Model y devuelve convocatoria.html |
| ConvocatoriaController | `ConvocatoriaService` `@Service` | `obtenerPorId(id)` — lanza excepción si no existe |
| ConvocatoriaRepository | `ConvocatoriaRepository` JpaRepository | SELECT * FROM convocatorias WHERE id = ? |
| Convocatoria | `Convocatoria` `@Entity` | Tabla convocatorias con todos los campos del detalle |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /convocatorias/{id} | Muestra el detalle de la convocatoria |

## Decisiones de diseño

- Si la convocatoria no existe, el servicio lanza excepción y el controlador redirige a `/convocatorias`.
- El template `convocatoria.html` incluye el botón "Importar convocatoria" que navega a `/convocatorias/importar`.
- Los campos de texto extenso (descripcion, requisitos, criteriosEvaluacion) se declaran como `@Lob` en la entidad para soportar contenido amplio.
