# FUNIBER GIPF > abrirInvestigadores > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirInvestigadores()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador consulte el directorio de investigadores.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirInvestigadores()](../../../images/analisis/investigador/abrirInvestigadores-investigador-analisis.svg)|
|-|
|Código fuente: [abrirInvestigadores.puml](../../../modelosUML/analisis/investigador/abrirInvestigadores.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### InvestigadoresView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirInvestigadores()` desde `:PANEL_PRINCIPAL_ABIERTO`
- Solicitar al controlador la lista de investigadores mediante `obtenerInvestigadores() : List<Investigador>`
- Permitir filtrar investigadores mediante `filtrarInvestigadores(criterio) : List<Investigador>`
- Mostrar el directorio al investigador
- Ofrecer acceso al perfil de un investigador y vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Desde `:PANEL_PRINCIPAL_ABIERTO` con `abrirInvestigadores()`
- **Control**: Se comunica con `InvestigadorController` mediante `obtenerInvestigadores()` y `filtrarInvestigadores(criterio)`
- **Salida**: Transita a `:INVESTIGADORES_ABIERTOS` (`investigadoresCargados()`), `:Collaboration AbrirInvestigador` (`abrirInvestigador(id)`), `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerInvestigadores()` y delegar en el repositorio la obtención de todos los investigadores
- Recibir `filtrarInvestigadores(criterio)` y delegar la búsqueda filtrada al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `InvestigadoresView`
- **Repositorio**: Delega en `InvestigadorRepository` mediante `obtenerTodos() : List<Investigador>` y `buscarPorCriterio(criterio) : List<Investigador>`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar todos los investigadores mediante `obtenerTodos() : List<Investigador>`
- Buscar investigadores por criterio mediante `buscarPorCriterio(criterio) : List<Investigador>`

**Colaboraciones**:
- **Control**: Responde a `InvestigadorController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de un investigador en el directorio

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PANEL_PRINCIPAL_ABIERTO`
2. El investigador solicita ver el directorio: `InvestigadoresView` recibe `abrirInvestigadores()`
3. `InvestigadoresView` invoca `obtenerInvestigadores()` en `InvestigadorController`
4. `InvestigadorController` delega en `InvestigadorRepository.obtenerTodos()` y obtiene `List<Investigador>`
5. La vista muestra el listado → transita a `:INVESTIGADORES_ABIERTOS` con `investigadoresCargados()`
6. El investigador puede filtrar: `InvestigadoresView` invoca `filtrarInvestigadores(criterio)` en `InvestigadorController` → delega en `InvestigadorRepository.buscarPorCriterio(criterio)`
7. Desde `:INVESTIGADORES_ABIERTOS` puede navegar a `abrirInvestigador(id)` o `abrirPanelPrincipal()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener directorio de investigadores|`InvestigadorController`|`obtenerInvestigadores() : List<Investigador>`|
|Acceder a todos los investigadores|`InvestigadorRepository`|`obtenerTodos() : List<Investigador>`|
|Filtrar por criterio|`InvestigadorController`|`filtrarInvestigadores(criterio) : List<Investigador>`|
|Buscar por criterio en repositorio|`InvestigadorRepository`|`buscarPorCriterio(criterio) : List<Investigador>`|
|Mostrar directorio|`InvestigadoresView`|`investigadoresCargados()`|
|Abrir perfil de investigador|`InvestigadoresView`|`abrirInvestigador(id)`|
|Volver al panel principal|`InvestigadoresView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el investigador
- **Control**: Solo coordinación y lógica de filtrado
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirInvestigadores()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`InvestigadoresView`), lógica de aplicación (`InvestigadorController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: abrirInvestigadores()](../../../context/casosDeUso/detalle/investigador/abrirInvestigadores/abrirInvestigadores.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
