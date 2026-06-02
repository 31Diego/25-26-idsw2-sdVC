# Sesión — 2026-06-02 · Diseño proyectos · Revisión diagramas de secuencia

## [~15:00] Orientación — buenas

**Prompt:** "buenas claude" — orientación de inicio de sesión.

**Resultado:** Estado: análisis coordinador completo y corregido, diseño + implementación de Proyectos (5 CUs) funcionando. Pendiente P1: agregarInvestigador, eliminarInvestigador, entregables, publicaciones, convocatorias.

**Decisión:** Aceptado.

---

## [~15:00] Corrección diagramas de secuencia — añadir navegador

**Prompt:** Los diagramas de secuencia de diseño arrancan directamente con la petición HTTP (`Coord -> Ctrl: GET /...`), pero los de referencia empiezan con el actor navegando o haciendo clic antes de la solicitud.

**Resultado:** Añadido participante `Navegador` en los 5 PUMLs de diseño de proyectos. Patrón: `Coord -> Nav: [acción UI]` → `Nav -> Ctrl: GET/POST /...` en la ida, y `Ctrl --> Nav: 200 OK / 302 redirect` → `Nav --> Coord: muestra/navega` en la vuelta.

**Decisión:** Aceptado.

---

## [~15:40] Duda — tokens en los diagramas

**Prompt:** El ejemplo de referencia incluye `FE -> FE: Verificar Token` y `Bearer Token` en la cabecera. ¿Es necesario en nuestros diagramas?

**Resultado:** No. La referencia usa JWT (React + FastAPI). Nuestro stack usa sesión HTTP (JSESSIONID) gestionada automáticamente por el filtro de Spring Security — es infraestructura transparente, no lógica de aplicación. No se añade al diagrama.

**Decisión:** Aceptado.

---

## [~16:10] Duda — nomenclatura de controllers

**Prompt:** "¿Dónde está el controlador de abrirProyecto? No lo encuentro."

**Resultado:** Se llama `ProyectoController`, no `AbrirProyectoController`. Los controllers se nombran por entidad/ruta, no por caso de uso. Los nombres de casos de uso (`abrirProyecto`, `crearProyecto`) son para análisis y diseño; en el código Spring se usa convención de entidad + acción CRUD.

**Decisión:** Aclaración teórica.

---

## [~17:00] Comprensión de diagramas de secuencia

**Prompt:** Serie de preguntas sobre cómo leer los diagramas: flechas hacia la derecha y hacia la izquierda, qué es JpaRepository, qué es Optional<Proyecto>, qué representa el bloque `alt`, y explicación de los diagramas crearProyecto, editarProyecto y eliminarProyecto.

**Resultado:** Explicaciones:
- Flecha derecha `->`: llamada a método del participante receptor.
- Flecha izquierda `-->`: return del método llamado.
- `JpaRepository`: interfaz de Spring Data que provee métodos CRUD automáticos (findAll, findById, save, deleteById) sin escribirlos.
- `Optional<Proyecto>`: contenedor que puede tener valor o estar vacío; `.orElseThrow()` extrae el objeto o lanza excepción si no existe.
- Bloque `alt`: if-else UML; en abrirProyectos representa los dos modos (sin filtro / con filtro).
- `crearProyecto`: dos fases — GET carga formulario vacío, POST guarda con INSERT.
- `editarProyecto`: dos fases — GET carga formulario pre-relleno (findById), POST actualiza con findById + modificarCampos + save (UPDATE).
- `eliminarProyecto`: dos fases — GET muestra confirmación, POST borra con deleteById (void, sin retorno).

**Decisión:** Solo explicación teórica.

---
