# iniciarSesion — Diseño

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Coordinador
- **Caso de uso**: iniciarSesion()

## Propósito

Permite al coordinador autenticarse en la plataforma introduciendo sus credenciales; Spring Security valida el usuario y redirige al panel principal o muestra un error.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/coordinador/iniciarSesion-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/coordinador/iniciarSesion.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| Interfaz de login | LoginController @Controller | Sirve el formulario en GET /login |
| Sistema de seguridad | Spring Security POST /login | Intercepta el envío de credenciales y gestiona la autenticación |
| Servicio de autenticación | AutenticacionService @Service | Implementa UserDetailsService; carga el usuario por username |
| Repositorio de investigadores | InvestigadorRepository JpaRepository | Consulta la base de datos por username |
| Base de datos | H2 | Almacén persistente de investigadores |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /login | Muestra el formulario de inicio de sesión |
| POST | /login | Spring Security valida credenciales (username, password) |

## Decisiones de diseño

- Spring Security intercepta el POST /login directamente, sin pasar por un controlador de aplicación.
- `AutenticacionService` implementa `UserDetailsService.loadUserByUsername(username)`.
- La contraseña se verifica con `BCrypt.matches(password, hash)`.
- Flujo alternativo `alt`: si las credenciales son correctas → redirect /panel; si son incorrectas → redirect /login?error con mensaje de error en el formulario.
- El repositorio utiliza `findByUsername(username)` para localizar al investigador.
