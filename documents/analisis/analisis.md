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

# Análisis — Índice de casos de uso

Este documento lista los artefactos de análisis del sistema, organizados por actor y módulo funcional. Cada enlace lleva al documento de análisis correspondiente, que incluye el diagrama de secuencia y la descripción de participantes.

---

## Coordinador

### Sesión
| Caso de uso | Descripción |
|---|---|
| [iniciarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/iniciarSesion.md) | Acceso autenticado al sistema mediante usuario y contraseña |
| [cerrarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/cerrarSesion.md) | Cierre de la sesión activa |

### Navegación
| Caso de uso | Descripción |
|---|---|
| [abrirPanelPrincipal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirPanelPrincipal.md) | Visualización del panel de inicio con acceso a todas las secciones |

### Perfil
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirOpcionesPerfil.md) | Acceso al menú de opciones del perfil propio |
| [abrirOpcionesPerfilInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirOpcionesPerfilInvestigador.md) | Acceso a las opciones de perfil de un investigador concreto |
| [editarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarPerfil.md) | Modificación de los datos del perfil propio |
| [solicitarEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/solicitarEliminacionPerfil.md) | Solicitud de eliminación de la cuenta propia |
| [abrirSolicitudesEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirSolicitudesEliminacionPerfil.md) | Listado de solicitudes de eliminación de perfil pendientes |
| [abrirSolicitudEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirSolicitudEliminacionPerfil.md) | Vista detallada de una solicitud de eliminación |

### Carga de trabajo
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirOpcionesCargaTrabajo.md) | Consulta de horas asignadas por tipo de actividad |
| [editarCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarCargaTrabajo.md) | Actualización de las horas de docencia, investigación y actividades |

### Proyectos
| Caso de uso | Descripción |
|---|---|
| [abrirProyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirProyectos.md) | Listado de todos los proyectos del sistema |
| [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirProyecto.md) | Vista detallada de un proyecto concreto |
| [abrirProyectosDeInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirProyectosDeInvestigador.md) | Listado de proyectos asignados a un investigador |
| [crearProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/crearProyecto.md) | Alta de un nuevo proyecto de investigación |
| [editarProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarProyecto.md) | Modificación de los datos de un proyecto existente |
| [eliminarProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarProyecto.md) | Baja de un proyecto del sistema |

### Investigadores de proyecto
| Caso de uso | Descripción |
|---|---|
| [abrirInvestigadoresDeProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirInvestigadoresDeProyecto.md) | Listado de investigadores asignados a un proyecto |
| [agregarInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/agregarInvestigador.md) | Incorporación de un investigador a un proyecto |
| [eliminarInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarInvestigador.md) | Retirada de un investigador de un proyecto |

### Entregables
| Caso de uso | Descripción |
|---|---|
| [abrirEntregables](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirEntregables.md) | Listado de entregables de un proyecto |
| [abrirEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirEntregable.md) | Vista detallada de un entregable |
| [crearEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/crearEntregable.md) | Alta de un nuevo entregable en un proyecto |
| [editarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarEntregable.md) | Modificación de los datos de un entregable |
| [eliminarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarEntregable.md) | Baja de un entregable |

### Publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirPublicaciones.md) | Listado general de publicaciones del sistema |
| [abrirPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirPublicacion.md) | Vista detallada de una publicación |
| [responderPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/responderPublicacion.md) | Adición de una respuesta a una publicación |
| [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarPublicacion.md) | Modificación de cualquier publicación (rol coordinador) |
| [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarPublicacion.md) | Eliminación de cualquier publicación (rol coordinador) |

### Mis publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirMisPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirMisPublicaciones.md) | Listado de las publicaciones propias del coordinador |
| [abrirMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirMiPublicacion.md) | Vista detallada de una publicación propia |
| [crearPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/crearPublicacion.md) | Creación de una nueva publicación |
| [editarMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarMiPublicacion.md) | Modificación de una publicación propia |
| [eliminarMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarMiPublicacion.md) | Eliminación de una publicación propia |

