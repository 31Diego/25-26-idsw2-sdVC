# Sesión — 2026-05-31 · refactor coordinador + editarPerfil · Diseño y Desarrollo
> **Nota:** Este log cubre dos sesiones del mismo día. Las entradas finales (desde "Re-decisión arquitectónica" en adelante) han sido **reconstruidas a partir del código**, ya que el transcript de la segunda sesión fue truncado.

## [Inicio] Orientación y revisión del estado del proyecto

**Prompt:** "buenas claude" — orientación de inicio de sesión.

**Resultado:** Revisión del estado del proyecto leyendo archivos directamente (el conversation-log estaba vacío). Se detectó que el proyecto estaba más avanzado de lo que el transcript previo mostraba: proyectos, entregables, investigadores, convocatorias ya implementados.

**Decisión:** Aceptado. Se usó exploración directa de archivos como fuente de verdad.

---

## [Revisión] ¿Quién soy cuando entro como admin?

**Prompt:** "cuando estoy en el sistema quien soy, es decir entro como admin, pero no salgo en la lista de investigadores ni nada"

**Resultado:** Revisión del análisis de `abrirOpcionesPerfil` y `editarPerfil`. Conclusión: el coordinador era una entidad separada de `Investigador`, por eso no aparecía en la lista. El análisis dice que esos casos de uso gestionan el perfil de *un investigador*, no del propio coordinador.

**Decisión:** Detectada inconsistencia de diseño — el coordinador debería ser un investigador con privilegios, no una entidad separada.

---

## [Diseño/Decisión] El coordinador es un investigador con privilegios

**Prompt:** "el coordinador es un investigador con privilegios del sistema"

**Resultado:** Identificados los cambios necesarios:
- Eliminar `Coordinador.java` y `CoordinadorRepository.java`
- `Investigador` implementa `UserDetails` con campo `rol` (`COORDINADOR` / `INVESTIGADOR`)
- `AutenticacionService` carga desde `InvestigadorRepository`
- `DataLoader` crea el admin como `Investigador` con `rol = COORDINADOR`

**Decisión:** Aprobado. Se procedió con el refactor completo.

---

## [Desarrollo] Refactor: fusionar Coordinador en Investigador

**Prompt:** "si" — confirmación para proceder.

**Resultado:** Cambios realizados:
- `Investigador.java` — implementa `UserDetails`, añadidos `username`, `password`, `rol`
- `InvestigadorRepository` — añadido `findByUsername()`
- `AutenticacionService` — usa `InvestigadorRepository`
- `DataLoader` — crea admin como `Investigador` con `rol=COORDINADOR`; investigadores de demo con `rol=INVESTIGADOR`
- Eliminados `Coordinador.java` y `CoordinadorRepository.java`

**Decisión:** Aceptado íntegramente. Requiere borrar `funiber.mv.db` y reiniciar.

---

## [Revisión] ¿Está implementado editarPerfil?

**Prompt:** "puedes confirmarme si hemos implementado editarPerfil?"

**Resultado:** No. Solo existía `abrirOpcionesPerfilInvestigador` (lectura). No había controlador de edición, ni template de formulario, ni método `guardarPerfil` en el servicio.

**Decisión:** Se identificó como tarea a hacer en esta sesión.

---

## [Diseño + Desarrollo] editarPerfil

**Prompt:** "vamos a por ello, primero el diseño y luego la implementacion"

**Resultado:**

Diseño:
- `modelosUML/diseño/coordinador/editarPerfil.puml` — diagrama de secuencia completo (carga formulario + guardado)
- `documents/diseño/coordinador/editarPerfil.md` — documento de diseño con participantes, rutas, campos editables y decisiones

Implementación:
- `PerfilService` — añadido `guardarPerfil(id, datos)` que actualiza solo campos de perfil (no toca `username`, `password`, `rol`)
- `EditarPerfilController` — GET `/investigadores/{id}/perfil/editar` + POST `/investigadores/{id}/perfil`
- `editar-perfil.html` — formulario pre-rellenado
- `opciones-perfil-investigador.html` — añadido enlace "Editar perfil"

**Decisión:** Aceptado.

---

## [Desarrollo] Acceso a Mi perfil desde el panel principal

**Prompt:** "desde el panel principal debo tener acceso a las opciones de mi perfil"

**Resultado:**
- `PanelPrincipalController` — inyecta el `Investigador` autenticado via `@AuthenticationPrincipal` y pasa `usuarioId` al modelo
- `panel.html` — añadido enlace "Mi perfil" → `/investigadores/{usuarioId}/perfil`

**Decisión:** Aceptado.

---

## [Revisión] Cohesión y acoplamiento en las clases implementadas

**Prompt:** "antes de nada queria revisar un poco lo que llevamos hecho, lo primero son los conceptos de cohesion y acoplamiento, queria saber si nuestras clases tienen alta cohesion y bajo acoplamiento"

