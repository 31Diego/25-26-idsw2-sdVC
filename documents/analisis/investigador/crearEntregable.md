# FUNIBER GIPF > crearEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador registre un nuevo entregable en un proyecto.

## diagrama de colaboración

<div align=center>

|![Análisis: crearEntregable()](../../../images/analisis/investigador/crearEntregable-analisis.svg)|
|-|
|Código fuente: [crearEntregable.puml](../../../modelosUML/analisis/investigador/crearEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de creación de entregable al Investigador
- Capturar los datos del nuevo entregable (título, descripción, fecha límite, archivo)
- Invocar el guardado en el controlador
- Navegar a la lista de entregables tras la creación

**Colaboraciones**:
- **Entrada**: Recibe `crearEntregable()` desde `:ENTREGABLES_ABIERTOS`
- **Control**: Se comunica con `EntregableController`
- **Salida**: Navega a `:ENTREGABLES_ABIERTOS`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de creación del nuevo entregable
- Validar los datos recibidos del formulario
- Persistir el nuevo entregable a través del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearEntregableView`
- **Repositorio**: Delega la persistencia a `EntregableRepository`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de entregables
- Proporcionar método para crear un nuevo entregable

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del nuevo entregable
- Encapsular atributos: título, descripción, fecha límite, estado inicial, archivo adjunto

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:ENTREGABLES_ABIERTOS` → `CrearEntregableView.crearEntregable()`
2. **Captura de datos**: El Investigador rellena el formulario
3. **Validación**: `CrearEntregableView` → `EntregableController.validarDatos(datos)` : `boolean`
4. **Guardado**: `CrearEntregableView` → `EntregableController.guardarEntregable(datos)` : `Entregable`
5. **Persistencia**: `EntregableController` → `EntregableRepository.crear(entregable)` : `Entregable`
6. **Finalización**: `CrearEntregableView` → `:ENTREGABLES_ABIERTOS.abrirEntregables()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario|`CrearEntregableView`|Captura datos del nuevo entregable|
|Validar datos|`EntregableController`|`validarDatos(datos)`|
|Persistir nuevo entregable|`EntregableController`|`guardarEntregable(datos)` → `EntregableRepository.crear()`|
|Confirmar creación|`CrearEntregableView`|→ `:ENTREGABLES_ABIERTOS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Investigador
- **Control**: Solo coordinación de la validación y persistencia
- **Entidad**: Solo datos y reglas de negocio del entregable

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `crearEntregable()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`EntregableRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`CrearEntregableView`), lógica de aplicación (`EntregableController`) y datos (`Entregable`, `EntregableRepository`).

## referencias

- [Especificación detallada: crearEntregable()](../../../context/casosDeUso/detalle/investigador/crearEntregable/crearEntregable.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
