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

# Diseño — Índice de casos de uso

Este documento lista los artefactos de diseño del sistema, organizados por actor y módulo funcional. Cada enlace lleva al documento de diseño correspondiente, que incluye el diagrama de secuencia con las clases Spring Boot reales (controllers, services, repositories) y las decisiones de diseño adoptadas.

---

## Coordinador

### Sesión
| Caso de uso | Descripción |
|---|---|
| [iniciarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/iniciarSesion.md) | Flujo de autenticación con Spring Security |
| [cerrarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/cerrarSesion.md) | Invalidación de sesión mediante Spring Security logout |

### Navegación
| Caso de uso | Descripción |
|---|---|
| [abrirPanelPrincipal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirPanelPrincipal.md) | Carga del panel principal y contexto del usuario autenticado |

### Perfil
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirOpcionesPerfil.md) | Recuperación del perfil propio desde el servicio |
| [editarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarPerfil.md) | Actualización de datos de perfil con persistencia JPA |
| [solicitarEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/solicitarEliminacionPerfil.md) | Creación de solicitud de eliminación en la base de datos |
| [abrirSolicitudesEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirSolicitudesEliminacionPerfil.md) | Listado de solicitudes pendientes mediante el servicio |
| [abrirSolicitudEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirSolicitudEliminacionPerfil.md) | Recuperación de una solicitud concreta por ID |
| [eliminarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarPerfil.md) | Baja de un investigador y resolución de su solicitud |

### Carga de trabajo
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirOpcionesCargaTrabajo.md) | Consulta de horas desde el servicio de carga de trabajo |
| [editarCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarCargaTrabajo.md) | Actualización de las horas de un investigador |

### Proyectos
| Caso de uso | Descripción |
|---|---|
| [abrirProyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirProyectos.md) | Recuperación del listado completo de proyectos |
| [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirProyecto.md) | Recuperación de un proyecto por ID |
| [abrirInvestigadoresDeProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirInvestigadoresDeProyecto.md) | Consulta de investigadores asignados a un proyecto |
| [crearProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/crearProyecto.md) | Persistencia de un nuevo proyecto en la base de datos |
| [editarProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarProyecto.md) | Actualización de datos de un proyecto existente |
| [eliminarProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarProyecto.md) | Baja de un proyecto y sus entidades dependientes |
| [agregarInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/agregarInvestigador.md) | Asociación de un investigador a un proyecto |
| [eliminarInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarInvestigador.md) | Desasociación de un investigador de un proyecto |

### Entregables
| Caso de uso | Descripción |
|---|---|
| [abrirEntregables](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirEntregables.md) | Recuperación de entregables de un proyecto |
| [abrirEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirEntregable.md) | Recuperación de un entregable por ID |
| [crearEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/crearEntregable.md) | Persistencia de un nuevo entregable asociado a un proyecto |
| [editarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarEntregable.md) | Actualización de los datos de un entregable |
| [eliminarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarEntregable.md) | Baja de un entregable |

### Publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirPublicaciones.md) | Recuperación del listado global de publicaciones |
| [abrirPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirPublicacion.md) | Recuperación de una publicación con sus respuestas |
| [responderPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/responderPublicacion.md) | Persistencia de una respuesta asociada a una publicación |
| [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarPublicacion.md) | Actualización de cualquier publicación (acción de coordinador) |
| [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarPublicacion.md) | Baja de cualquier publicación (acción de coordinador) |

### Mis publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirMisPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirMisPublicaciones.md) | Recuperación de publicaciones del usuario autenticado |
| [abrirMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirMiPublicacion.md) | Recuperación de una publicación propia con sus respuestas |
| [crearPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/crearPublicacion.md) | Persistencia de una nueva publicación con el autor autenticado |
| [editarMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarMiPublicacion.md) | Actualización de una publicación propia |
| [eliminarMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarMiPublicacion.md) | Baja de una publicación propia |

### Convocatorias
| Caso de uso | Descripción |
|---|---|
| [abrirConvocatorias](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirConvocatorias.md) | Recuperación del listado de convocatorias |
| [abrirConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirConvocatoria.md) | Recuperación de una convocatoria por ID |
| [importarConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/importarConvocatoria.md) | Persistencia de una convocatoria con subida de archivo adjunto |
| [eliminarConvocatoria](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarConvocatoria.md) | Baja de una convocatoria del sistema |

