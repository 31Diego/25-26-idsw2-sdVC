# FUNIBER GIPF > abrirPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador consulte el detalle de una publicación y pueda responderla.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirPublicacion()](../../../images/analisis/investigador/abrirPublicacion-investigador-analisis.svg)|
|-|
|Código fuente: [abrirPublicacion.puml](../../../modelosUML/analisis/investigador/abrirPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### PublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirPublicacion(id)` desde `:PUBLICACIONES_ABIERTAS`
- Solicitar al controlador los datos de la publicación mediante `obtenerPublicacion(id) : Publicacion`
- Mostrar el detalle de la publicación al investigador
- Ofrecer la opción de responder la publicación y vuelta al listado

**Colaboraciones**:
- **Entrada**: Desde `:PUBLICACIONES_ABIERTAS` con `abrirPublicacion(id)`
- **Control**: Se comunica con `PublicacionController` mediante `obtenerPublicacion(id) : Publicacion`
- **Salida**: Transita a `:PUBLICACION_ABIERTA` (`publicacionMostrada()`), `:Collaboration ResponderPublicacion` (`responderPublicacion()`), `:PUBLICACIONES_ABIERTAS` (`abrirPublicaciones()`)

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerPublicacion(id)` y delegar en el repositorio la obtención de la publicación

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `PublicacionView`
- **Repositorio**: Delega en `PublicacionRepository` mediante `obtenerPorId(id) : Publicacion`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar una publicación por id mediante `obtenerPorId(id) : Publicacion`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos completos de la publicación a mostrar

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PUBLICACIONES_ABIERTAS`
2. El investigador selecciona una publicación: `PublicacionView` recibe `abrirPublicacion(id)`
3. `PublicacionView` invoca `obtenerPublicacion(id)` en `PublicacionController`
4. `PublicacionController` delega en `PublicacionRepository.obtenerPorId(id)` y obtiene un objeto `Publicacion`
5. La vista muestra el detalle → transita a `:PUBLICACION_ABIERTA` con `publicacionMostrada()`
6. Desde `:PUBLICACION_ABIERTA` el investigador puede navegar a `responderPublicacion()` o `abrirPublicaciones()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener datos de la publicación|`PublicacionController`|`obtenerPublicacion(id) : Publicacion`|
|Acceder a la publicación por id|`PublicacionRepository`|`obtenerPorId(id) : Publicacion`|
|Mostrar detalle de la publicación|`PublicacionView`|`publicacionMostrada()`|
|Responder publicación|`PublicacionView`|`responderPublicacion()`|
|Volver al listado|`PublicacionView`|`abrirPublicaciones()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y obtención de la publicación
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`PublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: abrirPublicacion()](../../../context/casosDeUso/detalle/investigador/abrirPublicacion/abrirPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
