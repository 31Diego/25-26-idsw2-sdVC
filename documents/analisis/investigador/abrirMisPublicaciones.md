# FUNIBER GIPF > abrirMisPublicaciones > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirMisPublicaciones()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador gestione sus publicaciones propias.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirMisPublicaciones()](../../../images/analisis/investigador/abrirMisPublicaciones-investigador-analisis.svg)|
|-|
|Código fuente: [abrirMisPublicaciones.puml](../../../modelosUML/analisis/investigador/abrirMisPublicaciones.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### MisPublicacionesView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirMisPublicaciones()` desde `:PANEL_PRINCIPAL_ABIERTO` o desde `:MI_PUBLICACION_ABIERTA`
- Solicitar al controlador las publicaciones propias mediante `obtenerMisPublicaciones() : List<Publicacion>`
- Mostrar la lista de publicaciones propias al investigador
- Ofrecer acceso a publicaciones individuales, a la creación y vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Desde `:PANEL_PRINCIPAL_ABIERTO` o `:MI_PUBLICACION_ABIERTA` con `abrirMisPublicaciones()`
- **Control**: Se comunica con `PublicacionController` mediante `obtenerMisPublicaciones() : List<Publicacion>`
- **Salida**: Transita a `:MIS_PUBLICACIONES_ABIERTAS` (`publicacionesCargadas()`), `:Collaboration AbrirMiPublicacion` (`abrirMiPublicacion(id)`), `:Collaboration CrearPublicacion` (`crearPublicacion()`), `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerMisPublicaciones()` y delegar en el repositorio la obtención de publicaciones por autor

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `MisPublicacionesView`
- **Repositorio**: Delega en `PublicacionRepository` mediante `obtenerPorAutor(id) : List<Publicacion>`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar publicaciones filtradas por autor mediante `obtenerPorAutor(id) : List<Publicacion>`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de una publicación propia en el listado

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PANEL_PRINCIPAL_ABIERTO` o `:MI_PUBLICACION_ABIERTA`
2. El investigador solicita ver sus publicaciones: `MisPublicacionesView` recibe `abrirMisPublicaciones()`
3. `MisPublicacionesView` invoca `obtenerMisPublicaciones()` en `PublicacionController`
4. `PublicacionController` delega en `PublicacionRepository.obtenerPorAutor(id)` y obtiene `List<Publicacion>`
5. La vista muestra la lista → transita a `:MIS_PUBLICACIONES_ABIERTAS` con `publicacionesCargadas()`
6. Desde `:MIS_PUBLICACIONES_ABIERTAS` el investigador puede navegar a `abrirMiPublicacion(id)`, `crearPublicacion()` o `abrirPanelPrincipal()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener publicaciones propias|`PublicacionController`|`obtenerMisPublicaciones() : List<Publicacion>`|
|Acceder a publicaciones por autor|`PublicacionRepository`|`obtenerPorAutor(id) : List<Publicacion>`|
|Mostrar lista de publicaciones propias|`MisPublicacionesView`|`publicacionesCargadas()`|
|Abrir publicación propia|`MisPublicacionesView`|`abrirMiPublicacion(id)`|
|Crear nueva publicación|`MisPublicacionesView`|`crearPublicacion()`|
|Volver al panel principal|`MisPublicacionesView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y filtrado por autor
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirMisPublicaciones()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`MisPublicacionesView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: abrirMisPublicaciones()](../../../context/casosDeUso/detalle/investigador/abrirMisPublicaciones/abrirMisPublicaciones.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
