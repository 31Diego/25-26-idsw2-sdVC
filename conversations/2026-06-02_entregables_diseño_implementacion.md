# Sesión — 2026-06-02 · entregables · diseño + implementación

## [~20:00] Orientación — buenas

**Prompt:** "buenas claude"

**Resultado:** Estado del proyecto: P0 coordinador completo, diseño + implementación de Proyectos (5 CUs) funcionando. Pendiente P1: agregarInvestigador, eliminarInvestigador, entregables, publicaciones, convocatorias.

**Decisión:** Aceptado.

---

## [~20:05] Corrección protocolo "buenas" — conversation-log.md no tiene contenido

**Prompt:** El archivo `conversation-log.md` nunca tendrá contenido; las conversaciones están en `conversations/`.

**Resultado:** Actualizado `CLAUDE.md` para que el trigger "buenas" lea el archivo más reciente de `conversations/` (y el anterior si hace falta), en lugar de `conversation-log.md`. Eliminadas referencias a `conversation-log.md` en el protocolo de cierre. Guardado en memoria.

**Decisión:** Aceptado.

---

## [~20:10] Diseño e implementación de entregables

**Prompt:** Hacer diseño e implementación de los entregables de un proyecto. Al crear un entregable se puede subir un archivo que se guarda en la carpeta `archivos/`.

**Resultado:** Generados 23 archivos:
- **Diseño:** 5 `.md` en `documents/diseño/coordinador/` + 5 `.puml` en `modelosUML/diseño/coordinador/` para abrirEntregables, abrirEntregable, crearEntregable, editarEntregable, eliminarEntregable.
- **Implementación:** `Entregable.java` (@Entity, @ManyToOne Proyecto), `EntregableRepository` (findByProyectoId), `EntregableService` (CRUD + gestión de archivos con Files.copy/deleteIfExists en ./archivos/), 5 controllers (Entregables, Entregable, CrearEntregable, EditarEntregable, EliminarEntregable), 5 templates HTML.

**Decisión:** Aceptado.

---

## [~21:30] Corrección — no dejaba descargar el archivo adjunto

**Prompt:** "no deja descargar el entregable"

**Resultado:** Añadido `ArchivoController.java` con endpoint `GET /archivos/{nombre}` que sirve el fichero desde `./archivos/` con cabecera `Content-Disposition: attachment`. Actualizado `entregable.html` para mostrar enlace de descarga en lugar de texto plano.

**Decisión:** Aceptado.

---
