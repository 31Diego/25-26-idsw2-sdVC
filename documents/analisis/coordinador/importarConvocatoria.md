# FUNIBER GIPF > importarConvocatoria > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `importarConvocatoria()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador importe una convocatoria al sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: importarConvocatoria()](../../../images/analisis/coordinador/importarConvocatoria-analisis.svg)|
|-|
|Código fuente: [importarConvocatoria.puml](../../../modelosUML/analisis/coordinador/importarConvocatoria.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ImportarConvocatoriaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `importarConvocatoria()` desde `:CONVOCATORIA_ABIERTA`
- Solicitar al controlador la importación con los datos proporcionados mediante `importarConvocatoria(datos) : Convocatoria`
- Navegar de vuelta a la convocatoria importada o al panel principal

**Colaboraciones**:
- **Entrada**: Desde `:CONVOCATORIA_ABIERTA` con `importarConvocatoria()`
- **Control**: Se comunica con `ConvocatoriaController` mediante `importarConvocatoria(datos) : Convocatoria`
- **Salida**: Transita a `:CONVOCATORIA_ABIERTA` (`convocatoriaImportada()`) o a `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### ConvocatoriaController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `importarConvocatoria(datos)` y delegar la creación al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ImportarConvocatoriaView`
- **Repositorio**: Delega la persistencia a `ConvocatoriaRepository` mediante `crear(convocatoria) : Convocatoria`

### clases de entidad (entity)

#### ConvocatoriaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Persistir la convocatoria importada mediante `crear(convocatoria) : Convocatoria`

**Colaboraciones**:
- **Control**: Responde a `ConvocatoriaController`
- **Entidad**: Gestiona instancias de `Convocatoria`

#### Convocatoria
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de la convocatoria importada

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ConvocatoriaRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:CONVOCATORIA_ABIERTA`
2. El coordinador solicita importar convocatoria: `ImportarConvocatoriaView` recibe `importarConvocatoria()`
3. El coordinador introduce o confirma los datos de la convocatoria
4. `ImportarConvocatoriaView` invoca `importarConvocatoria(datos)` en `ConvocatoriaController` → devuelve `Convocatoria`
5. `ConvocatoriaController` delega en `ConvocatoriaRepository.crear(convocatoria)` y obtiene el objeto `Convocatoria` creado
6. La vista navega → `:CONVOCATORIA_ABIERTA` (`convocatoriaImportada()`) o `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Importar convocatoria al sistema|`ConvocatoriaController`|`importarConvocatoria(datos) : Convocatoria`|
|Crear convocatoria en repositorio|`ConvocatoriaRepository`|`crear(convocatoria) : Convocatoria`|
|Navegar a la convocatoria importada|`ImportarConvocatoriaView`|`convocatoriaImportada()`|
|Navegar al panel principal|`ImportarConvocatoriaView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el coordinador
- **Control**: Solo coordinación de la lógica de importación
- **Entidad**: Solo datos y reglas de negocio de la convocatoria

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `importarConvocatoria()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ConvocatoriaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ImportarConvocatoriaView`), lógica de aplicación (`ConvocatoriaController`) y datos (`Convocatoria`, `ConvocatoriaRepository`).

## referencias

- [Especificación detallada: importarConvocatoria()](../../../context/casosDeUso/detalle/coordinador/importarConvocatoria/importarConvocatoria.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
