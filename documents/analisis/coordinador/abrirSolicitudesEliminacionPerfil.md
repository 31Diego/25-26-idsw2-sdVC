# FUNIBER GIPF > abrirSolicitudesEliminacionPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirSolicitudesEliminacionPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador consulte el listado de solicitudes de eliminación de perfil pendientes.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirSolicitudesEliminacionPerfil()](../../../images/analisis/coordinador/abrirSolicitudesEliminacionPerfil-analisis.svg)|
|-|
|Código fuente: [abrirSolicitudesEliminacionPerfil.puml](../../../modelosUML/analisis/coordinador/abrirSolicitudesEliminacionPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### SolicitudesEliminacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirSolicitudesEliminacionPerfil()` desde `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` o `:SOLICITUD_ELIMINACION_PERFIL_ABIERTA`
- Solicitar al controlador el listado de solicitudes mediante `obtenerSolicitudes() : List<SolicitudEliminacion>`
- Mostrar el listado al coordinador
- Ofrecer navegación a una solicitud concreta o volver a las opciones de perfil del investigador

**Colaboraciones**:
- **Entrada**: Desde `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` o `:SOLICITUD_ELIMINACION_PERFIL_ABIERTA` con `abrirSolicitudesEliminacionPerfil()`
- **Control**: Se comunica con `EliminacionController` mediante `obtenerSolicitudes() : List<SolicitudEliminacion>`
- **Salida**: Transita a `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS` (`solicitudesCargadas()`), a `:Collaboration AbrirSolicitudEliminacionPerfil` (`abrirSolicitudEliminacionPerfil(id)`) o a `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` (`abrirOpcionesPerfilInvestigador(id)`)

### clases de control

#### EliminacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir la petición `obtenerSolicitudes()` y devolver la lista completa

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `SolicitudesEliminacionView`
- **Repositorio**: Delega el acceso a datos a `SolicitudEliminacionRepository` mediante `obtenerTodos() : List<SolicitudEliminacion>`

### clases de entidad (entity)

#### SolicitudEliminacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar todas las solicitudes de eliminación mediante `obtenerTodos() : List<SolicitudEliminacion>`

**Colaboraciones**:
- **Control**: Responde a `EliminacionController`
- **Entidad**: Gestiona instancias de `SolicitudEliminacion`

#### SolicitudEliminacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de una solicitud de eliminación de perfil

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SolicitudEliminacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` o en `:SOLICITUD_ELIMINACION_PERFIL_ABIERTA`
2. El coordinador solicita ver el listado: `SolicitudesEliminacionView` recibe `abrirSolicitudesEliminacionPerfil()`
3. `SolicitudesEliminacionView` invoca `obtenerSolicitudes()` en `EliminacionController`
4. `EliminacionController` delega en `SolicitudEliminacionRepository.obtenerTodos()` y obtiene `List<SolicitudEliminacion>`
5. El listado se muestra → estado `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS` con `solicitudesCargadas()`
6. Desde la vista el coordinador puede:
   - Abrir una solicitud → `:Collaboration AbrirSolicitudEliminacionPerfil` con `abrirSolicitudEliminacionPerfil(id)`
   - Volver a opciones de perfil → `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` con `abrirOpcionesPerfilInvestigador(id)`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar listado de solicitudes de eliminación|`SolicitudesEliminacionView`|`obtenerSolicitudes() : List<SolicitudEliminacion>`|
|Recuperar todas las solicitudes|`EliminacionController`|`obtenerSolicitudes() : List<SolicitudEliminacion>`|
|Acceder a datos de solicitudes|`SolicitudEliminacionRepository`|`obtenerTodos() : List<SolicitudEliminacion>`|
|Navegar al detalle de una solicitud|`SolicitudesEliminacionView`|`abrirSolicitudEliminacionPerfil(id)`|
|Volver a opciones de perfil del investigador|`SolicitudesEliminacionView`|`abrirOpcionesPerfilInvestigador(id)`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el coordinador
- **Control**: Solo coordinación y recuperación del listado de solicitudes
- **Entidad**: Solo datos y reglas de negocio de las solicitudes de eliminación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirSolicitudesEliminacionPerfil()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`SolicitudEliminacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`SolicitudesEliminacionView`), lógica de aplicación (`EliminacionController`) y datos (`SolicitudEliminacion`, `SolicitudEliminacionRepository`).

## referencias

- [Especificación detallada: abrirSolicitudesEliminacionPerfil()](../../../context/casosDeUso/detalle/coordinador/abrirSolicitudesEliminacionPerfil/abrirSolicitudesEliminacionPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
