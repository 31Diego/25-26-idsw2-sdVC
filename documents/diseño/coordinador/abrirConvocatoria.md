# FUNIBER GIPF > abrirConvocatoria > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-29
- **Autor**: Diego Martínez

## propósito

Detallar el flujo técnico para recuperar y mostrar el detalle de una convocatoria específica, concretando las clases de análisis en componentes Spring Boot con Thymeleaf y H2.

## diagrama de secuencia

<div align=center>

|![Diseño: abrirConvocatoria()](/images/diseño/abrirConvocatoria-diseño.svg)|
|-|
|Código fuente: [abrirConvocatoria.puml](abrirConvocatoria.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `ConvocatoriaController` | `@Controller` (Spring MVC) | `ConvocatoriaView` |
| `ConvocatoriaService` | `@Service` | `ConvocatoriaController` |
| `ConvocatoriaRepository` | `JpaRepository<Convocatoria, Long>` | `ConvocatoriaRepository` |
| `Convocatoria` | `@Entity` (JPA) | `Convocatoria` |
| `ConvocatoriaDTO` | POJO | — (nuevo en diseño) |
| Thymeleaf `convocatoria.html` | Template HTML | — |
| Base de Datos | H2 (archivo `funiber.mv.db`) | — |

## flujo principal

1. El Coordinador hace click en una convocatoria desde la lista.
2. El navegador envía `GET /convocatorias/{id}` con la cookie de sesión.
3. `ConvocatoriaController` recibe la petición y llama a `ConvocatoriaService.obtenerConvocatoria(id)`.
4. `ConvocatoriaService` llama a `ConvocatoriaRepository.findById(id)`.
5. `ConvocatoriaRepository` ejecuta `SELECT * FROM convocatorias WHERE id = ?` contra H2.
6. H2 devuelve la entidad `Convocatoria`.
7. `ConvocatoriaService` convierte la entidad a `ConvocatoriaDTO` y lo retorna al controlador.
8. `ConvocatoriaController` añade el DTO al `Model` de Spring MVC y retorna el nombre de la vista `"convocatoria"`.
9. Thymeleaf renderiza `convocatoria.html` con los datos del DTO.
10. El navegador muestra el detalle de la convocatoria al Coordinador.

## decisiones de diseño

- **Autenticación**: sesión HTTP gestionada por Spring Security (form login). Apropiado para Thymeleaf — no se usa JWT (que es para APIs REST consumidas por SPAs).
- **DTO**: `ConvocatoriaService` convierte la `@Entity` a `ConvocatoriaDTO` antes de pasarla a la vista. La entidad JPA no se expone directamente al template.
- **Navegación desde la vista**: el template `convocatoria.html` incluye botones para:
  - `GET /convocatorias/{id}/importar` → `ImportarConvocatoria`
  - `GET /convocatorias` → volver a `abrirConvocatorias`

## referencias

- [Análisis: abrirConvocatoria()](../../analisis/coordinador/abrirConvocatoria.md)
- [Especificación detallada](../../../context/casosDeUso/detalle/coordinador/abrirConvocatoria/abrirConvocatoria.md)
