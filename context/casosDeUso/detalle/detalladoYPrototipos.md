

<h1 align="center">FUNIBER GIPF — Plataforma Interna de Investigación</h1>

<div align="center">

[![](https://img.shields.io/badge/-Inicio-0A3B64?style=for-the-badge&logo=github&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/README.md)
[![](https://img.shields.io/badge/-Modelo_del_Dominio-0A3B64?style=for-the-badge&logo=drawio&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/modeloDominio.md)
[![](https://img.shields.io/badge/-Actores_y_CdU-0A3B64?style=for-the-badge&logo=uml&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/casosDeUso.md)
[![](https://img.shields.io/badge/-Diagramas_de_Contexto-0A3B64?style=for-the-badge&logo=sitemap&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/diagramas/diagramasContexto.md)
[![](https://img.shields.io/badge/-Detalle_de_CdU-0A3B64?style=for-the-badge&logo=readme&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/detalladoYPrototipos.md)
[![](https://img.shields.io/badge/-Análisis-0A3B64?style=for-the-badge&logo=databricks&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/analisis.md)
[![](https://img.shields.io/badge/-Diseño-0A3B64?style=for-the-badge&logo=figma&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/dise%C3%B1o.md)

</div>

# Detalle y Prototipo de Casos de Uso
Esta carpeta contiene la **especificación detallada** y el **prototipado** (PlantUML + SALT) de cada caso de uso identificado en la **Plataforma Interna de Investigación de FUNIBER (GIPF)**.

Los casos de uso están organizados por **actor** y por **módulo funcional**, siguiendo los diagramas de contexto y los diagramas de casos de uso del proyecto.

---

## Casos de uso especificados

> Nota rápida:  
> - El **Investigador** opera sobre información **propia** (proyectos propios, publicaciones propias, entregables de sus proyectos).  
> - El **Coordinador** tiene visibilidad y acciones sobre el conjunto del sistema (incluyendo control centralizado).

---

## Coordinador

### Gestión de sesión
- [iniciarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/iniciarSesion/iniciarSesion.md) — Acceso autenticado al sistema
- [cerrarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/cerrarSesion/cerrarSesion.md) — Cierre de sesión

### Navegación general
- [abrirPanelPrincipal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirPanelPrincipal/abrirPanelPrincipal.md) — Acceso al panel principal

### Perfil
- [abrirOpcionesPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirOpcionesPerfil/abrirOpcionesPerfil.md) — Apertura de opciones del perfil
- [editarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarPerfil/editarPerfil.md) — Edición de datos de perfil
- [solicitarEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/solicitarEliminacionPerfil/solicitarEliminacionPerfil.md) — Solicitud de eliminación del perfil
- [abrirSolicitudesEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirSolicitudesEliminacionPerfil/abrirSolicitudesEliminacionPerfil.md) — Listado de solicitudes de eliminación
- [abrirSolicitudEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirSolicitudEliminacionPerfil/abrirSolicitudEliminacionPerfil.md) — Consulta de una solicitud concreta
- [eliminarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarPerfil/eliminarPerfil.md) — Eliminación del perfil de un investigador

### Carga de trabajo
- [abrirOpcionesCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo.md) — Consulta de carga de trabajo
- [editarCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarCargaTrabajo/editarCargaTrabajo.md) — Edición de carga de trabajo

### Publicaciones
- [abrirPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirPublicaciones/abrirPublicaciones.md) — Listado general de publicaciones
- [abrirPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirPublicacion/abrirPublicacion.md) — Apertura de una publicación
- [responderPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/responderPublicacion/responderPublicacion.md) — Respuesta a una publicación
- [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarPublicacion/editarPublicacion.md) — Edición de publicación
- [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarPublicacion/eliminarPublicacion.md) — Eliminación de publicación

### Mis publicaciones
- [abrirMisPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirMisPublicaciones/abrirMisPublicaciones.md) — Listado de publicaciones propias
- [abrirMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirMiPublicacion/abrirMiPublicacion.md) — Apertura de una publicación propia
- [crearPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/crearPublicacion/crearPublicacion.md) — Creación de publicación propia
- [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarMiPublicacion/editarPublicacion.md) — Edición de publicación propia
- [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarMiPublicacion/eliminarPublicacion.md) — Eliminación de publicación propia

### Convocatorias
- [abrirConvocatorias](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirConvocatorias/abrirConvocatorias.md) — Listado de convocatorias
- [abrirConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirConvocatoria/abrirConvocatoria.md) — Consulta de una convocatoria
- [importarConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/importarConvocatoria/importarConvocatoria.md) — Importación de convocatoria

### Recompensas
- [abrirRecompensas](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirRecompensas/abrirRecompensas.md) — Listado de recompensas
- [abrirRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirRecompensa/abrirRecompensa.md) — Consulta de recompensa
- [crearRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/crearRecompensa/crearRecompensa.md) — Creación de recompensa
- [editarRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarRecompensa/editarRecompensa.md) — Edición de recompensa
- [eliminarRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarRecompensa/eliminarRecompensa.md) — Eliminación de recompensa

### Proyectos
- [abrirProyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirProyectos/abrirProyectos.md) — Listado de proyectos
- [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirProyecto/abrirProyecto.md) — Consulta de un proyecto
- [crearProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/crearProyecto/crearProyecto.md) — Creación de proyecto
- [editarProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarProyecto/editarProyecto.md) — Edición de proyecto
- [eliminarProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarProyecto/eliminarProyecto.md) — Eliminación de proyecto
- [agregarInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/agregarInvestigador/agregarInvestigador.md) — Asignación de investigador a proyecto
- [eliminarInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarInvestigador/eliminarInvestigador.md) — Retirada de investigador del proyecto
- [abrirEntregables](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirEntregables/abrirEntregables.md) — Listado de entregables del proyecto
- [abrirEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirEntregable/abrirEntregable.md) — Consulta de un entregable
- [crearEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/crearEntregable/crearEntregable.md) — Creación de entregable
- [editarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/editarEntregable/editarEntregable.md) — Edición de entregable
- [eliminarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/eliminarEntregable/eliminarEntregable.md) — Eliminación de entregable

### Investigadores
- [abrirInvestigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirInvestigadores/abrirInvestigadores.md) — Listado de investigadores
- [abrirInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/abrirInvestigador/abrirInvestigador.md) — Consulta de un investigador
- [crearInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/coordinador/crearInvestigador/crearInvestigador.md) — Registro de investigador

---

## Investigador

### Gestión de sesión
- [iniciarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/iniciarSesion/iniciarSesion.md) — Acceso autenticado al sistema
- [cerrarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/cerrarSesion/cerrarSesion.md) — Cierre de sesión

### Navegación general
- [abrirPanelPrincipal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirPanelPrincipal/abrirPanelPrincipal.md) — Acceso al panel principal

### Perfil
- [abrirOpcionesPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirOpcionesPerfil/abrirOpcionesPerfil.md) — Apertura de opciones del perfil
- [editarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/editarPerfil/editarPerfil.md) — Edición de datos de perfil
- [solicitarEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/solicitarEliminacionPerfil/solicitarEliminacionPerfil.md) — Solicitud de eliminación del perfil

### Carga de trabajo (individual)
- [abrirOpcionesCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo.md) — Consulta de carga de trabajo propia
- [editarCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/editarCargaTrabajo/editarCargaTrabajo.md) — Edición de carga de trabajo propia (según contexto del actor)

### Publicaciones (común)
- [abrirPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirPublicaciones/abrirPublicaciones.md) — Listado general de publicaciones
- [abrirPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirPublicacion/abrirPublicacion.md) — Apertura de una publicación
- [responderPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/responderPublicacion/responderPublicacion.md) — Respuesta a una publicación

### Mis publicaciones (propias)
- [abrirMisPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirMisPublicaciones/abrirMisPublicaciones.md) — Listado de publicaciones propias
- [abrirMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirMiPublicacion/abrirMiPublicacion.md) — Apertura de una publicación propia
- [crearPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/crearPublicacion/crearPublicacion.md) — Creación de publicación
- [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/editarPublicacion/editarPublicacion.md) — Edición de publicación propia
- [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/eliminarPublicacion/eliminarPublicacion.md) — Eliminación de publicación propia

### Proyectos (propios)
- [abrirProyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirProyectos/abrirProyectos.md) — Listado de proyectos propios
- [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirProyecto/abrirProyecto.md) — Consulta de proyecto propio
- [abrirEntregables](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirEntregables/abrirEntregables.md) — Listado de entregables del proyecto
- [abrirEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirEntregable/abrirEntregable.md) — Consulta de entregable
- [crearEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/crearEntregable/crearEntregable.md) — Creación de entregable
- [editarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/editarEntregable/editarEntregable.md) — Edición de entregable
- [eliminarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/eliminarEntregable/eliminarEntregable.md) — Eliminación de entregable
- [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirProyecto/abrirProyecto.md) — Volver al proyecto desde entregables

### Recompensas
- [abrirRecompensas](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirRecompensas/abrirRecompensas.md) — Listado de recompensas propias
- [abrirRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirRecompensa/abrirRecompensa.md) — Consulta de una recompensa

### Investigadores (consulta)
- [abrirInvestigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirInvestigadores/abrirInvestigadores.md) — Listado de investigadores
- [abrirInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/investigador/abrirInvestigador/abrirInvestigador.md) — Consulta de un investigador

---

## Estructura de cada caso de uso

Cada carpeta de caso de uso contiene:

- **README.md** — Especificación completa del caso de uso
- **especificacion.puml** — Diagrama de especificación (detallado) en PlantUML
- **prototipo.puml** — Wireframes de prototipado en SALT

---

## Metodología aplicada

- **Coherencia con diagramas de contexto:** no se definen transiciones fuera del contexto.
- **Vocabulario controlado:** acciones del actor y del sistema según el estándar del proyecto.
- **Confirmar vs cancelar:**  
  - Confirmar/Guardar → vuelve al objeto abierto  
  - Cancelar → vuelve al listado
- **Trazabilidad:** las salidas indican la transición.
- **Consistencia 1:1:** campos y botones del prototipo coinciden con el detallado.
