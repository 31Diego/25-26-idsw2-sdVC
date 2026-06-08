# Sesión — 2026-06-08 · Refactor SOLID · Controllers y Services

## [~18:00] Orientación y repaso de la estructura del proyecto

**Prompt:** Resumen del propósito de cada carpeta de clases y explicación detallada de una de ellas.

**Resultado:**
- Resumen de las 7 carpetas: `models/`, `repositories/`, `services/`, `config/`, `controllers/`, `policies/`, raíz.
- Explicación detallada de `policies/`: patrón Strategy, por qué se creó, cómo `ProyectoService` usa mapas `Rol → estrategia` en lugar de `if/else`, por qué `ConsultaCoordinador`/`ConsultaInvestigador` reciben el repository en el constructor (no son beans de Spring).

**Decisión:** Aceptado sin cambios.

---

## [~18:30] Dudas conceptuales sobre modelos, services e InvestigadorController

**Prompt:** ¿Qué son los elementos al final de los modelos? ¿Las variables `final` de repository en services son para tener métodos JPA? ¿El `if (criterio != null)` en InvestigadorController línea 22 rompe SOLID?

**Resultado:**
- Los elementos al final de los modelos son relaciones JPA (`@ManyToMany`, `@ManyToOne`). No rompen SOLID — son estructura del dominio.
- Las variables `final` con inyección por constructor es el patrón recomendado de Spring. La `final` garantiza inmutabilidad de la referencia. Correcto en SOLID (DIP: se depende de la interfaz `JpaRepository`).
- El `if (criterio != null)` en el controller sí viola SRP: el controller decide cuál método del service llamar, decisión que pertenece al service.

**Decisión:** Corregir el `if` de InvestigadorController.

---

## [~18:45] Fix: InvestigadorController — criterio

**Prompt:** Corregir el `if (criterio != null && !criterio.isBlank())` en InvestigadorController.

**Resultado:**
- Añadido `obtenerInvestigadores(String criterio)` a `InvestigadorService` — unifica el if/else dentro del service.
- `InvestigadorController.abrirInvestigadores` queda en una línea: `investigadorService.obtenerInvestigadores(criterio)`.

**Decisión:** Aceptado.

---

## [~19:00] Revisión completa de todos los controllers

**Prompt:** "He revisado y en todos los controllers menos login y panel hay lógica de negocio."

**Resultado:** Análisis controller por controller:

| Controller | Problema identificado |
|---|---|
| `InvestigadorController` L22 | `if (criterio != null)` para model attribute — simplificable |
| `ProyectoController` | `if (criterio != null)` en `abrirProyectos` + stream filter en `mostrarDisponibles` |
| `PerfilController` | `if (rol != null)` eligiendo entre dos métodos del service |
| `EliminacionController` | L88: guard de auto-eliminación con comparación de IDs |

Casos debatibles (dejados):
- `EliminacionController` L34/47: guardas de autorización (similar a `@PreAuthorize` pero con ID propio).
- `EliminacionController` L52: logout tras enviar solicitud — requiere sesión HTTP, no puede ir al service completo.

**Decisión:** Corregir los tres primeros y el de EliminacionController L88.

---

## [~19:20] Fixes en masa: controllers y services

**Prompt:** Aplicar todos los cambios.

**Resultado:**
- `InvestigadorController`: eliminado `if` de criterio — siempre `model.addAttribute("criterio", criterio)`.
- `ProyectoController.abrirProyectos`: ídem criterio.
- `ProyectoController.mostrarDisponibles`: stream filter movido a `InvestigadorService.obtenerNoMiembros(Proyecto)` — usa comparación por ID para evitar problemas de referencia.
- `InvestigadorService`: unificados `actualizarPerfil` + `actualizarPerfilConRol` en un único `actualizarPerfil(..., Rol rol, ...)` donde `null` significa "no cambiar rol".
- `InvestigadorService.eliminarPerfil`: cambia firma a `(Long actorId, Long targetId)` devolviendo `boolean`; la regla "no auto-eliminación" vive en el service.
- `PerfilController`: ambos métodos POST usan el nuevo `actualizarPerfil` unificado.
- `EliminacionController.eliminarPerfil`: usa `investigadorService.eliminarPerfil(coordinador.getId(), id)` — el controller solo enruta el resultado.

**Decisión:** Aceptado.

---

## [~19:40] Cómo distinguir lógica de negocio de lógica de controller

**Prompt:** "Sigue habiendo ifs, ¿cómo puedo diferenciar?"

**Resultado:** Regla: **¿puede el service responder esto sin saber que existe HTTP?**
- Si sí → service.
- Si no (códigos HTTP, sesiones, redirects) → controller.

Tabla de clasificación aplicada a los `if`s restantes:
- `if (!resource.exists())` → HTTP 404 → controller ✅
- `if (!proyectoService.tieneAcceso(...))` → regla en service, controller solo redirige ✅
- `if (session != null)` → HTTP → controller ✅
- `if (rol == INVESTIGADOR && !id.equals(myId))` → regla del dominio → service ❌ (corregir)
- `if (rol == INVESTIGADOR)` tras enviar solicitud → la condición es dominio; la acción (logout) es HTTP → condición al service

**Decisión:** Corregir los dos últimos.

---

## [~20:00] Fix: EliminacionController — guardas de autorización y logout

**Prompt:** Aplicar los dos fixes restantes de EliminacionController.

**Resultado:**
- Añadidos a `SolicitudEliminacionService`:
  - `puedeGestionar(Investigador, Long targetId)` → `rol == COORDINADOR || id.equals(targetId)`
  - `requiereLogoutTrasEnviar(Investigador)` → `rol == INVESTIGADOR`
- `EliminacionController`: reemplazados los tres `if` de rol por llamadas al service.
- Import de `Rol` eliminado de `EliminacionController` (ya no lo necesita — confirma que el refactor es correcto).

**Decisión:** Aceptado.

---

## [~20:20] Revisión SOLID de InvestigadorService

**Prompt:** "¿Viola algún principio SOLID tras los cambios?"

**Resultado:**

| Principio | Estado |
|---|---|
| SRP | ⚠️ Smell: `eliminarPerfil` depende de `ProyectoRepository` y `SolicitudEliminacionRepository`. Causa raíz: sin cascades JPA en el modelo. Deuda técnica. |
| OCP | ✅ Sin problemas |
| LSP | ✅ N/A |
| ISP / DIP | ⚠️ Sin interfaces de service — deuda técnica conocida de la sesión anterior |

- Eliminados métodos muertos: `obtenerTodosLosInvestigadores()` y `filtrarInvestigadores(String)` — ya no los llamaba nadie tras los refactors anteriores.

**Decisión:** Deuda técnica de SRP identificada y documentada. Métodos muertos eliminados.

---

## Pendiente para la próxima sesión

1. Continuar con casos de uso pendientes según priorización: **publicaciones** (P1 — coordinador e investigador)
2. Renderizar a SVG los diagramas modificados en sesiones anteriores
3. DIP — analizar si implementar interfaces de service (deuda técnica)
