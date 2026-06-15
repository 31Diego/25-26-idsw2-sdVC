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

# Diagramas de contexto

Este documento recoge los **diagramas de contexto** del sistema para los dos actores principales: **Coordinador** e **Investigador**.  
Cada diagrama define los **estados de pantalla** y las **transiciones** permitidas mediante acciones (casos de uso) coherentes con el contexto del actor.

---

## Coordinador — Diagrama de contexto

- **Actor**: Coordinador  
- **Objetivo**: Visualizar la navegación completa del coordinador (visión global del sistema) y las acciones disponibles desde cada pantalla.

<div align=center>

|![Diagrama de contexto: Coordinador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/imagenes/diagramaContextoCoordinador.svg)|
|-|
|Código fuente: [diagramaContextoCoordinador.puml](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/diagramas/diagramaContextoCoordinador.puml)|

</div>

### Estados principales resumidos
- `SESION_CERRADA` / `PANEL_PRINCIPAL_ABIERTO`
- Accesos desde panel: `OPCIONES_PERFIL_ABIERTO`, `OPCIONES_CARGA_TRABAJO_ABIERTAS`, `PROYECTOS_ABIERTOS`, `INVESTIGADORES_ABIERTOS`, `MIS_PUBLICACIONES_ABIERTAS`, `PUBLICACIONES_ABIERTAS`, `CONVOCATORIAS_ABIERTAS`, `RECOMPENSAS_ABIERTAS`
- Subpantallas de detalle: `PUBLICACION_ABIERTA`, `MI_PUBLICACION_ABIERTA`, `CONVOCATORIA_ABIERTA`, `RECOMPENSA_ABIERTA`, `PROYECTO_ABIERTO`, `ENTREGABLES_ABIERTOS`, `ENTREGABLE_ABIERTO`, `INVESTIGADOR_ABIERTO`, `SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`, `SOLICITUD_ELIMINACION_PERFIL_ABIERTA`

---

## Investigador — Diagrama de contexto

- **Actor**: Investigador  
- **Objetivo**: Visualizar la navegación del investigador (operación sobre información propia y acceso de consulta) y sus acciones disponibles.

<div align=center>

|![Diagrama de contexto: Investigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/imagenes/diagramaContextoInvestigador.svg)|
|-|
|Código fuente: [diagramaContextoInvestigador.puml](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/diagramas/diagramaContextoInvestigador.puml)|

</div>

### Estados principales resumidos
- `SESION_CERRADA` / `PANEL_PRINCIPAL_ABIERTO`
- Accesos desde panel: `OPCIONES_PERFIL_ABIERTO`, `OPCIONES_CARGA_TRABAJO_ABIERTAS`, `PROYECTOS_ABIERTOS`, `INVESTIGADORES_ABIERTOS`, `MIS_PUBLICACIONES_ABIERTAS`, `PUBLICACIONES_ABIERTAS`, `RECOMPENSAS_ABIERTAS`
- Subpantallas de detalle: `PUBLICACION_ABIERTA`, `MI_PUBLICACION_ABIERTA`, `PROYECTO_ABIERTO`, `ENTREGABLES_ABIERTOS`, `ENTREGABLE_ABIERTO`, `RECOMPENSA_ABIERTA`, `INVESTIGADOR_ABIERTO`

---






