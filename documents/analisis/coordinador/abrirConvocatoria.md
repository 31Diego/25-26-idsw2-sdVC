# FUNIBER GIPF > abrirConvocatoria > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-22
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirConvocatoria()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el detalle de una convocatoria al Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirConvocatoria()](/images/analisis/abrirConvocatoria-analisis.svg)|
|-|
|Código fuente: [abrirConvocatoria.puml](abrirConvocatoria.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ConvocatoriaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud de apertura de una convocatoria concreta
- Interactuar con el controlador para obtener el detalle de la convocatoria
- Presentar al Coordinador: título, área, estado, fechas, descripción, requisitos, criterios, documentación e información de contacto
- Ofrecer opciones de navegación: importar convocatoria, volver a la lista, volver al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirConvocatoria(id)` desde `:CONVOCATORIAS_ABIERTAS`
- **Control**: Se comunica con `ConvocatoriaController`
- **Salida**: Navega a `CONVOCATORIA_ABIERTA`, a `CONVOCATORIAS_ABIERTAS` o a `importarConvocatoria()`

### clases de control

#### ConvocatoriaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del detalle de la convocatoria solicitada
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ConvocatoriaView`
- **Repositorio**: Delega el acceso a datos a `ConvocatoriaRepository`

### clases de entidad (entity)

#### ConvocatoriaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de convocatorias
- Proporcionar método para obtener una convocatoria por identificador

**Colaboraciones**:
- **Control**: Responde a `ConvocatoriaController`
- **Entidad**: Gestiona instancias de `Convocatoria`

#### Convocatoria
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de una convocatoria
- Encapsular atributos: título, área, estado, fechas relevantes, descripción, requisitos y condiciones, criterios de evaluación y dotación, documentación asociada, información de contacto

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ConvocatoriaRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:CONVOCATORIAS_ABIERTAS` → `ConvocatoriaView.abrirConvocatoria(id)`
2. **Obtención de detalle**: `ConvocatoriaView` → `ConvocatoriaController.obtenerConvocatoria(id)`
3. **Acceso a datos**: `ConvocatoriaController` → `ConvocatoriaRepository.obtenerPorId(id)` : `Convocatoria`
4. **Presentación**: `ConvocatoriaView` presenta el detalle completo al Coordinador
5. **Finalización**: `ConvocatoriaView` → `CONVOCATORIAS_ABIERTAS` (`abrirConvocatorias()`) o → `importarConvocatoria()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar detalle de la convocatoria|`ConvocatoriaView`|Coordina con `ConvocatoriaController.obtenerConvocatoria(id)`|
|Título, área, estado, fechas, descripción, requisitos, criterios, documentación, contacto|`Convocatoria`|Encapsula todos los atributos|
|Acceso a datos de la convocatoria|`ConvocatoriaRepository`|`obtenerPorId(id)`|
|Navegar a importar convocatoria|`ConvocatoriaView`|Colaboración con `ImportarConvocatoria`|
|Volver a la lista de convocatorias|`ConvocatoriaView`|Colaboración con `CONVOCATORIAS_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y obtención del detalle
- **Entidad**: Solo datos y reglas de negocio de la convocatoria

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirConvocatoria()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ConvocatoriaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ConvocatoriaView`), lógica de aplicación (`ConvocatoriaController`) y datos (`Convocatoria`, `ConvocatoriaRepository`).

## referencias

- [Especificación detallada: abrirConvocatoria()](../../../context/casosDeUso/detalle/coordinador/abrirConvocatoria/abrirConvocatoria.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
