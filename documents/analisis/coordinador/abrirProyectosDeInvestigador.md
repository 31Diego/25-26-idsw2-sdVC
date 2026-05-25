# FUNIBER GIPF > abrirProyectosDeInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-25
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirProyectosDeInvestigador(investigadorId)` mediante el patrón MVC. Este caso de uso surge del split de `abrirProyectos()`: cuando se invoca desde el perfil de un investigador concreto, el sistema debe listar únicamente los proyectos asociados a ese investigador, no todos los proyectos de la plataforma.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirProyectosDeInvestigador()](/images/analisis/abrirProyectosDeInvestigador-analisis.svg)|
|-|
|Código fuente: [abrirProyectosDeInvestigador.puml](abrirProyectosDeInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ProyectosInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de proyectos del investigador concreto al Coordinador
- Ofrecer acceso a un proyecto concreto
- Navegar de vuelta al perfil del investigador

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyectosDeInvestigador(investigadorId)` desde `:INVESTIGADOR_ABIERTO`
- **Control**: Se comunica con `ProyectosController`
- **Salida**: Navega a `:PROYECTOS_INVESTIGADOR_ABIERTOS` y colaboración `AbrirProyecto`

### clases de control

#### ProyectosController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de los proyectos filtrados por investigador
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ProyectosInvestigadorView`
- **Repositorio**: Delega el acceso a datos a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para obtener proyectos filtrados por investigador

**Colaboraciones**:
- **Control**: Responde a `ProyectosController`
- **Entidad**: Gestiona instancias de `Proyecto`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un proyecto
- Encapsular atributos: título, estado, coordinador, fechas, investigadores asociados

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:INVESTIGADOR_ABIERTO` → `ProyectosInvestigadorView.abrirProyectosDeInvestigador(investigadorId)`
2. **Listado filtrado**: `ProyectosInvestigadorView` → `ProyectosController.obtenerProyectosPorInvestigador(investigadorId)` : `List<Proyecto>`
3. **Acceso a datos**: `ProyectosController` → `ProyectoRepository.buscarPorInvestigador(investigadorId)` : `List<Proyecto>`
4. **Presentación**: `ProyectosInvestigadorView` → `:PROYECTOS_INVESTIGADOR_ABIERTOS.proyectosCargados()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Listar proyectos del investigador|`ProyectosInvestigadorView`|Coordina con `ProyectosController.obtenerProyectosPorInvestigador(investigadorId)`|
|Abrir proyecto concreto|`ProyectosInvestigadorView`|→ Colaboración `AbrirProyecto`|
|Volver al perfil del investigador|`ProyectosInvestigadorView`|→ `:INVESTIGADOR_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y filtrado de proyectos por investigador
- **Entidad**: Solo datos y reglas de negocio del proyecto

### distinción respecto a `abrirProyectos()`

Este caso de uso difiere de `abrirProyectos()` en el scope de los datos:
- `abrirProyectos()` → lista todos los proyectos de la plataforma (desde el panel principal)
- `abrirProyectosDeInvestigador(investigadorId)` → lista solo los proyectos del investigador indicado (desde su perfil)

## patrones aplicados

### repository pattern
`ProyectoRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ProyectosInvestigadorView`), lógica de aplicación (`ProyectosController`) y datos (`Proyecto`, `ProyectoRepository`).

## referencias

- [Análisis: abrirProyectos()](abrirProyectos.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
