# Criterios de Revisión — Refactorización y Sincronización

Este documento define el proceso completo de revisión del código, en dos fases ordenadas.  
Referencia complementaria: [solid.md](solid.md).

---

## Fase 1 — Revisión SOLID (primero)

Para cada clase del sistema, comprobar los siguientes puntos en orden.

---

### 1.1 SRP — ¿Tiene una sola responsabilidad?

**Preguntas a responder:**
- ¿Puede describirse la responsabilidad de esta clase en una frase sin usar "y"?
- ¿Contiene un `Controller` lógica de negocio (filtros, cálculos, validaciones de dominio)?
- ¿Contiene un `Service` lógica de presentación o acceso directo a la BD sin pasar por el `Repository`?
- ¿Contiene una `Entity` métodos que no sean getters/setters o lógica de dominio pura?

**Acción:** extraer la responsabilidad extra a la clase/capa que le corresponda.

---

### 1.2 OCP — ¿Está cerrada a modificación?

**Preguntas a responder:**
- ¿Hay bloques `if (rol == X) { ... } else { ... }` dentro de un método de servicio que deberían resolverse de otra forma?
- ¿Añadir un nuevo caso de uso o un nuevo rol obliga a modificar código existente en lugar de extenderlo?

**Acción:** extraer la variante a un método separado, una subclase o un parámetro de comportamiento según la complejidad. Si el refactor es desproporcionado, documentar la violación con un comentario `// OCP: pendiente de refactorizar`.

---

### 1.3 LSP — ¿Se respetan los contratos de las interfaces?

**Preguntas a responder:**
- ¿Alguna implementación lanza `UnsupportedOperationException` o ignora parámetros del contrato?
- ¿Alguna subclase rompe una precondición o postcondición definida en la interfaz o clase padre?

**Acción:** corregir la implementación o rediseñar la jerarquía si el contrato no se puede cumplir.

---

### 1.4 ISP — ¿Las interfaces son cohesivas?

**Preguntas a responder:**
- ¿Hay interfaces o clases abstractas con métodos que algún implementador no necesita?
- ¿Algún `Controller` inyecta un `Service` completo para usar solo uno o dos métodos?

**Acción:** dividir la interfaz en partes más pequeñas y cohesivas si la clase creció demasiado.

---

### 1.5 DIP — ¿Las dependencias apuntan a abstracciones?

**Preguntas a responder:**
- ¿Alguna clase instancia sus dependencias con `new` en lugar de inyectarlas?
- ¿Se usa `@Autowired` en campo en lugar de inyección por constructor?
- ¿Algún `Controller` o `Service` depende de una clase concreta en lugar de una interfaz?

**Acción:** convertir a inyección por constructor y depender del tipo interfaz/abstracto.

---

### Resultado de la Fase 1 por clase

Para cada clase revisada, anotar:

| Clase | Principio | Estado | Acción tomada |
|---|---|:---:|---|
| `XxxController` | SRP | ✅ / ⚠️ / ❌ | — |
| `XxxService` | OCP | ✅ / ⚠️ / ❌ | Extraído método `yyy` |
| … | … | … | … |

- ✅ Cumple
- ⚠️ Violación identificada pero no corregida (demasiado compleja o fuera de alcance)
- ❌ Violación corregida

---

## Fase 2 — Sincronización de diagramas (después)

Solo se ejecuta una vez la Fase 1 ha estabilizado el código.

Para cada clase modificada en la Fase 1:

### 2.1 Identificar los casos de uso afectados

- Buscar en `modelosUML/diseño/` los `.puml` que referencien esa clase.
- Buscar en `modelosUML/analisis/` los `.puml` relacionados con esos casos de uso.

### 2.2 Comprobar coherencia diagrama ↔ código

Para cada diagrama afectado verificar:

| Elemento | Qué comprobar |
|---|---|
| Nombre del participante | ¿Coincide con el nombre real de la clase? |
| Métodos llamados | ¿Existen en la clase con esa signatura? |
| Orden de mensajes | ¿Refleja el flujo real del código? |
| Retornos | ¿El tipo/valor devuelto es correcto? |
| Participantes presentes | ¿No falta ni sobra ninguna capa? |

### 2.3 Corregir el `.puml`

- Actualizar solo lo que diverge; no reescribir el diagrama entero si no es necesario.
- Si el cambio es relevante, regenerar el `.svg` correspondiente en `images/`.

### 2.4 Registrar en progreso.md

- Si un caso de uso cambia de estado, actualizar `documents/progreso.md`.

---

## Orden de revisión de clases recomendado

Revisar de abajo hacia arriba en la arquitectura (las capas bajas primero, para que los cambios se propaguen hacia arriba de forma controlada):

1. Entidades (`model/`)
2. Repositorios (`repository/`)
3. Servicios (`service/`)
4. Controladores (`controller/`)
5. Plantillas Thymeleaf (`templates/`) — solo verificar que no contengan lógica que debería estar en otra capa
