# Sesión — 2026-05-29 · Diseño · coordinador

## [~18:00] Orientación inicial

**Prompt:** "buenas claude"

**Resultado:** Lectura de logs. Estado: análisis coordinador completo, pendiente diseño. Sesión anterior había terminado explorando el stack tecnológico.

**Decisión:** Continuar con decisiones de stack y luego iniciar diseño del coordinador.

---

## [~18:10] Decisión del stack — backend

**Prompt:** "me gustaría poder hacer las clases de MVC en Java, el resto no tengo preferencia, lo que más sencillo sea"

**Resultado:** Stack recomendado: Spring Boot (Java), H2 en archivo, Spring Data JPA, Spring Security + JWT, React + Vite o Thymeleaf (pendiente decidir frontend).

**Decisión:** Java + Spring Boot aceptado.

---

## [~18:20] Explicación de H2

**Prompt:** "no entiendo muy bien lo de la base de datos H2, puedes explicarlo más porfa"

**Resultado:** Explicación de H2 en modo archivo (`jdbc:h2:file:./funiber`), consola web en `/h2-console`, comparativa con SQLite y MySQL.

**Decisión:** H2 en modo archivo aceptado.

---

## [~18:30] Decisión del stack — frontend

**Prompt:** "que diferencia hay entre esas opciones" (Thymeleaf vs React + Vite)

**Resultado:** Explicación: Thymeleaf = HTML generado en servidor, todo en un proyecto Java, sin JS. React = proyecto separado, SPA, más moderno pero más complejo.

**Decisión:** Thymeleaf elegido — más sencillo, todo en un proyecto Java.

---

## [~18:40] Plantilla de diagramas de secuencia

**Prompt:** "puedes hacer una plantilla de cómo debería ser un diagrama de secuencia, como hicimos con el diagrama de colaboración"

**Resultado:** Creada `context/ejemplos/diseño/plantillaSecuencia.puml` con 5 patrones: Listar, Abrir detalle, Crear, Editar (4a cargar form + 4b guardar), Eliminar.

**Decisión:** Aceptada íntegra.

---

## [~18:50] Diseño de abrirConvocatoria

**Prompt:** "vamos en orden, con abrirConvocatoria"

**Resultado:** Generados `modelosUML/diseño/coordinador/abrirConvocatoria.puml` y `documents/diseño/coordinador/abrirConvocatoria.md`. Sigue el Patrón 2 (abrir detalle): `GET /convocatorias/{id}` → `ConvocatoriasService` → `findById(id)` → `ConvocatoriaDTO` → `convocatoria.html`.

**Decisión:** Aceptado.

---

## [~19:00] Diseño de abrirConvocatorias

**Prompt:** "haz abrirConvocatorias porfa"

**Resultado:** Generados `modelosUML/diseño/coordinador/abrirConvocatorias.puml` y `documents/diseño/coordinador/abrirConvocatorias.md`. Sigue el Patrón 1 (listar) con dos secciones: carga inicial (`GET /convocatorias` → `findAll()`) y filtrado (`GET /convocatorias?texto=&area=&estado=` → `buscarPorCriterio()`). Un solo endpoint, parámetros opcionales.

**Decisión:** Aceptado.

---
