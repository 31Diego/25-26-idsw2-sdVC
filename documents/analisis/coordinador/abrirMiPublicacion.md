# FUNIBER GIPF > abrirMiPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirMiPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador consulte el detalle de una de sus propias publicaciones.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirMiPublicacion()](../../../images/analisis/coordinador/abrirMiPublicacion-analisis.svg)|
|-|
|Código fuente: [abrirMiPublicacion.puml](../../../modelosUML/analisis/coordinador/abrirMiPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### MiPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirMiPublicacion(id)` desde `:MIS_PUBLICACIONES_ABIERTAS`
- Solicitar al controlador los datos de la publicación mediante `obtenerPublicacion(id) : Publicacion`
- Mostrar el detalle de la publicación al coordinador
- Ofrecer las opciones de editar publicación, eliminar publicación o volver al listado

**Colaboraciones**:
- **Entrada**: Desde `:MIS_PUBLICACIONES_ABIERTAS` con `abrirMiPublicacion(id)`
- **Control**: Se comunica con `PublicacionController` mediante `obtenerPublicacion(id) : Publicacion`
- **Salida**: Transita a `:MI_PUBLICACION_ABIERTA` (`publicacionMostrada()`), a `:Collaboration EditarMiPublicacion` (`editarMiPublicacion()`), a `:Collaboration EliminarMiPublicacion` (`eliminarMiPublicacion()`) o a `:MIS_PUBLICACIONES_ABIERTAS` (`abrirMisPublicaciones()`)

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir la petición `obtenerPublicacion(id)` desde la vista
- Delegar la recuperación de la publicación al repositorio mediante `obtenerPorId(id)`

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `MiPublicacionView`
- **Repositorio**: Delega el acceso a datos a `PublicacionRepository` mediante `obtenerPorId(id) : Publicacion`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar una publicación concreta por su identificador mediante `obtenerPorId(id) : Publicacion`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de una publicación del coordinador

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema llega al estado `:MIS_PUBLICACIONES_ABIERTAS`
2. El coordinador selecciona una publicación: `MiPublicacionView` recibe `abrirMiPublicacion(id)`
3. `MiPublicacionView` invoca `obtenerPublicacion(id)` en `PublicacionController`
4. `PublicacionController` delega en `PublicacionRepository.obtenerPorId(id)` y obtiene un objeto `Publicacion`
5. `MiPublicacionView` muestra el detalle → estado `:MI_PUBLICACION_ABIERTA` con `publicacionMostrada()`
6. Desde la vista el coordinador puede:
   - Editar la publicación → `:Collaboration EditarMiPublicacion` con `editarMiPublicacion()`
   - Eliminar la publicación → `:Collaboration EliminarMiPublicacion` con `eliminarMiPublicacion()`
   - Volver al listado → `:MIS_PUBLICACIONES_ABIERTAS` con `abrirMisPublicaciones()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle de una publicación propia|`MiPublicacionView`|`abrirMiPublicacion(id)`|
|Recuperar la publicación por id|`PublicacionController`|`obtenerPublicacion(id) : Publicacion`|
|Acceder a datos de la publicación|`PublicacionRepository`|`obtenerPorId(id) : Publicacion`|
|Navegar a editar publicación|`MiPublicacionView`|`editarMiPublicacion()`|
|Navegar a eliminar publicación|`MiPublicacionView`|`eliminarMiPublicacion()`|
|Volver al listado de mis publicaciones|`MiPublicacionView`|`abrirMisPublicaciones()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el coordinador
- **Control**: Solo coordinación y recuperación del objeto `Publicacion`
- **Entidad**: Solo datos y reglas de negocio de las publicaciones

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirMiPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`MiPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: abrirMiPublicacion()](../../../context/casosDeUso/detalle/coordinador/abrirMiPublicacion/abrirMiPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
