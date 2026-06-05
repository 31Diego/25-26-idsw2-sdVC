# abrirPanelPrincipal — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: abrirPanelPrincipal()

## Propósito

Mostrar el panel principal del sistema, que actúa como hub de navegación hacia todas las secciones disponibles para el Coordinador.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/abrirPanelPrincipal-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/abrirPanelPrincipal.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| PanelPrincipalView | `PanelPrincipalController` `@Controller` | Recibe GET /panel y devuelve panel.html |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /panel | Muestra el panel principal |

## Decisiones de diseño

- El panel no carga datos del dominio; es una vista estática de navegación.
- No requiere servicio ni repositorio: el controlador devuelve directamente el nombre de la plantilla.
- Spring Security protege la ruta; cualquier acceso sin sesión activa redirige a /login.
- Thymeleaf + Thymeleaf Security (`sec:authorize`) gestiona la visibilidad de opciones según el rol del usuario autenticado.
