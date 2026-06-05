# FUNIBER GIPF > abrirInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirInvestigador()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el perfil de un investigador al Investigador en modo consulta.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirInvestigador()](../../../images/analisis/investigador/abrirInvestigador-analisis.svg)|
|-|
|Código fuente: [abrirInvestigador.puml](../../../modelosUML/analisis/investigador/abrirInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### InvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el perfil completo de un investigador en modo consulta
- Mostrar información: nombre, email, rol, carga de trabajo
- Navegar de vuelta al directorio de investigadores

**Colaboraciones**:
- **Entrada**: Recibe `abrirInvestigador(id)` desde `:INVESTIGADORES_ABIERTOS`
- **Control**: Se comunica con `InvestigadorController`
- **Salida**: Navega a `:INVESTIGADOR_ABIERTO` y `:INVESTIGADORES_ABIERTOS`

### clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del perfil del investigador solicitado
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
- Encapsular atributos: nombre, email, rol, carga de trabajo

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:INVESTIGADORES_ABIERTOS` → `InvestigadorView.abrirInvestigador(id)`
2. **Obtención de datos**: `InvestigadorView` → `InvestigadorController.obtenerInvestigador(id)` : `Investigador`
3. **Acceso a datos**: `InvestigadorController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Presentación**: `InvestigadorView` → `:INVESTIGADOR_ABIERTO.investigadorMostrado()`
5. **Navegación**: El Investigador puede volver al directorio

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar perfil del investigador|`InvestigadorView`|Coordina con `InvestigadorController.obtenerInvestigador(id)`|
|Datos completos del perfil|`Investigador`|Encapsula todos los atributos|
|Acceso a datos|`InvestigadorRepository`|`obtenerPorId(id)`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y obtención del perfil
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

- [Especificación detallada: abrirInvestigador()](../../../context/casosDeUso/detalle/investigador/abrirInvestigador/abrirInvestigador.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
