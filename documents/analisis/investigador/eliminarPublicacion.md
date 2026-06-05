# FUNIBER GIPF > eliminarPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador elimine una publicación propia tras confirmar la acción.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarPublicacion()](../../../images/analisis/investigador/eliminarPublicacion-analisis.svg)|
|-|
|Código fuente: [eliminarPublicacion.puml](../../../modelosUML/analisis/investigador/eliminarPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la pantalla de confirmación de eliminación con los datos de la publicación
- Capturar la confirmación del Investigador
- Invocar la eliminación en el controlador
- Navegar al listado de mis publicaciones tras la eliminación

**Colaboraciones**:
- **Entrada**: Recibe `eliminarPublicacion()` desde `:MI_PUBLICACION_ABIERTA`
- **Control**: Se comunica con `PublicacionController`
- **Salida**: Navega a `:MIS_PUBLICACIONES_ABIERTAS`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga de la publicación para mostrar en la confirmación
- Ejecutar la eliminación del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarPublicacionView`
- **Repositorio**: Delega la operación de datos a `PublicacionRepository`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones
- Proporcionar métodos para obtener y eliminar una publicación por identificador

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de la publicación a eliminar
- Encapsular atributos para mostrar en la pantalla de confirmación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:MI_PUBLICACION_ABIERTA` → `EliminarPublicacionView.eliminarPublicacion()`
2. **Carga para confirmación**: `EliminarPublicacionView` → `PublicacionController.cargarPublicacionParaEliminacion(id)` : `Publicacion`
3. **Acceso a datos**: `PublicacionController` → `PublicacionRepository.obtenerPorId(id)` : `Publicacion`
4. **Confirmación**: El Investigador confirma la eliminación
5. **Eliminación**: `EliminarPublicacionView` → `PublicacionController.eliminarPublicacion(id)` : `void`
6. **Persistencia**: `PublicacionController` → `PublicacionRepository.eliminarPorId(id)` : `void`
7. **Finalización**: `EliminarPublicacionView` → `:MIS_PUBLICACIONES_ABIERTAS.abrirMisPublicaciones()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar confirmación con datos|`PublicacionController`|`cargarPublicacionParaEliminacion(id)`|
|Ejecutar eliminación|`PublicacionController`|`eliminarPublicacion(id)` → `PublicacionRepository.eliminarPorId(id)`|
|Redirigir tras eliminación|`EliminarPublicacionView`|→ `:MIS_PUBLICACIONES_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de confirmación e interacción con el Investigador
- **Control**: Solo coordinación de la carga y eliminación
- **Entidad**: Solo datos y reglas de negocio de la publicación

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

- [Especificación detallada: eliminarPublicacion()](../../../context/casosDeUso/detalle/investigador/eliminarPublicacion/eliminarPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
