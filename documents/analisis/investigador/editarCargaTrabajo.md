# FUNIBER GIPF > editarCargaTrabajo > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarCargaTrabajo()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador actualice su disponibilidad y horas de trabajo.

## diagrama de colaboración

<div align=center>

|![Análisis: editarCargaTrabajo()](../../../images/analisis/investigador/editarCargaTrabajo-investigador-analisis.svg)|
|-|
|Código fuente: [editarCargaTrabajo.puml](../../../modelosUML/analisis/investigador/editarCargaTrabajo.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarCargaTrabajoView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarCargaTrabajo()` desde `:OPCIONES_CARGA_TRABAJO_ABIERTAS`
- Solicitar los datos actuales mediante `obtenerCargaTrabajo(id) : Investigador`
- Notificar los campos modificados mediante `modificarCampos(datos) : void`
- Solicitar el guardado mediante `guardarCargaTrabajo(datos) : Investigador`
- Mostrar el formulario de edición prellenado al investigador
- Transitar a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` o `:PANEL_PRINCIPAL_ABIERTO` al finalizar

**Colaboraciones**:
- **Entrada**: Desde `:OPCIONES_CARGA_TRABAJO_ABIERTAS` con `editarCargaTrabajo()`
- **Control**: Se comunica con `CargaTrabajoController` mediante `obtenerCargaTrabajo(id)`, `modificarCampos(datos)` y `guardarCargaTrabajo(datos)`
- **Salida**: Transita a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` (`edicionFinalizada()`) o `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### CargaTrabajoController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerCargaTrabajo(id)` y delegar en el repositorio la obtención del investigador
- Recibir `modificarCampos(datos)` y gestionar el estado de los campos modificados
- Recibir `guardarCargaTrabajo(datos)` y delegar la actualización en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarCargaTrabajoView`
- **Repositorio**: Delega en `InvestigadorRepository` mediante `obtenerPorId(id) : Investigador` y `actualizar(investigador) : Investigador`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar el investigador por id mediante `obtenerPorId(id) : Investigador`
- Actualizar el investigador con la nueva carga de trabajo mediante `actualizar(investigador) : Investigador`

**Colaboraciones**:
- **Control**: Responde a `CargaTrabajoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar al investigador incluyendo sus datos de carga de trabajo

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:OPCIONES_CARGA_TRABAJO_ABIERTAS`
2. El investigador solicita editar: `EditarCargaTrabajoView` recibe `editarCargaTrabajo()`
3. `EditarCargaTrabajoView` invoca `obtenerCargaTrabajo(id) : Investigador` en `CargaTrabajoController`
4. `CargaTrabajoController` delega en `InvestigadorRepository.obtenerPorId(id)` y obtiene el `Investigador`
5. El investigador modifica los campos del formulario
6. `EditarCargaTrabajoView` invoca `modificarCampos(datos) : void` en `CargaTrabajoController`
7. `EditarCargaTrabajoView` invoca `guardarCargaTrabajo(datos) : Investigador` en `CargaTrabajoController`
8. `CargaTrabajoController` delega en `InvestigadorRepository.actualizar(investigador)` y obtiene el `Investigador` actualizado
9. La vista transita a `:OPCIONES_CARGA_TRABAJO_ABIERTAS` con `edicionFinalizada()` o a `:PANEL_PRINCIPAL_ABIERTO`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener datos actuales para prellenar|`CargaTrabajoController`|`obtenerCargaTrabajo(id) : Investigador`|
|Acceder al investigador por id|`InvestigadorRepository`|`obtenerPorId(id) : Investigador`|
|Registrar campos modificados|`CargaTrabajoController`|`modificarCampos(datos) : void`|
|Guardar carga de trabajo actualizada|`CargaTrabajoController`|`guardarCargaTrabajo(datos) : Investigador`|
|Persistir la actualización|`InvestigadorRepository`|`actualizar(investigador) : Investigador`|
|Confirmar edición|`EditarCargaTrabajoView`|`edicionFinalizada()`|
|Volver al panel principal|`EditarCargaTrabajoView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el investigador
- **Control**: Solo coordinación de la carga, modificación y persistencia
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
