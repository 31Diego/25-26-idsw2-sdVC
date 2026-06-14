# FUNIBER GIPF > solicitarEliminacionPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `solicitarEliminacionPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador registre una solicitud de eliminación de su cuenta.

## diagrama de colaboración

<div align=center>

|![Análisis: solicitarEliminacionPerfil()](../../../images/analisis/investigador/solicitarEliminacionPerfil-investigador-analisis.svg)|
|-|
|Código fuente: [solicitarEliminacionPerfil.puml](../../../modelosUML/analisis/investigador/solicitarEliminacionPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### SolicitarEliminacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `solicitarEliminacionPerfil()` desde `:OPCIONES_PERFIL_ABIERTO`
- Solicitar el envío de la solicitud de eliminación mediante `enviarSolicitud(datos) : SolicitudEliminacion`
- Mostrar el formulario de solicitud al investigador
- Transitar a `:OPCIONES_PERFIL_ABIERTO` al finalizar

**Colaboraciones**:
- **Entrada**: Desde `:OPCIONES_PERFIL_ABIERTO` con `solicitarEliminacionPerfil()`
- **Control**: Se comunica con `EliminacionController` mediante `enviarSolicitud(datos) : SolicitudEliminacion`
- **Salida**: Transita a `:OPCIONES_PERFIL_ABIERTO` con `abrirOpcionesPerfil()`

### clases de control

#### EliminacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `enviarSolicitud(datos)` y delegar la persistencia en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `SolicitarEliminacionView`
- **Repositorio**: Delega en `SolicitudEliminacionRepository` mediante `crear(solicitud) : SolicitudEliminacion`

### clases de entidad (entity)

#### SolicitudEliminacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Persistir una nueva solicitud de eliminación mediante `crear(solicitud) : SolicitudEliminacion`

**Colaboraciones**:
- **Control**: Responde a `EliminacionController`
- **Entidad**: Gestiona instancias de `SolicitudEliminacion`

#### SolicitudEliminacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de la solicitud de eliminación del perfil del investigador

**Colaboraciones**:
- **Repositorio**: Es gestionado por `SolicitudEliminacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:OPCIONES_PERFIL_ABIERTO`
2. El investigador solicita la eliminación de su perfil: `SolicitarEliminacionView` recibe `solicitarEliminacionPerfil()`
3. El investigador confirma la solicitud en el formulario
4. `SolicitarEliminacionView` invoca `enviarSolicitud(datos) : SolicitudEliminacion` en `EliminacionController`
5. `EliminacionController` delega en `SolicitudEliminacionRepository.crear(solicitud)` y obtiene la `SolicitudEliminacion` persistida
6. La vista transita a `:OPCIONES_PERFIL_ABIERTO` con `abrirOpcionesPerfil()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Enviar solicitud de eliminación|`EliminacionController`|`enviarSolicitud(datos) : SolicitudEliminacion`|
|Persistir la solicitud|`SolicitudEliminacionRepository`|`crear(solicitud) : SolicitudEliminacion`|
|Volver a opciones de perfil|`SolicitarEliminacionView`|`abrirOpcionesPerfil()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el investigador
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
