# Referencias técnicas — Spring Boot / FUNIBER GIPF

Documento de consulta rápida sobre conceptos del proyecto.

---

## Lógica de negocio — ¿dónde va?

**Regla:** la lógica de negocio vive en el **service**, nunca en el controller ni en el repository.

**Cómo detectarla:** si la condición de un `if` contiene reglas de dominio (roles, pertenencia, propiedad), es lógica de negocio.

```java
// ❌ MAL — la regla está en el controller
if (usuario.getRol() != Rol.COORDINADOR && !recompensa.getDestinatario().getId().equals(usuario.getId())) { ... }

// ✅ BIEN — la regla está en el service, el controller solo redirige
if (!recompensaService.puedeVer(usuario, recompensa)) { ... }
```

**El `if` en el controller no es el problema — lo que importa es qué hay dentro del `if`.**

Si el controller llama a un método del service y actúa sobre el `true/false` resultante, está bien. Es responsabilidad del controller decidir la respuesta HTTP (redirect, 404...) ante el resultado de negocio.

---

## Anotaciones de los modelos

### Antes de la clase

| Anotación | Qué hace |
|---|---|
| `@Entity` | Indica a JPA que esta clase es una tabla en la BD |
| `@Table(name = "...")` | Establece el nombre exacto de la tabla en la BD |
| `@Getter` | Lombok genera todos los `getX()` automáticamente |
| `@Setter` | Lombok genera todos los `setX()` automáticamente |
| `@NoArgsConstructor` | Lombok genera `new Clase()`, obligatorio para JPA |

### En los campos

| Anotación | Qué hace |
|---|---|
| `@Id` | Marca el campo como clave primaria |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | La BD asigna el id automáticamente (autoincrement) |
| `@Column(columnDefinition = "TEXT")` | Mapea el campo a tipo TEXT en SQL (sin límite de longitud). Sin esto, un `String` es `VARCHAR(255)` |
| `@Column(unique = true)` | Añade restricción de unicidad en la BD |
| `@Enumerated(EnumType.STRING)` | Guarda el enum como texto (`"COORDINADOR"`) en vez de número |

### Relaciones entre entidades

**`@ManyToOne` + `@JoinColumn`** — muchos registros apuntan a uno. Crea una columna de clave foránea.
```java
@ManyToOne
@JoinColumn(name = "proyecto_id")
private Proyecto proyecto;
```

**`@ManyToMany` + `@JoinTable`** — relación muchos a muchos. Necesita una tabla intermedia en la BD.
```java
@ManyToMany
@JoinTable(
    name = "proyecto_investigador",        // nombre de la tabla intermedia
    joinColumns = @JoinColumn(name = "proyecto_id"),
    inverseJoinColumns = @JoinColumn(name = "investigador_id")
)
private List<Investigador> investigadores = new ArrayList<>();
```

**`@OneToOne(mappedBy = ..., cascade = CascadeType.ALL, orphanRemoval = true)`** — relación uno a uno. `cascade` propaga operaciones al objeto relacionado; `orphanRemoval` lo borra si se desvincula.

---

## Repositories — tres niveles

### Nivel 1 — herencia de JpaRepository
```java
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> { }
```
Solo con extender `JpaRepository<Entidad, TipoId>` tienes gratis: `findById()`, `findAll()`, `save()`, `deleteById()`, `count()`...

### Nivel 2 — métodos por nombre
```java
List<Recompensa> findByDestinatario(Investigador destinatario);
Optional<CargaTrabajo> findByInvestigadorId(Long investigadorId);
void deleteByProyectoId(Long proyectoId);
```
Spring Data lee el nombre del método y genera el SQL. `Optional` se usa cuando el resultado puede no existir.

### Nivel 3 — @Query explícita
```java
@Query("SELECT p FROM Proyecto p WHERE p.titulo LIKE %:criterio% OR p.descripcion LIKE %:criterio%")
List<Proyecto> buscarPorCriterio(@Param("criterio") String criterio);
```
Para consultas con `OR`, `JOIN` o condiciones que el nombre del método no puede expresar. Es JPQL (sobre objetos Java, no tablas SQL). Los tres niveles conviven en el mismo interface.

---

## Patrón Strategy — policies

Cuando el comportamiento depende del rol del usuario, en lugar de `if (rol == COORDINADOR)` en el service, se usan dos interfaces con implementaciones por rol.

