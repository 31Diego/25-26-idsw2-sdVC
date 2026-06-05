# Sesión — 2026-06-05 · Perfil · Agregar/EliminarInvestigador · Diseño e implementación

## [~14:00] Norma de diagramas de diseño por actor

**Prompt:** Los diseños de los casos de uso se hacen por actor, sin el `alt`.

**Resultado:** Guardado en memoria. Un diagrama por actor, sin fragmentos combinados para unificar actores distintos.

**Decisión:** Aceptado como norma permanente.

---

## [~14:10] abrirOpcionesPerfil — Diseño e implementación

**Prompt:** Hay un error grave: cambiarRol está en `abrirInvestigador` y debería estar en `abrirOpcionesPerfil`. Diseñar e implementar `abrirOpcionesPerfil` y mover esa funcionalidad.

**Resultado:**
- Diseño: PUML + MD para coordinador (`GET /investigadores/{id}/opciones`) e investigador (`GET /perfil/opciones`)
- Implementación: `OpcionesPerfilController` (2 rutas), `opciones-perfil.html`, `obtenerInvestigadorPorUsername` en `InvestigadorService`, enlace de opciones en `investigador.html`, enlace en `panel.html`, `CambiarRolController` redirige a opciones
- `abrirInvestigador.md` actualizado (sección cambiarRol eliminada)

**Decisión:** Aceptado.

---

## [~14:30] cambiarRol → editarPerfil

**Prompt:** El cambio de rol debe ir en `editarPerfil`, no en `abrirOpcionesPerfil`.

**Resultado:**
- Diseño: PUML + MD para coordinador (`GET|POST /investigadores/{id}/editar`) e investigador (`GET|POST /perfil/editar`)
- Implementación: `EditarPerfilController` (4 endpoints), `editar-perfil.html` con campo `rol` condicional (`sec:authorize` + coordinador), `actualizarPerfil` y `actualizarPerfilConRol` en `InvestigadorService`, enlace a editar arreglado en `opciones-perfil.html`, botón cambiarRol eliminado

**Decisión:** Aceptado.

---

## [~14:45] editarPerfil — añadir username y contraseña

**Prompt:** En `editarPerfil` también se debe poder editar usuario y contraseña.

**Resultado:** Campos `username` (requerido, prellenado) y `password` (opcional, vacío) añadidos al formulario y a los métodos del servicio. Contraseña solo se re-hashea si viene rellena.

**Decisión:** Aceptado.

---

## [~15:00] editarPerfil — rol visible sin importar la ruta

**Prompt:** Si se accede a `editarPerfil` desde `panelPrincipal → abrirOpcionesPerfil`, el campo `rol` no aparece. Debe aparecer siempre para el coordinador.

**Resultado:** Eliminada la condición `th:if="${!esPropioPeril}"` del `div` del rol en el template. El POST de `/perfil/editar` acepta ahora `rol` como parámetro opcional.

**Decisión:** Aceptado.

---

## [~15:10] crearInvestigador — restringido a coordinador

**Prompt:** El investigador no puede crear un nuevo investigador, eso solo puede hacerlo el coordinador.

**Resultado:** `@PreAuthorize("hasRole('COORDINADOR')")` añadido en GET y POST de `CrearInvestigadorController`.

**Decisión:** Aceptado.

---

## [~15:15] abrirInvestigadores — ocultar "Nuevo investigador" al investigador

**Prompt:** Quitar la opción "Nuevo investigador" del menú de `abrirInvestigadores` solo para el investigador.

**Resultado:** Añadido namespace `sec` a `investigadores.html` y enlace envuelto en `sec:authorize="hasRole('COORDINADOR')"`.

**Decisión:** Aceptado.

---

## [~15:40] agregarInvestigador — Diseño e implementación

**Prompt:** Diseñar e implementar `agregarInvestigador` del coordinador.

**Resultado:**
- Diseño: PUML + MD para coordinador (`GET|POST /proyectos/{id}/investigadores/agregar`)
- Implementación: `obtenerTodosLosInvestigadores` en `InvestigadorService`, `agregarInvestigador(proyecto, investigador)` en `ProyectoService`, `AgregarInvestigadorController`, `agregar-investigador.html` (tabla con investigadores disponibles filtrados, un form POST por fila)

**Decisión:** Aceptado.

---


## [~16:00] eliminarInvestigador — Diseño e implementación

**Prompt:** Diseñar e implementar `eliminarInvestigador` del coordinador.

**Resultado:**
- Diseño: PUML + MD para coordinador (`GET|POST /proyectos/{pId}/investigadores/{iId}/eliminar`)
- Implementación: `eliminarInvestigador(proyecto, investigador)` en `ProyectoService`, `EliminarInvestigadorController`, `eliminar-investigador.html` (página de confirmación), enlace "Eliminar" por investigador en `proyecto.html` (solo coordinador)

**Decisión:** Aceptado.

---

## [~16:20] Bug: investigador no puede abrir proyecto concreto

**Prompt:** Cuando se agrega un investigador a un proyecto, puede verlo en la lista de proyectos pero no puede abrirlo. Solo pasa con el actor investigador.

**Resultado:** El `contains` en `ProyectoController` comparaba por referencia de objeto (el `Investigador` de `@AuthenticationPrincipal` y el de la BD son instancias distintas). Corregido con `.stream().noneMatch(inv -> inv.getId().equals(investigador.getId()))`.

**Decisión:** Aceptado.

---
