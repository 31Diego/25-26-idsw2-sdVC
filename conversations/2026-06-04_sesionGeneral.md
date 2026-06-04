# Sesión — 2026-06-04 · Diseño · Reflexiones sobre diagramas de secuencia

## [~20:20] Diagrama de secuencia unificado por caso de uso

**Prompt:** Propuesta de consolidar los diagramas de secuencia de diseño: en lugar de un diagrama por CU por actor, tener un diagrama por CU y usar `alt` cuando el comportamiento difiera según el actor.

**Resultado:** Se discutieron ventajas (menos duplicación, visión unificada) y límites (si las diferencias son muy grandes, conviene mantenerlos separados). Se identificó que ya se estaba haciendo parcialmente en `abrirProyectos` del investigador.

**Decisión:** Aceptado como norma para los nuevos CUs. Pendiente decidir si se aplica retroactivamente a los ya hechos.

---

## [~20:40] Nombre técnico del `alt`

**Prompt:** ¿El `alt` tiene un nombre técnico?

**Resultado:** Sí — **fragmento combinado** (*combined fragment*) de tipo **alternativa** (*alternative*). Equivalente al `if/else` en diagramas de secuencia. Es el término de la especificación UML formal.

**Decisión:** Ninguna acción requerida, solo contexto.

---

## [~21:00] Revisión del `alt` en `abrirProyectos`

**Prompt:** ¿Por qué tenemos el `alt` tanto en el diagrama del coordinador como en el del investigador de `abrirProyectos`?

**Resultado:** Se leyeron ambos `.puml`. El `alt sin filtro / con filtro` modela si el parámetro `?criterio=` está presente o no — no representa dos caminos de negocio distintos. Es el mismo flujo con un parámetro opcional.

**Decisión:** El `alt` se puede quitar. No aporta información de negocio relevante y añade ruido al diagrama. En diseño, el nombre del método ya implica si hay criterio o no.

---
