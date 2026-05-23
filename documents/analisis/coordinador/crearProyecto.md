# FUNIBER GIPF > crearProyecto > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearProyecto()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para registrar un nuevo proyecto de investigación en el sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: crearProyecto()](/images/analisis/crearProyecto-analisis.svg)|
|-|
|Código fuente: [crearProyecto.puml](crearProyecto.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearProyectoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de creación de proyecto al Coordinador
- Capturar los datos del nuevo proyecto
- Invocar el guardado en el controlador
- Navegar a la lista de proyectos o al panel principal tras la creación

**Colaboraciones**:
- **Entrada**: Recibe `crearProyecto()` desde `:PROYECTOS_ABIERTOS`
- **Control**: Se comunica con `ProyectoController`
- **Salida**: Navega a `:PROYECTOS_ABIERTOS` o `:PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de creación del nuevo proyecto
- Validar los datos recibidos del formulario
- Persistir el nuevo proyecto a través del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearProyectoView`
- **Repositorio**: Delega la persistencia a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para crear un nuevo proyecto

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un nuevo proyecto de investigación
- Encapsular atributos: título, descripción, estado inicial, fechas previstas

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PROYECTOS_ABIERTOS` → `CrearProyectoView.crearProyecto()`
2. **Captura de datos**: El Coordinador rellena el formulario con los datos del nuevo proyecto
3. **Guardado**: `CrearProyectoView` → `ProyectoController.guardarProyecto(datos)` : `Proyecto`
4. **Persistencia**: `ProyectoController` → `ProyectoRepository.crear(proyecto)` : `Proyecto`
5. **Finalización**: `CrearProyectoView` → `:PROYECTOS_ABIERTOS.abrirProyectos()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de creación|`CrearProyectoView`|Captura datos del nuevo proyecto|
|Persistir nuevo proyecto|`ProyectoController`|`guardarProyecto(datos)` → `ProyectoRepository.crear()`|
|Confirmar creación|`CrearProyectoView`|→ `:PROYECTOS_ABIERTOS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la validación y persistencia
- **Entidad**: Solo datos y reglas de negocio del proyecto

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `crearProyecto()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ProyectoRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`CrearProyectoView`), lógica de aplicación (`ProyectoController`) y datos (`Proyecto`, `ProyectoRepository`).

## referencias

- [Especificación detallada: crearProyecto()](../../../context/casosDeUso/detalle/coordinador/crearProyecto/crearProyecto.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
