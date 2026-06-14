# FUNIBER GIPF > abrirOpcionesCargaTrabajo > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirOpcionesCargaTrabajo()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador consulte su resumen de carga de trabajo y acceda a la edición.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirOpcionesCargaTrabajo()](../../../images/analisis/investigador/abrirOpcionesCargaTrabajo-investigador-analisis.svg)|
|-|
|Código fuente: [abrirOpcionesCargaTrabajo.puml](../../../modelosUML/analisis/investigador/abrirOpcionesCargaTrabajo.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CargaTrabajoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirOpcionesCargaTrabajo()` desde `:PANEL_PRINCIPAL_ABIERTO`
- Solicitar al controlador el resumen de carga de trabajo mediante `obtenerResumenCargaTrabajo() : Investigador`
- Mostrar el resumen de carga de trabajo al investigador
- Ofrecer acceso a la colaboración de edición y vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Desde `:PANEL_PRINCIPAL_ABIERTO` con `abrirOpcionesCargaTrabajo()`
- **Control**: Se comunica con `CargaTrabajoController` mediante `obtenerResumenCargaTrabajo() : Investigador`
- **Salida**: Transita a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` (`cargaTrabajoCargada()`), `:Collaboration EditarCargaTrabajo` (`editarCargaTrabajo()`), `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### CargaTrabajoController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerResumenCargaTrabajo()` y delegar en el repositorio la obtención del investigador

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CargaTrabajoView`
- **Repositorio**: Delega en `InvestigadorRepository` mediante `obtenerPorId(id) : Investigador`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar el investigador autenticado por id mediante `obtenerPorId(id) : Investigador`

**Colaboraciones**:
- **Control**: Responde a `CargaTrabajoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del investigador incluyendo su carga de trabajo

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PANEL_PRINCIPAL_ABIERTO`
2. El investigador solicita ver su carga de trabajo: `CargaTrabajoView` recibe `abrirOpcionesCargaTrabajo()`
3. `CargaTrabajoView` invoca `obtenerResumenCargaTrabajo()` en `CargaTrabajoController`
4. `CargaTrabajoController` delega en `InvestigadorRepository.obtenerPorId(id)` y obtiene un objeto `Investigador`
5. La vista muestra el resumen → transita a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` con `cargaTrabajoCargada()`
6. Desde `:OPCIONES_CARGA_TRABAJO_ABIERTAS` el investigador puede navegar a `editarCargaTrabajo()` o `abrirPanelPrincipal()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener resumen de carga de trabajo|`CargaTrabajoController`|`obtenerResumenCargaTrabajo() : Investigador`|
|Acceder al investigador por id|`InvestigadorRepository`|`obtenerPorId(id) : Investigador`|
|Mostrar resumen de carga de trabajo|`CargaTrabajoView`|`cargaTrabajoCargada()`|
|Editar carga de trabajo|`CargaTrabajoView`|`editarCargaTrabajo()`|
|Volver al panel principal|`CargaTrabajoView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y obtención del resumen
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirOpcionesCargaTrabajo()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`CargaTrabajoView`), lógica de aplicación (`CargaTrabajoController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: abrirOpcionesCargaTrabajo()](../../../context/casosDeUso/detalle/investigador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
