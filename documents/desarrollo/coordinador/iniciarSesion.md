# FUNIBER GIPF > iniciarSesion > Desarrollo

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Desarrollo
- **Disciplina**: Implementación
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## descripción

Autenticación mediante formulario Thymeleaf gestionado por Spring Security. Las contraseñas se almacenan cifradas con BCrypt.

## estado

🚧 **En progreso** — Iteración 1

## archivos

| Capa | Archivo |
|-|-|
| Modelo | [src/main/java/com/funiber/gipf/models/Usuario.java](../../../src/main/java/com/funiber/gipf/models/Usuario.java) |
| Repositorio | [src/main/java/com/funiber/gipf/repositories/UsuarioRepository.java](../../../src/main/java/com/funiber/gipf/repositories/UsuarioRepository.java) |
| Servicio | [src/main/java/com/funiber/gipf/services/AutenticacionService.java](../../../src/main/java/com/funiber/gipf/services/AutenticacionService.java) |
| Controlador | [src/main/java/com/funiber/gipf/controllers/IniciarSesionController.java](../../../src/main/java/com/funiber/gipf/controllers/IniciarSesionController.java) |
| Configuración | [src/main/java/com/funiber/gipf/config/SecurityConfig.java](../../../src/main/java/com/funiber/gipf/config/SecurityConfig.java) |
| Template | [src/main/resources/templates/login.html](../../../src/main/resources/templates/login.html) |
| Datos prueba | [src/main/java/com/funiber/gipf/DataLoader.java](../../../src/main/java/com/funiber/gipf/DataLoader.java) |

## notas de implementación

- **Spring Security intercepta el POST**: no hay `@PostMapping("/login")`. Spring Security gestiona la autenticación automáticamente llamando a `AutenticacionService.loadUserByUsername()`.
- **BCrypt**: contraseñas cifradas. Nunca se guarda texto plano.
- **SecurityConfig**: configura qué URLs son públicas (`/login`, `/h2-console`) y cuáles requieren autenticación. También configura el redirect tras login (`/panel`) y logout (`/login?logout`).
- **DataLoader**: crea el usuario de prueba `admin/1234` al arrancar si la tabla está vacía. Es datos de prueba, no lógica de negocio.
- **H2 console**: requiere `frameOptions(sameOrigin)` y excluir `/h2-console/**` del CSRF porque la consola usa iframes.

## pruebas manuales

1. Recargar Maven en VS Code (aparece notificación al cambiar `pom.xml`)
2. Reiniciar la aplicación
3. Abrir `http://localhost:8080/convocatorias` — debe redirigir a `/login`
4. Introducir `admin` / `1234` → debe ir al panel (dará 404 hasta implementar `abrirPanelPrincipal`)
5. Introducir credenciales incorrectas → debe volver al formulario con mensaje de error
6. Acceder a `http://localhost:8080/h2-console` → debe ser accesible sin login

## referencias

- [Diseño: iniciarSesion()](../../diseño/coordinador/iniciarSesion.md)
- [Análisis: iniciarSesion()](../../analisis/coordinador/iniciarSesion.md)
