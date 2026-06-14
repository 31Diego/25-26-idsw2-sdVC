# FUNIBER GIPF > eliminarEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador elimine un entregable tras confirmar la acción.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarEntregable()](../../../images/analisis/investigador/eliminarEntregable-investigador-analisis.svg)|
|-|
|Código fuente: [eliminarEntregable.puml](../../../modelosUML/analisis/investigador/eliminarEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarEntregable()` desde `:ENTREGABLE_ABIERTO`
- Solicitar los datos del entregable para la confirmación mediante `cargarEntregableParaEliminacion(id) : Entregable`
- Solicitar la eliminación definitiva mediante `eliminarEntregable(id) : void`
- Mostrar la pantalla de confirmación al investigador
- Transitar a `:ENTREGABLES_ABIERTOS` al finalizar

**Colaboraciones**:
- **Entrada**: Desde `:ENTREGABLE_ABIERTO` con `eliminarEntregable()`
- **Control**: Se comunica con `EntregableController` mediante `cargarEntregableParaEliminacion(id)` y `eliminarEntregable(id)`
- **Salida**: Transita a `:ENTREGABLES_ABIERTOS` con `abrirEntregables()`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `cargarEntregableParaEliminacion(id)` y delegar en el repositorio la obtención del entregable
- Recibir `eliminarEntregable(id)` y delegar la eliminación en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarEntregableView`
- **Repositorio**: Delega en `EntregableRepository` mediante `obtenerPorId(id) : Entregable` y `eliminarPorId(id) : void`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar un entregable por id para la confirmación mediante `obtenerPorId(id) : Entregable`
- Eliminar definitivamente el entregable mediante `eliminarPorId(id) : void`

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del entregable a mostrar en la confirmación de eliminación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:ENTREGABLE_ABIERTO`
2. El investigador solicita eliminar: `EliminarEntregableView` recibe `eliminarEntregable()`
3. `EliminarEntregableView` invoca `cargarEntregableParaEliminacion(id) : Entregable` en `EntregableController`
4. `EntregableController` delega en `EntregableRepository.obtenerPorId(id)` y obtiene el `Entregable`
5. La vista muestra la pantalla de confirmación con los datos del entregable
6. El investigador confirma la eliminación
7. `EliminarEntregableView` invoca `eliminarEntregable(id) : void` en `EntregableController`
8. `EntregableController` delega en `EntregableRepository.eliminarPorId(id)`
9. La vista transita a `:ENTREGABLES_ABIERTOS` con `abrirEntregables()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cargar entregable para confirmación|`EntregableController`|`cargarEntregableParaEliminacion(id) : Entregable`|
|Acceder al entregable por id|`EntregableRepository`|`obtenerPorId(id) : Entregable`|
|Eliminar el entregable|`EntregableController`|`eliminarEntregable(id) : void`|
|Ejecutar eliminación en base de datos|`EntregableRepository`|`eliminarPorId(id) : void`|
|Volver al listado de entregables|`EliminarEntregableView`|`abrirEntregables()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de la confirmación e interacción con el investigador
- **Control**: Solo coordinación de la carga para confirmación y la eliminación
- **Entidad**: Solo datos y reglas de negocio del entregable

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `eliminarEntregable()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`EntregableRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EliminarEntregableView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

## referencias

- [Especificación detallada: eliminarEntregable()](../../../context/casosDeUso/detalle/investigador/eliminarEntregable/eliminarEntregable.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
