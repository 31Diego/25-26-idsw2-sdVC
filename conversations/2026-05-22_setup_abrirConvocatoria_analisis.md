# Sesión — 2026-05-22 · Setup del proyecto + abrirConvocatoria · Análisis

---

## [~18:00] Configuración inicial del CLAUDE.md con trigger "buenas"

**Prompt:** Quiero que al escribir una palabra clave como "buenas", la IA se lea los archivos pertinentes para el caso de uso que se trate en esa sesión.

**Resultado:** Se creó el `CLAUDE.md` con el trigger `buenas [actor/]casodeuso` que carga en paralelo el modelo de dominio, los casos de uso generales, el detalle del caso de uso indicado y el conversation-log.

**Decisión:** Aceptado.

---

## [~18:15] Adaptación del CLAUDE.md según documento del profesor

**Prompt:** El profesor compartió su propio CLAUDE.md con reglas estructuradas. Se pidió adaptar el nuestro siguiendo ese modelo.

**Resultado:** Se incorporaron: protocolo de gestión de contexto con obligaciones de Claude y del usuario, protocolo de cierre de sesión, ley de rama de revisión (`xRevisar`), y regla de idioma en español.

**Decisión:** Se aceptó la estructura general. Se rechazó posteriormente la ley de rama de revisión por innecesaria para este proyecto.

---

## [~18:30] Refinamiento del trigger "buenas" y protocolo "terminamos"

**Prompt:** "buenas" va solo, sin argumentos. Al ponerlo, Claude se orienta (fecha, estado del proyecto, casos pendientes) y espera. Al decir "terminamos", guarda la sesión en `conversations/`.

**Resultado:** CLAUDE.md reescrito con el nuevo comportamiento. Se creó la carpeta `conversations/`.

**Decisión:** Aceptado. El nombre de archivo de sesión sigue el patrón `YYYY-MM-DD_{casodeuso}_{fase}.md`.

---

## [~18:40] Eliminación de la estructura del repositorio del CLAUDE.md

**Prompt:** ¿Conviene tener la estructura del repositorio en CLAUDE.md si va a cambiar?

**Resultado:** Se eliminó esa sección del CLAUDE.md para evitar que quede desactualizada.

**Decisión:** Aceptado. Solo se mantienen rutas a archivos estables (contexto de IDSW1).

---

## [~18:45] Carpeta de ejemplos por fase

**Prompt:** Se explicó que `context/ejemplos/` contiene ejemplos de artefactos por fase (análisis, diseño, desarrollo, detalle), con un `.md` y un `.puml` cada uno, basados en el caso `abrirAulas` del proyecto del profesor.

**Resultado:** Se añadió al CLAUDE.md la instrucción de leer el ejemplo de la fase correspondiente (`context/ejemplos/{fase}/abrirAulas.md` + `.puml`) como plantilla de estructura al producir cada artefacto.

**Decisión:** Aceptado.

---

## [~19:00] Prueba del sistema — "buenas"

**Prompt:** "buenas" (trigger de orientación).

**Resultado:** Claude leyó `conversation-log.md` y `priorizacionCasosDeUso.md`, informó de la fecha (2026-05-22), indicó que el log estaba en blanco (primera sesión real) y listó los casos de uso pendientes por prioridad.

**Decisión:** Funcionó correctamente.

---

## [~19:05] Análisis de abrirConvocatoria

**Prompt:** "vamos a hacer el análisis del caso de uso abrirConvocatoria"

**Resultado:** Claude cargó el detalle del caso de uso (`abrirConvocatoria.puml`), el modelo de dominio y el ejemplo de análisis (`abrirAulas`). Generó:
- `abrirConvocatoria.md` — documento de análisis con clases `ConvocatoriaView` (boundary), `ConvocatoriaController` (control), `ConvocatoriaRepository` y `Convocatoria` (entity).
- `abrirConvocatoria.puml` — diagrama de colaboración MVC.

**Decisión:** Aceptado.

---

## [~19:15] Regla de organización de artefactos

**Prompt:** Los artefactos deben ir en carpetas raíz según su tipo: `.md` → `documents/`, `.puml` → `modelosUML/`, `.svg` → `images/`, código → `src/`.

**Resultado:** Se añadió la regla al CLAUDE.md. Se movieron los archivos generados a sus rutas correctas:
- `documents/analisis/coordinador/abrirConvocatoria/abrirConvocatoria.md`
- `modelosUML/analisis/coordinador/abrirConvocatoria/abrirConvocatoria.puml`

**Decisión:** Aceptado.

---

## [~19:25] Intento de generación del SVG

**Prompt:** Generar el SVG a partir del PUML y revisar el MD.

**Resultado:** No se pudo localizar el `plantuml.jar` en el sistema. La sesión se cerró antes de completarlo.

**Decisión:** Pendiente para la próxima sesión: localizar `plantuml.jar` o indicar a Claude la ruta correcta.

---
