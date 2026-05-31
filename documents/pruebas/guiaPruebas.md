# FUNIBER GIPF — Guía de Pruebas Manuales

## Configuración previa

Antes de probar cualquier caso de uso:

1. Arrancar la aplicación (Run en `GipfApplication.java`)
2. Verificar en el terminal que aparece `Started GipfApplication in X seconds`
3. Credenciales de prueba: **`admin` / `1234`**
4. Consola H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:file:./funiber`, User: `sa`, Password: vacío)

---

## Casos de uso implementados

---

### iniciarSesion

| # | Acción | Resultado esperado |
|-|-|-|
| 1 | Navegar a `http://localhost:8080/convocatorias` sin sesión | Redirige a `/login` |
| 2 | Introducir `admin` / `1234` y pulsar Entrar | Redirige a `/panel` |
| 3 | Introducir usuario incorrecto | Vuelve a `/login` con mensaje de error |
| 4 | Introducir contraseña incorrecta | Vuelve a `/login` con mensaje de error |
| 5 | Dejar usuario vacío | Vuelve a `/login` con mensaje de error |
| 6 | Navegar a `http://localhost:8080/h2-console` sin sesión | Accesible (URL pública) |

---

### abrirConvocatorias

Preparación — el DataLoader carga 5 convocatorias automáticamente al arrancar.

| # | Acción | Resultado esperado |
|-|-|-|
| 1 | Navegar a `/convocatorias` (con sesión) | Muestra tabla con las 2 convocatorias |
| 2 | No hay datos en la tabla | Muestra mensaje "No hay convocatorias disponibles" |
| 3 | Filtrar por texto `Horizonte` | Muestra solo la primera convocatoria |
| 4 | Filtrar por estado `Cerrada` | Muestra solo la segunda convocatoria |
| 5 | Filtrar por área `Salud` | Muestra solo la segunda convocatoria |
| 6 | Limpiar filtros | Muestra las 2 convocatorias |
| 7 | Filtrar con valor que no coincide con ninguna | Muestra tabla vacía |

---

### abrirConvocatoria

Preparación — necesita datos del paso anterior.

| # | Acción | Resultado esperado |
|-|-|-|
| 1 | Pulsar "Ver detalle" en una convocatoria de la lista | Muestra la página de detalle con todos los campos |
| 2 | Verificar que aparecen: título, área, estado, fechas, descripción | Todos los campos visibles |
| 3 | Pulsar "Volver a la lista" | Vuelve a `/convocatorias` |
| 4 | Navegar directamente a `/convocatorias/1` | Muestra el detalle de la convocatoria con id 1 |
| 5 | Navegar a `/convocatorias/999` (id inexistente) | Error 500 (hasta implementar manejo de errores) |

---

### cerrarSesion

| # | Acción | Resultado esperado |
|-|-|-|
| 1 | Hacer click en "Cerrar sesión" desde el panel | Navega a `/cerrar-sesion` con pantalla de confirmación |
| 2 | Pulsar "Confirmar" | Redirige a `/login?logout` con mensaje "Sesión cerrada correctamente" |
| 3 | Pulsar "Cancelar" | Vuelve a `/panel` sin cerrar sesión |
| 4 | Intentar acceder a `/convocatorias` tras cerrar sesión | Redirige a `/login` |

---

### abrirPanelPrincipal

| # | Acción | Resultado esperado |
|-|-|-|
| 1 | Iniciar sesión con `admin` / `1234` | Redirige a `/panel` con el menú de navegación |
| 2 | Navegar directamente a `/panel` con sesión activa | Muestra el panel |
| 3 | Verificar que aparecen los enlaces (Proyectos, Investigadores, Convocatorias, Recompensas, Publicaciones) | Todos visibles |
| 4 | Verificar que aparece el enlace "Cerrar sesión" | Visible y lleva a `/cerrar-sesion` |
| 5 | Click en "Convocatorias" | Navega a `/convocatorias` |

---

## Plantilla para nuevos casos de uso

Cuando se implemente un nuevo caso de uso, añadir una sección siguiendo este formato:

```
### nombreCasoDeUso

Preparación — [SQL o pasos previos necesarios]

| # | Acción | Resultado esperado |
|-|-|-|
| 1 | [flujo principal — paso 1] | [qué debe ocurrir] |
| 2 | [flujo principal — paso 2] | [qué debe ocurrir] |
| 3 | [escenario de error 1] | [qué debe ocurrir] |
| 4 | [escenario de error 2] | [qué debe ocurrir] |
| 5 | [caso borde] | [qué debe ocurrir] |
```

### Qué probar siempre

Para cualquier caso de uso, verificar como mínimo:

- **Flujo principal**: el camino normal funciona de principio a fin
- **Datos vacíos**: qué pasa si no hay registros en la BD
- **Id inexistente**: qué pasa al acceder a un recurso que no existe
- **Sin sesión**: intentar acceder sin estar autenticado redirige a `/login`
- **Navegación**: los enlaces "volver" llevan al sitio correcto