**Resultado:** Análisis de todas las clases. Conclusión: alta cohesión y bajo acoplamiento en general. El patrón MVC ayuda — cada capa tiene su rol claro. Punto débil detectado: `Investigador` agrupa tres conceptos (datos de perfil, carga de trabajo, enlace de autenticación), aunque aceptable por decisión de dominio. `DataLoader` tiene alto acoplamiento justificado.

**Decisión:** Aceptado como revisión teórica.

---

## [Decisión arquitectónica] Investigador es prematuro; Usuario no existe en el dominio

**Prompt:** "aun no deberiamos haber hecho la clase investigador; ya que de investigador no tenemos hecho nada de analisis ni de diseño, y luego con usuario pasa algo similar, no puede existir pq no existe como tal en nuestro sistema; en nuestro sistema solo pueden haber coordinadores e investigadores; siendo el coordinador un administrador del sistema con acceso a todas las funciones del mismo"

**Resultado:** Identificadas dos inconsistencias:
1. `Investigador.java` fue creado prematuramente sin análisis ni diseño de ese actor.
2. `Usuario` no es una entidad del dominio — el dominio solo tiene `Coordinador` e `Investigador`.

Plan de corrección:
- Eliminar `Investigador.java` y todo lo que depende de él
- Renombrar `Usuario` → `Coordinador` con sus repositorios y referencias
- Aplazar `abrirOpcionesPerfil`, `editarPerfil`, `abrirOpcionesCargaTrabajo`, `editarCargaTrabajo` hasta tener el análisis del investigador hecho

**Decisión:** Aprobado. Revierte los cambios de la sesión anterior de hoy (fusión Coordinador→Investigador).

---

## [Desarrollo] Refactor: eliminar Investigador + renombrar Usuario → Coordinador

**Prompt:** "si, procede"

**Resultado:** Cambios realizados:
- Eliminados: `Investigador.java`, `InvestigadorRepository.java`, `PerfilService.java`, `CargaTrabajoService.java`, `OpcionesPerfilController.java`, `EditarPerfilController.java`, `CargaTrabajoController.java`, `EditarCargaTrabajoController.java`
- Eliminados: templates de perfil y carga de trabajo, docs de diseño/desarrollo de esos 4 CUs, diagramas PUML correspondientes
- Creados: `Coordinador.java` (tabla `coordinadores`, implementa `UserDetails`), `CoordinadorRepository.java`
- Actualizados: `AutenticacionService` → usa `CoordinadorRepository`; `DataLoader` → solo crea coordinador + convocatorias; `panel.html` → sin enlaces a perfil ni carga de trabajo
- Eliminados: `Usuario.java`, `UsuarioRepository.java`; `guiaPruebas.md` limpiado

**Decisión:** Aceptado.

---

## [Desarrollo] Diseño + Desarrollo: proyectos (5 casos de uso)

**Prompt:** "vale, hoy nos vamos a centrar en hacer el diseño y la implementacion de los proyectos"

**Resultado:** Implementados los 5 casos de uso de proyectos partiendo de los PUMLs de análisis existentes.

Modelo y repositorio:
- `Proyecto.java` — entidad JPA (titulo, descripcion, objetivos, estado, fechaInicio, fechaFin, documentacion)
- `ProyectoRepository.java` — con `buscarPorCriterio()`

Servicios:
- `ProyectosService.java` — `obtenerProyectos()` + `filtrarProyectos(criterio)`
- `ProyectoService.java` — `obtenerProyecto()`, `guardarProyecto()`, `actualizarProyecto()`, `eliminarProyecto()`

Controladores: `ProyectosController`, `ProyectoController`, `CrearProyectoController`, `EditarProyectoController`, `EliminarProyectoController`

Templates: `proyectos.html`, `proyecto.html`, `crear-proyecto.html`, `editar-proyecto.html`, `eliminar-proyecto.html`

Diagramas de diseño: `abrirProyectos.puml`, `abrirProyecto.puml`, `crearProyecto.puml`, `editarProyecto.puml`, `eliminarProyecto.puml`

DataLoader: añadidos 3 proyectos de demo.

**Decisión:** Aceptado. Funcionó correctamente al arrancar la app.

---

## [Teoría] H2, funiber.mv.db y DataLoader

**Prompt:** Serie de preguntas sobre por qué se crea `funiber.mv.db`, cómo funciona H2 comparado con un JSON, y para qué sirve el DataLoader en producción.

**Resultado:** Explicaciones dadas:
- `funiber.mv.db` es la BD completa en un archivo binario — mismo concepto que un JSON de datos, pero optimizado para búsquedas
- El DataLoader es una clase de desarrollo/pruebas, no de producción; en producción habría migraciones (Flyway/Liquibase) y los datos los introducen los usuarios
- `ddl-auto=update` crea/actualiza tablas automáticamente al arrancar; borrar el archivo fuerza recreación limpia
- `count() > 0` evita duplicar datos en cada reinicio

**Decisión:** Solo explicación teórica, sin cambios en el código.

---

## [Re-decisión arquitectónica] Volver a "Coordinador = Investigador con rol"

**Prompt:** (reconstruido del código — transcript truncado)

