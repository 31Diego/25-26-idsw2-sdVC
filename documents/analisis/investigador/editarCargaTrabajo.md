# FUNIBER GIPF > editarCargaTrabajo > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarCargaTrabajo()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador actualice su disponibilidad y horas de trabajo.

## diagrama de colaboración

<div align=center>

|![Análisis: editarCargaTrabajo()](../../../images/analisis/investigador/editarCargaTrabajo-analisis.svg)|
|-|
|Código fuente: [editarCargaTrabajo.puml](../../../modelosUML/analisis/investigador/editarCargaTrabajo.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarCargaTrabajoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición de carga de trabajo prellenado con los datos actuales
- Capturar los cambios introducidos por el Investigador
- Invocar el guardado en el controlador
- Navegar de vuelta a las opciones de carga de trabajo o al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `editarCargaTrabajo()` desde `:OPCIONES_CARGA_TRABAJO_ABIERTAS`
- **Control**: Se comunica con `CargaTrabajoController`
- **Salida**: Navega a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` o `:PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### CargaTrabajoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga de los datos actuales para prellenar el formulario
- Validar y persistir los cambios en la carga de trabajo

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarCargaTrabajoView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar métodos para obtener y actualizar el investigador

**Colaboraciones**:
- **Control**: Responde a `CargaTrabajoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar al investigador incluyendo su carga de trabajo editable
- Encapsular atributos de disponibilidad y horas asignadas

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:OPCIONES_CARGA_TRABAJO_ABIERTAS` → `EditarCargaTrabajoView.editarCargaTrabajo()`
2. **Carga de datos**: `EditarCargaTrabajoView` → `CargaTrabajoController.obtenerCargaTrabajo(id)` : `Investigador`
3. **Acceso a datos**: `CargaTrabajoController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Edición**: El Investigador modifica los campos del formulario
5. **Guardado**: `EditarCargaTrabajoView` → `CargaTrabajoController.guardarCargaTrabajo(datos)` : `Investigador`
6. **Persistencia**: `CargaTrabajoController` → `InvestigadorRepository.actualizar(investigador)` : `Investigador`
7. **Finalización**: `EditarCargaTrabajoView` → `:OPCIONES_CARGA_TRABAJO_ABIERTAS.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Prellenar formulario con datos actuales|`CargaTrabajoController`|`obtenerCargaTrabajo(id)` → `InvestigadorRepository.obtenerPorId(id)`|
|Capturar cambios|`EditarCargaTrabajoView`|Formulario de disponibilidad y horas|
|Persistir cambios|`CargaTrabajoController`|`guardarCargaTrabajo(datos)` → `InvestigadorRepository.actualizar()`|
|Confirmar edición|`EditarCargaTrabajoView`|→ `:OPCIONES_CARGA_TRABAJO_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Investigador
- **Control**: Solo coordinación de la carga y persistencia
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `editarCargaTrabajo()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EditarCargaTrabajoView`), lógica de aplicación (`CargaTrabajoController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: editarCargaTrabajo()](../../../context/casosDeUso/detalle/investigador/editarCargaTrabajo/editarCargaTrabajo.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
