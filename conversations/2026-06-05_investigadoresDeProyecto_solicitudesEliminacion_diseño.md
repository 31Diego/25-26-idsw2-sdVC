# Sesión — 2026-06-05 · abrirInvestigadoresDeProyecto + Solicitudes de eliminación · Diseño e implementación

> ⚠️ **NOTA PARA EL INICIO DE LA PRÓXIMA SESIÓN**: Revisar bien los diseños PUML creados hoy (pendientes de renderizar como SVG) y repasar las últimas implementaciones antes de continuar. Ver sección "Pendiente de revisión" al final.

---

## [~17:30] abrirInvestigadoresDeProyecto — Diseño e implementación (coordinador)

**Prompt:** Diseñar e implementar `abrirInvestigadoresDeProyecto` del coordinador.

**Resultado:**
- PUML + MD en `modelosUML/diseño/coordinador/` y `documents/diseño/coordinador/`
- `InvestigadoresProyectoController` — `GET /proyectos/{id}/investigadores`, restringido a `COORDINADOR`
- `investigadores-proyecto.html` — tabla con investigadores del proyecto, enlace a cada perfil
- Enlace "Ver investigadores" añadido en `proyecto.html` dentro del bloque coordinador

**Decisión:** Aceptado.

---

## [~18:00] abrirInvestigadoresDeProyecto — Diseño e implementación (investigador)

**Prompt:** Diseñarlo e implementarlo también para el investigador, con las diferencias de actor.

**Resultado:**
- PUML + MD en `modelosUML/diseño/investigador/` y `documents/diseño/investigador/`
- `InvestigadoresProyectoController` actualizado: eliminado `@PreAuthorize`, añadido check de membresía (redirige a `/proyectos` si el investigador no pertenece al proyecto)
- Enlace "Ver investigadores" movido fuera del bloque coordinador en `proyecto.html` (visible para ambos actores)

**Decisión:** Aceptado.

---

## [~18:20] abrirSolicitudesEliminacionPerfil — Diseño e implementación

**Prompt:** Diseñar e implementar `abrirSolicitudesEliminacionPerfil`.

**Resultado:**
- Nueva entidad `SolicitudEliminacion` (campos: investigador ManyToOne, motivo, fecha, estado)
- `SolicitudEliminacionRepository` + `SolicitudEliminacionService` (obtenerSolicitudes, obtenerSolicitud)
- `SolicitudesEliminacionController` — `GET /solicitudes-eliminacion`, solo coordinador
- `solicitudes-eliminacion.html` — tabla con enlace a cada solicitud
- Enlace añadido en `opciones-perfil.html` bajo coordinador

**Decisión:** Aceptado.

---

## [~18:50] Corrección: acceso a solicitudes solo desde opciones de perfil

**Prompt:** La lista de solicitudes solo debe ser accesible desde `OPCIONES_PERFIL_ABIERTO`, no desde el panel principal.

**Resultado:** Eliminado el enlace del `panel.html`. El enlace permanece únicamente en `opciones-perfil.html`.

**Decisión:** Aceptado como corrección de diseño.

---

## [~19:00] abrirSolicitudEliminacionPerfil + solicitarEliminacionPerfil + eliminarPerfil — Diseño e implementación

**Prompt:** Diseñar e implementar los tres casos de uso.

**Resultado:**

**Diseño (8 archivos):**
- `modelosUML/diseño/coordinador/`: abrirSolicitudEliminacionPerfil, solicitarEliminacionPerfil, eliminarPerfil
- `modelosUML/diseño/investigador/`: solicitarEliminacionPerfil
- Sus correspondientes MD en `documents/diseño/`

**Implementación:**
- `SolicitudEliminacionController` — `GET /solicitudes-eliminacion/{id}` (solo coordinador)
- `SolicitarEliminacionController` — `GET|POST /investigadores/{id}/solicitar-eliminacion` (ambos actores; investigador solo puede usar su propio id)
- `EliminarPerfilController` — `GET|POST /investigadores/{id}/eliminar-perfil` (solo coordinador, no sobre sí mismo)
- `solicitud-eliminacion.html`, `solicitar-eliminacion.html`, `eliminar-perfil.html`
- `SolicitudEliminacionService.crearSolicitud(investigador, motivo)` añadido
- `SolicitudEliminacionRepository.findByInvestigadorId(id)` añadido
- `InvestigadorService.eliminarPerfil(id)` añadido con `@Transactional`: quita al investigador de proyectos → borra sus solicitudes → borra el investigador
- `opciones-perfil.html` actualizado: enlace "Solicitar eliminación" activo para ambos actores; enlace "Eliminar perfil" solo para coordinador cuando visita el perfil de otro

**Decisión:** Aceptado.

---

## Pendiente de revisión al inicio de la próxima sesión

1. **Renderizar los PUML creados hoy como SVG** — los siguientes diagramas están en `.puml` pero no tienen aún su `.svg` en `images/`:
   - `images/diseño/coordinador/`: abrirInvestigadoresDeProyecto, abrirSolicitudesEliminacionPerfil, abrirSolicitudEliminacionPerfil, solicitarEliminacionPerfil, eliminarPerfil
   - `images/diseño/investigador/`: abrirInvestigadoresDeProyecto, solicitarEliminacionPerfil

2. **Probar el flujo completo de eliminación de perfil** antes de continuar:
   - Investigador solicita eliminación → aparece en la lista del coordinador → coordinador abre la solicitud → navega al perfil → elimina

3. **Continuar con los casos pendientes** según priorización: publicaciones (P1) o recompensas (P2) para ambos actores.