**Resultado:** Se revirtió la decisión "Investigador es prematuro" de la sesión anterior. Se retomó el enfoque de la primera sesión del día: `Investigador` implementa `UserDetails` con campo `rol` (`COORDINADOR` / `INVESTIGADOR`). Se añadió además:
- Campo `institucion` a `Investigador`
- Relación `@ManyToMany` entre `Proyecto` e `Investigador` (tabla intermedia `proyecto_investigador`)
- `Investigador.proyectos` como lado inverso (`mappedBy`)
- `Coordinador.java` y `CoordinadorRepository.java` eliminados a favor de `Investigador`

**Decisión:** Se consolida definitivamente la arquitectura: un solo actor de dominio `Investigador` con `rol` diferencia coordinadores e investigadores.

---

## [Diseño + Desarrollo] Investigadores — 7 casos de uso

**Prompt:** (reconstruido del código — transcript truncado)

**Resultado:** Diseño y desarrollo completo de los 7 CUs del módulo de investigadores.

Modelo y repositorio:
- `Investigador.java` — actualizado con `@ManyToMany` a `Proyecto` y campo `institucion`
- `InvestigadorRepository.java` — `buscarPorCriterio()`, `findByProyectosId()`, `findByUsername()`

Servicio:
- `InvestigadorService.java` — `obtenerInvestigadores()`, `filtrarInvestigadores()`, `obtenerInvestigador()`, `guardarInvestigador()`, `obtenerInvestigadoresPorProyecto()`, `obtenerProyectosPorInvestigador()`

Controladores: `InvestigadoresController`, `InvestigadorController`, `CrearInvestigadorController`, `EliminarInvestigadorController`, `AgregarInvestigadorController`, `InvestigadoresDeProyectoController`, `ProyectosDeInvestigadorController`

Templates: `investigadores.html`, `investigador.html`, `crear-investigador.html`, `investigadores-proyecto.html`, `agregar-investigador.html`, `proyectos-investigador.html`

Docs de diseño: `abrirInvestigadores.md`, `abrirInvestigador.md`, `crearInvestigador.md`, `eliminarInvestigador.md`, `agregarInvestigador.md`, `abrirInvestigadoresDeProyecto.md`, `abrirProyectosDeInvestigador.md`

Diagramas: PUMLs correspondientes para los 7 CUs.

Decisiones clave:
- `eliminarInvestigador` elimina la relación en `proyecto_investigador`, no al investigador del sistema
- `agregarInvestigador` filtra solo los investigadores aún no asignados al proyecto
- `abrirInvestigadoresDeProyecto` y `abrirProyectosDeInvestigador` usan Spring Data JPA para la query JOIN automática

**Decisión:** Aceptado.

---

## [Diseño + Desarrollo] Entregables — 5 casos de uso

**Prompt:** (reconstruido del código — transcript truncado)

**Resultado:** Diseño y desarrollo completo de los 5 CUs del módulo de entregables.

Modelo y repositorio:
- `Entregable.java` — entidad JPA (titulo, descripcion, fechaEntrega, estado, documentacion, `@ManyToOne` a `Proyecto`)
- `EntregableRepository.java` — `findByProyectoId()`

Servicio:
- `EntregableService.java` — `obtenerEntregables(proyectoId)`, `obtenerEntregable()`, `guardarEntregable()`, `actualizarEntregable()`, `eliminarEntregable()`

Controladores: `EntregablesController`, `EntregableController`, `CrearEntregableController`, `EditarEntregableController`, `EliminarEntregableController`

Templates: `entregables.html`, `entregable.html`, `crear-entregable.html`, `editar-entregable.html`, `eliminar-entregable.html`

Diagramas: PUMLs para los 5 CUs bajo `modelosUML/diseño/coordinador/`.

Decisión de diseño: los entregables están siempre bajo el contexto de un proyecto (`/proyectos/{pid}/entregables`).

**Decisión:** Aceptado.

---

## [Diseño + Desarrollo] abrirOpcionesPerfilInvestigador

**Prompt:** (reconstruido del código — transcript truncado)

**Resultado:** Vista de solo lectura del perfil de un investigador concreto, accesible por el coordinador desde la ficha del investigador.

- `OpcionesPerfilInvestigadorController` — `GET /investigadores/{id}/perfil`
- `opciones-perfil-investigador.html` — muestra datos del investigador
- `PerfilService` reutilizado (mismo servicio que usará `abrirOpcionesPerfil` del propio coordinador)
- Docs: `abrirOpcionesPerfilInvestigador.md` + PUML

**Decisión:** Aceptado.

---

## [Desarrollo] FileService + ArchivoController

**Prompt:** (reconstruido del código — transcript truncado)

**Resultado:** Servicio genérico de carga y descarga de archivos para soportar los campos `documentacion` de proyectos, entregables y convocatorias.

- `FileService.java` — `guardarArchivo(MultipartFile)` (guarda en `./archivos/` con timestamp), `cargarArchivo(nombre)` devuelve `Resource`
- `ArchivoController.java` — endpoint de descarga `GET /archivos/{nombre}`

**Decisión:** Aceptado.
