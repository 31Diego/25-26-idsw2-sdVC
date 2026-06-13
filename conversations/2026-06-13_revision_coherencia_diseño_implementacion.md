# Sesión — 2026-06-13 · Revisión coherencia diseño-implementación

## [inicio] Orientación

**Prompt:** "buenas claude"

**Resultado:** Orientación del proyecto. Todo análisis, diseño y código ✅. Pendiente solo la columna Doc. Desarrollo (⚠️ en todos los casos de uso). El usuario tenía abierto `EntregableService.java` en el IDE.

---

## [revisión] Coherencia diagramas de diseño vs clases del proyecto — Coordinador

**Prompt:** El usuario decidió revisar por su cuenta la coherencia entre los diagramas de diseño y las clases del proyecto, pidiendo ayuda puntual cuando tuviera dudas.

**Resultado:** Revisión completada con resultado muy positivo. El usuario fue repasando caso a caso:

**CONVOCATORIAS**
- `abrirConvocatoria` → correcto, todos los métodos existen
- `abrirConvocatorias` → bien, duda sobre qué es el parámetro `q` en `buscarPorCriterios`
- `importarConvocatorias` → bien, `save` es método heredado de JpaRepository
- `eliminarConvocatoria` → bien

**ENTREGABLES / ARCHIVO**
- `abrirEntregable` → correcto
- `abrirEntregables` → correcto
- `editarEntregable` → bien
- `eliminarEntregable` → bien
- `crearEntregable` → bien

**INVESTIGADOR / PERFIL**
- `abrirInvestigador` → bien
- `abrirInvestigadores` → bien, pero el SVG estaba desactualizado (el `.puml` era correcto)
- `crearInvestigador` → bien
- `abrirOpcionesPerfil` → bien
- `editarPerfil` → bien
- `abrirSolicitudesEliminacionPerfil` → bien
- `abrirSolicitudEliminacionPerfil` → bien
- `solicitarEliminacionPerfil` → bien
- `eliminarPerfil` → bien

**PROYECTOS**
- `abrirProyectos` → bien
- `abrirProyecto` → bien
- `abrirInvestigadoresDeProyecto` → bien
- `crearProyecto` → bien
- `editarProyecto` → bien
- `agregarInvestigador` → bien
- `eliminarProyecto` → bien
- `eliminarInvestigador` → bien, pero el diagrama omite el `remove()` sobre la colección en memoria antes del `save()`. Claude explicó que es una simplificación aceptable: `remove()` no toca la BD, solo modifica la lista en memoria; el `save()` es el que genera el DELETE en la tabla intermedia.

**Errores encontrados:** mínimos — un SVG desactualizado y un self-call omitido en `eliminarInvestigador`.

**Decisión:** Visto bueno dado. No se corrigieron los diagramas por ser detalles menores.

---

## [duda] Peticiones GET y POST — por qué solo esas dos

**Prompt:** Por qué en los diagramas solo aparecen peticiones GET o POST para casos de uso que implican editar o eliminar algo.

**Resultado:** Explicación del stack: el proyecto usa Thymeleaf con formularios HTML, y los navegadores solo soportan GET y POST nativamente. Para usar PUT/DELETE habría que activar `HiddenHttpMethodFilter` de Spring MVC con un campo oculto en el formulario. En este proyecto la convención es:
- `GET /entidad/{id}/editar` → abre el formulario
- `POST /entidad/{id}/editar` → procesa los cambios
- `POST /entidad/{id}/eliminar` → elimina

Es la simplificación estándar para aplicaciones web tradicionales con Thymeleaf.

---

## [duda] Por qué GET no debe tener efectos secundarios

**Prompt:** Por qué no es recomendable que una petición GET haga operaciones de eliminación, y por qué los POST sí pueden.

**Resultado:** HTTP define GET como seguro e idempotente — puede ejecutarse mil veces sin cambiar el estado del servidor. Los navegadores y la infraestructura confían en eso: prefetch de links, crawlers de Google, caché de proxies, recarga sin aviso. Si un `GET /proyecto/3/eliminar` borrara el proyecto, un crawler podría vaciar la base de datos. POST no tiene esa garantía: no se cachea, no se prefetchea, y si el usuario intenta recargar el navegador avisa "¿reenviar el formulario?". Esa advertencia es el navegador diciendo que la operación tuvo un efecto.

**Decisión:** Concepto asimilado. El usuario confirmó que en sus diagramas el GET solo abre formularios/confirmaciones y el POST ejecuta la operación.

---

## [duda] Diferencia entre Thymeleaf y API REST

**Prompt:** Qué diferencia hay entre una API REST y Thymeleaf, ya que el usuario tiene archivos HTML pero no entendía que "no tuviera frontend".

