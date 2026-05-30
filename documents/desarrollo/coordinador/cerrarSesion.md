# FUNIBER GIPF > cerrarSesion > Desarrollo

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Desarrollo
- **Disciplina**: Implementación
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## descripción

Cierre de sesión con pantalla de confirmación. Spring Security gestiona la invalidación de la sesión al recibir `POST /logout`.

## estado

🚧 **En progreso** — Iteración 1

## archivos

| Capa | Archivo |
|-|-|
| Controlador | [src/main/java/com/funiber/gipf/controllers/CerrarSesionController.java](../../../src/main/java/com/funiber/gipf/controllers/CerrarSesionController.java) |
| Template | [src/main/resources/templates/cerrar-sesion.html](../../../src/main/resources/templates/cerrar-sesion.html) |

## notas de implementación

- **Sin servicio propio**: `SesionController` del análisis corresponde al mecanismo de logout de Spring Security — ya configurado en `SecurityConfig` con `logoutUrl("/logout")` y `logoutSuccessUrl("/login?logout")`.
- **CSRF en el formulario**: `th:action="@{/logout}"` incluye el token CSRF automáticamente.
- **Cancelar**: enlace simple a `GET /panel`, no invalida la sesión.

## referencias

- [Diseño: cerrarSesion()](../../diseño/coordinador/cerrarSesion.md)
- [Análisis: cerrarSesion()](../../analisis/coordinador/cerrarSesion.md)
