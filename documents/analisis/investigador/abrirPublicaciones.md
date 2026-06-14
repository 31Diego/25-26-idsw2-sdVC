# FUNIBER GIPF > abrirPublicaciones > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirPublicaciones()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador consulte el tablón de publicaciones globales del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirPublicaciones()](../../../images/analisis/investigador/abrirPublicaciones-investigador-analisis.svg)|
|-|
|Código fuente: [abrirPublicaciones.puml](../../../modelosUML/analisis/investigador/abrirPublicaciones.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### PublicacionesView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirPublicaciones()` desde `:PANEL_PRINCIPAL_ABIERTO`
- Solicitar al controlador el listado de publicaciones mediante `obtenerPublicaciones() : List<Publicacion>`
- Permitir filtrar publicaciones mediante `filtrarPublicaciones(criterio) : List<Publicacion>`
- Mostrar la lista de publicaciones del sistema al investigador
- Ofrecer acceso al detalle de una publicación concreta y vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Desde `:PANEL_PRINCIPAL_ABIERTO` con `abrirPublicaciones()`
- **Control**: Se comunica con `PublicacionController` mediante `obtenerPublicaciones()` y `filtrarPublicaciones(criterio)`
- **Salida**: Transita a `:PUBLICACIONES_ABIERTAS` (`publicacionesCargadas()`), `:Collaboration AbrirPublicacion` (`abrirPublicacion(id)`), `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerPublicaciones()` y delegar en el repositorio la obtención de todas las publicaciones
- Recibir `filtrarPublicaciones(criterio)` y delegar la búsqueda filtrada al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `PublicacionesView`
- **Repositorio**: Delega en `PublicacionRepository` mediante `obtenerTodos() : List<Publicacion>` y `buscarPorCriterio(criterio) : List<Publicacion>`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar todas las publicaciones mediante `obtenerTodos() : List<Publicacion>`
- Buscar publicaciones por criterio mediante `buscarPorCriterio(criterio) : List<Publicacion>`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de una publicación en el listado

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PANEL_PRINCIPAL_ABIERTO`
2. El investigador solicita ver publicaciones: `PublicacionesView` recibe `abrirPublicaciones()`
3. `PublicacionesView` invoca `obtenerPublicaciones()` en `PublicacionController`
4. `PublicacionController` delega en `PublicacionRepository.obtenerTodos()` y obtiene `List<Publicacion>`
5. La vista muestra la lista → transita a `:PUBLICACIONES_ABIERTAS` con `publicacionesCargadas()`
6. El investigador puede filtrar: `PublicacionesView` invoca `filtrarPublicaciones(criterio)` → delega en `PublicacionRepository.buscarPorCriterio(criterio)`
7. Desde `:PUBLICACIONES_ABIERTAS` puede navegar a `abrirPublicacion(id)` o `abrirPanelPrincipal()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener todas las publicaciones|`PublicacionController`|`obtenerPublicaciones() : List<Publicacion>`|
|Acceder a publicaciones en repositorio|`PublicacionRepository`|`obtenerTodos() : List<Publicacion>`|
|Filtrar publicaciones|`PublicacionController`|`filtrarPublicaciones(criterio) : List<Publicacion>`|
|Buscar publicaciones con criterio|`PublicacionRepository`|`buscarPorCriterio(criterio) : List<Publicacion>`|
|Mostrar lista de publicaciones|`PublicacionesView`|`publicacionesCargadas()`|
|Abrir publicación concreta|`PublicacionesView`|`abrirPublicacion(id)`|
|Volver al panel principal|`PublicacionesView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y lógica de filtrado
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirPublicaciones()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`PublicacionesView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: abrirPublicaciones()](../../../context/casosDeUso/detalle/investigador/abrirPublicaciones/abrirPublicaciones.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
