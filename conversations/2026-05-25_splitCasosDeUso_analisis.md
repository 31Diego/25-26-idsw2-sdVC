# Sesión — 2026-05-25 · Split casos de uso con múltiples entradas · análisis

## [--:--] Revisión: estados vs colaboraciones en los diagramas

**Prompt:** Duda sobre por qué algunos diagramas usan `:ESTADO_EN_MAYÚSCULAS` y otros `:Collaboration NombreCasoDeUso` como destino.

**Resultado:** Explicación de los tres patrones de salida y creación de `context/ejemplos/analisis/plantillaNavegacion.puml` como referencia visual.

**Decisión:** Aceptado. (Registrado en sesión anterior 2026-05-25_plantillaNavegacion_analisis.md)

---

## [--:--] Identificar casos de uso con comportamiento diferente según la entrada

**Prompt:** "he estado revisando los diagramas de colaboracion de coordinador; y me he dado cuenta de una cosa; en los casos de uso que solo pueden ser llamados solo por un estado del diagrama de contexto se refleja bien, sin embargo los casos de uso que pueden ser llamados por mas de un estado no reflejan bien esto [...] dime a ver que opinas"

**Resultado:** Revisión de las 43 especificaciones detalladas del coordinador. Se identificaron dos categorías:

**Necesitan split (comportamiento diferente según entrada):**
- `abrirProyectos`: panel → todos / investigador → del investigador
- `abrirInvestigadores`: panel → todos / proyecto → del proyecto
- `abrirOpcionesPerfil`: panel → perfil propio / investigador → perfil de otro

**Solo falta añadir entradas al diagrama (mismo comportamiento):**
- `abrirConvocatorias`, `abrirEntregables`, `abrirInvestigador`, `abrirMisPublicaciones`, `abrirProyecto`, `abrirRecompensas`, `abrirSolicitudesEliminacionPerfil`

**Decisión:** Aceptado. Se decide hacer split (Opción A) para los 3 casos con comportamiento diferente.

---

## [--:--] Ejecutar el split de los 3 casos de uso

**Prompt:** "si"

**Resultado:** Creados 3 nuevos casos de uso con PUML + MD:
- `abrirProyectosDeInvestigador` — desde `INVESTIGADOR_ABIERTO`, proyectos filtrados
- `abrirInvestigadoresDeProyecto` — desde `PROYECTO_ABIERTO`, investigadores filtrados
- `abrirOpcionesPerfilInvestigador` — desde `INVESTIGADOR_ABIERTO` / `SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`

PUMLs existentes actualizados:
- `abrirProyectos` — añadida entrada `PROYECTO_ABIERTO`; eliminada `INVESTIGADOR_ABIERTO`
- `abrirOpcionesPerfil` — queda solo con `PANEL_PRINCIPAL_ABIERTO` (perfil propio)
- `abrirInvestigador` — `AbrirOpcionesPerfil` → `AbrirOpcionesPerfilInvestigador`; añadida colaboración `AbrirProyectosDeInvestigador`
- `abrirProyecto` — añadida colaboración `AbrirInvestigadoresDeProyecto`
- `abrirPanelPrincipal` — `AbrirOpcionesPerfil` → `AbrirOpcionesPerfilPropio`

SVGs generados por el usuario y verificados: 46 presentes en `images/analisis/`.

**Decisión:** Aceptado íntegro.

---
