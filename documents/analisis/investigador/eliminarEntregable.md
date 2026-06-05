# FUNIBER GIPF > eliminarEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador elimine un entregable tras confirmar la acción.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarEntregable()](../../../images/analisis/investigador/eliminarEntregable-analisis.svg)|
|-|
|Código fuente: [eliminarEntregable.puml](../../../modelosUML/analisis/investigador/eliminarEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la pantalla de confirmación de eliminación con los datos del entregable
- Capturar la confirmación del Investigador
- Invocar la eliminación en el controlador
- Navegar a la lista de entregables tras la eliminación

**Colaboraciones**:
- **Entrada**: Recibe `eliminarEntregable()` desde `:ENTREGABLE_ABIERTO`
- **Control**: Se comunica con `EntregableController`
- **Salida**: Navega a `:ENTREGABLES_ABIERTOS`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga del entregable para mostrar en la confirmación
- Ejecutar la eliminación del entregable del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarEntregableView`
- **Repositorio**: Delega la operación de datos a `EntregableRepository`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables
- Proporcionar métodos para obtener y eliminar un entregable por identificador

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del entregable a eliminar
- Encapsular atributos para mostrar en la pantalla de confirmación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:ENTREGABLE_ABIERTO` → `EliminarEntregableView.eliminarEntregable()`
2. **Carga para confirmación**: `EliminarEntregableView` → `EntregableController.cargarEntregableParaEliminacion(id)` : `Entregable`
3. **Acceso a datos**: `EntregableController` → `EntregableRepository.obtenerPorId(id)` : `Entregable`
4. **Confirmación**: El Investigador confirma la eliminación
5. **Eliminación**: `EliminarEntregableView` → `EntregableController.eliminarEntregable(id)` : `void`
6. **Persistencia**: `EntregableController` → `EntregableRepository.eliminarPorId(id)` : `void`
7. **Finalización**: `EliminarEntregableView` → `:ENTREGABLES_ABIERTOS.abrirEntregables()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar confirmación con datos del entregable|`EntregableController`|`cargarEntregableParaEliminacion(id)`|
|Ejecutar eliminación|`EntregableController`|`eliminarEntregable(id)` → `EntregableRepository.eliminarPorId(id)`|
|Redirigir tras eliminación|`EliminarEntregableView`|→ `:ENTREGABLES_ABIERTOS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de confirmación e interacción con el Investigador
- **Control**: Solo coordinación de la carga y eliminación
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
