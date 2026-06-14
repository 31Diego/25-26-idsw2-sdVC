# FUNIBER GIPF > editarEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador modifique los datos de un entregable existente.

## diagrama de colaboración

<div align=center>

|![Análisis: editarEntregable()](../../../images/analisis/investigador/editarEntregable-investigador-analisis.svg)|
|-|
|Código fuente: [editarEntregable.puml](../../../modelosUML/analisis/investigador/editarEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarEntregable()` desde `:ENTREGABLE_ABIERTO`
- Solicitar los datos actuales mediante `obtenerEntregable(id) : Entregable`
- Notificar los campos modificados mediante `modificarCampos(datos) : void`
- Solicitar el guardado mediante `guardarEntregable(datos) : Entregable`
- Mostrar el formulario de edición prellenado al investigador
- Transitar a `:ENTREGABLE_ABIERTO` al finalizar

**Colaboraciones**:
- **Entrada**: Desde `:ENTREGABLE_ABIERTO` con `editarEntregable()`
- **Control**: Se comunica con `EntregableController` mediante `obtenerEntregable(id)`, `modificarCampos(datos)` y `guardarEntregable(datos)`
- **Salida**: Transita a `:ENTREGABLE_ABIERTO` con `edicionFinalizada()`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerEntregable(id)` y delegar en el repositorio la obtención del entregable
- Recibir `modificarCampos(datos)` y gestionar el estado de los campos modificados
- Recibir `guardarEntregable(datos)` y delegar la actualización en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarEntregableView`
- **Repositorio**: Delega en `EntregableRepository` mediante `obtenerPorId(id) : Entregable` y `actualizar(entregable) : Entregable`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar un entregable por id mediante `obtenerPorId(id) : Entregable`
- Actualizar el entregable modificado mediante `actualizar(entregable) : Entregable`

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos editables del entregable

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:ENTREGABLE_ABIERTO`
2. El investigador solicita editar: `EditarEntregableView` recibe `editarEntregable()`
3. `EditarEntregableView` invoca `obtenerEntregable(id) : Entregable` en `EntregableController`
4. `EntregableController` delega en `EntregableRepository.obtenerPorId(id)` y obtiene el `Entregable`
5. El investigador modifica los campos del formulario
6. `EditarEntregableView` invoca `modificarCampos(datos) : void` en `EntregableController`
7. `EditarEntregableView` invoca `guardarEntregable(datos) : Entregable` en `EntregableController`
8. `EntregableController` delega en `EntregableRepository.actualizar(entregable)` y obtiene el `Entregable` actualizado
9. La vista transita a `:ENTREGABLE_ABIERTO` con `edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener datos actuales para prellenar|`EntregableController`|`obtenerEntregable(id) : Entregable`|
|Acceder al entregable por id|`EntregableRepository`|`obtenerPorId(id) : Entregable`|
|Registrar campos modificados|`EntregableController`|`modificarCampos(datos) : void`|
|Guardar entregable actualizado|`EntregableController`|`guardarEntregable(datos) : Entregable`|
|Persistir la actualización|`EntregableRepository`|`actualizar(entregable) : Entregable`|
|Confirmar edición|`EditarEntregableView`|`edicionFinalizada()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el investigador
- **Control**: Solo coordinación de la carga, modificación y persistencia
- **Entidad**: Solo datos y reglas de negocio del entregable

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `editarEntregable()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`EntregableRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EditarEntregableView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

## referencias

- [Especificación detallada: editarEntregable()](../../../context/casosDeUso/detalle/investigador/editarEntregable/editarEntregable.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
