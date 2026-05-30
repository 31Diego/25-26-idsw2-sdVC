# FUNIBER GIPF > abrirPanelPrincipal > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## propósito

Detallar el flujo para mostrar el panel principal de navegación tras el login o al volver desde cualquier sección.

## diagrama de secuencia

<div align=center>

|![Diseño: abrirPanelPrincipal()](/images/diseño/abrirPanelPrincipal-diseño.svg)|
|-|
|Código fuente: [abrirPanelPrincipal.puml](abrirPanelPrincipal.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `PanelPrincipalController` | `@Controller` | `PanelPrincipalView` |
| `PanelPrincipalService` | `@Service` | `PanelController` |
| Thymeleaf `panel.html` | Template HTML | — |

## flujo principal

1. Coordinador inicia sesión (redirect desde `/login`) o navega a `/panel` desde cualquier sección.
2. `PanelPrincipalController` recibe `GET /panel`.
3. Llama a `PanelPrincipalService.cargarPanel()` — devuelve `void` (sin datos que cargar por ahora).
4. Retorna la vista `panel.html` con los enlaces de navegación.
5. El Coordinador ve el menú con acceso a todas las secciones.

## decisiones de diseño

- **`cargarPanel()` devuelve void**: según el análisis, el panel no carga datos propios — es un hub de navegación. En iteraciones futuras podría cargar contadores o resúmenes.
- **8 entradas en el análisis**: el panel se puede abrir desde 8 estados distintos (al volver de cualquier sección). Todos llegan al mismo `GET /panel`.

## referencias

- [Análisis: abrirPanelPrincipal()](../../analisis/coordinador/abrirPanelPrincipal.md)
