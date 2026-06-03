# FUNIBER GIPF > abrirSolicitudEliminacionPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirSolicitudEliminacionPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para revisar el detalle de una solicitud de eliminación de perfil y gestionar su resolución.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirSolicitudEliminacionPerfil()](../../../images/analisis/coordinador/abrirSolicitudEliminacionPerfil-analisis.svg)|
|-|
|Código fuente: [abrirSolicitudEliminacionPerfil.puml](../../../modelosUML/analisis/coordinador/abrirSolicitudEliminacionPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### SolicitudEliminacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el detalle completo de la solicitud de eliminación al Coordinador
- Mostrar el perfil del investigador implicado
- Ofrecer opciones de resolución: acceder al perfil o volver a la lista
- Navegar entre solicitudes y perfiles de investigador

**Colaboraciones**:
- **Entrada**: Recibe `abrirSolicitudEliminacionPerfil(id)` desde `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`
- **Control**: Se comunica con `EliminacionController`
- **Salida**: Navega a `:SOLICITUD_ELIMINACION_PERFIL_ABIERTA`, colaboración `AbrirOpcionesPerfil` y vuelve a la lista

### clases de control

#### EliminacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del detalle de la solicitud de eliminación
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `SolicitudEliminacionView`
- **Repositorio**: Delega el acceso a datos a `SolicitudEliminacionRepository`

### clases de entidad (entity)

#### SolicitudEliminacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de solicitudes de eliminación
- Proporcionar método para obtener una solicitud por identificador

**Colaboraciones**:
- **Control**: Responde a `EliminacionController`
- **Entidad**: Gestiona instancias de `SolicitudEliminacion`

#### SolicitudEliminacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de una solicitud de eliminación de perfil
- Encapsular atributos: investigador implicado, motivo, fecha de solicitud, estado actual

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SolicitudEliminacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS` → `SolicitudEliminacionView.abrirSolicitudEliminacionPerfil(id)`
2. **Obtención**: `SolicitudEliminacionView` → `EliminacionController.obtenerSolicitud(id)` : `SolicitudEliminacion`
3. **Acceso a datos**: `EliminacionController` → `SolicitudEliminacionRepository.obtenerPorId(id)` : `SolicitudEliminacion`
4. **Presentación**: `SolicitudEliminacionView` → `:SOLICITUD_ELIMINACION_PERFIL_ABIERTA.solicitudMostrada()`
5. **Navegación**: El Coordinador puede acceder al perfil del investigador para resolver la solicitud

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle de la solicitud|`SolicitudEliminacionView`|Coordina con `EliminacionController.obtenerSolicitud(id)`|
|Datos de la solicitud|`SolicitudEliminacion`|Encapsula todos los atributos|
|Acceder al perfil del investigador|`SolicitudEliminacionView`|→ Colaboración `AbrirOpcionesPerfil`|
|Volver a la lista|`SolicitudEliminacionView`|→ `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y obtención del detalle
- **Entidad**: Solo datos y reglas de negocio de la solicitud

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirSolicitudEliminacionPerfil()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`SolicitudEliminacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`SolicitudEliminacionView`), lógica de aplicación (`EliminacionController`) y datos (`SolicitudEliminacion`, `SolicitudEliminacionRepository`).

## referencias

- [Especificación detallada: abrirSolicitudEliminacionPerfil()](../../../context/casosDeUso/detalle/coordinador/abrirSolicitudEliminacionPerfil/abrirSolicitudEliminacionPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
