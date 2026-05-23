# FUNIBER GIPF > abrirInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirInvestigador()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el perfil completo de un investigador al Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirInvestigador()](/images/analisis/abrirInvestigador-analisis.svg)|
|-|
|Código fuente: [abrirInvestigador.puml](abrirInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### InvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el perfil completo del investigador al Coordinador
- Ofrecer acceso a las opciones de gestión del perfil
- Navegar de vuelta a la lista de investigadores

**Colaboraciones**:
- **Entrada**: Recibe `abrirInvestigador(id)` desde `:INVESTIGADORES_ABIERTOS`
- **Control**: Se comunica con `InvestigadorController`
- **Salida**: Navega a `:INVESTIGADOR_ABIERTO` y colaboración `AbrirOpcionesPerfil`

### clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del perfil completo del investigador
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `InvestigadorView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener un investigador por identificador

**Colaboraciones**:
- **Control**: Responde a `InvestigadorController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa del perfil de un investigador
- Encapsular atributos: nombre, apellidos, correo, área, institución, proyectos, publicaciones

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:INVESTIGADORES_ABIERTOS` → `InvestigadorView.abrirInvestigador(id)`
2. **Obtención**: `InvestigadorView` → `InvestigadorController.obtenerInvestigador(id)` : `Investigador`
3. **Acceso a datos**: `InvestigadorController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Presentación**: `InvestigadorView` → `:INVESTIGADOR_ABIERTO.investigadorMostrado()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar perfil del investigador|`InvestigadorView`|Coordina con `InvestigadorController.obtenerInvestigador(id)`|
|Datos del investigador|`Investigador`|Encapsula todos los atributos|
|Gestionar perfil|`InvestigadorView`|→ Colaboración `AbrirOpcionesPerfil`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y obtención del detalle
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirInvestigador()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`InvestigadorView`), lógica de aplicación (`InvestigadorController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: abrirInvestigador()](../../../context/casosDeUso/detalle/coordinador/abrirInvestigador/abrirInvestigador.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
