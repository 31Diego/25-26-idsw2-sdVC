# Sesión — 2026-05-25 · Entradas faltantes en diagramas de coordinador · análisis

## [~21:40] Añadir entradas secundarias a 7 diagramas

**Prompt:** "puedes hacer los 7 diagramas que faltan"

**Resultado:** Revisados y corregidos los 7 PUMLs con entradas secundarias faltantes:
- `abrirConvocatorias` — ya estaba correcto (tenía CONVOCATORIA_ABIERTA)
- `abrirEntregables` — añadida entrada `ENTREGABLE_ABIERTO`
- `abrirInvestigador` — añadida entrada `OPCIONES_PERFIL_INVESTIGADOR_ABIERTO`
- `abrirMisPublicaciones` — añadida entrada `MI_PUBLICACION_ABIERTA`
- `abrirProyecto` — añadida entrada `ENTREGABLES_ABIERTOS`
- `abrirRecompensas` — añadida entrada `RECOMPENSA_ABIERTA`
- `abrirSolicitudesEliminacionPerfil` — reescrito: entradas corregidas a `OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` + `SOLICITUD_ELIMINACION_PERFIL_ABIERTA` (antes tenía PANEL_PRINCIPAL_ABIERTO incorrectamente)

**Decisión:** Aceptado íntegro.

---

## [~21:43] Verificar abrirPanelPrincipal

**Prompt:** "he vuelto a mirar los casos de uso de antes y me he dado cuenta que abrirPanelPrincipal viene de INVESTIGADORES_ABIERTOS, solamente"

**Resultado:** El PUML ya tenía las 8 entradas correctas. El SVG estaba desactualizado (generado antes de que se añadieran todas las entradas). El usuario regeneró el SVG.

**Decisión:** Solo era necesario regenerar el SVG. PUML correcto.

---

## [~21:45] Verificación final

**Prompt:** "vale, en principio ya estan, puedes verificar que estan los archivos correctamente"

**Resultado:** Glob confirmó 46 SVGs presentes en `images/analisis/`. Todo correcto.

**Decisión:** Verificación aprobada.

---
