# FUNIBER GIPF > abrirOpcionesCargaTrabajo > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirOpcionesCargaTrabajo()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar al Investigador un resumen de su carga de trabajo actual y ofrecer la opción de editarla.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirOpcionesCargaTrabajo()](../../../images/analisis/investigador/abrirOpcionesCargaTrabajo-analisis.svg)|
|-|
|Código fuente: [abrirOpcionesCargaTrabajo.puml](../../../modelosUML/analisis/investigador/abrirOpcionesCargaTrabajo.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CargaTrabajoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Mostrar el resumen de carga de trabajo actual del Investigador
- Ofrecer la opción de editar la carga de trabajo
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirOpcionesCargaTrabajo()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Control**: Se comunica con `CargaTrabajoController`
- **Salida**: Navega a colaboración `EditarCargaTrabajo` o regresa al panel

### clases de control

#### CargaTrabajoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del resumen de carga de trabajo del Investigador autenticado
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CargaTrabajoView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener investigador por identificador

**Colaboraciones**:
- **Control**: Responde a `CargaTrabajoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar al investigador incluyendo su carga de trabajo
- Encapsular atributos de disponibilidad y horas asignadas

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `CargaTrabajoView.abrirOpcionesCargaTrabajo()`
2. **Obtención de datos**: `CargaTrabajoView` → `CargaTrabajoController.obtenerResumenCargaTrabajo()` : `Investigador`
3. **Acceso a datos**: `CargaTrabajoController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Presentación**: `CargaTrabajoView` → `:OPCIONES_CARGA_TRABAJO_ABIERTAS.cargaTrabajoCargada()`
5. **Navegación**: El Investigador puede editar su carga de trabajo o volver al panel

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar resumen de carga de trabajo|`CargaTrabajoView`|Coordina con `CargaTrabajoController.obtenerResumenCargaTrabajo()`|
|Acceso a datos del investigador|`InvestigadorRepository`|`obtenerPorId(id)`|
|Editar carga de trabajo|`CargaTrabajoView`|→ Colaboración `EditarCargaTrabajo`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
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
