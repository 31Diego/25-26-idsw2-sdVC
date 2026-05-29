# FUNIBER GIPF > abrirConvocatorias > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-29
- **Autor**: Diego Martínez

## propósito

Detallar el flujo técnico para recuperar y mostrar la lista de convocatorias, incluyendo el filtrado por texto, área y estado.

## diagrama de secuencia

<div align=center>

|![Diseño: abrirConvocatorias()](/images/diseño/abrirConvocatorias-diseño.svg)|
|-|
|Código fuente: [abrirConvocatorias.puml](abrirConvocatorias.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `ConvocatoriasController` | `@Controller` (Spring MVC) | `ListarConvocatoriasView` |
| `ConvocatoriasService` | `@Service` | `ConvocatoriasController` |
| `ConvocatoriaRepository` | `JpaRepository<Convocatoria, Long>` | `ConvocatoriaRepository` |
| `Convocatoria` | `@Entity` (JPA) | `Convocatoria` |
| `ConvocatoriaDTO` | POJO | — (nuevo en diseño) |
| Thymeleaf `convocatorias.html` | Template HTML | — |
| Base de Datos | H2 (archivo `funiber.mv.db`) | — |

## flujos

### carga inicial

1. El Coordinador navega a la sección de convocatorias (desde panel principal o desde `CONVOCATORIA_ABIERTA`).
2. El navegador envía `GET /convocatorias`.
3. `ConvocatoriasController` llama a `ConvocatoriasService.listarConvocatorias()`.
4. `ConvocatoriasService` llama a `ConvocatoriaRepository.findAll()`.
5. H2 ejecuta `SELECT * FROM convocatorias`.
6. `ConvocatoriasService` convierte la lista de entidades a `List<ConvocatoriaDTO>`.
7. El controlador añade la lista al `Model` y retorna la vista `"convocatorias"`.
8. Thymeleaf renderiza `convocatorias.html` con la lista completa.

### filtrado

1. El Coordinador aplica filtros en el formulario de búsqueda.
2. El navegador envía `GET /convocatorias?texto=&area=&estado=`.
3. `ConvocatoriasController` llama a `ConvocatoriasService.filtrarConvocatorias(texto, area, estado)`.
4. `ConvocatoriasService` llama a `ConvocatoriaRepository.buscarPorCriterio(texto, area, estado)`.
5. H2 ejecuta la query con cláusulas `WHERE` según los filtros informados.
6. Se devuelve la lista filtrada y se re-renderiza `convocatorias.html`.

## decisiones de diseño

- **Un solo endpoint** `GET /convocatorias` gestiona tanto la carga inicial (sin parámetros) como el filtrado (con parámetros opcionales). Si los parámetros están vacíos, `ConvocatoriasService` llama a `findAll()`; si hay alguno informado, llama a `buscarPorCriterio()`.
- **Thymeleaf form**: el formulario de filtros usa `method="get"` para que los filtros activos sean visibles en la URL y el estado sea reproducible.
- La vista dispone de enlaces para `abrirConvocatoria(id)` (Patrón 2) y para volver al panel principal.

## referencias

- [Análisis: abrirConvocatorias()](../../analisis/coordinador/abrirConvocatorias.md)
- [Especificación detallada](../../../context/casosDeUso/detalle/coordinador/abrirConvocatoria/abrirConvocatoria.md)
