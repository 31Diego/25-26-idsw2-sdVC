# FUNIBER GIPF > eliminarInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarInvestigador()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador desvincule un investigador de un proyecto.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarInvestigador()](../../../images/analisis/coordinador/eliminarInvestigador-analisis.svg)|
|-|
|Código fuente: [eliminarInvestigador.puml](../../../modelosUML/analisis/coordinador/eliminarInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarInvestigador()` desde `:PROYECTO_ABIERTO`
- Solicitar al controlador los datos del investigador a desvincular mediante `cargarInvestigadorParaEliminacion(idInvestigador) : Investigador`
- Mostrar la pantalla de confirmación con los datos del investigador
- Solicitar al controlador la desvinculación definitiva mediante `eliminarInvestigador(idProyecto, idInvestigador) : void`
- Navegar de vuelta al proyecto tras la desvinculación

**Colaboraciones**:
- **Entrada**: Desde `:PROYECTO_ABIERTO` con `eliminarInvestigador()`
- **Control**: Se comunica con `ProyectoController` mediante `cargarInvestigadorParaEliminacion(idInvestigador)` y `eliminarInvestigador(idProyecto, idInvestigador)`
- **Salida**: Transita a `:PROYECTO_ABIERTO` con `investigadorEliminado()`

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `cargarInvestigadorParaEliminacion(idInvestigador)` y delegar en `InvestigadorRepository` la obtención del investigador
- Recibir `eliminarInvestigador(idProyecto, idInvestigador)` y delegar la desvinculación a `ProyectoRepository`

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarInvestigadorView`
- **Repositorio investigador**: Delega en `InvestigadorRepository` mediante `obtenerPorId(idInvestigador) : Investigador`
- **Repositorio proyecto**: Delega en `ProyectoRepository` mediante `eliminarInvestigador(idProyecto, idInvestigador) : void`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar un investigador por id mediante `obtenerPorId(idInvestigador) : Investigador`

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del investigador mostrados en la confirmación de desvinculación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Gestionar la desvinculación del investigador del proyecto mediante `eliminarInvestigador(idProyecto, idInvestigador) : void`

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar el proyecto del que se desvincula el investigador

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PROYECTO_ABIERTO`
2. El coordinador solicita desvincular investigador: `EliminarInvestigadorView` recibe `eliminarInvestigador()`
3. `EliminarInvestigadorView` invoca `cargarInvestigadorParaEliminacion(idInvestigador)` en `ProyectoController`
4. `ProyectoController` delega en `InvestigadorRepository.obtenerPorId(idInvestigador)` y obtiene un objeto `Investigador`
5. La pantalla de confirmación se muestra con los datos del investigador
6. El coordinador confirma: `EliminarInvestigadorView` invoca `eliminarInvestigador(idProyecto, idInvestigador) : void` en `ProyectoController`
7. `ProyectoController` delega en `ProyectoRepository.eliminarInvestigador(idProyecto, idInvestigador)`
8. La vista navega de vuelta → `:PROYECTO_ABIERTO` con `investigadorEliminado()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cargar datos del investigador para confirmación|`ProyectoController`|`cargarInvestigadorParaEliminacion(idInvestigador) : Investigador`|
|Acceder al investigador por id|`InvestigadorRepository`|`obtenerPorId(idInvestigador) : Investigador`|
|Desvincular investigador del proyecto|`ProyectoController`|`eliminarInvestigador(idProyecto, idInvestigador) : void`|
|Persistir la desvinculación|`ProyectoRepository`|`eliminarInvestigador(idProyecto, idInvestigador) : void`|
|Volver al proyecto|`EliminarInvestigadorView`|`investigadorEliminado()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de la confirmación e interacción con el coordinador
- **Control**: Solo coordinación del proceso de desvinculación
- **Entidad**: Solo datos y gestión de la persistencia del investigador y del proyecto

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `eliminarInvestigador()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` y `ProyectoRepository` abstraen el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EliminarInvestigadorView`), lógica de aplicación (`ProyectoController`) y datos (`Investigador`, `InvestigadorRepository`, `Proyecto`, `ProyectoRepository`).

## referencias

- [Especificación detallada: eliminarInvestigador()](../../../context/casosDeUso/detalle/coordinador/eliminarInvestigador/eliminarInvestigador.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
