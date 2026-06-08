# Sesión — 2026-06-08 · SOLID · Continuación

## [~15:00] Análisis completo del estado SOLID tras el refactor anterior

**Prompt:** Explicar qué principios SOLID se han aplicado y cuáles no en el proyecto actual.

**Resultado:** Análisis por principio:
- **SRP ⚠️:** `Investigador` implementa `UserDetails` — mezcla modelo de dominio con contrato de Spring Security
- **OCP ⚠️:** `ProyectoService.tieneAcceso()` y `obtenerProyectosParaUsuario()` tienen `if (rol == Rol.COORDINADOR)` — añadir un rol nuevo obliga a modificar código existente
- **LSP ✅:** Sin problemas. Todas las implementaciones respetan el contrato de su tipo base
- **ISP ⚠️:** Sin interfaces de servicio; controladores dependen de clases concretas con más métodos de los que usan
- **DIP ⚠️:** Derivado de ISP — inyección por constructor correcta pero sobre clases concretas, no abstracciones

**Decisión:** Implementar SRP y OCP. ISP y DIP se dejan como deuda técnica identificada.

---

## [~15:20] SRP — Separación de Investigador y UserDetails

**Prompt:** Explicación del problema y propuesta de solución con clase adaptadora `InvestigadorUserDetails`.

**Resultado:** Explicación en detalle de por qué `Investigador implements UserDetails` viola SRP (dos razones para cambiar: dominio y Spring Security API). Propuesta del patrón adaptador.

**Decisión:** Aceptado e implementado.

---

## [~15:30] SRP — Implementación de InvestigadorUserDetails

**Prompt:** Implementar la separación.

**Resultado:**
- Creado `config/InvestigadorUserDetails.java` — única responsable del contrato `UserDetails`; envuelve `Investigador` y expone `getInvestigador()`
- `Investigador.java` — eliminados `implements UserDetails`, sus 5 métodos y todos los imports de `org.springframework.security.*`
- `AutenticacionService.loadUserByUsername()` — ahora devuelve `new InvestigadorUserDetails(investigador)`
- `ProyectoController` (3 métodos) y `EliminacionController` (3 métodos) — `@AuthenticationPrincipal InvestigadorUserDetails userDetails` + `userDetails.getInvestigador()`
- `PerfilController` no cambia — ya usaba `authentication.getName()`
- Diagramas de análisis y diseño: ninguno necesita cambios (el cambio es interno a `AutenticacionService`, el flujo visible no varía)

**Decisión:** Aceptado.

---

## [~15:50] OCP — Discusión sobre la tensión OCP/SRP

**Prompt:** ¿El fix OCP (mover lógica al enum) no viola SRP?

**Resultado:** Confirmado que sí — hay tensión real entre OCP y SRP. Mover la lógica al enum le da una segunda responsabilidad. Se analizaron alternativas: doble despacho (no encaja — solo hay un eje de variación) y patrón Strategy (separa responsabilidades correctamente).

**Decisión:** Aplicar patrón Strategy en lugar de abstract methods en el enum.

---

## [~17:10] OCP — Implementación del patrón Strategy

**Prompt:** Implementar Strategy para `tieneAcceso()` y `obtenerProyectosParaUsuario()`.

**Resultado:**
- Creado paquete `policies/` con 6 archivos:
  - `PoliticaAcceso.java` — interfaz con `tieneAcceso(Proyecto, Investigador)`
  - `AccesoCoordinador.java` — devuelve `true` siempre
  - `AccesoInvestigador.java` — comprueba membresía en el proyecto
  - `PoliticaConsulta.java` — interfaz con `obtener(Investigador, criterio)`
  - `ConsultaCoordinador.java` — consulta todos los proyectos; recibe `ProyectoRepository`
  - `ConsultaInvestigador.java` — consulta solo proyectos propios; recibe `ProyectoRepository`
- `ProyectoService.java`:
  - Añadidos campos `Map<Rol, PoliticaAcceso> politicas` y `Map<Rol, PoliticaConsulta> consultas`
  - Constructor inicializa ambos mapas con las implementaciones
  - `tieneAcceso()` → 1 línea: `politicas.get(rol).tieneAcceso(...)`
  - `obtenerProyectosParaUsuario()` → 1 línea: `consultas.get(rol).obtener(...)`
- Diagramas actualizados: `abrirProyectos` (coordinador) y `abrirProyectos` (investigador) — añadido `ConsultaCoordinador`/`ConsultaInvestigador` como participant entre `ProyectoService` y `ProyectoRepository`
- Diagramas NO actualizados: `abrirProyecto` y `abrirInvestigadoresDeProyecto` — tratan `tieneAcceso()` como caja negra, el cambio es interno

**Decisión:** Aceptado.

---

## [~17:30] LSP — Análisis

**Prompt:** Analizar si se cumple LSP.

**Resultado:** Se cumple en todos los puntos:
- `InvestigadorUserDetails implements UserDetails` — contrato respetado correctamente
- Repositorios extienden `JpaRepository` sin redefinir comportamiento base
- Las 4 clases de `policies/` implementan sus interfaces respetando parámetros, tipos de retorno y ausencia de efectos secundarios inesperados

**Decisión:** Sin cambios necesarios.

---

## [~17:40] ISP — Análisis

**Prompt:** Analizar si se cumple ISP.

**Resultado:** Deuda identificada: sin interfaces de servicio, los controladores dependen de clases concretas con más métodos de los que usan (ejemplo: `EntregableController` depende de `ProyectoService` pero solo usa `obtenerProyecto()`). Dos opciones discutidas: interfaces simples (una por service) o interfaces segmentadas por caso de uso.

**Decisión:** Dejar como deuda técnica — el proyecto no está terminado y no hay múltiples implementaciones que justifiquen las interfaces ahora.

---

## Pendiente para la próxima sesión

1. DIP — analizar y decidir si implementar (derivado directamente de ISP)
2. Renderizar a SVG los diagramas modificados en esta sesión
3. Continuar con casos de uso pendientes según priorización: **publicaciones** (P1)
