# CLAUDE.md — Proyecto IDSW2 · FUNIBER GIPF

Este archivo proporciona orientación a Claude Code cuando trabaja en este repositorio.

---

## PROTOCOLO: Trigger "buenas"

Cuando el usuario escriba **"buenas"** (solo, sin argumentos), activar el protocolo de orientación:

### Qué leer (solo lo indispensable)

1. El archivo más reciente de `conversations/` (ordenar por nombre, tomar el último) — Para saber dónde se quedó el proyecto. Si con uno no es suficiente para orientarse, leer el anterior también, pero no más de los necesarios.
2. `context/casosDeUso/priorizacionCasosDeUso.md` — Para saber qué casos de uso hay y en qué orden

Con eso es suficiente para orientarse. No leer más hasta que el usuario indique qué se va a hacer.

### Regla permanente de seguimiento

Al terminar trabajo sobre cualquier caso de uso (análisis, diseño, código o doc de desarrollo), actualizar la fila correspondiente en `documents/progreso.md` cambiando ⬜ → ✅ o ⬜ → ⚠️ según corresponda. Hacerlo en el momento, no al final de la sesión.

### Respuesta tras orientarse

Indicar brevemente:
- La fecha actual
- En qué punto está el proyecto (según el log)
- Qué casos de uso están pendientes según la priorización

Luego **esperar** a que el usuario indique la tarea concreta.

---

## Cuando el usuario indica la tarea

El usuario dirá algo como:
- "vamos a hacer el análisis de `crearProyecto`"
- "vamos con el diseño de `iniciarSesion` del coordinador"
- "continuamos con el desarrollo de `editarPerfil`"

En ese momento, leer **en paralelo** dos grupos de archivos:

### 1. Archivos del caso de uso concreto

- `context/modeloDelDominio/modeloDominio.md`
- `context/casosDeUso/casosDeUso.md`
- `context/casosDeUso/detalle/{actor}/{casodeuso}/{casodeuso}.md`
- `context/casosDeUso/detalle/{actor}/{casodeuso}/README.md` (si existe)

Si el actor no se menciona, buscar en ambas carpetas (`coordinador` e `investigador`).

### 2. Ejemplo de referencia para la fase indicada

Leer el ejemplo de la carpeta `context/ejemplos/{fase}/`:

| Fase indicada | Archivos a leer |
|---|---|
| análisis | `context/ejemplos/analisis/abrirAulas.md` + `context/ejemplos/analisis/abrirAulas.puml` |
| diseño | `context/ejemplos/diseño/abrirAulas.md` + `context/ejemplos/diseño/abrirAulas.puml` |
| desarrollo | `context/ejemplos/desarrollo/abrirAulas.md` |
| detalle | `context/ejemplos/detalle/abrirAulas.md` + `context/ejemplos/detalle/abrirAulas.puml` |

El ejemplo de `abrirAulas` es la **plantilla de estructura y formato** que debe seguir el artefacto producido. El contenido se adapta al caso de uso real, pero la organización de secciones, el estilo del diagrama y los niveles de detalle deben respetar el ejemplo.

---

## PROTOCOLO DE CIERRE: "terminamos"

Cuando el usuario escriba **"terminamos"**, guardar el progreso de la sesión:

### Qué guardar

Crear un archivo en la carpeta `conversations/` con el registro de la sesión.

### Nombre del archivo

```
conversations/YYYY-MM-DD_{casodeuso}_{fase}.md
```

Ejemplos:
- `conversations/2026-05-22_crearProyecto_analisis.md`
- `conversations/2026-05-22_iniciarSesion_diseño.md`
- `conversations/2026-05-22_sesionGeneral.md` (si no se trabajó un caso concreto)

### Contenido del archivo

```markdown
# Sesión — {fecha} · {casodeuso} · {fase}

## [HH:MM] Título breve de lo que se pidió

**Prompt:** lo que se le dijo a la IA (textual o resumido fielmente)

**Resultado:** lo que produjo

**Decisión:** qué se aceptó, rechazó o modificó, y por qué

---
```

Una entrada por cada intercambio relevante de la sesión, en orden cronológico.

---

## ORGANIZACIÓN DE ARTEFACTOS

Todo archivo generado va a la carpeta raíz correspondiente a su tipo, manteniendo la misma estructura interna de subcarpetas (`{fase}/{actor}/{casodeuso}/`):

| Tipo de archivo | Carpeta raíz |
|---|---|
| `.md` (documentación) | `documents/` |
| `.puml` (diagramas fuente) | `modelosUML/` |
| `.svg` (diagramas renderizados) | `images/` |
| Código fuente (`.py`, `.ts`, etc.) | `src/` |

**Ejemplo para el análisis de `abrirConvocatoria` del coordinador:**
- `documents/analisis/coordinador/abrirConvocatoria.md`
- `modelosUML/analisis/coordinador/abrirConvocatoria.puml`
- `images/analisis/abrirConvocatoria-analisis.svg` (generado por el usuario)

**Regla:** el archivo va directamente en la carpeta del actor, sin crear una subcarpeta adicional con el nombre del caso de uso.

Las rutas de imágenes en los `.md` deben apuntar siempre a `images/...`.

---

## IDIOMA VEHICULAR

**Español** en toda la documentación, commits y comunicación.

---

## Resumen del Proyecto

**FUNIBER GIPF** — Plataforma interna de investigación que conecta investigadores de la red FUNIBER con convocatorias de financiación y permite colaborar en proyectos desde la propuesta hasta la entrega de resultados.

**Actores:** `coordinador` (visión global) | `investigador` (información propia)

**Fases del proyecto IDSW2:** Análisis · Diseño · Desarrollo · Pruebas

---