### Convocatorias
| Caso de uso | Descripción |
|---|---|
| [abrirConvocatorias](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirConvocatorias.md) | Listado de convocatorias de financiación registradas |
| [abrirConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirConvocatoria.md) | Vista detallada de una convocatoria |
| [importarConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/importarConvocatoria.md) | Registro de una nueva convocatoria en el sistema |
| [eliminarConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarConvocatoria.md) | Eliminación de una convocatoria |

### Recompensas
| Caso de uso | Descripción |
|---|---|
| [abrirRecompensas](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirRecompensas.md) | Listado de recompensas registradas |
| [abrirRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirRecompensa.md) | Vista detallada de una recompensa |
| [crearRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/crearRecompensa.md) | Alta de una nueva recompensa para un investigador |
| [editarRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/editarRecompensa.md) | Modificación de una recompensa existente |
| [eliminarRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/eliminarRecompensa.md) | Eliminación de una recompensa |

### Investigadores
| Caso de uso | Descripción |
|---|---|
| [abrirInvestigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirInvestigadores.md) | Listado de todos los investigadores del sistema |
| [abrirInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/abrirInvestigador.md) | Vista detallada del perfil de un investigador |
| [crearInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/coordinador/crearInvestigador.md) | Alta de un nuevo investigador en el sistema |

---

## Investigador

### Sesión
| Caso de uso | Descripción |
|---|---|
| [iniciarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/iniciarSesion.md) | Acceso autenticado al sistema |
| [cerrarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/cerrarSesion.md) | Cierre de la sesión activa |

### Navegación
| Caso de uso | Descripción |
|---|---|
| [abrirPanelPrincipal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirPanelPrincipal.md) | Panel de inicio con acceso a las secciones disponibles |

### Perfil
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirOpcionesPerfil.md) | Acceso al menú de opciones del perfil propio |
| [editarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/editarPerfil.md) | Modificación de los datos del perfil propio |
| [solicitarEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/solicitarEliminacionPerfil.md) | Solicitud de eliminación de la cuenta propia |

### Carga de trabajo
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirOpcionesCargaTrabajo.md) | Consulta de horas asignadas al investigador |
| [editarCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/editarCargaTrabajo.md) | Actualización de las horas de docencia, investigación y actividades |

### Proyectos propios
| Caso de uso | Descripción |
|---|---|
| [abrirProyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirProyectos.md) | Listado de proyectos en los que participa el investigador |
| [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirProyecto.md) | Vista detallada de un proyecto propio |

### Entregables
| Caso de uso | Descripción |
|---|---|
| [abrirEntregables](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirEntregables.md) | Listado de entregables de un proyecto propio |
| [abrirEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirEntregable.md) | Vista detallada de un entregable |
| [crearEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/crearEntregable.md) | Alta de un nuevo entregable en un proyecto propio |
| [editarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/editarEntregable.md) | Modificación de un entregable propio |
| [eliminarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/eliminarEntregable.md) | Eliminación de un entregable propio |

### Publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirPublicaciones.md) | Listado general de publicaciones |
| [abrirPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirPublicacion.md) | Vista detallada de una publicación |
| [responderPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/responderPublicacion.md) | Adición de una respuesta a una publicación |

### Mis publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirMisPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirMisPublicaciones.md) | Listado de las publicaciones propias del investigador |
| [abrirMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirMiPublicacion.md) | Vista detallada de una publicación propia |
| [crearPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/crearPublicacion.md) | Creación de una nueva publicación propia |
| [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/editarPublicacion.md) | Modificación de una publicación propia |
| [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/eliminarPublicacion.md) | Eliminación de una publicación propia |

### Recompensas
| Caso de uso | Descripción |
|---|---|
| [abrirRecompensas](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirRecompensas.md) | Listado de recompensas recibidas |
| [abrirRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirRecompensa.md) | Vista detallada de una recompensa |

### Investigadores
| Caso de uso | Descripción |
|---|---|
| [abrirInvestigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirInvestigadores.md) | Listado de investigadores de la red FUNIBER |
| [abrirInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/investigador/abrirInvestigador.md) | Vista detallada del perfil de un investigador |
