# FUNIBER GIPF > abrirPanelPrincipal > Desarrollo

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Desarrollo
- **Disciplina**: Implementación
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## descripción

Hub de navegación principal. Muestra el menú con acceso a todas las secciones del sistema. No carga datos propios.

## estado

🚧 **En progreso** — Iteración 1

## archivos

| Capa | Archivo |
|-|-|
| Servicio | [src/main/java/com/funiber/gipf/services/PanelPrincipalService.java](../../../src/main/java/com/funiber/gipf/services/PanelPrincipalService.java) |
| Controlador | [src/main/java/com/funiber/gipf/controllers/PanelPrincipalController.java](../../../src/main/java/com/funiber/gipf/controllers/PanelPrincipalController.java) |
| Template | [src/main/resources/templates/panel.html](../../../src/main/resources/templates/panel.html) |

## notas de implementación

- **`cargarPanel()` vacío**: según el análisis devuelve `void`. En iteraciones futuras podría cargar contadores (proyectos activos, convocatorias abiertas...).
- **Redirect tras login**: `SecurityConfig` tiene `defaultSuccessUrl("/panel", true)` — tras login correcto siempre aterriza aquí.
- **Los enlaces 404**: los enlaces a `/proyectos`, `/investigadores`, etc. dan 404 hasta que se implementen sus controladores.

## referencias

- [Diseño: abrirPanelPrincipal()](../../diseño/coordinador/abrirPanelPrincipal.md)
- [Análisis: abrirPanelPrincipal()](../../analisis/coordinador/abrirPanelPrincipal.md)
