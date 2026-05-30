# FUNIBER GIPF > cerrarSesion > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## propósito

Detallar el flujo de cierre de sesión con pantalla de confirmación intermedia gestionada por Spring Security.

## diagrama de secuencia

<div align=center>

|![Diseño: cerrarSesion()](/images/diseño/cerrarSesion-diseño.svg)|
|-|
|Código fuente: [cerrarSesion.puml](cerrarSesion.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `CerrarSesionController` | `@Controller` | `CerrarSesionView` |
| `Spring Security` | `SecurityFilterChain` | `SesionController` (infraestructura) |
| Thymeleaf `cerrar-sesion.html` | Template HTML | — |

## flujos

### mostrar confirmación
1. Coordinador hace click en "Cerrar sesión" desde el panel.
2. Navegador envía `GET /cerrar-sesion`.
3. `CerrarSesionController` devuelve la vista `cerrar-sesion.html`.
4. Se muestra pantalla con los botones "Confirmar" y "Cancelar".

### confirmar cierre
1. Coordinador pulsa "Confirmar".
2. El formulario envía `POST /logout`.
3. Spring Security invalida la sesión HTTP.
4. Redirect a `GET /login?logout` → Thymeleaf muestra mensaje "Sesión cerrada correctamente".

### cancelar cierre
1. Coordinador pulsa "Cancelar".
2. Navegador redirige a `GET /panel` — sin cerrar sesión.

## decisiones de diseño

- **Spring Security gestiona el POST /logout**: igual que con el login, no se escribe un `@PostMapping`. Spring Security intercepta `/logout` automáticamente.
- **`SesionController` del análisis** corresponde al mecanismo de logout de Spring Security — no se implementa como `@Service` separado porque no hay lógica de negocio más allá de invalidar la sesión.
- **Confirmación en pantalla propia**: se evita usar `window.confirm()` (JavaScript) para mantener coherencia con la arquitectura Thymeleaf sin JS.

## referencias

- [Análisis: cerrarSesion()](../../analisis/coordinador/cerrarSesion.md)
