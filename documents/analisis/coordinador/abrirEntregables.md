# FUNIBER GIPF > abrirEntregables > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirEntregables()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para listar los entregables asociados a un proyecto.

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
- Presentar la lista de entregables del proyecto al Coordinador
- Ofrecer acceso a un entregable concreto y a la creación de nuevos
- Navegar de vuelta al proyecto

**Colaboraciones**:
- **Entrada**: Recibe `abrirEntregables()` desde `:PROYECTO_ABIERTO`
- **Control**: Se comunica con `EntregableController`
- **Salida**: Navega a `:ENTREGABLES_ABIERTOS`, colaboraciones `AbrirEntregable` y `CrearEntregable`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de entregables del proyecto
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EntregablesView`
- **Repositorio**: Delega el acceso a datos a `EntregableRepository`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables
- Proporcionar método para obtener entregables por proyecto

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un entregable de proyecto
- Encapsular atributos: título, descripción, fecha límite, estado, documentos asociados

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PROYECTO_ABIERTO` → `EntregablesView.abrirEntregables()`
2. **Listado**: `EntregablesView` → `EntregableController.obtenerEntregables(idProyecto)` : `List<Entregable>`
3. **Acceso a datos**: `EntregableController` → `EntregableRepository.obtenerPorProyecto(idProyecto)` : `List<Entregable>`
4. **Presentación**: `EntregablesView` → `:ENTREGABLES_ABIERTOS.entregablesCargados()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Listar entregables del proyecto|`EntregablesView`|Coordina con `EntregableController.obtenerEntregables(idProyecto)`|
|Abrir entregable concreto|`EntregablesView`|→ Colaboración `AbrirEntregable`|
|Crear nuevo entregable|`EntregablesView`|→ Colaboración `CrearEntregable`|
|Volver al proyecto|`EntregablesView`|→ `:PROYECTO_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y acceso a datos
- **Entidad**: Solo datos y reglas de negocio del entregable

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
