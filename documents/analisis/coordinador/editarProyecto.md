# FUNIBER GIPF > editarProyecto > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarProyecto()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para modificar los datos de un proyecto de investigación existente.

## diagrama de colaboración

<div align=center>

|![Análisis: editarProyecto()](/images/analisis/editarProyecto-analisis.svg)|
|-|
|Código fuente: [editarProyecto.puml](editarProyecto.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición con los datos actuales del proyecto
- Recuperar los datos actuales a través del controlador
- Capturar los cambios introducidos por el Coordinador
- Invocar el guardado en el controlador
- Navegar de vuelta al proyecto tras la edición

**Colaboraciones**:
- **Entrada**: Recibe `editarProyecto()` desde `:PROYECTO_ABIERTO`
- **Control**: Se comunica con `ProyectoController`
- **Salida**: Navega a `:PROYECTO_ABIERTO`

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de los datos actuales del proyecto
- Validar y persistir los datos modificados
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarProyectoView`
- **Repositorio**: Delega operaciones de datos a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para obtener un proyecto por identificador
- Persistir los cambios en el proyecto

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de un proyecto de investigación
- Encapsular atributos editables: título, descripción, estado, fechas

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PROYECTO_ABIERTO` → `EditarProyectoView.editarProyecto()`
2. **Carga de datos**: `EditarProyectoView` → `ProyectoController.obtenerProyecto(id)` : `Proyecto`
3. **Acceso a datos**: `ProyectoController` → `ProyectoRepository.obtenerPorId(id)` : `Proyecto`
4. **Edición**: El Coordinador modifica los datos del proyecto
5. **Guardado**: `EditarProyectoView` → `ProyectoController.guardarProyecto(datos)` : `Proyecto`
6. **Persistencia**: `ProyectoController` → `ProyectoRepository.actualizar(proyecto)` : `Proyecto`
7. **Finalización**: `EditarProyectoView` → `:PROYECTO_ABIERTO.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos actuales del proyecto|`EditarProyectoView`|Coordina con `ProyectoController.obtenerProyecto(id)`|
|Modificar datos del proyecto|`EditarProyectoView`|Captura cambios en el formulario|
|Persistir cambios|`ProyectoController`|`guardarProyecto(datos)` → `ProyectoRepository.actualizar()`|
|Volver al proyecto|`EditarProyectoView`|→ `:PROYECTO_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la obtención y persistencia de datos
- **Entidad**: Solo datos y reglas de negocio del proyecto

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `editarProyecto()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ProyectoRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EditarProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

## referencias

- [Especificación detallada: editarProyecto()](../../../context/casosDeUso/detalle/coordinador/editarProyecto/editarProyecto.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
