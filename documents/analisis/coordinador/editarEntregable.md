# FUNIBER GIPF > editarEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para modificar los datos de un entregable existente.

## diagrama de colaboración

<div align=center>

|![Análisis: editarEntregable()](/images/analisis/editarEntregable-analisis.svg)|
|-|
|Código fuente: [editarEntregable.puml](editarEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición con los datos actuales del entregable
- Recuperar los datos actuales a través del controlador
- Capturar los cambios introducidos por el Coordinador
- Invocar el guardado en el controlador
- Navegar de vuelta al entregable tras la edición

**Colaboraciones**:
- **Entrada**: Recibe `editarEntregable()` desde `:ENTREGABLE_ABIERTO`
- **Control**: Se comunica con `EntregableController`
- **Salida**: Navega a `:ENTREGABLE_ABIERTO`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de los datos actuales del entregable
- Validar y persistir los datos modificados
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarEntregableView`
- **Repositorio**: Delega operaciones de datos a `EntregableRepository`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables
- Proporcionar método para obtener y actualizar un entregable

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de un entregable
- Encapsular atributos editables: título, descripción, fecha límite, estado

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:ENTREGABLE_ABIERTO` → `EditarEntregableView.editarEntregable()`
2. **Carga de datos**: `EditarEntregableView` → `EntregableController.obtenerEntregable(id)` : `Entregable`
3. **Acceso a datos**: `EntregableController` → `EntregableRepository.obtenerPorId(id)` : `Entregable`
4. **Edición**: El Coordinador modifica los datos del entregable
5. **Guardado**: `EditarEntregableView` → `EntregableController.guardarEntregable(datos)` : `Entregable`
6. **Persistencia**: `EntregableController` → `EntregableRepository.actualizar(entregable)` : `Entregable`
7. **Finalización**: `EditarEntregableView` → `:ENTREGABLE_ABIERTO.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos actuales|`EditarEntregableView`|Coordina con `EntregableController.obtenerEntregable(id)`|
|Modificar datos del entregable|`EditarEntregableView`|Captura cambios en el formulario|
|Persistir cambios|`EntregableController`|`guardarEntregable(datos)` → `EntregableRepository.actualizar()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la obtención y persistencia de datos
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

- [Especificación detallada: editarEntregable()](../../../context/casosDeUso/detalle/coordinador/editarEntregable/editarEntregable.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
