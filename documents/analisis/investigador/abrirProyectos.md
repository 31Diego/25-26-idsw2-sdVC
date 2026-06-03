# FUNIBER GIPF > abrirProyectos > Análisis (Investigador)

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Actor**: Investigador
- **Versión**: 1.0
- **Fecha**: 2026-06-03
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirProyectos()` del Investigador mediante el patrón MVC. A diferencia del Coordinador, el Investigador solo accede a los proyectos en los que participa como miembro, no a todos los proyectos del sistema. Tampoco dispone de la opción de crear proyectos.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirProyectos() — Investigador](../../../images/analisis/investigador/abrirProyectos-investigador-analisis.svg)|
|-|
|Código fuente: [abrirProyectos.puml](../../../modelosUML/analisis/investigador/abrirProyectos.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ProyectosView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar al Investigador la lista de sus proyectos propios
- Permitir filtrar los proyectos por criterios de búsqueda
- Ofrecer acceso a un proyecto concreto
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyectos()` desde `:PANEL_PRINCIPAL_ABIERTO` o `:PROYECTO_ABIERTO`
- **Control**: Se comunica con `ProyectosController`
- **Salida**: Navega a `:PROYECTOS_ABIERTOS` y a la colaboración `AbrirProyecto`

### clases de control

#### ProyectosController
**Estereotipo**: Control  
**Responsabilidades**:
- Recuperar la identidad del Investigador autenticado
- Coordinar la obtención de proyectos filtrados por membresía del investigador
- Gestionar la lógica de filtrado por criterios de texto sobre los proyectos propios

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ProyectosView`
- **Repositorio**: Delega el acceso a datos a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para obtener los proyectos en los que participa un investigador concreto
- Implementar búsqueda filtrada por criterios sobre los proyectos del investigador

**Colaboraciones**:
- **Control**: Responde a `ProyectosController`
- **Entidad**: Gestiona instancias de `Proyecto` con su relación hacia `Investigador`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un proyecto de investigación
- Encapsular atributos: título, estado, fechas
- Mantener la relación con los investigadores participantes

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`
- **Entidad**: Asociado a `Investigador` a través de la relación de membresía

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Aportar la identidad del usuario autenticado para filtrar los proyectos que le corresponden

**Colaboraciones**:
- **Control**: `ProyectosController` lo usa para obtener el `id` del investigador en sesión
- **Repositorio**: `ProyectoRepository` lo usa como criterio de filtrado de la consulta

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `ProyectosView.abrirProyectos()`
2. **Obtención de identidad**: `ProyectosController` recupera el `investigadorId` del contexto de seguridad
3. **Listado propio**: `ProyectosView` → `ProyectosController.obtenerProyectosDeInvestigador(investigadorId)` : `List<Proyecto>`
4. **Acceso a datos**: `ProyectosController` → `ProyectoRepository.findByInvestigadorId(investigadorId)` : `List<Proyecto>`
5. **Filtrado (opcional)**: `ProyectosView` → `ProyectosController.filtrarProyectosDeInvestigador(investigadorId, criterio)` : `List<Proyecto>`
6. **Presentación**: `ProyectosView` → `:PROYECTOS_ABIERTOS.proyectosCargados()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar solo proyectos propios|`ProyectosController`|`obtenerProyectosDeInvestigador(investigadorId)`|
|Filtrar por criterio sobre proyectos propios|`ProyectosController`|`filtrarProyectosDeInvestigador(investigadorId, criterio)`|
|Acceso a datos filtrados por membresía|`ProyectoRepository`|`findByInvestigadorId(investigadorId)`|
|Abrir proyecto concreto|`ProyectosView`|→ Colaboración `AbrirProyecto`|

## diferencias respecto al análisis del Coordinador

| Aspecto | Coordinador | Investigador |
|---|---|---|
| Alcance de la consulta | Todos los proyectos (`obtenerTodos()`) | Solo los propios (`findByInvestigadorId(id)`) |
| Crear proyecto | Sí → colaboración `CrearProyecto` | No |
| Parámetro del controlador | — | `investigadorId` del contexto de seguridad |

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Recupera identidad del investigador y orquesta la consulta filtrada
- **Entidad**: Datos del proyecto y relación de membresía con el investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

## referencias

- [Especificación detallada: abrirProyectos() — Investigador](../../../context/casosDeUso/detalle/investigador/abrirProyectos/abrirProyectos.puml)
- [Diferencias entre actores](../../diferenciasActores.md)
- [Análisis abrirProyectos — Coordinador](../coordinador/abrirProyectos.md)
