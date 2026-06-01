# FUNIBER GIPF > eliminarProyecto > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarProyecto()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para eliminar un proyecto de investigación del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarProyecto()](../../../images/analisis/eliminarProyecto-analisis.svg)|
|-|
|Código fuente: [eliminarProyecto.puml](../../../modelosUML/analisis/coordinador/eliminarProyecto.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Mostrar confirmación de eliminación del proyecto al Coordinador
- Invocar la eliminación en el controlador tras confirmación
- Navegar a la lista de proyectos tras la eliminación

**Colaboraciones**:
- **Entrada**: Recibe `eliminarProyecto()` desde `:PROYECTO_ABIERTO`
- **Control**: Se comunica con `ProyectoController`
- **Salida**: Navega a `:PROYECTOS_ABIERTOS`

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de eliminación del proyecto
- Validar que la eliminación es posible
- Invocar la eliminación en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarProyectoView`
- **Repositorio**: Delega la eliminación a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para eliminar un proyecto por identificador

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del proyecto a eliminar
- Mantener la integridad referencial con entidades relacionadas

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PROYECTO_ABIERTO` → `EliminarProyectoView.eliminarProyecto()`
2. **Confirmación**: El Coordinador confirma la eliminación
3. **Eliminación**: `EliminarProyectoView` → `ProyectoController.eliminarProyecto(id)` : `void`
4. **Persistencia**: `ProyectoController` → `ProyectoRepository.eliminarPorId(id)` : `void`
5. **Finalización**: `EliminarProyectoView` → `:PROYECTOS_ABIERTOS.abrirProyectos()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Solicitar confirmación|`EliminarProyectoView`|Muestra diálogo de confirmación|
|Eliminar proyecto del sistema|`ProyectoController`|`eliminarProyecto(id)` → `ProyectoRepository.eliminarPorId()`|
|Navegar a lista de proyectos|`EliminarProyectoView`|→ `:PROYECTOS_ABIERTOS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de la confirmación e interacción con el Coordinador
- **Control**: Solo coordinación del proceso de eliminación
- **Entidad**: Solo datos y gestión de la persistencia

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `eliminarProyecto()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ProyectoRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EliminarProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

## referencias

- [Especificación detallada: eliminarProyecto()](../../../context/casosDeUso/detalle/coordinador/eliminarProyecto/eliminarProyecto.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
