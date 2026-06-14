# FUNIBER GIPF > abrirProyecto > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirProyecto()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador consulte el detalle de un proyecto.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirProyecto()](../../../images/analisis/investigador/abrirProyecto-investigador-analisis.svg)|
|-|
|Código fuente: [abrirProyecto.puml](../../../modelosUML/analisis/investigador/abrirProyecto.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirProyecto(id)` desde `:PROYECTOS_ABIERTOS` o `:ENTREGABLES_ABIERTOS`
- Solicitar al controlador los datos del proyecto mediante `obtenerProyecto(id) : Proyecto`
- Mostrar el detalle del proyecto al investigador en modo consulta
- Ofrecer acceso a los entregables, a la lista de investigadores y vuelta a proyectos

**Colaboraciones**:
- **Entrada**: Desde `:PROYECTOS_ABIERTOS` o `:ENTREGABLES_ABIERTOS` con `abrirProyecto(id)`
- **Control**: Se comunica con `ProyectoController` mediante `obtenerProyecto(id) : Proyecto`
- **Salida**: Transita a `:PROYECTO_ABIERTO` (`proyectoMostrado()`), `:Collaboration AbrirEntregables` (`abrirEntregables()`), `:Collaboration AbrirInvestigadores` (`abrirInvestigadores()`), `:PROYECTOS_ABIERTOS` (`abrirProyectos()`)

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerProyecto(id)` y delegar en el repositorio la obtención del proyecto

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ProyectoView`
- **Repositorio**: Delega en `ProyectoRepository` mediante `obtenerPorId(id) : Proyecto`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar un proyecto por id mediante `obtenerPorId(id) : Proyecto`

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos completos del proyecto a mostrar

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PROYECTOS_ABIERTOS` o `:ENTREGABLES_ABIERTOS`
2. El investigador selecciona un proyecto: `ProyectoView` recibe `abrirProyecto(id)`
3. `ProyectoView` invoca `obtenerProyecto(id)` en `ProyectoController`
4. `ProyectoController` delega en `ProyectoRepository.obtenerPorId(id)` y obtiene un objeto `Proyecto`
5. La vista muestra el detalle → transita a `:PROYECTO_ABIERTO` con `proyectoMostrado()`
6. Desde `:PROYECTO_ABIERTO` el investigador puede navegar a `abrirEntregables()`, `abrirInvestigadores()` o `abrirProyectos()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener datos del proyecto|`ProyectoController`|`obtenerProyecto(id) : Proyecto`|
|Acceder al proyecto por id|`ProyectoRepository`|`obtenerPorId(id) : Proyecto`|
|Mostrar detalle del proyecto|`ProyectoView`|`proyectoMostrado()`|
|Acceder a los entregables|`ProyectoView`|`abrirEntregables()`|
|Acceder a los investigadores del proyecto|`ProyectoView`|`abrirInvestigadores()`|
|Volver al listado de proyectos|`ProyectoView`|`abrirProyectos()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y obtención del proyecto
- **Entidad**: Solo datos y reglas de negocio del proyecto

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirProyecto()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ProyectoRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

## referencias

- [Especificación detallada: abrirProyecto()](../../../context/casosDeUso/detalle/investigador/abrirProyecto/abrirProyecto.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
