# FUNIBER GIPF > eliminarPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador elimine una publicación del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarPublicacion()](../../../images/analisis/coordinador/eliminarPublicacion-analisis.svg)|
|-|
|Código fuente: [eliminarPublicacion.puml](../../../modelosUML/analisis/coordinador/eliminarPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarPublicacion()` desde `:PUBLICACION_ABIERTA`
- Solicitar al controlador los datos de la publicación a eliminar mediante `cargarPublicacionParaEliminacion(id) : Publicacion`
- Mostrar la pantalla de confirmación con los datos de la publicación
- Solicitar al controlador la eliminación definitiva mediante `eliminarPublicacion(id) : void`
- Navegar al listado de publicaciones tras la eliminación

**Colaboraciones**:
- **Entrada**: Desde `:PUBLICACION_ABIERTA` con `eliminarPublicacion()`
- **Control**: Se comunica con `PublicacionController` mediante `cargarPublicacionParaEliminacion(id)` y `eliminarPublicacion(id)`
- **Salida**: Transita a `:PUBLICACIONES_ABIERTAS` con `abrirPublicaciones()`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `cargarPublicacionParaEliminacion(id)` y delegar en el repositorio la obtención de la publicación
- Recibir `eliminarPublicacion(id)` y delegar la eliminación al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarPublicacionView`
- **Repositorio**: Delega en `PublicacionRepository` mediante `obtenerPorId(id) : Publicacion` y `eliminarPorId(id) : void`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar una publicación por id mediante `obtenerPorId(id) : Publicacion`
- Eliminar una publicación del sistema mediante `eliminarPorId(id) : void`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de la publicación mostrados en la confirmación de eliminación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PUBLICACION_ABIERTA`
2. El coordinador solicita eliminar publicación: `EliminarPublicacionView` recibe `eliminarPublicacion()`
3. `EliminarPublicacionView` invoca `cargarPublicacionParaEliminacion(id)` en `PublicacionController`
4. `PublicacionController` delega en `PublicacionRepository.obtenerPorId(id)` y obtiene un objeto `Publicacion`
5. La pantalla de confirmación se muestra con los datos de la publicación
6. El coordinador confirma: `EliminarPublicacionView` invoca `eliminarPublicacion(id) : void` en `PublicacionController`
7. `PublicacionController` delega en `PublicacionRepository.eliminarPorId(id)`
8. La vista navega → `:PUBLICACIONES_ABIERTAS` con `abrirPublicaciones()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cargar datos para confirmación|`PublicacionController`|`cargarPublicacionParaEliminacion(id) : Publicacion`|
|Acceder a la publicación por id|`PublicacionRepository`|`obtenerPorId(id) : Publicacion`|
|Eliminar publicación del sistema|`PublicacionController`|`eliminarPublicacion(id) : void`|
|Persistir la eliminación|`PublicacionRepository`|`eliminarPorId(id) : void`|
|Navegar al listado de publicaciones|`EliminarPublicacionView`|`abrirPublicaciones()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de la confirmación e interacción con el coordinador
- **Control**: Solo coordinación del proceso de eliminación
- **Entidad**: Solo datos y gestión de la persistencia

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `eliminarPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EliminarPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: eliminarPublicacion()](../../../context/casosDeUso/detalle/coordinador/eliminarPublicacion/eliminarPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