**Resultado:** Aclaración: sí tiene frontend. La diferencia es dónde se genera el HTML:
- **Thymeleaf (server-side rendering):** el servidor recibe la petición, combina la plantilla `.html` con los datos del modelo y devuelve HTML ya construido. El navegador solo lo muestra.
- **API REST + React/Angular:** el servidor devuelve solo JSON. El navegador descarga una aplicación JavaScript que se ejecuta en él, hace peticiones al servidor pidiendo datos, recibe el JSON y construye el HTML en el propio navegador.

La diferencia visual para el usuario es mínima; la diferencia real es dónde ocurre el trabajo de construir la interfaz.

---

## [duda] Quién crea el HTML — Tomcat vs Thymeleaf

**Prompt:** El usuario preguntó si es Tomcat el que crea el HTML.

**Resultado:** No exactamente. Se distinguieron los roles:
- **Tomcat:** servidor web, recibe la petición HTTP, la enruta al controller correcto y devuelve la respuesta. No sabe nada de HTML.
- **Thymeleaf:** motor de plantillas, toma el `.html` + los datos del `Model` y produce el HTML final.
- **El controller:** orquesta todo, llama al service, mete datos en el `Model` y le dice a Thymeleaf qué plantilla renderizar con el `return "nombre-plantilla"`.

Flujo completo: `Navegador → Tomcat → Controller → Service → (datos al Model) → Thymeleaf renderiza → HTML final → Tomcat → Navegador`.

---

## [duda] Tipos de servidores web

**Prompt:** Qué tipos de servidores web hay y qué características tienen.

**Resultado:** Resumen breve de los más conocidos (Tomcat, Nginx, Apache HTTP, Jetty, Node.js/Express). Se destacó que Tomcat está embebido en Spring Boot — no hay que instalarlo ni configurarlo, arranca solo al ejecutar la aplicación mediante `SpringApplication.run()`.

---

## [duda] Códigos de estado HTTP

**Prompt:** Qué son los códigos 200 OK y 302 redirect que aparecen en los diagramas de secuencia.

**Resultado:** Explicación de los códigos relevantes para el proyecto:
- `200 OK` → petición resuelta correctamente, devuelve una página
- `302 Found` → redirección temporal, resultado del `return "redirect:/..."` en los controllers
- `404 Not Found`, `403 Forbidden`, `500 Internal Server Error` → mencionados como contexto

El flujo típico tras un POST es: `POST → 302 redirect → GET → 200 OK`. El redirect evita que al recargar se repita el POST — esto es el patrón PRG (Post/Redirect/Get).

---

## [duda] Mensajes reflexivos en diagramas de secuencia

**Prompt:** Por qué hay flechas que dan una vuelta de 180 grados sin llegar a otra clase, como las de `model.addAttribute()` en el diagrama de eliminar entregable.

**Resultado:** Son **self-calls** o mensajes reflexivos — el objeto se manda un mensaje a sí mismo. En este caso `model.addAttribute()` es una operación que el controller hace sobre el objeto `model` que ya tiene en mano. Como `Model` no aparece como caja participante en el diagrama (es infraestructura de Spring, no una clase de dominio), la llamada no puede apuntar a ningún participante y se representa como self-call sobre el propio controller. Es una simplificación habitual y correcta.

---

## [duda] Flujo de vuelta desde la BD

**Prompt:** El flujo de vuelta de una petición (cuando ya ha llegado a la BD y sale de ahí), ¿lo que devuelve tiene que ser un objeto o lista de objetos?

**Resultado:** Sí. El flujo de vuelta es: `Repository → Service → Controller → Thymeleaf → HTML`. Repository devuelve objetos de dominio (`Entregable`, `Proyecto`...), el service los procesa si hace falta, el controller los mete en el `Model` con `addAttribute()`, y Thymeleaf los consume para construir el HTML. Lo único que "sale" distinto es al final: el controller no devuelve un objeto al navegador sino el nombre de una plantilla.

---

## [duda] Formulario de creación sin consulta a la BD

**Prompt:** En el diagrama de `crearEntregable`, el GET para mostrar el formulario no sale del controller hacia ningún service ni repository. ¿Qué muestra entonces?

**Resultado:** Cuando el formulario es de creación, no hay nada que buscar en la BD porque el objeto aún no existe. El controller instancia un objeto vacío (`new Entregable()`) y lo pasa al modelo. Thymeleaf enlaza los campos del formulario (`th:field`) a los atributos de ese objeto vacío, que aparecen en blanco. Cuando el usuario rellena y envía, Spring coge esos valores y los mete en el objeto automáticamente mediante `@ModelAttribute`.

---

## [duda] Métodos del diagrama de análisis vs HTML

**Prompt:** En el diagrama de análisis de `editarCargaTrabajo`, la `View` usa métodos como `obtenerCargaTrabajo(id)`, pero en el HTML no se ven esos métodos. ¿Por qué?

