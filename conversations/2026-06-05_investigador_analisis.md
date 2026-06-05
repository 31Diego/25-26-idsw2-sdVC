# Sesión — 2026-06-05 · Investigador · Análisis

## [~17:00] Generar todos los casos de análisis del actor investigador

**Prompt:** Crear todos los casos de análisis que faltan del actor investigador, usando como referencia los ya existentes del coordinador.

**Resultado:**
- **25 PUML nuevos** en `modelosUML/analisis/investigador/`: iniciarSesion, cerrarSesion, abrirPanelPrincipal, abrirOpcionesPerfil, editarPerfil, abrirOpcionesCargaTrabajo, editarCargaTrabajo, abrirEntregables, abrirEntregable, crearEntregable, editarEntregable, eliminarEntregable, abrirPublicaciones, abrirPublicacion, responderPublicacion, abrirMisPublicaciones, abrirMiPublicacion, crearPublicacion, editarPublicacion, eliminarPublicacion, abrirRecompensas, abrirRecompensa, abrirInvestigadores, abrirInvestigador, solicitarEliminacionPerfil
- **27 MD nuevos** en `documents/analisis/investigador/`: todos los anteriores más abrirProyectos y abrirProyecto (cuyos PUML ya existían)
- Diferencias clave respecto al coordinador reflejadas en los artefactos: `abrirPanelPrincipal` sin convocatorias; `abrirProyectos` filtra por investigador autenticado y sin opción crear; `abrirProyecto` solo consulta sin editar/eliminar/agregar investigadores; `abrirPublicacion` solo permite responder; `abrirRecompensa` y `abrirInvestigador` en modo consulta puro

**Decisión:** Aceptado (revisión pendiente por el usuario).

---
