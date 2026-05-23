# FUNIBER GIPF > abrirInvestigadores > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirInvestigadores()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para listar y filtrar los investigadores de la plataforma.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirInvestigadores()](/images/analisis/abrirInvestigadores-analisis.svg)|
|-|
|Código fuente: [abrirInvestigadores.puml](abrirInvestigadores.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### InvestigadoresView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de investigadores al Coordinador
- Permitir filtrar por criterios de búsqueda
- Ofrecer acceso a un investigador concreto y a la creación de nuevos
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirInvestigadores()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Control**: Se comunica con `InvestigadorController`
- **Salida**: Navega a `:INVESTIGADORES_ABIERTOS` y colaboraciones de gestión

### clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de todos los investigadores
- Gestionar la lógica de filtrado
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `InvestigadoresView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener todos los investigadores e implementar búsqueda por criterios

**Colaboraciones**:
- **Control**: Responde a `InvestigadorController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un investigador
- Encapsular atributos: nombre, apellidos, área, institución, proyectos activos

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `InvestigadoresView.abrirInvestigadores()`
2. **Listado**: `InvestigadoresView` → `InvestigadorController.obtenerInvestigadores()` : `List<Investigador>`
3. **Acceso a datos**: `InvestigadorController` → `InvestigadorRepository.obtenerTodos()` : `List<Investigador>`
4. **Filtrado (opcional)**: `InvestigadoresView` → `InvestigadorController.filtrarInvestigadores(criterio)` : `List<Investigador>`
5. **Presentación**: `InvestigadoresView` → `:INVESTIGADORES_ABIERTOS.investigadoresCargados()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Listar investigadores|`InvestigadoresView`|Coordina con `InvestigadorController.obtenerInvestigadores()`|
|Filtrar investigadores|`InvestigadoresView`|Invoca `InvestigadorController.filtrarInvestigadores(criterio)`|
|Abrir investigador concreto|`InvestigadoresView`|→ Colaboración `AbrirInvestigador`|
|Crear nuevo investigador|`InvestigadoresView`|→ Colaboración `CrearInvestigador`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
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

- [Especificación detallada: abrirInvestigadores()](../../../context/casosDeUso/detalle/coordinador/abrirInvestigadores/abrirInvestigadores.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
