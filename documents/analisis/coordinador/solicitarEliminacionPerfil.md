# FUNIBER GIPF > solicitarEliminacionPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `solicitarEliminacionPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Coordinador solicite la eliminación del perfil de un investigador.

## diagrama de colaboración

<div align=center>

|![Análisis: solicitarEliminacionPerfil()](../../../images/analisis/solicitarEliminacionPerfil-analisis.svg)|
|-|
|Código fuente: [solicitarEliminacionPerfil.puml](../../../modelosUML/analisis/coordinador/solicitarEliminacionPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### SolicitarEliminacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de solicitud de eliminación de perfil al Coordinador
- Capturar los datos necesarios para la solicitud (motivo, identificación del perfil)
- Invocar el envío de la solicitud en el controlador
- Navegar de vuelta a las opciones de perfil

**Colaboraciones**:
- **Entrada**: Recibe `solicitarEliminacionPerfil()` desde `:OPCIONES_PERFIL_ABIERTO`
- **Control**: Se comunica con `EliminacionController`
- **Salida**: Navega a `:OPCIONES_PERFIL_ABIERTO`

### clases de control

#### EliminacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de creación de la solicitud de eliminación
- Validar los datos de la solicitud
- Persistir la solicitud a través del repositorio

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
- Representar la solicitud de eliminación de perfil
- Encapsular atributos: investigador solicitado, motivo, fecha, estado

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SolicitudEliminacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:OPCIONES_PERFIL_ABIERTO` → `SolicitarEliminacionView.solicitarEliminacionPerfil()`
2. **Captura**: El Coordinador rellena el formulario de solicitud
3. **Envío**: `SolicitarEliminacionView` → `EliminacionController.enviarSolicitud(datos)` : `SolicitudEliminacion`
4. **Persistencia**: `EliminacionController` → `SolicitudEliminacionRepository.crear(solicitud)` : `SolicitudEliminacion`
5. **Finalización**: `SolicitarEliminacionView` → `:OPCIONES_PERFIL_ABIERTO.abrirOpcionesPerfil()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de solicitud|`SolicitarEliminacionView`|Captura datos de la solicitud|
|Registrar solicitud|`EliminacionController`|`enviarSolicitud(datos)` → `SolicitudEliminacionRepository.crear()`|
|Confirmar solicitud|`SolicitarEliminacionView`|→ `:OPCIONES_PERFIL_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la validación y registro de la solicitud
- **Entidad**: Solo datos y reglas de negocio de la solicitud

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

- [Especificación detallada: solicitarEliminacionPerfil()](../../../context/casosDeUso/detalle/coordinador/solicitarEliminacionPerfil/solicitarEliminacionPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
