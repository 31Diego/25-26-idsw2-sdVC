# FUNIBER GIPF > solicitarEliminacionPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `solicitarEliminacionPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador registre una solicitud de eliminación de su cuenta.

## diagrama de colaboración

<div align=center>

|![Análisis: solicitarEliminacionPerfil()](../../../images/analisis/investigador/solicitarEliminacionPerfil-analisis.svg)|
|-|
|Código fuente: [solicitarEliminacionPerfil.puml](../../../modelosUML/analisis/investigador/solicitarEliminacionPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### SolicitarEliminacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de solicitud de eliminación al Investigador
- Capturar la confirmación y motivo de la solicitud
- Invocar el envío en el controlador
- Regresar a las opciones del perfil tras enviar la solicitud

**Colaboraciones**:
- **Entrada**: Recibe `solicitarEliminacionPerfil()` desde `:OPCIONES_PERFIL_ABIERTO`
- **Control**: Se comunica con `EliminacionController`
- **Salida**: Navega a `:OPCIONES_PERFIL_ABIERTO`

### clases de control

#### EliminacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de registro de la solicitud de eliminación
- Persistir la solicitud con los datos del Investigador autenticado

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `SolicitarEliminacionView`
- **Repositorio**: Delega la persistencia a `SolicitudEliminacionRepository`

### clases de entidad (entity)

#### SolicitudEliminacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de solicitudes de eliminación
- Proporcionar método para crear una nueva solicitud

**Colaboraciones**:
- **Control**: Responde a `EliminacionController`
- **Entidad**: Gestiona instancias de `SolicitudEliminacion`

#### SolicitudEliminacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la solicitud de eliminación de un perfil
- Encapsular atributos: investigador solicitante, fecha, estado

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SolicitudEliminacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:OPCIONES_PERFIL_ABIERTO` → `SolicitarEliminacionView.solicitarEliminacionPerfil()`
2. **Captura**: El Investigador confirma su solicitud
3. **Envío**: `SolicitarEliminacionView` → `EliminacionController.enviarSolicitud(datos)` : `SolicitudEliminacion`
4. **Persistencia**: `EliminacionController` → `SolicitudEliminacionRepository.crear(solicitud)` : `SolicitudEliminacion`
5. **Finalización**: `SolicitarEliminacionView` → `:OPCIONES_PERFIL_ABIERTO.abrirOpcionesPerfil()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de solicitud|`SolicitarEliminacionView`|Captura confirmación del Investigador|
|Persistir solicitud|`EliminacionController`|`enviarSolicitud(datos)` → `SolicitudEliminacionRepository.crear()`|
|Confirmar envío|`SolicitarEliminacionView`|→ `:OPCIONES_PERFIL_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Investigador
- **Control**: Solo coordinación del guardado de la solicitud
- **Entidad**: Solo datos y reglas de negocio de la solicitud de eliminación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `solicitarEliminacionPerfil()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`SolicitudEliminacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`SolicitarEliminacionView`), lógica de aplicación (`EliminacionController`) y datos (`SolicitudEliminacion`, `SolicitudEliminacionRepository`).

## referencias

- [Especificación detallada: solicitarEliminacionPerfil()](../../../context/casosDeUso/detalle/investigador/solicitarEliminacionPerfil/solicitarEliminacionPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
