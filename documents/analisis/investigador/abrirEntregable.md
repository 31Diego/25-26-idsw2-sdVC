# FUNIBER GIPF > abrirEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el detalle de un entregable al Investigador y ofrecer opciones de gestión.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirEntregable()](../../../images/analisis/investigador/abrirEntregable-analisis.svg)|
|-|
|Código fuente: [abrirEntregable.puml](../../../modelosUML/analisis/investigador/abrirEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el detalle del entregable al Investigador
- Mostrar información: título, descripción, fecha límite, estado, archivo adjunto
- Ofrecer opciones de gestión: editar y eliminar
- Navegar de vuelta a la lista de entregables

**Colaboraciones**:
- **Entrada**: Recibe `abrirEntregable(id)` desde `:ENTREGABLES_ABIERTOS`
- **Control**: Se comunica con `EntregableController`
- **Salida**: Navega a `:ENTREGABLE_ABIERTO` y colaboraciones de gestión

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del detalle del entregable
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EntregableView`
- **Repositorio**: Delega el acceso a datos a `EntregableRepository`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables
- Proporcionar método para obtener un entregable por identificador

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de un entregable
- Encapsular atributos: título, descripción, fecha límite, estado, archivo adjunto

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:ENTREGABLES_ABIERTOS` → `EntregableView.abrirEntregable(id)`
2. **Obtención de datos**: `EntregableView` → `EntregableController.obtenerEntregable(id)` : `Entregable`
3. **Acceso a datos**: `EntregableController` → `EntregableRepository.obtenerPorId(id)` : `Entregable`
4. **Presentación**: `EntregableView` → `:ENTREGABLE_ABIERTO.entregableMostrado()`
5. **Navegación**: El Investigador puede editar, eliminar o volver a entregables

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle del entregable|`EntregableView`|Coordina con `EntregableController.obtenerEntregable(id)`|
|Datos completos del entregable|`Entregable`|Encapsula todos los atributos|
|Acceso a datos|`EntregableRepository`|`obtenerPorId(id)`|
|Editar entregable|`EntregableView`|→ Colaboración `EditarEntregable`|
|Eliminar entregable|`EntregableView`|→ Colaboración `EliminarEntregable`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y obtención del detalle
- **Entidad**: Solo datos y reglas de negocio del entregable

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirEntregable()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`EntregableRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EntregableView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

## referencias

- [Especificación detallada: abrirEntregable()](../../../context/casosDeUso/detalle/investigador/abrirEntregable/abrirEntregable.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
