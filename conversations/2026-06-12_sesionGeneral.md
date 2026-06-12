# Sesión — 2026-06-12 · Revisión general del código

## [inicio] Orientación

**Prompt:** "buenas claude"

**Resultado:** Orientación del proyecto. Todo análisis, diseño y código ✅. Pendiente solo la columna Doc. Desarrollo (⚠️ en todos los casos de uso).

---

## [controllers] Detección de lógica de negocio

**Prompt:** Revisión de varios controllers para identificar lógica de negocio mal ubicada. Se señaló `ArchivoController` como caso de ejemplo, con otros casos en `RecompensaController`, `PublicacionController`, `ProyectoController` y `EliminacionController`.

**Resultado:** Diagnóstico completo:
- `RecompensaController.abrirRecompensa` (líneas 41-44): regla de rol + destinatario inline → lógica de negocio real ❌
- `PublicacionController.abrirMiPublicacion` (línea 102): `pub.getAutor().getId().equals(...)` → lógica de negocio real ❌
- `ArchivoController`: `new UrlResource(...)` en el controller → SRP leve ⚠️
- `ProyectoController.sinAcceso`, `EliminacionController.puedeGestionar`, `PublicacionController.puedeEditarOEliminar` → correctos, la regla está en el service ✅

**Decisión:** Se aplicaron los tres fixes.

---

## [fix] Correcciones aplicadas

**Prompt:** "vale perfecto corrige lo que consideres"

**Resultado:**
- `RecompensaService` + `RecompensaController`: añadido `puedeVer(usuario, recompensa)`; eliminado import `Rol` del controller
- `PublicacionService` + `PublicacionController`: añadido `esAutor(investigador, publicacion)`
- `ArchivoService`: `obtenerRuta()` → `obtenerRecurso()` devuelve `Optional<Resource>`; `ArchivoController` usa `.map()` sobre el Optional

**Decisión:** Aceptados.

---

## [concepto] Distinción lógica de negocio vs control de flujo HTTP

**Prompt:** Por qué `if (sinAcceso(proyecto, investigador))` en `ProyectoController` no es lógica de negocio si tiene un `if`.

**Resultado:** La distinción es qué hay *dentro* del `if`. Si la condición delega al service (`proyectoService.tieneAcceso()`), el controller no conoce la regla — solo actúa sobre el resultado. Si la condición contiene la regla directamente (`rol != X && id != Y`), sí es lógica de negocio mal ubicada.

---

## [modelos] Anotaciones

**Prompt:** Explicación de `@Entity`, `@Table`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@Column`, `@ManyToMany`, `@JoinTable`.

**Resultado:** Explicación completa sobre `Proyecto.java`. Guardada en `documents/referencias-tecnicas.md`.

---

## [policies] Patrón Strategy

**Prompt:** Explicación de las dos interfaces en el paquete `policies` y por qué una devuelve `boolean` y la otra `List`.

**Resultado:** Explicación del patrón Strategy aplicado a `PoliticaAcceso` y `PoliticaConsulta`. El `Map<Rol, Politica>` en `ProyectoService` elimina todos los `if (rol == COORDINADOR)` del service.

---

## [repositories] Tres niveles

**Prompt:** Explicación del propósito de los repositories y de todos los que hay en el proyecto.

**Resultado:** Explicación de los tres niveles: herencia de `JpaRepository` (nivel 1), métodos por nombre (nivel 2), `@Query` explícita (nivel 3). Recorrido de los 9 repositories del proyecto.

---

## [config] SecurityConfig e InvestigadorUserDetails

**Prompt:** Explicación de los archivos de configuración.

**Resultado:** `InvestigadorUserDetails` es un adaptador entre el modelo de dominio y la interfaz de Spring Security. `SecurityConfig` define rutas públicas/protegidas, BCrypt, y las excepciones para H2.

---

## [H2] Base de datos embebida

**Prompt:** Aclaraciones sobre H2, el archivo `funiber.mv.db` y `funiber.trace.db`.

**Resultado:** H2 es un motor SQL embebido en la app. `funiber.mv.db` es la BD en formato binario propio. `funiber.trace.db` es el log interno de H2, sin utilidad práctica en desarrollo. `ddl-auto=update` recrea las tablas si no existen.

---

## [DataLoader] Funcionamiento y actualización

**Prompt:** Por qué se regenera la BD al borrarla y qué hacer con DataLoader. Petición de actualizar el DataLoader con un coordinador y un investigador, y añadir entidades que faltaban (convocatorias, recompensas, entregables).

**Resultado:** DataLoader reescrito con:
- Credenciales: `coordinador/coordinador` y `investigador/investigador`
- 2 proyectos con 3 entregables
- 3 convocatorias
- 2 recompensas para el investigador
- 2 publicaciones y 1 respuesta

**Decisión:** Aceptado.

---

## [arranque] Inversión de control

**Prompt:** Cómo arranca la aplicación si no hay un método `run()` visible en `GipfApplication`.

**Resultado:** El `main()` delega en `SpringApplication.run()`, que se encarga de todo: leer configuración, crear tablas, escanear e inyectar componentes, arrancar Tomcat, registrar rutas y ejecutar los `CommandLineRunner`. Concepto de inversión de control explicado.

---

## [cierre] Documento de referencia

**Prompt:** Guardar un documento con todas las dudas técnicas resueltas en la sesión.

**Resultado:** Creado `documents/referencias-tecnicas.md` con secciones para: lógica de negocio, anotaciones de modelos, repositories, patrón Strategy, config, H2, DataLoader, pom.xml, arranque y target/classes.

---
