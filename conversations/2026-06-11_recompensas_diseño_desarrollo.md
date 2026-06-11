# Sesión — 2026-06-11 · Recompensas · Diseño + Desarrollo

## [~] Diseño e implementación de los 5 casos de uso de Recompensas

**Prompt:** Hacer el diseño e implementación de `abrirRecompensas`, `abrirRecompensa`, `crearRecompensa`, `editarRecompensa` y `eliminarRecompensa`. El coordinador ve todas las recompensas; el investigador solo las suyas. Al crear una recompensa el coordinador selecciona el investigador destinatario.

**Resultado:**

Diseño — 14 artefactos (`.md` + `.puml` por cada caso de uso):
- `documents/diseño/coordinador/{abrirRecompensas,abrirRecompensa,crearRecompensa,editarRecompensa,eliminarRecompensa}.md`
- `modelosUML/diseño/coordinador/{abrirRecompensas,abrirRecompensa,crearRecompensa,editarRecompensa,eliminarRecompensa}.puml`
- `documents/diseño/investigador/{abrirRecompensas,abrirRecompensa}.md`
- `modelosUML/diseño/investigador/{abrirRecompensas,abrirRecompensa}.puml`

Código:
- `Recompensa.java` — entidad JPA con `titulo`, `tipo`, `valor`, `descripcion` (TEXT), `condiciones` (TEXT), `fechaCreacion` y `@ManyToOne Investigador destinatario`
- `RecompensaRepository.java` — JpaRepository con `findByDestinatario`
- `RecompensaService.java` — `obtenerTodas`, `obtenerPorDestinatario`, `obtenerPorId`, `crear`, `actualizar`, `eliminar`
- `RecompensaController.java` — controlador único con diferenciación por rol
- `recompensas.html`, `recompensa.html`, `crear-recompensa.html`, `editar-recompensa.html`, `eliminar-recompensa.html`
- `panel.html` — añadido enlace "Recompensas" visible para ambos roles

**Decisiones:**
- `GET /recompensas` — mismo endpoint para coordinador e investigador; el controlador comprueba el rol: coordinador → `obtenerTodas()`, investigador → `obtenerPorDestinatario(usuario)`.
- `GET /recompensas/{id}` — si el usuario es investigador y no es el destinatario, redirige a `/recompensas`.
- `crear/editar/eliminar` — `@PreAuthorize("hasRole('COORDINADOR')")` a nivel de método (no de clase, para no bloquear al investigador en el listado/detalle).
- Formulario de crear/editar incluye `<select>` con todos los investigadores para elegir destinatario.
- `progreso.md` actualizado: todas las filas de Recompensas (coordinador e investigador) pasan a ✅ diseño + ✅ código + ⚠️ doc. desarrollo.

---