```java
// Interface
public interface PoliticaAcceso {
    boolean tieneAcceso(Proyecto proyecto, Investigador investigador);
}

// Implementaciones
class AccesoCoordinador implements PoliticaAcceso {
    public boolean tieneAcceso(...) { return true; }        // siempre puede
}
class AccesoInvestigador implements PoliticaAcceso {
    public boolean tieneAcceso(...) { return proyecto.getInvestigadores().stream()... } // solo si es miembro
}

// Uso en el service — sin ningún if
Map<Rol, PoliticaAcceso> politicas = Map.of(
    Rol.COORDINADOR, new AccesoCoordinador(),
    Rol.INVESTIGADOR, new AccesoInvestigador());

public boolean tieneAcceso(Proyecto p, Investigador i) {
    return politicas.get(i.getRol()).tieneAcceso(p, i);
}
```

---

## Config — seguridad

### `InvestigadorUserDetails`
Adaptador entre el modelo `Investigador` (dominio) y la interfaz `UserDetails` (Spring Security). Spring Security no conoce `Investigador` — esta clase hace de puente.

- `getAuthorities()` convierte el rol (`COORDINADOR`) al formato de Spring Security (`ROLE_COORDINADOR`), que es lo que necesita `@PreAuthorize("hasRole('COORDINADOR')")`.
- `getInvestigador()` permite recuperar el objeto de dominio en los controllers con `@AuthenticationPrincipal`.

### `SecurityConfig`
- `PasswordEncoder` — BCrypt para hashear contraseñas.
- `DaoAuthenticationProvider` — conecta el service de autenticación con el encoder.
- `SecurityFilterChain` — define qué rutas son públicas (`/login`, `/h2-console`) y cuáles requieren login.
- Las líneas de `csrf` y `frameOptions` son necesarias para que la consola H2 funcione en el navegador.

---

## H2 — base de datos embebida

H2 es un motor SQL que arranca dentro de la propia aplicación (no es un servidor externo).

| Fichero | Qué es |
|---|---|
| `funiber.mv.db` | La base de datos completa en formato binario propio de H2 |
| `funiber.trace.db` | Log interno de H2 — se puede ignorar |

**Configuración clave en `application.properties`:**
```properties
spring.datasource.url=jdbc:h2:file:./funiber   # persiste en archivo (no en memoria)
spring.jpa.hibernate.ddl-auto=update            # crea/actualiza tablas al arrancar sin borrar datos
spring.h2.console.enabled=true                  # consola web en /h2-console
```

Si se borra `funiber.mv.db`: Hibernate recrea las tablas vacías, luego `DataLoader` las rellena con los datos de ejemplo.

---

## DataLoader

Implementa `CommandLineRunner` — Spring llama a su método `run()` automáticamente justo después de arrancar la aplicación. Sirve para poblar la BD con datos de ejemplo en desarrollo.

Guard al inicio: si ya hay datos, no inserta nada:
```java
if (investigadorRepository.count() > 0) return;
```

Credenciales de ejemplo: `coordinador / coordinador` · `investigador / investigador`

---

## pom.xml — dependencias

Fichero de configuración de Maven (el gestor de dependencias). Cada `<dependency>` es una librería que Maven descarga automáticamente.

| Dependencia | Para qué |
|---|---|
| `spring-boot-starter-web` | Servidor web (Tomcat embebido + Spring MVC) |
| `spring-boot-starter-thymeleaf` | Motor de plantillas HTML |
| `spring-boot-starter-data-jpa` | JPA + Hibernate (capa de acceso a BD) |
| `spring-boot-starter-security` | Spring Security |
| `h2` (`scope: runtime`) | Motor de base de datos H2 |
| `lombok` | Generación de getters/setters/constructores |

---

## Arranque de la aplicación

```java
public static void main(String[] args) {
    SpringApplication.run(GipfApplication.class, args);
}
```

`SpringApplication.run()` hace automáticamente:
1. Lee `application.properties`
2. Conecta con H2 y crea las tablas
3. Escanea `@Component`, `@Service`, `@Controller`... y los instancia e inyecta entre sí
4. Arranca Tomcat en el puerto 8080
5. Registra las rutas de los controllers
6. Ejecuta los `CommandLineRunner` (DataLoader)
7. La app queda en escucha esperando peticiones HTTP

**Inversión de control:** tú no llamas al framework — es el framework quien llama a tu código cuando toca. Tú declaras las piezas con anotaciones y Spring las conecta.

---

## target/classes

Contiene los `.class` — bytecode Java generado por el compilador a partir de los `.java`. Los ejecuta la JVM en el servidor; el navegador nunca los ve (solo recibe HTML, CSS, etc.). Es una carpeta generada — si se borra, Maven la recrea al compilar. Está en `.gitignore`.
