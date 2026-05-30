# FUNIBER GIPF > abrirConvocatoria > Desarrollo

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Desarrollo
- **Disciplina**: Implementación
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## descripción

Muestra el detalle completo de una convocatoria concreta identificada por su id.

## estado

🚧 **En progreso** — Iteración 1

## archivos

| Capa | Archivo |
|-|-|
| Modelo | [src/main/java/com/funiber/gipf/models/Convocatoria.java](../../../src/main/java/com/funiber/gipf/models/Convocatoria.java) |
| Repositorio | [src/main/java/com/funiber/gipf/repositories/ConvocatoriaRepository.java](../../../src/main/java/com/funiber/gipf/repositories/ConvocatoriaRepository.java) |
| Servicio | [src/main/java/com/funiber/gipf/services/ConvocatoriasService.java](../../../src/main/java/com/funiber/gipf/services/ConvocatoriasService.java) |
| Controlador | [src/main/java/com/funiber/gipf/controllers/ConvocatoriasController.java](../../../src/main/java/com/funiber/gipf/controllers/ConvocatoriasController.java) |
| Template | [src/main/resources/templates/convocatoria.html](../../../src/main/resources/templates/convocatoria.html) |

## URL

| Caso | URL |
|-|-|
| Abrir detalle | `GET /convocatorias/{id}` |

## flujo de datos

1. Navegador → `GET /convocatorias/1`
2. `ConvocatoriasController.abrirConvocatoria(1)` llama al servicio
3. `ConvocatoriasService.obtenerConvocatoria(1)` → `findById(1)`
4. H2 ejecuta `SELECT * FROM convocatorias WHERE id = 1`
5. Si no existe → `RuntimeException` (Spring devuelve 500; se refinará con manejo de errores)
6. El servicio convierte la entidad a `ConvocatoriaDTO` con `toDTO()`
7. El controlador añade el DTO al `Model` y retorna la vista `"convocatoria"`
8. Thymeleaf renderiza `convocatoria.html` con los atributos del DTO

## notas de implementación

- **Compartido con abrirConvocatorias**: el controlador, servicio y repositorio son los mismos. Solo cambia el método del controlador y el template.
- **Navegación**: el template incluye enlace a `importarConvocatoria` (`/convocatorias/{id}/importar`) y vuelta a la lista (`/convocatorias`). Estos enlaces devolverán 404 hasta que se implementen los casos de uso correspondientes.

## pruebas manuales

1. Arrancar la aplicación: `mvn spring-boot:run`
2. Insertar una convocatoria desde `http://localhost:8080/h2-console`
3. Abrir `http://localhost:8080/convocatorias/1`
4. Verificar que se muestra el detalle completo
5. Verificar que el enlace "Volver a la lista" lleva a `/convocatorias`

## referencias

- [Diseño: abrirConvocatoria()](../../diseño/coordinador/abrirConvocatoria.md)
- [Análisis: abrirConvocatoria()](../../analisis/coordinador/abrirConvocatoria.md)
