# FUNIBER GIPF > abrirProyectos > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirProyectos()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para listar únicamente los proyectos en los que el Investigador participa.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirProyectos()](../../../images/analisis/investigador/abrirProyectos-analisis.svg)|
|-|
|Código fuente: [abrirProyectos.puml](../../../modelosUML/analisis/investigador/abrirProyectos.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ProyectosView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de proyectos en los que participa el Investigador
- Permitir filtrar proyectos por criterios de búsqueda
- Ofrecer acceso a un proyecto concreto
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirProyectos()` desde `:PANEL_PRINCIPAL_ABIERTO` o `:PROYECTO_ABIERTO`
- **Control**: Se comunica con `ProyectosController`
- **Salida**: Navega a `:PROYECTOS_ABIERTOS` y colaboración `AbrirProyecto`

### clases de control

#### ProyectosController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de los proyectos del Investigador autenticado
- Gestionar la lógica de filtrado por criterios
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ProyectosView`
- **Repositorio**: Delega el acceso a datos a `ProyectoRepository`

### clases de entidad (entity)

#### ProyectoRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de proyectos
- Proporcionar método para obtener proyectos filtrados por investigador
- Implementar búsqueda por criterios sobre los proyectos del investigador

**Colaboraciones**:
- **Control**: Responde a `ProyectosController`
- **Entidad**: Gestiona instancias de `Proyecto` e `Investigador`

#### Proyecto
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un proyecto de investigación
- Encapsular atributos: título, descripción, estado, fechas, investigadores participantes

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ProyectoRepository`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Identificar al investigador autenticado para filtrar sus proyectos

**Colaboraciones**:
- **Repositorio**: Relacionado con `Proyecto` a través de `ProyectoRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `ProyectosView.abrirProyectos()`
2. **Listado**: `ProyectosView` → `ProyectosController.obtenerProyectosDeInvestigador(investigadorId)` : `List<Proyecto>`
3. **Acceso a datos**: `ProyectosController` → `ProyectoRepository.findByInvestigadorId(investigadorId)` : `List<Proyecto>`
4. **Filtrado (opcional)**: `ProyectosView` → `ProyectosController.filtrarProyectosDeInvestigador(investigadorId, criterio)` : `List<Proyecto>`
5. **Presentación**: `ProyectosView` → `:PROYECTOS_ABIERTOS.proyectosCargados()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar solo proyectos propios|`ProyectosView`|Coordina con `ProyectosController.obtenerProyectosDeInvestigador()`|
|Permitir filtrado|`ProyectosView`|Invoca `ProyectosController.filtrarProyectosDeInvestigador(criterio)`|
|Abrir proyecto concreto|`ProyectosView`|→ Colaboración `AbrirProyecto`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y lógica de filtrado por investigador
- **Entidad**: Solo datos y reglas de negocio del proyecto

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirProyectos()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ProyectoRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ProyectosView`), lógica de aplicación (`ProyectosController`) y datos (`Proyecto`, `ProyectoRepository`).

## referencias

- [Especificación detallada: abrirProyectos()](../../../context/casosDeUso/detalle/investigador/abrirProyectos/abrirProyectos.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