**Resultado:** En el análisis la `View` es una abstracción que agrupa toda la capa de presentación, incluyendo las peticiones que lanza. Esos "métodos" no son métodos reales del HTML — representan las peticiones HTTP que el formulario dispara al controller (`GET /carga-trabajo/{id}`, `POST /carga-trabajo/{id}/editar`). El HTML no tiene lógica ni métodos, solo un `<form action="..." method="post">`. El análisis describe el "qué" (intención), el diseño describe el "cómo" (verbos HTTP reales, nombres de métodos exactos).

---

## [duda] Licitud de usar GET y POST para todas las operaciones

**Prompt:** ¿Es lícito hacer operaciones de edición y eliminación usando solo GET y POST?

**Resultado:** Sí, con matices:
- POST para editar/eliminar → correcto en contexto web con navegador como cliente.
- GET para editar/eliminar → incorrecto. GET debe ser seguro e idempotente. Si un GET elimina, crawlers, prefetch o recargas pueden ejecutarlo sin intención del usuario.

En el proyecto el GET solo abre formularios/confirmaciones y el POST ejecuta la operación — patrón correcto.

---

## [duda] PUT, DELETE, PATCH — por qué existen si con GET y POST se puede todo

**Prompt:** El usuario razonó que quizás PUT/DELETE son combinaciones de GET+POST. Luego matizó: quizás es al revés, que como no tiene PUT/DELETE los sustituye con la combinación GET+POST.

**Resultado:** La segunda intuición es la correcta. PUT/DELETE/PATCH son verbos independientes con semántica propia — no son combinaciones de GET y POST. Existen porque en una API REST el cliente necesita expresar exactamente qué intención tiene. Lo que sí es correcto es que **como Thymeleaf no puede usar PUT/DELETE**, el proyecto los sustituye con la combinación GET (formulario/confirmación) + POST (ejecutar). La causa es la limitación del navegador como cliente, no el protocolo.

Con DELETE directo el flujo sería: `DELETE /entregable/3 → 200 OK` (un solo flujo). Con Thymeleaf son dos flujos: `GET /entregable/3/eliminar → confirmación` y `POST /entregable/3/eliminar → redirect`. Por eso en los diagramas de secuencia las operaciones de edición/eliminación tienen siempre dos flujos separados.

**Conclusión del usuario:** "Entonces mi aproximación con Thymeleaf es más primitiva o vanilla". Correcto — es server-side rendering clásico, la forma en que se hacían las aplicaciones web antes de que JavaScript tomara protagonismo en el navegador.

---

## [duda] Guardia de auto-eliminación en `eliminarPerfil`

**Prompt:** Qué comprueba exactamente el `if (!investigadorService.eliminarPerfil(coordinador.getId(), id))` en el controller.

**Resultado:** Llama a `eliminarPerfil(actorId, targetId)` del service, que devuelve `false` si el coordinador intenta eliminarse a sí mismo (`actorId.equals(targetId)`). El `if` del controller actúa sobre ese resultado redirigiendo a opciones si la eliminación falló. El controller no sabe por qué falló — la lógica está en el service. SRP y SoC correctos, no incumple SOLID.

---

## [duda] Error en diagrama de `eliminarInvestigador`

**Prompt:** En el POST de `eliminarInvestigador`, el método del `ProyectoService` llama primero a `remove()` y luego a `save()`, pero el diagrama solo muestra el `save()`.

**Resultado:** Confirmado. El método real (líneas 71-74 de `ProyectoService`) es:
```java
proyecto.getInvestigadores().remove(investigador);
proyectoRepository.save(proyecto);
```
El diagrama colapsa los dos pasos en uno. `remove()` modifica la lista en memoria sin tocar la BD; `save()` es el que hace que JPA genere el DELETE en la tabla intermedia. Para corregirlo habría que añadir un self-call en `PService` antes del `save`.

**Decisión:** Se deja como está — es una simplificación menor.

---

## [diagramas faltantes] Investigador — 9 diagramas de diseño creados

**Prompt:** El usuario detectó que la carpeta `modelosUML/diseño/investigador/` solo tenía 16 diagramas, mientras que el análisis del investigador parece tener los 26 casos de uso y la implementación funciona bien.

**Resultado:** Comparando con la priorización, faltaban los siguientes diagramas de diseño del investigador:
- `abrirEntregables`, `abrirEntregable`, `crearEntregable`, `editarEntregable`, `eliminarEntregable`
- `editarPublicacion`, `eliminarPublicacion`
- `abrirInvestigadores`, `abrirInvestigador`

Se crearon los 9 diagramas basándose en los del coordinador como referencia, adaptando solo el actor. Los flujos son idénticos porque los mismos controllers y services gestionan ambos actores — el control de acceso está encapsulado en las policies.

**Causa identificada:** Al refactorizar con el patrón Strategy (policies), los controllers pasaron a gestionar ambos actores con una sola implementación. La diferenciación por rol quedó encapsulada en las policies y se asumió que los flujos del investigador estaban cubiertos, sin generar los diagramas propios.

**Pendiente:** Renderizar los 9 `.puml` nuevos a `.svg`.

---
