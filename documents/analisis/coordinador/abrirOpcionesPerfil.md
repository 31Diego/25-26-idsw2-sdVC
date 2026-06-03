# FUNIBER GIPF > abrirOpcionesPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirOpcionesPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para mostrar las opciones de gestión del perfil de un investigador al Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirOpcionesPerfil()](../../../images/analisis/coordinador/abrirOpcionesPerfil-analisis.svg)|
|-|
|Código fuente: [abrirOpcionesPerfil.puml](../../../modelosUML/analisis/coordinador/abrirOpcionesPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### OpcionesPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el resumen del perfil del investigador al Coordinador
- Ofrecer opciones: editar perfil, solicitar eliminación del perfil, volver
- Recuperar los datos del perfil a través del controlador

**Colaboraciones**:
- **Entrada**: Recibe `abrirOpcionesPerfil()` desde `:PANEL_PRINCIPAL_ABIERTO`, `:INVESTIGADOR_ABIERTO` o `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`
- **Control**: Se comunica con `PerfilController`
- **Salida**: Navega a `:OPCIONES_PERFIL_ABIERTO` o a colaboraciones de edición y eliminación

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de los datos del perfil del investigador
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `OpcionesPerfilView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener un investigador por identificador

**Colaboraciones**:
- **Control**: Responde a `PerfilController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de un investigador
- Encapsular atributos de perfil: nombre, apellidos, correo, área de investigación, institución

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: Desde múltiples estados → `OpcionesPerfilView.abrirOpcionesPerfil()`
2. **Obtención de perfil**: `OpcionesPerfilView` → `PerfilController.obtenerPerfil()` : `Investigador`
3. **Acceso a datos**: `PerfilController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Presentación**: `OpcionesPerfilView` → `:OPCIONES_PERFIL_ABIERTO.perfilMostrado()`
5. **Navegación**: El Coordinador puede editar perfil, solicitar eliminación o volver

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos del perfil|`OpcionesPerfilView`|Coordina con `PerfilController.obtenerPerfil()`|
|Datos del investigador|`Investigador`|Encapsula todos los atributos|
|Acceso a datos|`InvestigadorRepository`|`obtenerPorId(id)`|
|Editar perfil|`OpcionesPerfilView`|→ Colaboración `EditarPerfil`|
|Solicitar eliminación|`OpcionesPerfilView`|→ Colaboración `SolicitarEliminacionPerfil`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
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

- [Especificación detallada: abrirOpcionesPerfil()](../../../context/casosDeUso/detalle/coordinador/abrirOpcionesPerfil/abrirOpcionesPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