### Recompensas
| Caso de uso | Descripción |
|---|---|
| [abrirRecompensas](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirRecompensas.md) | Recuperación del listado de recompensas |
| [abrirRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirRecompensa.md) | Recuperación de una recompensa por ID |
| [crearRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/crearRecompensa.md) | Persistencia de una nueva recompensa para un investigador |
| [editarRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/editarRecompensa.md) | Actualización de los datos de una recompensa |
| [eliminarRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/eliminarRecompensa.md) | Baja de una recompensa |

### Investigadores
| Caso de uso | Descripción |
|---|---|
| [abrirInvestigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirInvestigadores.md) | Recuperación del listado completo de investigadores |
| [abrirInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/abrirInvestigador.md) | Recuperación del perfil de un investigador por ID |
| [crearInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/coordinador/crearInvestigador.md) | Alta de un nuevo investigador con contraseña cifrada |

---

## Investigador

### Sesión
| Caso de uso | Descripción |
|---|---|
| [iniciarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/iniciarSesion.md) | Flujo de autenticación con Spring Security |
| [cerrarSesion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/cerrarSesion.md) | Invalidación de sesión mediante Spring Security logout |

### Navegación
| Caso de uso | Descripción |
|---|---|
| [abrirPanelPrincipal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirPanelPrincipal.md) | Carga del panel principal con las secciones disponibles para el investigador |

### Perfil
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirOpcionesPerfil.md) | Recuperación del perfil propio desde el servicio |
| [editarPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/editarPerfil.md) | Actualización de datos de perfil propios con persistencia JPA |
| [solicitarEliminacionPerfil](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/solicitarEliminacionPerfil.md) | Creación de solicitud de eliminación de la cuenta propia |

### Carga de trabajo
| Caso de uso | Descripción |
|---|---|
| [abrirOpcionesCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirOpcionesCargaTrabajo.md) | Consulta de las horas asignadas al investigador autenticado |
| [editarCargaTrabajo](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/editarCargaTrabajo.md) | Actualización de las propias horas de trabajo |

### Proyectos propios
| Caso de uso | Descripción |
|---|---|
| [abrirProyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirProyectos.md) | Recuperación de los proyectos en los que participa el investigador |
| [abrirProyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirProyecto.md) | Recuperación de un proyecto propio con control de acceso |

### Entregables
| Caso de uso | Descripción |
|---|---|
| [abrirEntregables](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirEntregables.md) | Recuperación de entregables de un proyecto propio |
| [abrirEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirEntregable.md) | Recuperación de un entregable concreto |
| [crearEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/crearEntregable.md) | Persistencia de un nuevo entregable en un proyecto propio |
| [editarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/editarEntregable.md) | Actualización de un entregable propio |
| [eliminarEntregable](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/eliminarEntregable.md) | Baja de un entregable propio |

### Publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirPublicaciones.md) | Recuperación del listado global de publicaciones |
| [abrirPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirPublicacion.md) | Recuperación de una publicación con sus respuestas |
| [responderPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/responderPublicacion.md) | Persistencia de una respuesta a una publicación |

### Mis publicaciones
| Caso de uso | Descripción |
|---|---|
| [abrirMisPublicaciones](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirMisPublicaciones.md) | Recuperación de publicaciones del investigador autenticado |
| [abrirMiPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirMiPublicacion.md) | Recuperación de una publicación propia con sus respuestas |
| [crearPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/crearPublicacion.md) | Persistencia de una nueva publicación propia |
| [editarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/editarPublicacion.md) | Actualización de una publicación propia |
| [eliminarPublicacion](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/eliminarPublicacion.md) | Baja de una publicación propia |

### Recompensas
| Caso de uso | Descripción |
|---|---|
| [abrirRecompensas](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirRecompensas.md) | Listado de recompensas recibidas por el investigador |
| [abrirRecompensa](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirRecompensa.md) | Vista detallada de una recompensa |

### Investigadores
| Caso de uso | Descripción |
|---|---|
| [abrirInvestigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirInvestigadores.md) | Listado de investigadores de la red FUNIBER |
| [abrirInvestigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/investigador/abrirInvestigador.md) | Vista detallada del perfil de un investigador |
