# Sesión — 2026-06-07 · SOLID · Refactoring

## [~18:00] Análisis de principios SOLID en el código existente

**Prompt:** Revisión del código desde el punto de vista de SOLID: qué principios se han aplicado, cuáles no, y cuáles se podrían implementar.

**Resultado:** Análisis por principio:
- **SRP ⚠️:** Bien en capas (Controller→Service→Repository), pero `EntregableService` mezcla lógica de negocio y filesystem; `ProyectoController` tenía guards de rol inline
- **OCP ❌:** Roles hardcodeados como strings `"COORDINADOR"` / `"INVESTIGADOR"` en 5 archivos; añadir un nuevo rol requería modificar código existente
- **LSP ✅:** Sin problemas. `Investigador` implementa `UserDetails` correctamente
- **ISP ⬜:** Sin interfaces de service (deuda, no violación activa)
- **DIP ✅:** Constructor injection consistente en todo el proyecto

**Decisión:** Implementar los tres puntos accionables: `ArchivoService` (SRP), enum `Rol` (OCP), y mover guards de rol al service (SRP).

---

## [~18:20] Discusión sobre el alcance de OCP

**Prompt:** ¿El problema de OCP aplica también a los tipos de proyecto, entregable, etc.?

**Resultado:** Distinción entre campos donde el código ramifica (→ enum tiene sentido) y campos que son solo datos (→ string está bien, enum sería peor). El `rol` es el único caso donde hay `if/equals` repartidos. Los `tipo` y `estado` de entidades no tienen lógica condicional en el código Java.

**Decisión:** Aplicar enum solo a `Rol`. Los estados/tipos de entidades se dejan como strings.

---

## [~18:30] Implementación — SRP: ArchivoService

**Prompt:** Crear `ArchivoService` para separar gestión de ficheros de `EntregableService`.

**Resultado:**
- Creado `ArchivoService.java` con `guardarArchivo`, `eliminarArchivo`, `obtenerRuta`
- `EntregableService` delegado a `ArchivoService`, eliminados imports de `java.nio`
- `ArchivoController` inyecta `ArchivoService` en lugar de hardcodear el path
- Diagramas de diseño actualizados: `crearEntregable`, `editarEntregable`, `eliminarEntregable` (coordinador) — `Sistema de ficheros` reemplazado por `ArchivoService` como participant

**Decisión:** Aceptado.

---

## [~18:50] Implementación — OCP: enum Rol

**Prompt:** Crear `enum Rol` y sustituir todos los strings de rol en el código.

**Resultado:**
- Creado `Rol.java` con `COORDINADOR` e `INVESTIGADOR`
- `Investigador.java`: `String rol` → `@Enumerated(EnumType.STRING) Rol rol`; `getAuthorities()` usa `rol.name()`
- `InvestigadorService`: firma de `actualizarPerfilConRol` recibe `Rol`; toggle en `cambiarRol` usa `==`
- `DataLoader`, `ProyectoController`, `EliminacionController`, `PerfilController`: todos los `"COORDINADOR".equals(...)` sustituidos por `== Rol.X`
- `@PreAuthorize("hasRole('COORDINADOR')")` no cambian — Spring los gestiona independientemente
- El grep final confirma cero strings de rol en el código Java

**Decisión:** Aceptado.

---

## [~19:10] Implementación — SRP: guards de rol en ProyectoController

**Prompt:** Mover la lógica de acceso a proyectos fuera del controller.

**Resultado:**
- `ProyectoService` — dos métodos nuevos:
  - `obtenerProyectosParaUsuario(investigador, criterio)`: decide internamente qué proyectos devolver según rol
  - `tieneAcceso(proyecto, investigador)`: encapsula la regla de membresía al proyecto
- `ProyectoController.abrirProyectos`: 14 líneas → 4 líneas; el import de `Rol` desapareció (prueba de que el controller ya no sabe de roles)
- `abrirProyecto` y `abrirInvestigadoresDeProyecto`: guards sustituidos por `!proyectoService.tieneAcceso(...)`
- Diagramas de diseño actualizados: `abrirProyectos` (coordinador + investigador), `abrirProyecto` (investigador), `abrirInvestigadoresDeProyecto` (investigador)

**Decisión:** Aceptado.

---

## [~19:30] Explicación teórica — OCP con nuevo rol

**Prompt:** ¿Cómo se implementaría un nuevo rol `REVISOR` siguiendo OCP?

**Resultado:** Con strings, añadir un rol exigía modificar código existente en 5+ sitios con riesgo de olvidar alguno. Con enum, el paso 1 es añadir `REVISOR` al enum (una línea) y el paso 2 es escribir solo la lógica nueva donde el comportamiento difiere. El código existente no se toca. El compilador avisa si hay un `switch` sin cubrir el nuevo caso.

**Decisión:** Informativo / conceptual.

---

## Pendiente para la próxima sesión

1. Renderizar como SVG los diagramas corregidos en esta sesión y en la anterior
2. Continuar con los principios SOLID pendientes: revisar si hay más puntos a aplicar
3. Continuar con casos de uso pendientes según priorización: **publicaciones** (P1)
