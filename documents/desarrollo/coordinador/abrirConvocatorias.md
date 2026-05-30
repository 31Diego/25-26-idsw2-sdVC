# FUNIBER GIPF > abrirConvocatorias > Desarrollo

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Desarrollo
- **Disciplina**: Implementación
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## descripción

Lista todas las convocatorias del sistema. Permite filtrar por texto (título), área y estado mediante parámetros opcionales en la URL.

## estado

🚧 **En progreso** — Iteración 1

## archivos

| Capa | Archivo |
|-|-|
| Modelo | [src/main/java/com/funiber/gipf/models/Convocatoria.java](../../../src/main/java/com/funiber/gipf/models/Convocatoria.java) |
| Repositorio | [src/main/java/com/funiber/gipf/repositories/ConvocatoriaRepository.java](../../../src/main/java/com/funiber/gipf/repositories/ConvocatoriaRepository.java) |
| Servicio | [src/main/java/com/funiber/gipf/services/ConvocatoriasService.java](../../../src/main/java/com/funiber/gipf/services/ConvocatoriasService.java) |
| Controlador | [src/main/java/com/funiber/gipf/controllers/ConvocatoriasController.java](../../../src/main/java/com/funiber/gipf/controllers/ConvocatoriasController.java) |
| Template | [src/main/resources/templates/convocatorias.html](../../../src/main/resources/templates/convocatorias.html) |

## URL

| Caso | URL |
|-|-|
| Listar todas | `GET /convocatorias` |
| Filtrar | `GET /convocatorias?texto=horizonte&area=ciencias&estado=abierta` |

## flujo de datos

1. Navegador → `GET /convocatorias` (con o sin parámetros)
2. `ConvocatoriasController.abrirConvocatorias()` detecta si hay parámetros
3. Sin parámetros → `ConvocatoriasService.listarConvocatorias()` → `findAll()`
4. Con parámetros → `ConvocatoriasService.filtrarConvocatorias()` → `buscarPorCriterio()`
5. H2 ejecuta la query → devuelve `List<Convocatoria>`
6. El servicio convierte a `List<ConvocatoriaDTO>` con `toDTO()`
7. El controlador añade la lista al `Model` y retorna la vista `"convocatorias"`
8. Thymeleaf renderiza `convocatorias.html` con `th:each`

## notas de implementación

- **Un solo endpoint**: `GET /convocatorias` gestiona lista completa y filtrado. Los parámetros son opcionales (`required = false`).
- **Query JPQL**: `buscarPorCriterio` usa `:param IS NULL` para ignorar filtros no informados — no hace falta construir la query dinámicamente.
- **Filtros persistentes**: los valores de los filtros se devuelven al `Model` para que el formulario Thymeleaf los mantenga visibles.
- **Lombok**: `@Getter @Setter @NoArgsConstructor` en `Convocatoria`. Requiere el plugin de Lombok en IntelliJ y activar *Enable annotation processing*.

## pruebas manuales

1. Arrancar la aplicación: `mvn spring-boot:run`
2. Abrir `http://localhost:8080/convocatorias`
3. Verificar que se muestra la tabla (vacía si no hay datos)
4. Insertar datos desde `http://localhost:8080/h2-console` y recargar
5. Probar filtros en la URL: `?texto=horizonte`, `?estado=abierta`, `?area=salud`

## referencias

- [Diseño: abrirConvocatorias()](../../diseño/coordinador/abrirConvocatorias.md)
- [Análisis: abrirConvocatorias()](../../analisis/coordinador/abrirConvocatorias.md)
