# FUNIBER GIPF > editarEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador modifique los datos de un entregable existente.

## diagrama de colaboración

<div align=center>

|![Análisis: editarEntregable()](../../../images/analisis/investigador/editarEntregable-analisis.svg)|
|-|
|Código fuente: [editarEntregable.puml](../../../modelosUML/analisis/investigador/editarEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición prellenado con los datos actuales del entregable
- Capturar los cambios introducidos por el Investigador
- Invocar el guardado en el controlador
- Navegar de vuelta al detalle del entregable

**Colaboraciones**:
- **Entrada**: Recibe `editarEntregable()` desde `:ENTREGABLE_ABIERTO`
- **Control**: Se comunica con `EntregableController`
- **Salida**: Navega a `:ENTREGABLE_ABIERTO`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga del entregable actual para prellenar el formulario
- Validar y persistir los cambios introducidos

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarEntregableView`
- **Repositorio**: Delega el acceso a datos a `EntregableRepository`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables
- Proporcionar métodos para obtener y actualizar un entregable

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información editable del entregable
- Encapsular atributos: título, descripción, fecha límite, estado, archivo adjunto

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:ENTREGABLE_ABIERTO` → `EditarEntregableView.editarEntregable()`
2. **Carga del entregable**: `EditarEntregableView` → `EntregableController.obtenerEntregable(id)` : `Entregable`
3. **Acceso a datos**: `EntregableController` → `EntregableRepository.obtenerPorId(id)` : `Entregable`
4. **Edición**: El Investigador modifica los campos del formulario
5. **Guardado**: `EditarEntregableView` → `EntregableController.guardarEntregable(datos)` : `Entregable`
6. **Persistencia**: `EntregableController` → `EntregableRepository.actualizar(entregable)` : `Entregable`
7. **Finalización**: `EditarEntregableView` → `:ENTREGABLE_ABIERTO.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Prellenar formulario|`EntregableController`|`obtenerEntregable(id)` → `EntregableRepository.obtenerPorId(id)`|
|Capturar cambios|`EditarEntregableView`|Formulario prellenado|
|Persistir cambios|`EntregableController`|`guardarEntregable(datos)` → `EntregableRepository.actualizar()`|
|Confirmar edición|`EditarEntregableView`|→ `:ENTREGABLE_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Investigador
- **Control**: Solo coordinación de la carga y persistencia
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
