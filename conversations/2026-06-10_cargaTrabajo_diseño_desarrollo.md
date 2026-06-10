# Sesión — 2026-06-10 · Carga de trabajo · Diseño + Desarrollo

## [~10:00] Diseño e implementación de abrirOpcionesCargaTrabajo

**Prompt:** Vamos a hacer el diseño e implementación de carga de trabajo. El caso del coordinador permite visualizar todas las cargas de trabajo del sistema; el del investigador solo la suya propia.

**Resultado:**

Nueva entidad `CargaTrabajo` con campos `horasDocencia`, `horasInvestigacion`, `horasActividades` (double). Vinculada a `Investigador` mediante `@OneToOne` bidireccional (`@JoinColumn` en `CargaTrabajo`; `mappedBy + cascade + orphanRemoval` en `Investigador`).

Archivos creados:
- `models/CargaTrabajo.java`
- `repositories/CargaTrabajoRepository.java` — con `findByInvestigadorId`
- `services/CargaTrabajoService.java` — con `obtenerOCrearPorInvestigador` (crea con 0.0 si no existe)
- `controllers/CargaTrabajoController.java` — GET `/carga-trabajo`; bifurca por rol: coordinador → `obtenerInvestigadores(null)`, investigador → `obtenerOCrearPorInvestigador`
- `templates/carga-trabajo.html` — sección coordinador con tabla global (`th:if="${investigadores}"`) y sección investigador con resumen personal (`th:if="${carga}"`)

Archivos modificados:
- `Investigador.java` — back-reference `@OneToOne(mappedBy, cascade ALL, orphanRemoval)`
- `DataLoader.java` — inyectado `CargaTrabajoRepository`; crea entradas para todos los usuarios al arrancar (investigador: 15+20+5 h, coordinador: 8+12+3 h)
- `panel.html` — enlace "Carga de trabajo" en la nav

Diseño documentado:
- `modelosUML/diseño/coordinador/abrirOpcionesCargaTrabajo.puml` (actualizado desde versión obsoleta)
- `documents/diseño/coordinador/abrirOpcionesCargaTrabajo.md` (actualizado)
- `modelosUML/diseño/investigador/abrirOpcionesCargaTrabajo.puml` (nuevo, con alt existe/no existe)
- `documents/diseño/investigador/abrirOpcionesCargaTrabajo.md` (nuevo)

**Decisión:** Aceptado. La entidad `CargaTrabajo` como entidad separada (no campos en `Investigador`) es la elección correcta porque `editarCargaTrabajo` necesita algo que editar. El `@OneToOne` bidireccional permite acceder a `inv.cargaTrabajo` directamente en el template del coordinador sin N+1 (EAGER por defecto en `@OneToOne`).

---

## [~10:30] Diseño e implementación de editarCargaTrabajo

**Prompt:** Vale, haz ahora el diseño e implementación de editarCargaTrabajo.

**Resultado:**

Ampliado `CargaTrabajoService` con método `actualizar(Long id, double horasDocencia, double horasInvestigacion, double horasActividades)`.

Añadidos 4 handlers a `CargaTrabajoController`:
- `GET /carga-trabajo/editar` — investigador edita su propia carga
- `POST /carga-trabajo/editar` — investigador guarda
- `GET /investigadores/{id}/carga-trabajo/editar` — coordinador edita la carga de cualquier persona (`@PreAuthorize COORDINADOR`)
- `POST /investigadores/{id}/carga-trabajo/editar` — coordinador guarda

Template `editar-carga-trabajo.html` — formulario con 3 campos numéricos (step=0.5, min=0). El `th:action` es condicional: si `${investigador}` está en el model (coordinador) apunta a `/investigadores/{id}/…`; si no, a `/carga-trabajo/editar`. Mismo template para ambos actores.

`carga-trabajo.html` actualizado:
- Coordinador: columna "Acciones" con enlace "Editar" → `/investigadores/{inv.id}/carga-trabajo/editar`
- Investigador: enlace "Editar carga de trabajo" → `/carga-trabajo/editar`

Diseño documentado:
- `modelosUML/diseño/coordinador/editarCargaTrabajo.puml` (nuevo, GET + POST)
- `documents/diseño/coordinador/editarCargaTrabajo.md` (nuevo)
- `modelosUML/diseño/investigador/editarCargaTrabajo.puml` (nuevo)
- `documents/diseño/investigador/editarCargaTrabajo.md` (nuevo)

`documents/progreso.md` — añadidas filas `editarCargaTrabajo` (✅/✅/✅/⚠️) para coordinador e investigador.

**Decisión:** Aceptado. URL separadas por actor siguiendo el mismo patrón que `editarPerfil` (`/perfil/editar` vs `/investigadores/{id}/editar`).

---

## [~11:00] Regla de negocio: límite de carga de trabajo para agregarInvestigador

**Prompt:** Si la carga de trabajo de un investigador supera un límite no se puede agregar a un proyecto. Límite: 40 h/sem.

**Resultado:**

Añadido `CargaTrabajoService.excedeLimite(Investigador)` — devuelve `true` si `docencia + investigación + actividades ≥ 40.0`; `false` si no tiene CargaTrabajo registrada.

`ProyectoController` actualizado:
- Inyectado `CargaTrabajoService`
- GET `mostrarDisponibles`: calcula `Set<Long> bloqueados` (IDs de disponibles que exceden el límite) y lo pasa al model
- POST `agregarInvestigador`: si `excedeLimite(investigador)` → `redirectAttributes.addFlashAttribute("error", "…")` y redirige de vuelta al formulario; si no → flujo normal

`agregar-investigador.html` actualizado:
- Mensaje de error en rojo (flash)
- Nueva columna "Carga total (h/sem)" con valor en rojo+negrita si bloqueado
- Botón "Agregar" deshabilitado con texto "Carga excedida" si el investigador está bloqueado

`modelosUML/diseño/coordinador/agregarInvestigador.puml` actualizado con la bifurcación `alt carga < 40 / carga ≥ 40` y la participación de `CargaTrabajoService`.

**Decisión:** Aceptado. La guardia se aplica en dos niveles: UI (botón deshabilitado en GET) y backend (redirect con flash en POST). El límite de 40 h/sem es una constante implícita en `excedeLimite`; está inline como literal ya que no hay ningún requisito de configurabilidad.

---

## Pendiente para la próxima sesión

1. Renderizar a SVG los `.puml` nuevos y actualizados de esta sesión:
   - `abrirOpcionesCargaTrabajo` (coordinador e investigador)
   - `editarCargaTrabajo` (coordinador e investigador)
   - `agregarInvestigador` (coordinador, actualizado)
2. Continuar con casos de uso pendientes: **publicaciones** (P1 — coordinador e investigador)
