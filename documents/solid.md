# Principios SOLID — FUNIBER GIPF

Referencia de los cinco principios SOLID aplicados al contexto de este proyecto (Spring Boot · JPA · Thymeleaf).

---

## S — Single Responsibility Principle (SRP)
**"Una clase debe tener una sola razón para cambiar."**

Cada clase tiene una única responsabilidad bien delimitada:

| Capa | Responsabilidad única |
|---|---|
| `Entity` | Modelar el estado persistente del dominio |
| `Repository` | Acceder a la base de datos (queries JPA) |
| `Service` | Contener la lógica de negocio |
| `Controller` | Recibir la petición HTTP, delegar al servicio y devolver la vista |
| `Template (.html)` | Presentar datos al usuario |

**Violación típica:** un `Controller` que contiene lógica de negocio (filtros, cálculos, validaciones de dominio) en lugar de delegarla al `Service`.

---

## O — Open/Closed Principle (OCP)
**"Las clases deben estar abiertas a extensión y cerradas a modificación."**

Estrategia en este proyecto:
- Añadir nuevo comportamiento mediante nuevos métodos en `Service` o nuevas clases, no modificando lógica existente que ya funciona.
- Usar herencia o interfaces cuando dos actores (coordinador / investigador) comparten un flujo con variantes, en lugar de anidar `if (rol == X)` dentro del método principal.

**Violación típica:** añadir un bloque `if (esCoordinador) { ... } else { ... }` dentro de un método de servicio en lugar de extraer la variante o usar polimorfismo.

---

## L — Liskov Substitution Principle (LSP)
**"Un subtipo debe poder sustituir a su supertipo sin alterar el comportamiento correcto del programa."**

Relevante si se usan jerarquías de entidades o interfaces de servicio:
- Si `InvestigadorService` implementa una interfaz `UsuarioService`, todos sus métodos deben cumplir el contrato definido en la interfaz.
- No lanzar excepciones inesperadas ni ignorar parámetros en la implementación concreta.

**Violación típica:** sobreescribir un método lanzando `UnsupportedOperationException` porque la subclase "no necesita" ese comportamiento.

---

## I — Interface Segregation Principle (ISP)
**"Ningún cliente debe depender de métodos que no usa."**

En este proyecto:
- Si un `Service` crece demasiado, dividirlo en interfaces más pequeñas y cohesivas (p.ej. `PublicacionLecturaService` vs `PublicacionEscrituraService`).
- Los `Controller` solo inyectan los servicios que realmente necesitan; no se inyecta un servicio completo solo para usar uno de sus métodos.

**Violación típica:** una interfaz de servicio con 15 métodos de los cuales el controller solo usa 2.

---

## D — Dependency Inversion Principle (DIP)
**"Los módulos de alto nivel no deben depender de módulos de bajo nivel; ambos deben depender de abstracciones."**

En Spring Boot esto se aplica de forma natural mediante inyección de dependencias (`@Autowired` / constructor injection):
- Los `Controller` dependen de la **interfaz** (o del tipo abstracto) del servicio, no de su implementación concreta.
- Los `Service` dependen de la **interfaz** `Repository` (JPA), no de la implementación de Hibernate.
- Se prefiere la inyección por constructor sobre `@Autowired` en campo para facilitar el testing.

**Violación típica:** instanciar un servicio con `new MiServicio()` dentro de un controller en lugar de inyectarlo.

---

## Regla de trabajo durante la refactorización

Cuando se detecte una violación de alguno de estos principios y se aplique un cambio:

1. **Identificar** el principio violado y la clase/método afectado.
2. **Aplicar** el refactor mínimo necesario (no reescribir lo que ya funciona bien).
3. **Revisar** si el cambio afecta a algún diagrama de análisis (`modelosUML/analisis/`) o diseño (`modelosUML/diseño/`) y, si es así, actualizar el `.puml` correspondiente.
4. **Actualizar** `documents/progreso.md` si el estado de algún caso de uso cambia.
