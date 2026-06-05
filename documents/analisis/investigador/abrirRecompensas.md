# FUNIBER GIPF > abrirRecompensas > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirRecompensas()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador consulte el catálogo de recompensas disponibles.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirRecompensas()](../../../images/analisis/investigador/abrirRecompensas-analisis.svg)|
|-|
|Código fuente: [abrirRecompensas.puml](../../../modelosUML/analisis/investigador/abrirRecompensas.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### RecompensasView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de recompensas disponibles al Investigador en modo consulta
- Permitir filtrar recompensas por criterios de búsqueda
- Ofrecer acceso al detalle de una recompensa concreta
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirRecompensas()` desde `:PANEL_PRINCIPAL_ABIERTO` o `:RECOMPENSA_ABIERTA`
- **Control**: Se comunica con `RecompensaController`
- **Salida**: Navega a `:RECOMPENSAS_ABIERTAS` y colaboración `AbrirRecompensa`

### clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del catálogo de recompensas
- Gestionar la lógica de filtrado por criterios
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `RecompensasView`
- **Repositorio**: Delega el acceso a datos a `RecompensaRepository`

### clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas
- Proporcionar método para obtener todas las recompensas
- Implementar búsqueda por criterios específicos

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`
- **Entidad**: Gestiona instancias de `Recompensa`

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de una recompensa
- Encapsular atributos: nombre, descripción, puntos, condiciones

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `RecompensasView.abrirRecompensas()`
2. **Listado**: `RecompensasView` → `RecompensaController.obtenerRecompensas()` : `List<Recompensa>`
3. **Acceso a datos**: `RecompensaController` → `RecompensaRepository.obtenerTodos()` : `List<Recompensa>`
4. **Filtrado (opcional)**: `RecompensasView` → `RecompensaController.filtrarRecompensas(criterio)` : `List<Recompensa>`
5. **Presentación**: `RecompensasView` → `:RECOMPENSAS_ABIERTAS.recompensasCargadas()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar lista de recompensas|`RecompensasView`|Coordina con `RecompensaController.obtenerRecompensas()`|
|Permitir filtrado|`RecompensasView`|Invoca `RecompensaController.filtrarRecompensas(criterio)`|
|Abrir recompensa concreta|`RecompensasView`|→ Colaboración `AbrirRecompensa`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y lógica de filtrado
- **Entidad**: Solo datos y reglas de negocio de la recompensa

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirRecompensas()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`RecompensaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`RecompensasView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

## referencias

- [Especificación detallada: abrirRecompensas()](../../../context/casosDeUso/detalle/investigador/abrirRecompensas/abrirRecompensas.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
