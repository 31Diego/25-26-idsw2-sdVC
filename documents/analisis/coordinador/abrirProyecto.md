# FUNIBER GIPF > abrirProyecto > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirProyecto()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el detalle completo de un proyecto al Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirProyecto()](../../../images/analisis/coordinador/abrirProyecto-analisis.svg)|
|-|
|Código fuente: [abrirProyecto.puml](../../../modelosUML/analisis/coordinador/abrirProyecto.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el detalle completo del proyecto al Coordinador
- Mostrar información del proyecto: título, descripción, estado, fechas, investigadores, entregables
- Ofrecer opciones de gestión: editar, eliminar, gestionar entregables, agregar investigador
- Navegar de vuelta a la lista de proyectos

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyecto(id)` desde `:PROYECTOS_ABIERTOS`
- **Control**: Se comunica con `ProyectoController`
- **Salida**: Navega a `:PROYECTO_ABIERTO` y a colaboraciones de gestión

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del detalle completo del proyecto
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ProyectoView`
- **Repositorio**: Delega el acceso a datos a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para obtener un proyecto por identificador

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de un proyecto de investigación
- Encapsular atributos: título, descripción, estado, fechas de inicio y fin, investigadores, entregables

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PROYECTOS_ABIERTOS` → `ProyectoView.abrirProyecto(id)`
2. **Obtención de datos**: `ProyectoView` → `ProyectoController.obtenerProyecto(id)` : `Proyecto`
3. **Acceso a datos**: `ProyectoController` → `ProyectoRepository.obtenerPorId(id)` : `Proyecto`
4. **Presentación**: `ProyectoView` → `:PROYECTO_ABIERTO.proyectoMostrado()`
5. **Navegación**: El Coordinador puede editar, eliminar, gestionar entregables o agregar investigadores

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle del proyecto|`ProyectoView`|Coordina con `ProyectoController.obtenerProyecto(id)`|
|Datos completos del proyecto|`Proyecto`|Encapsula todos los atributos|
|Acceso a datos|`ProyectoRepository`|`obtenerPorId(id)`|
|Editar proyecto|`ProyectoView`|→ Colaboración `EditarProyecto`|
|Eliminar proyecto|`ProyectoView`|→ Colaboración `EliminarProyecto`|
|Gestionar entregables|`ProyectoView`|→ Colaboración `AbrirEntregables`|
|Agregar investigador|`ProyectoView`|→ Colaboración `AgregarInvestigador`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y obtención del detalle
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

- [Especificación detallada: abrirProyecto()](../../../context/casosDeUso/detalle/coordinador/abrirProyecto/abrirProyecto.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
