# FUNIBER GIPF > abrirInvestigadores > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirInvestigadores()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador consulte el directorio de investigadores del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirInvestigadores()](../../../images/analisis/investigador/abrirInvestigadores-analisis.svg)|
|-|
|Código fuente: [abrirInvestigadores.puml](../../../modelosUML/analisis/investigador/abrirInvestigadores.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### InvestigadoresView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de todos los investigadores del sistema en modo consulta
- Permitir filtrar investigadores por criterios de búsqueda
- Ofrecer acceso al perfil de un investigador concreto
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirInvestigadores()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Control**: Se comunica con `InvestigadorController`
- **Salida**: Navega a `:INVESTIGADORES_ABIERTOS` y colaboración `AbrirInvestigador`

### clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del directorio de investigadores
- Gestionar la lógica de filtrado por criterios
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `InvestigadoresView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener todos los investigadores
- Implementar búsqueda por criterios específicos

**Colaboraciones**:
- **Control**: Responde a `InvestigadorController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información básica de un investigador del directorio
- Encapsular atributos: nombre, email, rol, carga de trabajo

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
|Presentar directorio de investigadores|`InvestigadoresView`|Coordina con `InvestigadorController.obtenerInvestigadores()`|
|Permitir filtrado|`InvestigadoresView`|Invoca `InvestigadorController.filtrarInvestigadores(criterio)`|
|Abrir perfil de investigador|`InvestigadoresView`|→ Colaboración `AbrirInvestigador`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
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
