# Sesión — 2026-06-03 · investigadores + proyectos del investigador · diseño + implementación

## [~10:00] Orientación — buenas

**Prompt:** "buenas claude"

**Resultado:** Estado del proyecto: P0 coordinador completo, entregables con subida/descarga funcionando. Pendiente P1: agregarInvestigador/eliminarInvestigador, entregables, publicaciones, convocatorias. P2: recompensas, investigadores, gestión perfil.

**Decisión:** Aceptado.

---

## [~10:10] Diseño e implementación de investigadores

**Prompt:** "podemos hacer el diseño e implementacion de los investigadores"

**Resultado:** Generados 16 archivos para los CUs `abrirInvestigadores`, `abrirInvestigador`, `crearInvestigador` del coordinador:
- Diseño: 3 `.md` + 3 `.puml` en `documents/diseño/coordinador/` y `modelosUML/diseño/coordinador/`
- Modificado `Investigador.java` (añadidos `campo`, `carrera`, `master`)
- Modificado `InvestigadorRepository.java` (añadido `buscarPorCriterio`)
- Nuevos: `InvestigadoresService`, `InvestigadorService`, `InvestigadoresController`, `InvestigadorController`, `CrearInvestigadorController`
- Nuevos templates: `investigadores.html`, `investigador.html`, `crear-investigador.html`
- `panel.html` actualizado con enlace a `/investigadores`

**Decisión:** Aceptado.

---

## [~10:30] Añadir credenciales y cambio de rol

**Prompt:** Hacer que los investigadores tengan user y password reales en el formulario de creación, y que el coordinador pueda cambiar el rol de un investigador entre INVESTIGADOR y COORDINADOR.

**Resultado:**
- `crear-investigador.html` — añadido campo `Contraseña *`
- `InvestigadorService.guardarInvestigador` — ahora codifica la contraseña del formulario (antes usaba el username)
- `SecurityConfig` — añadido `@EnableMethodSecurity`
- `CambiarRolController` — POST `/investigadores/{id}/cambiar-rol`, protegido con `@PreAuthorize("hasRole('COORDINADOR')")`
- `InvestigadorService` — añadido `cambiarRol(id)` (alterna COORDINADOR ↔ INVESTIGADOR)
- `investigador.html` — botón de cambio de rol visible solo con `sec:authorize="hasRole('COORDINADOR')"`

**Decisión:** Aceptado.

---

## [~10:45] Actualización de diagramas tras cambios

**Prompt:** "has tenido que cambiar algo de los diagramas de secuencia de diseño?"

**Resultado:** Sí, se actualizaron:
- `crearInvestigador.md` y `.puml` — decisión de diseño y nota del diagrama: `encode(username)` → `encode(password_del_formulario)`; añadido campo `password` a la tabla de campos
- `abrirInvestigador.md` — añadida sección de cambio de rol (ruta, restricción, doble capa UI + backend)

**Decisión:** Aceptado.

---

## [~11:00] Documento de diferencias entre actores

**Prompt:** Antes de hacer el análisis del investigador, crear un `.md` que exponga las diferencias de los CUs compartidos entre coordinador e investigador.

**Resultado:** Creado `documents/diferenciasActores.md` con:
- CUs exclusivos del coordinador (14)
- CUs compartidos con comportamiento diferente (`abrirPanelPrincipal`, `abrirProyectos`, `abrirProyecto`, `abrirRecompensas`, `abrirInvestigadores`, `abrirInvestigador`, `abrirOpcionesCargaTrabajo`)
- CUs idénticos
- Implicaciones de implementación para cada diferencia

**Decisión:** Aceptado con correcciones:
- `abrirRecompensas` del investigador → solo sus propias recompensas (no todas)
- `abrirOpcionesCargaTrabajo` → coordinador ve todas las cargas, investigador solo la suya
Ambas correcciones aplicadas al documento.

---

## [~11:20] Análisis de proyectos del investigador

**Prompt:** "si" (confirmar arrancar con el análisis de proyectos del investigador)

**Resultado:** Generados 4 archivos de análisis para `abrirProyectos` y `abrirProyecto` del investigador:
- `documents/analisis/investigador/abrirProyectos.md` + `.puml`
- `documents/analisis/investigador/abrirProyecto.md` + `.puml`

Diferencias clave documentadas: `Investigador` aparece como entidad en `abrirProyectos` (filtro por membresía), sin colaboración `CrearProyecto`; `abrirProyecto` sin colaboraciones de gestión (Editar/Eliminar/Agregar).

**Decisión:** Aceptado. Se ajustó el skinparam del `.puml` de `abrirProyectos` por recuadros pequeños (`skinparam padding 12`).

---

## [~11:45] Diseño e implementación de proyectos del investigador

**Prompt:** "nada ya estan perfectos, puedes hacer el diseño y la implementacion de esos casos de uso"

**Resultado:** Generados 4 archivos de diseño + 9 cambios de implementación:

**Diseño:**
- `documents/diseño/investigador/abrirProyectos.md` + `.puml` — documenta `alt` coordinador/investigador, filtro por `findByInvestigadoresContaining`
- `documents/diseño/investigador/abrirProyecto.md` + `.puml` — documenta verificación de membresía con redirect si no es miembro

**Implementación:**
- `Proyecto.java` — añadida relación `@ManyToMany` con tabla `proyecto_investigador`
- `ProyectoRepository.java` — añadidos `findByInvestigadoresContaining` y `buscarPorCriterioEInvestigador`
- `ProyectosService.java` — añadidos `obtenerProyectosDeInvestigador` y `filtrarProyectosDeInvestigador`
- `ProyectosController.java` — `@AuthenticationPrincipal` + bifurcación por rol
- `ProyectoController.java` — `@AuthenticationPrincipal` + guard de membresía con redirect
- `proyectos.html` — botón "Nuevo proyecto" oculto para investigadores
- `proyecto.html` — Editar/Eliminar/Agregar ocultos; añadida lista del equipo
- `DataLoader.java` — crea investigadora "maría/maría" asignada a los dos primeros proyectos si no hay usuarios con rol INVESTIGADOR

**Decisión:** Aceptado.

---
