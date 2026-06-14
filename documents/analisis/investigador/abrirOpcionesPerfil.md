# FUNIBER GIPF > abrirOpcionesPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirOpcionesPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador consulte su perfil y acceda a las opciones disponibles.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirOpcionesPerfil()](../../../images/analisis/investigador/abrirOpcionesPerfil-investigador-analisis.svg)|
|-|
|Código fuente: [abrirOpcionesPerfil.puml](../../../modelosUML/analisis/investigador/abrirOpcionesPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### OpcionesPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirOpcionesPerfil()` desde `:PANEL_PRINCIPAL_ABIERTO`
- Solicitar al controlador los datos del perfil mediante `obtenerPerfil() : Investigador`
- Mostrar el perfil del investigador con las opciones disponibles
- Ofrecer acceso a la edición del perfil, solicitud de eliminación y vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Desde `:PANEL_PRINCIPAL_ABIERTO` con `abrirOpcionesPerfil()`
- **Control**: Se comunica con `PerfilController` mediante `obtenerPerfil() : Investigador`
- **Salida**: Transita a `:OPCIONES_PERFIL_ABIERTO` (`perfilMostrado()`), `:Collaboration EditarPerfil` (`editarPerfil()`), `:Collaboration SolicitarEliminacion` (`solicitarEliminacionPerfil()`), `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerPerfil()` y delegar en el repositorio la obtención del investigador autenticado

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `OpcionesPerfilView`
- **Repositorio**: Delega en `InvestigadorRepository` mediante `obtenerPorId(id) : Investigador`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar el investigador autenticado por id mediante `obtenerPorId(id) : Investigador`

**Colaboraciones**:
- **Control**: Responde a `PerfilController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del perfil del investigador

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PANEL_PRINCIPAL_ABIERTO`
2. El investigador solicita ver su perfil: `OpcionesPerfilView` recibe `abrirOpcionesPerfil()`
3. `OpcionesPerfilView` invoca `obtenerPerfil()` en `PerfilController`
4. `PerfilController` delega en `InvestigadorRepository.obtenerPorId(id)` y obtiene un objeto `Investigador`
5. La vista muestra el perfil con opciones → transita a `:OPCIONES_PERFIL_ABIERTO` con `perfilMostrado()`
6. Desde `:OPCIONES_PERFIL_ABIERTO` el investigador puede navegar a `editarPerfil()`, `solicitarEliminacionPerfil()` o `abrirPanelPrincipal()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener datos del perfil|`PerfilController`|`obtenerPerfil() : Investigador`|
|Acceder al investigador por id|`InvestigadorRepository`|`obtenerPorId(id) : Investigador`|
|Mostrar perfil con opciones|`OpcionesPerfilView`|`perfilMostrado()`|
|Editar perfil|`OpcionesPerfilView`|`editarPerfil()`|
|Solicitar eliminación del perfil|`OpcionesPerfilView`|`solicitarEliminacionPerfil()`|
|Volver al panel principal|`OpcionesPerfilView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y obtención del perfil
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirOpcionesPerfil()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`OpcionesPerfilView`), lógica de aplicación (`PerfilController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: abrirOpcionesPerfil()](../../../context/casosDeUso/detalle/investigador/abrirOpcionesPerfil/abrirOpcionesPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
