# FUNIBER GIPF > iniciarSesion > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## propósito

Detallar el flujo técnico de autenticación mediante Spring Security con formulario Thymeleaf y contraseñas cifradas con BCrypt.

## diagrama de secuencia

<div align=center>

|![Diseño: iniciarSesion()](/images/diseño/iniciarSesion-diseño.svg)|
|-|
|Código fuente: [iniciarSesion.puml](iniciarSesion.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `IniciarSesionController` | `@Controller` | `IniciarSesionView` |
| `Spring Security` | `SecurityFilterChain` | intercepta POST /login (infraestructura) |
| `AutenticacionService` | `@Service / UserDetailsService` | `AutenticacionController` |
| `UsuarioRepository` | `JpaRepository<Usuario, Long>` | `UsuarioRepository` |
| `Usuario` | `@Entity / UserDetails` | `Usuario` |
| Thymeleaf `login.html` | Template HTML | — |

## flujos

### mostrar formulario
1. Coordinador navega a cualquier URL protegida o directamente a `/login`.
2. Spring Security redirige a `/login`.
3. `IniciarSesionController` responde al `GET /login` devolviendo `login.html`.

### autenticación — credenciales correctas
1. Coordinador rellena usuario y contraseña y envía el formulario.
2. El navegador hace `POST /login` con los datos.
3. Spring Security intercepta la petición y llama a `AutenticacionService.loadUserByUsername(username)`.
4. `AutenticacionService` llama a `UsuarioRepository.findByUsername(username)`.
5. H2 devuelve el `Usuario`.
6. Spring Security compara la contraseña introducida con el hash BCrypt almacenado.
7. Éxito → redirect a `GET /panel`.

### autenticación — credenciales incorrectas
1. Spring Security detecta que la contraseña no coincide.
2. Redirect a `GET /login?error`.
3. Thymeleaf muestra el mensaje de error en el formulario.

## decisiones de diseño

- **Spring Security gestiona el POST**: no se escribe un `@PostMapping("/login")`. Spring Security intercepta automáticamente ese endpoint.
- **BCrypt**: las contraseñas se almacenan cifradas con BCrypt. Nunca en texto plano.
- **CSRF**: Thymeleaf con `th:action` incluye el token CSRF automáticamente — no hay que gestionarlo manualmente.
- **H2 console**: requiere configuración especial en `SecurityConfig` para no bloquearse (usa iframes).

## referencias

- [Análisis: iniciarSesion()](../../analisis/coordinador/iniciarSesion.md)
- [Especificación detallada](../../../context/casosDeUso/detalle/coordinador/iniciarSesion/iniciarSesion.md)
