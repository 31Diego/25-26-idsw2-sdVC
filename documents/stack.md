# FUNIBER GIPF — Stack Tecnológico

## Visión general

```
Navegador  ←→  Spring Boot (Java)  ←→  H2 (Base de datos)
                     ↑
               Thymeleaf (HTML)
```

Todo el proyecto es **una sola aplicación Java**. No hay proyecto separado para el frontend.

---

## Tecnologías y su papel

### Spring Boot
**Qué es**: Framework Java que arranca un servidor web completo con una sola clase (`GipfApplication.java`) y sin configuración externa.

**Por qué**: Es el estándar para aplicaciones Java en la industria. Elimina la mayor parte del trabajo de configuración — solo declaras las dependencias en el `pom.xml` y Spring Boot lo conecta todo automáticamente.

**Cómo se ve en el código**:
```java
@SpringBootApplication
public class GipfApplication {
    public static void main(String[] args) {
        SpringApplication.run(GipfApplication.class, args);  // arranca el servidor
    }
}
```

---

### Spring MVC (`@Controller`)
**Qué es**: La parte de Spring Boot que gestiona las peticiones HTTP. Recibe una URL del navegador y decide qué hacer.

**Corresponde al análisis**: `XxxView` (clase Boundary, azul)

**Por qué**: Es el mecanismo estándar de Spring para aplicaciones web con Thymeleaf.

**Cómo se ve en el código**:
```java
@Controller
@RequestMapping("/convocatorias")
public class ConvocatoriasController {

    @GetMapping            // responde a GET /convocatorias
    public String abrirConvocatorias(Model model) { ... }

    @GetMapping("/{id}")   // responde a GET /convocatorias/1
    public String abrirConvocatoria(@PathVariable Long id, Model model) { ... }
}
```

---

### Thymeleaf
**Qué es**: Motor de plantillas HTML. Los archivos `.html` de `src/main/resources/templates/` son procesados por Thymeleaf para insertar los datos del servidor.

**Por qué**: Al ser parte de Spring Boot, no necesita proyecto separado. El HTML se genera en el servidor y se envía completo al navegador — sin JavaScript.

**Cómo se ve en el código**:
```html
<!-- th: es el prefijo de Thymeleaf -->
<tr th:each="c : ${convocatorias}">
    <td th:text="${c.titulo}"></td>
    <td th:href="@{/convocatorias/{id}(id=${c.id})}">Ver</td>
</tr>
```

---

### Spring Data JPA + `@Service`
**Qué es**: JPA es el estándar Java para trabajar con bases de datos sin escribir SQL manual. Spring Data JPA lo simplifica aún más — el repositorio solo necesita declarar la interfaz.

**Corresponde al análisis**:
- `@Service` → `XxxController` (clase Control, amarillo)
- `JpaRepository` → `XxxRepository` (clase Entidad, naranja)
- `@Entity` → `Xxx` (clase Entidad, naranja)

**Por qué**: Se evita escribir SQL para operaciones básicas (`findAll`, `findById`, `save`, `deleteById`). Solo hay que añadir métodos con `@Query` cuando se necesitan consultas personalizadas.

**Cómo se ve en el código**:
```java
// Spring genera el SQL automáticamente para estos métodos
public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, Long> {
    // findAll(), findById(), save(), deleteById() → vienen gratis
    
    @Query("SELECT c FROM Convocatoria c WHERE ...")  // solo para consultas personalizadas
    List<Convocatoria> buscarPorCriterio(...);
}
```

---

### H2
**Qué es**: Base de datos SQL escrita en Java. En modo archivo guarda los datos en `funiber.mv.db` dentro de la carpeta del proyecto.

**Por qué**: No requiere instalación de ningún servidor externo (MySQL, PostgreSQL...). Para un proyecto en localhost es la opción más cómoda con Spring Boot.

**Consola web**: `http://localhost:8080/h2-console` permite ver y editar las tablas directamente durante el desarrollo.

---

### Lombok
**Qué es**: Librería Java que genera automáticamente código repetitivo (getters, setters, constructores) mediante anotaciones.

**Por qué**: Sin Lombok, cada campo de una clase `@Entity` necesitaría su propio `getX()` y `setX()` escritos a mano. Con Lombok, `@Getter @Setter` lo genera automáticamente en tiempo de compilación.

