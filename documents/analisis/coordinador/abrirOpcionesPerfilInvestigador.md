# FUNIBER GIPF > abrirOpcionesPerfilInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirOpcionesPerfilInvestigador(investigadorId)` mediante el patrón MVC. Este caso de uso surge del split de `abrirOpcionesPerfil()`: cuando el Coordinador accede a las opciones de perfil desde el perfil de un investigador concreto o desde una solicitud de eliminación, las opciones disponibles (editar perfil del investigador, gestionar solicitudes de eliminación) son distintas a las del perfil propio del Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirOpcionesPerfilInvestigador()](../../../images/analisis/coordinador/abrirOpcionesPerfilInvestigador-analisis.svg)|
|-|
|Código fuente: [abrirOpcionesPerfilInvestigador.puml](../../../modelosUML/analisis/coordinador/abrirOpcionesPerfilInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### OpcionesPerfilInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el resumen del perfil del investigador concreto al Coordinador
- Ofrecer opciones: editar perfil del investigador, gestionar solicitudes de eliminación, volver
- Recuperar los datos del perfil a través del controlador

**Colaboraciones**:
- **Entrada**: Recibe `abrirOpcionesPerfilInvestigador(investigadorId)` desde `:INVESTIGADOR_ABIERTO` o `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS`
- **Control**: Se comunica con `PerfilController`
- **Salida**: Navega a `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO` o a colaboraciones de edición y gestión

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de los datos del perfil del investigador indicado
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `OpcionesPerfilInvestigadorView`
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

1. **Inicio**: `:INVESTIGADOR_ABIERTO` o `:SOLICITUDES_ELIMINACION_PERFIL_ABIERTAS` → `OpcionesPerfilInvestigadorView.abrirOpcionesPerfilInvestigador(investigadorId)`
2. **Obtención de perfil**: `OpcionesPerfilInvestigadorView` → `PerfilController.obtenerPerfil(investigadorId)` : `Investigador`
3. **Acceso a datos**: `PerfilController` → `InvestigadorRepository.obtenerPorId(investigadorId)` : `Investigador`
4. **Presentación**: `OpcionesPerfilInvestigadorView` → `:OPCIONES_PERFIL_INVESTIGADOR_ABIERTO.perfilMostrado()`
5. **Navegación**: El Coordinador puede editar el perfil del investigador, gestionar solicitudes de eliminación o volver

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos del perfil del investigador|`OpcionesPerfilInvestigadorView`|Coordina con `PerfilController.obtenerPerfil(investigadorId)`|
|Datos del investigador|`Investigador`|Encapsula todos los atributos|
|Editar perfil del investigador|`OpcionesPerfilInvestigadorView`|→ Colaboración `EditarPerfil`|
|Gestionar solicitudes de eliminación|`OpcionesPerfilInvestigadorView`|→ Colaboración `AbrirSolicitudesEliminacionPerfil`|
|Volver al perfil del investigador|`OpcionesPerfilInvestigadorView`|→ `:INVESTIGADOR_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y obtención del perfil del investigador
- **Entidad**: Solo datos y reglas de negocio del investigador

### distinción respecto a `abrirOpcionesPerfil()`

Este caso de uso difiere de `abrirOpcionesPerfil()` en el sujeto del perfil:
- `abrirOpcionesPerfil()` → opciones sobre el perfil propio del Coordinador (desde el panel principal)
- `abrirOpcionesPerfilInvestigador(investigadorId)` → opciones sobre el perfil de un investigador concreto (desde su perfil o desde solicitudes de eliminación)

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`OpcionesPerfilInvestigadorView`), lógica de aplicación (`PerfilController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Análisis: abrirOpcionesPerfil()](abrirOpcionesPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
