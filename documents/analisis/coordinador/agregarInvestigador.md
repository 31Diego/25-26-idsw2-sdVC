# FUNIBER GIPF > agregarInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `agregarInvestigador()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para incorporar un investigador existente a un proyecto de investigación.

## diagrama de colaboración

<div align=center>

|![Análisis: agregarInvestigador()](/images/analisis/agregarInvestigador-analisis.svg)|
|-|
|Código fuente: [agregarInvestigador.puml](agregarInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### AgregarInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de investigadores disponibles al Coordinador
- Permitir seleccionar el investigador a agregar al proyecto
- Invocar la asociación en el controlador
- Navegar de vuelta al proyecto tras la operación

**Colaboraciones**:
- **Entrada**: Recibe `agregarInvestigador()` desde `:PROYECTO_ABIERTO`
- **Control**: Se comunica con `ProyectoController`
- **Salida**: Navega a `:PROYECTO_ABIERTO`

### clases de control

#### ProyectoController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de investigadores disponibles para el proyecto
- Gestionar la asociación entre investigador y proyecto
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `AgregarInvestigadorView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar lista de todos los investigadores disponibles

**Colaboraciones**:
- **Control**: Responde a `ProyectoController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del investigador a agregar al proyecto
- Encapsular atributos: nombre, área, institución

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PROYECTO_ABIERTO` → `AgregarInvestigadorView.agregarInvestigador()`
2. **Obtención de disponibles**: `AgregarInvestigadorView` → `ProyectoController.obtenerInvestigadoresDisponibles()` : `List<Investigador>`
3. **Acceso a datos**: `ProyectoController` → `InvestigadorRepository.obtenerTodos()` : `List<Investigador>`
4. **Selección**: El Coordinador selecciona el investigador a agregar
5. **Asociación**: `AgregarInvestigadorView` → `ProyectoController.agregarInvestigador(idProyecto, idInvestigador)` : `void`
6. **Finalización**: `AgregarInvestigadorView` → `:PROYECTO_ABIERTO.investigadorAgregado()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Listar investigadores disponibles|`AgregarInvestigadorView`|Coordina con `ProyectoController.obtenerInvestigadoresDisponibles()`|
|Seleccionar investigador|`AgregarInvestigadorView`|Captura selección del Coordinador|
|Asociar al proyecto|`ProyectoController`|`agregarInvestigador(idProyecto, idInvestigador)`|
|Confirmar adición|`AgregarInvestigadorView`|→ `:PROYECTO_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación de la lógica de asociación
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `agregarInvestigador()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`AgregarInvestigadorView`), lógica de aplicación (`ProyectoController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: agregarInvestigador()](../../../context/casosDeUso/detalle/coordinador/agregarInvestigador/agregarInvestigador.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
