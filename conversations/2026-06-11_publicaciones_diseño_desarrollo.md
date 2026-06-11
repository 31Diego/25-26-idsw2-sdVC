# Sesión — 2026-06-11 · Publicaciones + Mis Publicaciones · Diseño + Desarrollo

## [~10:00] Diseño e implementación de editarPublicacion y eliminarPublicacion (coordinador)

**Prompt:** Hacer diseño e implementación de `editarPublicacion` y `eliminarPublicacion`. Estos casos tienen el mismo nombre que los de Mis Publicaciones pero con comportamiento idéntico; solo el coordinador puede acceder cuando se trata de publicaciones generales.

**Resultado:**

Documentos de diseño creados:
- `documents/diseño/coordinador/editarPublicacion.md`
- `documents/diseño/coordinador/eliminarPublicacion.md`
- `modelosUML/diseño/coordinador/editarPublicacion.puml`
- `modelosUML/diseño/coordinador/eliminarPublicacion.puml`

Código:
- `PublicacionService` — añadidos `actualizar(id, titulo, contenido)` y `eliminar(id)`
- `PublicacionController` — 4 endpoints nuevos con `@PreAuthorize("hasRole('COORDINADOR')")`:
  - `GET/POST /publicaciones/{id}/editar`
  - `GET/POST /publicaciones/{id}/eliminar`
- `editar-publicacion.html` — formulario pre-rellenado con PRG
- `eliminar-publicacion.html` — pantalla de confirmación con conteo de respuestas
- `publicaciones.html` — añadidos enlaces Editar/Eliminar con `sec:authorize="hasRole('COORDINADOR')"`
- `publicacion.html` — ídem

**Decisión:** Aceptado. Las respuestas se borran en cascada al eliminar la publicación (`CascadeType.ALL` ya existente en el modelo). El investigador recibe 403 si intenta acceder por URL directa.

---

## [~10:30] Diseño e implementación de abrirMisPublicaciones, abrirMiPublicacion, crearPublicacion

**Prompt:** Diseño e implementación de los casos de uso de Mis Publicaciones: `abrirMisPublicaciones`, `abrirMiPublicacion` y `crearPublicacion`. Los demás casos (`editarMiPublicacion`, `eliminarMiPublicacion`) pueden ser reutilizados de la entidad Publicaciones.

**Resultado:**

Documentos de diseño creados (×2, uno por actor):
- `documents/diseño/{coordinador,investigador}/abrirMisPublicaciones.md`
- `documents/diseño/{coordinador,investigador}/abrirMiPublicacion.md`
- `documents/diseño/{coordinador,investigador}/crearPublicacion.md`
- `modelosUML/diseño/{coordinador,investigador}/abrirMisPublicaciones.puml`
- `modelosUML/diseño/{coordinador,investigador}/abrirMiPublicacion.puml`
- `modelosUML/diseño/{coordinador,investigador}/crearPublicacion.puml`

Código:
- `PublicacionRepository` — añadido `findByAutor(Investigador)`
- `PublicacionService` — añadidos `obtenerPorAutor(autor)` y `crear(titulo, contenido, autor)`
- `PublicacionController` — refactorizado:
  - Se elimina `@PreAuthorize("hasRole('COORDINADOR')")` de edit/delete; sustituido por helper privado `esCoordinadorOAutor()` que permite acceso al coordinador O al autor de la publicación
  - 4 endpoints nuevos: `GET /mis-publicaciones`, `GET /mis-publicaciones/{id}`, `GET/POST /mis-publicaciones/crear`
  - `abrirMiPublicacion` verifica que la publicación pertenece al usuario autenticado; si no, redirige a `/mis-publicaciones`
- `mis-publicaciones.html`, `mi-publicacion.html`, `crear-publicacion.html` — templates nuevos
- `panel.html` — añadido enlace "Mis publicaciones"

**Decisión:** Aceptado. Decisiones clave:
- URL `/mis-publicaciones` compartida entre ambos roles (misma lógica, sin bifurcación)
- `editarMiPublicacion` y `eliminarMiPublicacion` reutilizan literalmente los endpoints `/publicaciones/{id}/editar|eliminar`, que ahora permiten coordinador O autor
- `mi-publicacion.html` siempre muestra Editar/Eliminar (es tu propia publicación); `publicacion.html` los muestra solo al coordinador con `sec:authorize`

---

## [~11:00] Fix: quitar Editar/Eliminar del listado general de publicaciones

**Prompt:** Los casos de uso `editarPublicacion` y `eliminarPublicacion` deben ser accesibles solo desde la publicación concreta, no desde el listado. Solo en PUBLICACIONES; en MIS_PUBLICACIONES está bien.

**Resultado:**
- `publicaciones.html` — eliminados los enlaces Editar/Eliminar de cada fila de la tabla; eliminado también el namespace `sec` ya innecesario. Solo queda el enlace "Ver".

**Decisión:** Aceptado. Los enlaces Editar/Eliminar permanecen exclusivamente en `publicacion.html` (vista de detalle), protegidos por `sec:authorize="hasRole('COORDINADOR')"`.

---

## Pendiente para la próxima sesión

1. Renderizar a SVG los `.puml` de esta sesión (× 10):
   - `editarPublicacion`, `eliminarPublicacion` (coordinador)
   - `abrirMisPublicaciones`, `abrirMiPublicacion`, `crearPublicacion` (× 2 por actor)
2. Continuar con los casos de uso P1 pendientes:
   - `abrirConvocatorias`, `abrirConvocatoria`, `importarConvocatoria` (coordinador)
