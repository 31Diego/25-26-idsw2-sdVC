# Sesión — 2026-06-07 · Revisión diagramas de diseño + limpieza código

## [~14:00] Teoría: qué representa cada tipo de diagrama

**Prompt:** ¿Qué representan los diagramas de colaboración (análisis) frente a los de secuencia (diseño)? ¿Por qué no aparecen Services ni Repositories en análisis?

**Resultado:**
- **Análisis (colaboración):** modela el dominio del problema — separación lógica de responsabilidades (View, Controller, Model). El Model es la entidad del dominio directamente. No se incluyen Services ni Repositories porque son decisiones técnicas aún no tomadas.
- **Diseño (secuencia):** modela el flujo técnico de ejecución con clases reales, métodos concretos, parámetros y orden de llamadas. El "Model" del análisis se descompone en Service + Repository + Entity.
- Progresión: Análisis (qué) → Diseño (cómo) → Desarrollo (implementación)

**Decisión:** Aceptado.

---

## [~14:20] Teoría: proceso de revisión de diagramas

**Prompt:** ¿Cómo revisar que los diagramas de análisis y diseño concuerdan con la implementación? Ejemplo con abrirEntregable.

**Resultado:** Proceso en dos saltos:
- **Análisis → Diseño:** verificar que los elementos lógicos (View/Controller/Model) se descomponen correctamente en clases técnicas (Controller, Service, Repository, Entity)
- **Diseño → Código:** verificar nombre del controller, ruta (`@GetMapping`/`@PostMapping`), métodos del service, métodos del repo (declarados o heredados de JpaRepository), parámetros, return/redirect

Se trazó abrirEntregable completo: GET → `EntregableController.abrirEntregable` → `EntregableService.obtenerEntregable(id)` → `EntregableRepository.findById(id)` (heredado de JpaRepository). Todo concordante.

**Decisión:** Aceptado.

---

## [~14:40] Enfoque por niveles para la revisión

**Prompt:** Los diagramas simples son fáciles de revisar, pero los complejos como agregarInvestigador no sé cómo abordarlos.

**Resultado:** Se definió un enfoque por niveles:
- **Nivel 1 (abrirX, listarX):** patrón siempre igual — revisar ruta, método de service, método de repo. 30 segundos.
- **Nivel 2 (crear/editar/eliminar estándar):** trazar GET + POST, verificar parámetros y redirect. 2-3 minutos.
- **Nivel 3 (complejos):** foco en tres puntos: bloques `alt` (¿el código tiene ese condicional?), múltiples repositorios (¿el service usa exactamente los repos del diagrama?), orden de operaciones.

Estrategia acordada: revisar un representante de cada tipo y verificar que el resto sigue la misma estructura.

**Decisión:** Aceptado.

---

## [~15:00] Corrección de crear/editar/eliminarEntregable

**Prompt:** Revisar y corregir los diagramas de diseño de entregables.

**Resultado:** Los tres diagramas tenían el nombre de controller incorrecto (vestigio del refactor del día anterior):
- `CrearEntregableController` → `EntregableController`
- `EditarEntregableController` → `EntregableController`
- `EliminarEntregableController` → `EntregableController`

abrirEntregable ya estaba correcto.

**Decisión:** Aceptado. Tres PUML corregidos.

---

## [~15:20] Pregunta: qué son las anotaciones (@)

**Prompt:** ¿Para qué sirven las `@` en controllers y services?

**Resultado:** Explicación de las anotaciones Spring más usadas en el proyecto:
- Clases: `@Controller`, `@Service`, `@Repository`
- Métodos: `@GetMapping`, `@PostMapping`, `@PreAuthorize`, `@Transactional`
- Parámetros: `@PathVariable`, `@RequestParam`, `@ModelAttribute`, `@AuthenticationPrincipal`

Idea clave: sin anotaciones hay que escribir toda la configuración a mano; Spring la genera automáticamente al ver la `@`.

**Decisión:** Informativo.

---

## [~15:35] Pregunta: ¿usar interfaces para los services?

**Prompt:** ¿Sería buena idea añadir interfaces a las clases que tenemos?

**Resultado:** No recomendado para este proyecto. Los repositories ya son interfaces (el sitio correcto). Para los services, el patrón `XService` + `XServiceImpl` fue útil cuando había múltiples implementaciones o se necesitaban mocks; hoy Spring no lo necesita. Para los controllers no tiene sentido. Añadir interfaces sería boilerplate sin beneficio dado el tamaño del proyecto.

