# FUNIBER GIPF > abrirOpcionesPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirOpcionesPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar al Investigador las opciones disponibles sobre su propio perfil.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirOpcionesPerfil()](../../../images/analisis/investigador/abrirOpcionesPerfil-analisis.svg)|
|-|
|Código fuente: [abrirOpcionesPerfil.puml](../../../modelosUML/analisis/investigador/abrirOpcionesPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### OpcionesPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Mostrar los datos actuales del perfil del Investigador
- Ofrecer opciones: editar perfil y solicitar eliminación del perfil
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirOpcionesPerfil()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Control**: Se comunica con `PerfilController`
- **Salida**: Navega a colaboraciones `EditarPerfil` y `SolicitarEliminacion`, o regresa al panel

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del perfil del Investigador autenticado
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `OpcionesPerfilView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener investigador por identificador

**Colaboraciones**:
- **Control**: Responde a `PerfilController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del perfil del Investigador
- Encapsular atributos: nombre, email, username, carga de trabajo

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `OpcionesPerfilView.abrirOpcionesPerfil()`
2. **Obtención de datos**: `OpcionesPerfilView` → `PerfilController.obtenerPerfil()` : `Investigador`
3. **Acceso a datos**: `PerfilController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Presentación**: `OpcionesPerfilView` → `:OPCIONES_PERFIL_ABIERTO.perfilMostrado()`
5. **Navegación**: El Investigador puede editar su perfil o solicitar su eliminación

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos del perfil|`OpcionesPerfilView`|Coordina con `PerfilController.obtenerPerfil()`|
|Acceso a datos del perfil|`InvestigadorRepository`|`obtenerPorId(id)`|
|Editar perfil|`OpcionesPerfilView`|→ Colaboración `EditarPerfil`|
|Solicitar eliminación|`OpcionesPerfilView`|→ Colaboración `SolicitarEliminacion`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y obtención del perfil
- **Entidad**: Solo datos y reglas de negocio del perfil

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
