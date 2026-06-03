# Diferencias de comportamiento entre Coordinador e Investigador

Este documento recoge, caso de uso por caso de uso, qué cambia entre los dos actores del sistema.
Se usa como referencia antes de hacer el análisis, diseño o desarrollo de cualquier CU compartido.

---

## Casos de uso exclusivos del Coordinador

No existen en el diagrama de contexto del Investigador.

| Caso de uso | Motivo |
|---|---|
| `crearProyecto()` | Solo el coordinador crea proyectos |
| `editarProyecto()` | Solo el coordinador edita proyectos |
| `eliminarProyecto()` | Solo el coordinador elimina proyectos |
| `agregarInvestigador()` | Solo el coordinador gestiona el equipo de un proyecto |
| `eliminarInvestigador()` | Ídem |
| `abrirConvocatorias()` | El investigador no tiene acceso a convocatorias |
| `abrirConvocatoria()` | Ídem |
| `importarConvocatoria()` | Ídem |
| `crearRecompensa()` | Solo el coordinador crea recompensas |
| `editarRecompensa()` | Solo el coordinador edita recompensas |
| `eliminarRecompensa()` | Solo el coordinador elimina recompensas |
| `crearInvestigador()` | Solo el coordinador puede registrar nuevos investigadores |
| `abrirSolicitudesEliminacionPerfil()` | Solo el coordinador gestiona estas solicitudes |
| `abrirSolicitudEliminacionPerfil()` | Ídem |

---

## Casos de uso compartidos con comportamiento DIFERENTE

### `abrirPanelPrincipal()`

| | Coordinador | Investigador |
|---|---|---|
| Accesos disponibles | Proyectos, Investigadores, **Convocatorias**, Recompensas, Mis publicaciones, Publicaciones, Opciones perfil, Carga de trabajo | Proyectos, Investigadores, Recompensas, Mis publicaciones, Publicaciones, Opciones perfil, Carga de trabajo |
| Diferencia | Tiene acceso a Convocatorias | Sin acceso a Convocatorias |

---

### `abrirProyectos()`

| | Coordinador | Investigador |
|---|---|---|
| Alcance | **Todos los proyectos del sistema** | **Solo los proyectos en los que participa** |
| Crear proyecto | Sí (`crearProyecto()`) | No |
| Filtro/búsqueda | Sí | Sí |
| Datos mostrados | ID, título, estado, coordinador, fechas | ID, título, estado, fechas |

> **Implicación de implementación**: la query del repositorio para el investigador debe filtrar por `investigador_id` en la tabla de relación proyecto-investigador, no hacer `findAll()`.

---

### `abrirProyecto()`

| | Coordinador | Investigador |
|---|---|---|
| Acceso | Cualquier proyecto | Solo proyectos propios |
| Datos mostrados | ID, título, estado, entidad financiadora, coordinador, fechas, descripción, equipo | Datos del proyecto, descripción, equipo |
| Editar proyecto | Sí | No |
| Eliminar proyecto | Sí | No |
| Agregar/eliminar investigador | Sí | No |
| Abrir entregables | Sí | Sí |
| Abrir investigadores del proyecto | Sí | Sí |

> **Implicación de implementación**: el controlador del investigador debe verificar que el investigador autenticado es miembro del proyecto antes de mostrar la vista.

---

### `abrirRecompensas()`

| | Coordinador | Investigador |
|---|---|---|
| Alcance | **Todas las recompensas del sistema** | **Solo las recompensas propias** |
| Crear recompensa | Sí (`crearRecompensa()`) | No |

> **Implicación de implementación**: la query del investigador debe filtrar por `investigador_id`, no hacer `findAll()`.

---

### `abrirInvestigadores()`

| | Coordinador | Investigador |
|---|---|---|
| Alcance | Todos los investigadores | Todos los investigadores |
| Crear investigador | Sí (`crearInvestigador()`) | No |
| Diferencia | El listado también muestra el botón "Nuevo investigador" | Sin botón de creación |

---

### `abrirInvestigador()`

| | Coordinador | Investigador |
|---|---|---|
| Acceso | Cualquier investigador | Cualquier investigador |
| Datos mostrados | ID, nombre, apellidos, campo, carrera, máster, rol | ID, nombre, apellidos, campo, carrera, máster, rol |
| Editar perfil del investigador | Sí | No |
| Cambiar rol | Sí | No |
| Diferencia | Acciones de gestión disponibles | Solo consulta |

---

## Casos de uso compartidos con comportamiento IDÉNTICO

Mismo actor, mismo flujo, mismas opciones. No hace falta implementación separada (se puede reutilizar).

| Caso de uso | Notas |
|---|---|
| `iniciarSesion()` | Idéntico; el rol determina el panel al que se redirige |
| `cerrarSesion()` | Idéntico |
| `abrirOpcionesPerfil()` | Cada actor opera sobre su propio perfil |
| `editarPerfil()` | Cada actor edita su propio perfil |
| `solicitarEliminacionPerfil()` | Cada actor solicita eliminar su propio perfil |
| `abrirPublicaciones()` | Ambos ven todas las publicaciones del sistema |
| `abrirPublicacion()` | Ambos ven el detalle de cualquier publicación |
| `responderPublicacion()` | Ambos pueden responder |
| `abrirMisPublicaciones()` | Cada actor ve sus propias publicaciones |
| `abrirMiPublicacion()` | Cada actor ve el detalle de su publicación |
| `crearPublicacion()` | Idéntico |
| `editarPublicacion()` *(mis publicaciones)* | Cada actor edita sus propias publicaciones |
| `eliminarPublicacion()` *(mis publicaciones)* | Cada actor elimina sus propias publicaciones |
| `abrirEntregables()` | Idéntico — ambos pueden ver la lista y crear entregable |
| `abrirEntregable()` | Idéntico |
| `crearEntregable()` | Idéntico |
| `editarEntregable()` | Idéntico |
| `eliminarEntregable()` | Idéntico |
| `abrirRecompensa()` | Idéntico — detalle de una recompensa (cada actor sobre las suyas) |

---

## `abrirOpcionesCargaTrabajo()`

| | Coordinador | Investigador |
|---|---|---|
| Alcance | **Carga de trabajo de todos los investigadores del sistema** | **Solo su propia carga de trabajo** |
| Editar carga ajena | Sí | No aplica |
