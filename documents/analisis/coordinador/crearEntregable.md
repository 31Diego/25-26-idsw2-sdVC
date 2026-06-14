# FUNIBER GIPF > crearEntregable > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearEntregable()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador registre un nuevo entregable en un proyecto.

## diagrama de colaboración

<div align=center>

|![Análisis: crearEntregable()](../../../images/analisis/coordinador/crearEntregable-analisis.svg)|
|-|
|Código fuente: [crearEntregable.puml](../../../modelosUML/analisis/coordinador/crearEntregable.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearEntregableView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `crearEntregable()` desde `:ENTREGABLES_ABIERTOS`
- Solicitar al controlador la validación de los datos introducidos mediante `validarDatos(datos) : boolean`
- Solicitar al controlador el guardado del nuevo entregable mediante `guardarEntregable(datos) : Entregable`
- Navegar de vuelta al listado de entregables tras la creación

**Colaboraciones**:
- **Entrada**: Desde `:ENTREGABLES_ABIERTOS` con `crearEntregable()`
- **Control**: Se comunica con `EntregableController` mediante `validarDatos(datos) : boolean` y `guardarEntregable(datos) : Entregable`
- **Salida**: Transita a `:ENTREGABLES_ABIERTOS` con `abrirEntregables()`

### clases de control

#### EntregableController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir y ejecutar `validarDatos(datos) : boolean`
- Recibir `guardarEntregable(datos)` y delegar la creación al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearEntregableView`
- **Repositorio**: Delega la persistencia a `EntregableRepository` mediante `crear(entregable) : Entregable`

### clases de entidad (entity)

#### EntregableRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Persistir un nuevo entregable mediante `crear(entregable) : Entregable`

**Colaboraciones**:
- **Control**: Responde a `EntregableController`
- **Entidad**: Gestiona instancias de `Entregable`

#### Entregable
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del nuevo entregable a crear

**Colaboraciones**:
- **Repositorio**: Es gestionado por `EntregableRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:ENTREGABLES_ABIERTOS`
2. El coordinador solicita crear entregable: `CrearEntregableView` recibe `crearEntregable()`
3. El coordinador rellena el formulario con los datos del entregable
4. `CrearEntregableView` invoca `validarDatos(datos)` en `EntregableController` → devuelve `boolean`
5. Si la validación es correcta, `CrearEntregableView` invoca `guardarEntregable(datos)` en `EntregableController`
6. `EntregableController` delega en `EntregableRepository.crear(entregable)` y obtiene el objeto `Entregable` creado
7. La vista navega de vuelta → `:ENTREGABLES_ABIERTOS` con `abrirEntregables()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de creación|`CrearEntregableView`|`crearEntregable()`|
|Validar datos del formulario|`EntregableController`|`validarDatos(datos) : boolean`|
|Persistir nuevo entregable|`EntregableController`|`guardarEntregable(datos) : Entregable`|
|Crear entregable en repositorio|`EntregableRepository`|`crear(entregable) : Entregable`|
|Volver al listado de entregables|`CrearEntregableView`|`abrirEntregables()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el coordinador
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

- [Especificación detallada: crearEntregable()](../../../context/casosDeUso/detalle/coordinador/crearEntregable/crearEntregable.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
