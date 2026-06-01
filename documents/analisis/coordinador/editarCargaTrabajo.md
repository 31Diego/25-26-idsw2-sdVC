# FUNIBER GIPF > editarCargaTrabajo > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarCargaTrabajo()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para modificar la carga de trabajo asignada a un investigador.

## diagrama de colaboración

<div align=center>

|![Análisis: editarCargaTrabajo()](../../../images/analisis/editarCargaTrabajo-analisis.svg)|
|-|
|Código fuente: [editarCargaTrabajo.puml](../../../modelosUML/analisis/coordinador/editarCargaTrabajo.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarCargaTrabajoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición de carga de trabajo con los datos actuales del investigador
- Recuperar los datos actuales a través del controlador
- Capturar los cambios de dedicación introducidos por el Coordinador
- Invocar el guardado en el controlador
- Navegar de vuelta a la lista de carga de trabajo o al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `editarCargaTrabajo()` desde `:OPCIONES_CARGA_TRABAJO_ABIERTAS`
- **Control**: Se comunica con `CargaTrabajoController`
- **Salida**: Navega a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` o `:PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### CargaTrabajoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de la carga de trabajo actual del investigador
- Validar y persistir los datos de carga modificados
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarCargaTrabajoView`
- **Repositorio**: Delega operaciones de datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener un investigador por identificador
- Persistir los cambios en la carga de trabajo

**Colaboraciones**:
- **Control**: Responde a `CargaTrabajoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del investigador incluyendo su carga de trabajo
- Encapsular atributos de dedicación: horas semanales, proyectos activos, porcentaje de dedicación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:OPCIONES_CARGA_TRABAJO_ABIERTAS` → `EditarCargaTrabajoView.editarCargaTrabajo()`
2. **Carga de datos**: `EditarCargaTrabajoView` → `CargaTrabajoController.obtenerCargaTrabajo(id)` : `Investigador`
3. **Acceso a datos**: `CargaTrabajoController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Edición**: El Coordinador modifica los datos de carga de trabajo
5. **Guardado**: `EditarCargaTrabajoView` → `CargaTrabajoController.guardarCargaTrabajo(datos)` : `Investigador`
6. **Persistencia**: `CargaTrabajoController` → `InvestigadorRepository.actualizar(investigador)` : `Investigador`
7. **Finalización**: `EditarCargaTrabajoView` → `:OPCIONES_CARGA_TRABAJO_ABIERTAS.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos actuales de carga|`EditarCargaTrabajoView`|Coordina con `CargaTrabajoController.obtenerCargaTrabajo(id)`|
|Modificar dedicación del investigador|`EditarCargaTrabajoView`|Captura cambios en el formulario|
|Persistir cambios|`CargaTrabajoController`|`guardarCargaTrabajo(datos)` → `InvestigadorRepository.actualizar()`|
|Volver a la lista|`EditarCargaTrabajoView`|→ `:OPCIONES_CARGA_TRABAJO_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la obtención y persistencia de la carga de trabajo
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

- [Especificación detallada: editarCargaTrabajo()](../../../context/casosDeUso/detalle/coordinador/editarCargaTrabajo/editarCargaTrabajo.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
