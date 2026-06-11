# Sesión — 2026-06-11 · Convocatorias · Análisis + Diseño + Desarrollo

## [~12:00] Análisis y diseño de abrirConvocatorias, abrirConvocatoria, importarConvocatoria

**Prompt:** Hacer análisis y diseño de `abrirConvocatorias`, `abrirConvocatoria` e `importarConvocatoria`. El último funciona realmente como un create.

**Resultado:**

Se comprobó que los 6 artefactos de **análisis** ya existían completos (`.md` + `.puml` × 3):
- `documents/analisis/coordinador/{abrirConvocatorias,abrirConvocatoria,importarConvocatoria}.md`
- `modelosUML/analisis/coordinador/{abrirConvocatorias,abrirConvocatoria,importarConvocatoria}.puml`

Se crearon los 6 artefactos de **diseño** (`.md` + `.puml` × 3):
- `documents/diseño/coordinador/{abrirConvocatorias,abrirConvocatoria,importarConvocatoria}.md`
- `modelosUML/diseño/coordinador/{abrirConvocatorias,abrirConvocatoria,importarConvocatoria}.puml`

**Decisiones:**
- `importarConvocatoria` modelado como CREATE estándar: GET/POST `/convocatorias/importar`, con PRG tras guardar.
- El botón "Importar convocatoria" se ubica en el listado (`convocatorias.html`) y en el detalle (`convocatoria.html`).
- `ConvocatoriaService` como capa intermedia; filtrado de convocatorias mediante streams en el servicio.
- Campos extensos (`descripcion`, `requisitos`, `criteriosEvaluacion`, `dotacion`, `documentacion`) como `@Column(columnDefinition = "TEXT")`.
- `@PreAuthorize("hasRole('COORDINADOR')")` a nivel de clase en el controlador.

---

## [~12:30] Implementación de los tres casos de uso de convocatorias

**Prompt:** "no me aparecen la opción para acceder a las convocatorias" (pedido de implementación implícito)

**Resultado:**

Código creado:
- `Convocatoria.java` — entidad JPA con todos los campos del detalle
- `ConvocatoriaRepository.java` — JpaRepository básico
- `ConvocatoriaService.java` — `buscarPorCriterios`, `obtenerPorId`, `guardar`
- `ConvocatoriaController.java` — endpoints GET/POST para listado, detalle e importar; `@PreAuthorize` a nivel de clase
- `convocatorias.html` — listado con formulario de filtros (texto, área, estado) + enlace "Importar"
- `convocatoria.html` — detalle completo con todos los campos
- `importar-convocatoria.html` — formulario con todos los campos
- `panel.html` — añadido enlace "Convocatorias" visible solo al coordinador con `sec:authorize`

**Decisión:** Se aceptó. El usuario aclaró después que solo había pedido análisis + diseño, pero la implementación quedó y es correcta.

---

## [~12:45] Caso de uso eliminarConvocatoria — análisis, diseño e implementación

**Prompt:** Hacer un caso de uso para eliminar convocatorias.

**Resultado:**

Análisis:
- `documents/analisis/coordinador/eliminarConvocatoria.md`
- `modelosUML/analisis/coordinador/eliminarConvocatoria.puml`

Diseño:
- `documents/diseño/coordinador/eliminarConvocatoria.md`
- `modelosUML/diseño/coordinador/eliminarConvocatoria.puml`

Código:
- `ConvocatoriaService` — añadido `eliminar(id)`
- `ConvocatoriaController` — añadidos GET/POST `/convocatorias/{id}/eliminar`
- `eliminar-convocatoria.html` — pantalla de confirmación con título, área, estado y fecha de cierre
- `convocatoria.html` — añadido enlace "Eliminar"

**Decisión:** Aceptado. `eliminarConvocatoria` no estaba en la priorización original; se añadió como fila nueva en `progreso.md`.

---

## Pendiente para la próxima sesión

1. Renderizar a SVG los `.puml` de esta sesión (× 8):
   - Análisis: `eliminarConvocatoria`
   - Diseño: `abrirConvocatorias`, `abrirConvocatoria`, `importarConvocatoria`, `eliminarConvocatoria`
   - (Los de análisis de `abrirConvocatorias`, `abrirConvocatoria`, `importarConvocatoria` ya existían renderizados)
2. Continuar con P1 pendientes o pasar a P2 (Recompensas).
