# FUNIBER GIPF > abrirEntregables > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirEntregables()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador consulte el listado de entregables asociados a un proyecto.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirEntregables()](../../../images/analisis/coordinador/abrirEntregables-analisis.svg)|
|-|
|Código fuente: [abrirEntregables.puml](../../../modelosUML/analisis/coordinador/abrirEntregables.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EntregablesView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirEntregables()` desde `:PROYECTO_ABIERTO` o `:ENTREGABLE_ABIERTO`
- Solicitar al controlador el listado de entregables del proyecto mediante `obtenerEntregables(idProyecto) : List<Entregable>`
- Mostrar el listado al coordinador
- Ofrecer navegación a un entregable concreto, crear entregable o volver al proyecto

**Colaboraciones**:
- **Entrada**: Desde `:PROYECTO_ABIERTO` o `:ENTREGABLE_ABIERTO` con `abrirEntregables()`
- **Control**: Se comunica con `EntregableController` mediante `obtenerEntregables(idProyecto) : List<Entregable>`
- **Salida**: Transita a `:ENTREGABLES_ABIERTOS` (`entregablesCargados()`), a `:Collaboration AbrirEntregable` (`abrirEntregable(id)`), a `:Collaboration CrearEntregable` (`crearEntregable()`) o a `:PROYECTO_ABIERTO` (`abrirProyecto(id)`)

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir la petición `obtenerEntregables(idProyecto)` y devolver la lista de entregables del proyecto

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EntregablesView`
- **Repositorio**: Delega el acceso a datos a `EntregableRepository` mediante `obtenerPorProyecto(idProyecto) : List<Entregable>`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar todos los entregables de un proyecto mediante `obtenerPorProyecto(idProyecto) : List<Entregable>`

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de un entregable asociado a un proyecto

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PROYECTO_ABIERTO` o en `:ENTREGABLE_ABIERTO`
2. El coordinador solicita ver entregables: `EntregablesView` recibe `abrirEntregables()`
3. `EntregablesView` invoca `obtenerEntregables(idProyecto)` en `EntregableController`
4. `EntregableController` delega en `EntregableRepository.obtenerPorProyecto(idProyecto)` y obtiene `List<Entregable>`
5. El listado se muestra → estado `:ENTREGABLES_ABIERTOS` con `entregablesCargados()`
6. Desde la vista el coordinador puede:
   - Abrir un entregable → `:Collaboration AbrirEntregable` con `abrirEntregable(id)`
   - Crear un nuevo entregable → `:Collaboration CrearEntregable` con `crearEntregable()`
   - Volver al proyecto → `:PROYECTO_ABIERTO` con `abrirProyecto(id)`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar listado de entregables del proyecto|`EntregablesView`|`obtenerEntregables(idProyecto) : List<Entregable>`|
|Recuperar entregables por proyecto|`EntregableController`|`obtenerEntregables(idProyecto) : List<Entregable>`|
|Acceder a datos de entregables|`EntregableRepository`|`obtenerPorProyecto(idProyecto) : List<Entregable>`|
|Navegar al detalle de un entregable|`EntregablesView`|`abrirEntregable(id)`|
|Navegar a crear entregable|`EntregablesView`|`crearEntregable()`|
|Volver al proyecto|`EntregablesView`|`abrirProyecto(id)`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el coordinador
- **Control**: Solo coordinación y recuperación del listado de entregables por proyecto
- **Entidad**: Solo datos y reglas de negocio de los entregables

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirEntregables()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`EntregableRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EntregablesView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

## referencias

- [Especificación detallada: abrirEntregables()](../../../context/casosDeUso/detalle/coordinador/abrirEntregables/abrirEntregables.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