**Cómo se ve en el código**:
```java
@Getter @Setter @NoArgsConstructor   // genera getters, setters y constructor vacío
public class Convocatoria {
    private String titulo;
    // sin Lombok habría que escribir:
    // public String getTitulo() { return titulo; }
    // public void setTitulo(String titulo) { this.titulo = titulo; }
}
```

---

## Correspondencia análisis ↔ código

| Análisis (color) | Clase Java | Anotación |
|---|---|---|
| Vista / Boundary (azul) | `XxxController` | `@Controller` |
| Control (amarillo) | `XxxService` | `@Service` |
| Repositorio (naranja) | `XxxRepository` | `@Repository` / `JpaRepository` |
| Entidad (naranja) | `Xxx` | `@Entity` |

---

## Estructura de archivos

```
src/
├── main/
│   ├── java/com/funiber/gipf/
│   │   ├── GipfApplication.java       ← arranca el servidor
│   │   ├── controllers/               ← @Controller (Vista del análisis)
│   │   ├── services/                  ← @Service (Control del análisis)
│   │   ├── repositories/              ← JpaRepository (Repositorio del análisis)
│   │   └── models/                    ← @Entity (Entidad del análisis)
│   └── resources/
│       ├── templates/                 ← archivos .html (Thymeleaf)
│       └── application.properties    ← configuración (H2, puerto, etc.)
pom.xml                                ← dependencias del proyecto (Maven)
```

---

## pom.xml

Es el archivo de configuración de **Maven**, la herramienta de construcción de proyectos Java. Define:
- Qué versión de Spring Boot usar
- Qué librerías descargar (Thymeleaf, JPA, H2, Lombok...)
- Cómo compilar y empaquetar el proyecto

Sin `pom.xml`, Java no sabe qué dependencias necesita el proyecto.

---

## Thymeleaf vs React — ventajas e inconvenientes

### Thymeleaf (este proyecto)

| | |
|---|---|
| ✅ | Un solo proyecto Java — sin configurar dos servidores ni dos carpetas |
| ✅ | Sin JavaScript obligatorio — todo en Java, que ya conoces |
| ✅ | Más sencillo de arrancar y depurar para proyectos pequeños |
| ✅ | El HTML llega completo al navegador — mejor para SEO |
| ❌ | Cada acción recarga la página completa (click → servidor → HTML nuevo) |
| ❌ | Experiencia de usuario menos fluida que una SPA |
| ❌ | No reutilizable como API si en el futuro quisieras una app móvil |

### React + Spring Boot (API REST)

| | |
|---|---|
| ✅ | La página no recarga — las transiciones son instantáneas |
| ✅ | El backend queda como API pura, reutilizable por cualquier cliente (móvil, otra web...) |
| ✅ | Más escalable para aplicaciones grandes y equipos separados (front/back) |
| ❌ | Dos proyectos separados — más configuración inicial |
| ❌ | Necesitas aprender JavaScript/TypeScript además de Java |
| ❌ | Mayor complejidad: gestión de estado, tokens JWT en el cliente, CORS... |

### Angular + Spring Boot (como el proyecto del profesor)

Angular está en la misma categoría que React — es un framework que corre en el navegador y convierte el backend en una API REST pura. Las diferencias respecto a React:

| | Angular | React |
|---|---|---|
| Tipo | Framework completo | Librería de UI |
| Lenguaje | TypeScript (obligatorio) | JavaScript o TypeScript |
| Estructura | Muy rígida — todo tiene su sitio | Flexible, tú decides |
| Curva de aprendizaje | Alta | Media |
| Uso habitual | Proyectos empresariales grandes, equipos con Java en backend | Startups, apps modernas, equipos mixtos |

Desde el punto de vista de Spring Boot, da igual si el frontend es React o Angular — en ambos casos Spring Boot expone una API REST y devuelve JSON. El frontend (React o Angular) corre en el navegador y llama a esa API.

### Cuándo elegir cada uno

| Situación | Recomendación |
|---|---|
| Proyecto de curso, localhost, sin equipo | Thymeleaf |
| App con mucha interactividad (filtros en tiempo real, drag & drop...) | React o Angular |
| API que consume una app móvil también | React o Angular + API REST |
| Equipo grande, proyecto empresarial con Java | Angular |
| Prototipo rápido que solo necesita funcionar | Thymeleaf |
