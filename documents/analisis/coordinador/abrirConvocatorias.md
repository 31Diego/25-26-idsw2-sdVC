# FUNIBER GIPF > abrirConvocatorias > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-22
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirConvocatorias()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el listado de convocatorias con capacidad de búsqueda y filtrado al Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirConvocatorias()](../../../images/analisis/abrirConvocatorias-analisis.svg)|
|-|
|Código fuente: [abrirConvocatorias.puml](../../../modelosUML/analisis/coordinador/abrirConvocatorias.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ListarConvocatoriasView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud de apertura del listado de convocatorias (desde el panel principal o desde una convocatoria abierta)
- Interactuar con el controlador para obtener el listado completo
- Presentar el bloque de búsqueda y filtrado: texto, área y estado
- Presentar el listado de convocatorias con: ID, título, área, estado y fecha de cierre
- Capturar los criterios de filtrado introducidos por el Coordinador
- Informar al Coordinador si el sistema no puede cargar el listado
- Ofrecer opciones de navegación: abrir una convocatoria concreta o volver al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirConvocatorias()` desde `:PANEL_PRINCIPAL_ABIERTO` o desde `:CONVOCATORIA_ABIERTA`
- **Control**: Se comunica con `ConvocatoriasController`
- **Salida**: Navega a `CONVOCATORIAS_ABIERTAS`, a `abrirConvocatoria(id)` o a `PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### ConvocatoriasController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del listado completo de convocatorias
- Manejar la lógica de filtrado por texto, área y estado
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ListarConvocatoriasView`
- **Repositorio**: Delega operaciones de datos a `ConvocatoriaRepository`

### clases de entidad (entity)

#### ConvocatoriaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de convocatorias
- Proporcionar método para obtener todas las convocatorias
- Implementar búsqueda por criterios: texto, área y estado

**Colaboraciones**:
- **Control**: Responde a `ConvocatoriasController`
- **Entidad**: Gestiona instancias de `Convocatoria`

#### Convocatoria
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de una convocatoria
- Encapsular los atributos presentados en el listado: ID, título, área, estado, fecha de cierre

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ConvocatoriaRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` o `:CONVOCATORIA_ABIERTA` → `ListarConvocatoriasView.abrirConvocatorias()`
2. **Listado inicial**: `ListarConvocatoriasView` → `ConvocatoriasController.listarConvocatorias()`
3. **Acceso a datos**: `ConvocatoriasController` → `ConvocatoriaRepository.obtenerTodos()` : `List<Convocatoria>`
4. **Filtrado (opcional)**: `ListarConvocatoriasView` → `ConvocatoriasController.filtrarConvocatorias(texto, area, estado)`
5. **Búsqueda**: `ConvocatoriasController` → `ConvocatoriaRepository.buscarPorCriterio(texto, area, estado)` : `List<Convocatoria>`
6. **Finalización**: `ListarConvocatoriasView` → `abrirConvocatoria(id)` o → `abrirPanelPrincipal()`

### flujo alternativo: error al cargar

Si `ConvocatoriaRepository` no puede devolver el listado, `ListarConvocatoriasView` informa al Coordinador y mantiene la navegación anterior sin transicionar de estado.

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar listado de convocatorias|`ListarConvocatoriasView`|Coordina con `ConvocatoriasController.listarConvocatorias()`|
|Filtrar por texto, área y estado|`ListarConvocatoriasView`|Invoca `ConvocatoriasController.filtrarConvocatorias(texto, area, estado)`|
|ID, título, área, estado, fecha de cierre|`Convocatoria`|Encapsula atributos del listado|
|Acceso a datos de convocatorias|`ConvocatoriaRepository`|`obtenerTodos()`, `buscarPorCriterio(texto, area, estado)`|
|Navegar a convocatoria concreta|`ListarConvocatoriasView`|Colaboración con `AbrirConvocatoria`|
|Volver al panel principal|`ListarConvocatoriasView`|Colaboración con `PANEL_PRINCIPAL_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación, filtrado e interacción con el Coordinador
- **Control**: Solo coordinación y lógica de filtrado
- **Entidad**: Solo datos y reglas de negocio de las convocatorias

### dos puntos de entrada

A diferencia de la mayoría de casos de uso, `abrirConvocatorias()` puede ser invocado tanto desde el panel principal como desde una convocatoria ya abierta (navegación de retorno). `ListarConvocatoriasView` gestiona ambas entradas de forma transparente.

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirConvocatorias()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ConvocatoriaRepository` abstrae el acceso a datos, compartido con el caso de uso `abrirConvocatoria()`. Permite diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ListarConvocatoriasView`), lógica de aplicación (`ConvocatoriasController`) y datos (`Convocatoria`, `ConvocatoriaRepository`).

## referencias

- [Especificación detallada: abrirConvocatorias()](../../../context/casosDeUso/detalle/coordinador/abrirConvocatorias/abrirConvocatorias.puml)
- [Análisis relacionado: abrirConvocatoria()](../abrirConvocatoria/abrirConvocatoria.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
