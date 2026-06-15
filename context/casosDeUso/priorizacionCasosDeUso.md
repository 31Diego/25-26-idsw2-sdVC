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

# Priorización de Casos de Uso  

Este documento establece una **priorización** de los casos de uso identificados en el sistema, con el objetivo de definir un **orden de implementación recomendado**.

La priorización se ha realizado atendiendo a:
- Dependencias funcionales.
- Valor aportado al negocio.
- Necesidad para la navegación y uso básico del sistema.

Se distinguen tres niveles:
- **P0 — Imprescindible**: sin estos casos de uso el sistema no es utilizable.
- **P1 — Núcleo funcional**: funcionalidades principales del dominio.
- **P2 — Complementario / administrativo**: casos de uso de soporte o menor frecuencia.

---

## Coordinador

El **Coordinador** dispone de una visión global del sistema y puede actuar sobre todas las entidades relevantes del dominio.

### P0 — Base del sistema
- iniciarSesion()
- cerrarSesion()
- abrirPanelPrincipal()
- abrirOpcionesPerfil()
- editarPerfil()
- abrirOpcionesCargaTrabajo()

---

### P1 — Núcleo funcional

#### Proyectos y entregables
- abrirProyectos()
- abrirProyecto()
- crearProyecto()
- editarProyecto()
- eliminarProyecto()
- agregarInvestigador()
- eliminarInvestigador()
- abrirEntregables()
- abrirEntregable()
- crearEntregable()
- editarEntregable()
- eliminarEntregable()

#### Publicaciones
- abrirPublicaciones()
- abrirPublicacion()
- responderPublicacion()
- editarPublicacion()
- eliminarPublicacion()

#### Mis publicaciones
- abrirMisPublicaciones()
- abrirMiPublicacion()
- crearPublicacion()
- editarPublicacion()
- eliminarPublicacion()

#### Convocatorias
- abrirConvocatorias()
- abrirConvocatoria()
- importarConvocatoria()

---

### P2 — Gestión y administración

#### Recompensas
- abrirRecompensas()
- abrirRecompensa()
- crearRecompensa()
- editarRecompensa()
- eliminarRecompensa()

#### Investigadores
- abrirInvestigadores()
- abrirInvestigador()
- crearInvestigador()

#### Gestión de perfil (casos poco frecuentes)
- solicitarEliminacionPerfil()
- abrirSolicitudesEliminacionPerfil()
- abrirSolicitudEliminacionPerfil()

---

## Investigador

El **Investigador** opera sobre información propia, con acceso limitado a consultas globales cuando procede.

### P0 — Base del sistema
- iniciarSesion()
- cerrarSesion()
- abrirPanelPrincipal()
- abrirOpcionesPerfil()
- editarPerfil()
- abrirOpcionesCargaTrabajo()

---

### P1 — Núcleo funcional

#### Proyectos propios y entregables
- abrirProyectos()
- abrirProyecto()
- abrirEntregables()
- abrirEntregable()
- crearEntregable()
- editarEntregable()
- eliminarEntregable()

#### Publicaciones (común)
- abrirPublicaciones()
- abrirPublicacion()
- responderPublicacion()

#### Mis publicaciones
- abrirMisPublicaciones()
- abrirMiPublicacion()
- crearPublicacion()
- editarPublicacion()
- eliminarPublicacion()

---

### P2 — Funcionalidades complementarias

#### Recompensas
- abrirRecompensas()
- abrirRecompensa()

#### Investigadores (consulta)
- abrirInvestigadores()
- abrirInvestigador()

#### Gestión de perfil
- solicitarEliminacionPerfil()

---
