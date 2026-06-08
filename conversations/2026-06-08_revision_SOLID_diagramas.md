# Sesión — 2026-06-08 · Revisión SOLID + Sincronización de diagramas

## [~21:00] Consulta: cómo afrontar refactors SOLID sin efecto cascada

**Prompt:** Las últimas dos sesiones han sido de revisión SOLID pero al arreglar una cosa se viola otra. ¿Cómo proceder?

**Resultado:** Recomendación de parar el refactor retroactivo y estabilizar lo conseguido. Estrategia: dejar el código actual, documentar deuda técnica, aplicar SOLID solo en código nuevo.

**Decisión:** Aceptado. Se procede con revisión general acotada.

---

## [~21:10] Revisión general SOLID del codebase

**Prompt:** Revisar controllers, services y policies con lente SOLID.

**Resultado:** Análisis completo. Resumen:

**Bien:**
- `LoginController`, `PanelPrincipalController`, `AutenticacionService`, `ArchivoService`, `SolicitudEliminacionService` — SRP correcto
- `ProyectoService` con patrón Strategy (OCP) para `PoliticaAcceso` y `PoliticaConsulta`
- Inyección por constructor consistente

**Problemas identificados (a corregir):**
1. `ProyectoService` — 4 métodos muertos reemplazados por Strategy pero no eliminados
2. `ProyectoController` — guardia `tieneAcceso` duplicada en dos métodos
3. `EntregableService` — `ProyectoRepository` innecesario; el controller podía pasar `Proyecto` directamente

**Deuda técnica (dejar):**
- `InvestigadorService.eliminarPerfil` — SRP violado por 3 repositories; causa raíz: falta cascades JPA
- Servicios sin interfaz — DIP no aplicado en capa service

**Decisión:** Aplicar los 3 fixes. Dejar la deuda técnica documentada.

---

## [~21:20] Aplicación de los 3 fixes

**Prompt:** Aplicar los cambios identificados.

**Resultado:**
- `ProyectoService`: eliminados `obtenerProyectos()`, `filtrarProyectos()`, `obtenerProyectosDeInvestigador()`, `filtrarProyectosDeInvestigador()` + import `java.util.List` del controller
- `ProyectoController`: guardia unificada en método privado `sinAcceso(Proyecto, Investigador)`
- `EntregableService`: eliminado `ProyectoRepository`; `guardarEntregable` recibe `Proyecto` en vez de `Long proyectoId`; el controller resuelve el proyecto con `proyectoService.obtenerProyecto(proyectoId)` antes de llamar al service

**Decisión:** Aceptado.

---

## [~21:35] Comprobación de diagramas de diseño vs código

**Prompt:** ¿Los diagramas de diseño concuerdan con el código actual?

**Resultado:** 7 diagramas desincronizados encontrados:

| Diagrama | Problema |
|---|---|
| `crearEntregable` | Mostraba `ProyectoRepo` en `EntregableService`; firma `guardarEntregable(... proyectoId)` |
| `abrirInvestigadores` | `alt` en el controller con métodos obsoletos `obtenerTodosLosInvestigadores` / `filtrarInvestigadores` |
| `agregarInvestigador` | Controller llamaba `obtenerTodosLosInvestigadores` + filtraba en controller |
| `editarPerfil` (coordinador) | Llamaba `actualizarPerfilConRol` (método fusionado) |
| `solicitarEliminacionPerfil` (coordinador) | Faltaba llamada a `puedeGestionar` |
| `solicitarEliminacionPerfil` (investigador) | Guarda de rol como `note` en controller; faltaba `requiereLogoutTrasEnviar` |
| `eliminarPerfil` | Firma `eliminarPerfil(id)` en vez de `eliminarPerfil(coordinador.getId(), id)` |

Correctos: `editarEntregable`, `eliminarEntregable`, `abrirProyectos` (ambos actores), `abrirProyecto` (investigador), `abrirInvestigadoresDeProyecto` (investigador), `editarPerfil` (investigador) y resto.

**Decisión:** Actualizar los 7 diagramas.

---

## [~21:50] Actualización de los 7 diagramas

**Prompt:** Actualizar todos los diagramas desincronizados.

**Resultado:** Los 7 archivos `.puml` actualizados en `modelosUML/diseño/`. Pendiente: volver a renderizar los SVG correspondientes.

**Decisión:** Aceptado.

---

## [~22:00] Consulta: deuda técnica pendiente

**Prompt:** ¿Cuál era la deuda técnica que dejamos?

**Resultado:** Dos ítems:
1. `InvestigadorService.eliminarPerfil` — SRP: 3 repositories por falta de cascades JPA en el modelo
2. Servicios sin interfaz — DIP no aplicado en capa service (controllers dependen de implementaciones concretas)

Motivo para dejarlo: los cascades requieren tocar la capa de persistencia (riesgo no nulo); las interfaces de service sin múltiples implementaciones ni mocks son código sin utilidad real.

---

## [~22:10] Consulta: cómo afrontar el SRP de InvestigadorService

**Prompt:** ¿Cómo se aborda la deuda de SRP en `InvestigadorService`?

**Resultado:** Plan concreto:
- Añadir `@OneToMany(mappedBy = "investigador", cascade = CascadeType.ALL, orphanRemoval = true)` en `Investigador` para `SolicitudEliminacion`
- Añadir `@PreRemove` en `Investigador` para limpiar la tabla join de `@ManyToMany` con `Proyecto`
- Resultado: `eliminarPerfil` queda en 2 líneas, `ProyectoRepository` y `SolicitudEliminacionRepository` desaparecen del service

**Decisión:** Aplazado para más adelante.

---

## Pendiente para la próxima sesión

1. Renderizar a SVG los 7 diagramas actualizados
2. Continuar con casos de uso pendientes según priorización: **publicaciones** (P1 — coordinador e investigador)
3. Opcionalmente: resolver deuda técnica SRP en `InvestigadorService` con cascades JPA