**Decisión:** Se descarta añadir interfaces.

---

## [~15:50] Fusión de services duplicados

**Prompt:** ¿Por qué existen `InvestigadorService` e `InvestigadoresService`? (y análogamente `ProyectoService` y `ProyectosService`)

**Resultado:** Vestigio del refactor de controllers del día anterior — se consolidaron los controllers pero no los services. `InvestigadoresService.obtenerInvestigadores()` era duplicado exacto de `InvestigadorService.obtenerTodosLosInvestigadores()`. Mismo patrón en proyectos.

Cambios:
- `filtrarInvestigadores` movido a `InvestigadorService`
- `obtenerProyectos`, `filtrarProyectos`, `obtenerProyectosDeInvestigador`, `filtrarProyectosDeInvestigador` movidos a `ProyectoService` (+ import `List`)
- `InvestigadorController` y `ProyectoController` actualizados para usar solo el service principal
- `InvestigadoresService.java` y `ProyectosService.java` eliminados

**Decisión:** Aceptado.

---

## [~16:30] Corrección masiva de nombres de controller en diagramas de diseño

**Prompt:** El usuario detectó que muchos diagramas de diseño seguían con nombres de controller incorrectos.

**Resultado:** 13 PUML corregidos:

| Archivo | Corrección principal |
|---|---|
| `coordinador/abrirProyectos` | `ProyectosController` → `ProyectoController`, `ProyectosService` → `ProyectoService` |
| `coordinador/crearProyecto` | `CrearProyectoController` → `ProyectoController` |
| `coordinador/editarProyecto` | `EditarProyectoController` → `ProyectoController` |
| `coordinador/eliminarProyecto` | `EliminarProyectoController` → `ProyectoController` |
| `coordinador/agregarInvestigador` | `AgregarInvestigadorController` → `ProyectoController` (GET y POST) |
| `coordinador/eliminarInvestigador` | `EliminarInvestigadorController` → `ProyectoController` |
| `coordinador/abrirInvestigadoresDeProyecto` | `InvestigadoresProyectoController` → `ProyectoController` |
| `coordinador/abrirInvestigadores` | `InvestigadoresController` → `InvestigadorController`, `InvestigadoresService` → `InvestigadorService`, `obtenerInvestigadores()` → `obtenerTodosLosInvestigadores()` |
| `coordinador/crearInvestigador` | `CrearInvestigadorController` → `InvestigadorController` |
| `coordinador/abrirEntregables` | `EntregablesController` → `EntregableController` + flujo corregido (controller usaba `ProyectoRepository` directamente, ahora pasa por `ProyectoService`) |
| `investigador/abrirProyectos` | `ProyectosController` → `ProyectoController`, `ProyectosService` → `ProyectoService` |
| `investigador/abrirInvestigadoresDeProyecto` | `InvestigadoresProyectoController` → `ProyectoController` |

**Decisión:** Aceptado.

---

## [~17:00] Revisión diagramas de perfil y eliminación (ambos actores)

**Prompt:** Revisar abrirOpcionesPerfil, editarPerfil, solicitarEliminacionPerfil, eliminarPerfil, abrirSolicitudesEliminacionPerfil, abrirSolicitudEliminacionPerfil de coordinador e investigador.

**Resultado:** Cuatro correcciones:
- `editarPerfil` (coordinador): POST faltaban `username` y `password` en parámetros y en llamada a `actualizarPerfilConRol`
- `editarPerfil` (investigador): mismo problema con `actualizarPerfil`
- `solicitarEliminacionPerfil` (coordinador): el GET no mostraba la llamada a `InvestigadorService.obtenerInvestigador(id)`; se añadió `InvestigadorService` e `InvestigadorRepository` como participants separados y se completó también el fetch del investigador en el POST
- El resto (abrirOpcionesPerfil coord/inv, eliminarPerfil, abrirSolicitudesEliminacionPerfil, abrirSolicitudEliminacionPerfil, solicitarEliminacionPerfil inv) estaban correctos

**Decisión:** Aceptado.

---

## Pendiente para la próxima sesión

1. Renderizar todos los PUML corregidos como SVG
2. Continuar con casos pendientes según priorización: **publicaciones** (P1)
