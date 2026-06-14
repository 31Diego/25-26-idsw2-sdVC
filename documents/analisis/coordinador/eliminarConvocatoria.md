# FUNIBER GIPF > eliminarConvocatoria > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarConvocatoria()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador elimine una convocatoria del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarConvocatoria()](../../../images/analisis/coordinador/eliminarConvocatoria-analisis.svg)|
|-|
|Código fuente: [eliminarConvocatoria.puml](../../../modelosUML/analisis/coordinador/eliminarConvocatoria.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarConvocatoriaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarConvocatoria()` desde `:CONVOCATORIA_ABIERTA`
- Solicitar al controlador los datos de la convocatoria a eliminar mediante `cargarConvocatoriaParaEliminacion(id) : Convocatoria`
- Mostrar la pantalla de confirmación con los datos de la convocatoria
- Solicitar al controlador la eliminación definitiva mediante `eliminarConvocatoria(id) : void`
- Navegar al listado de convocatorias o cancelar volviendo al estado anterior

**Colaboraciones**:
- **Entrada**: Desde `:CONVOCATORIA_ABIERTA` con `eliminarConvocatoria()`
- **Control**: Se comunica con `ConvocatoriaController` mediante `cargarConvocatoriaParaEliminacion(id)` y `eliminarConvocatoria(id)`
- **Salida**: Transita a `:CONVOCATORIAS_ABIERTAS` (`abrirConvocatorias()`) o cancela con `cancelar()` a `:CONVOCATORIA_ABIERTA`

### clases de control

#### ConvocatoriaController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `cargarConvocatoriaParaEliminacion(id)` y delegar en el repositorio la obtención de la convocatoria
- Recibir `eliminarConvocatoria(id)` y delegar la eliminación al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarConvocatoriaView`
- **Repositorio**: Delega en `ConvocatoriaRepository` mediante `obtenerPorId(id) : Convocatoria` y `eliminarPorId(id) : void`

### clases de entidad (entity)

#### ConvocatoriaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar una convocatoria por id mediante `obtenerPorId(id) : Convocatoria`
- Eliminar una convocatoria del sistema mediante `eliminarPorId(id) : void`

**Colaboraciones**:
- **Control**: Responde a `ConvocatoriaController`
- **Entidad**: Gestiona instancias de `Convocatoria`

#### Convocatoria
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de la convocatoria mostrados en la confirmación de eliminación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `ConvocatoriaRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:CONVOCATORIA_ABIERTA`
2. El coordinador solicita eliminar convocatoria: `EliminarConvocatoriaView` recibe `eliminarConvocatoria()`
3. `EliminarConvocatoriaView` invoca `cargarConvocatoriaParaEliminacion(id)` en `ConvocatoriaController`
4. `ConvocatoriaController` delega en `ConvocatoriaRepository.obtenerPorId(id)` y obtiene un objeto `Convocatoria`
5. La pantalla de confirmación se muestra con los datos de la convocatoria
6. El coordinador confirma: `EliminarConvocatoriaView` invoca `eliminarConvocatoria(id) : void` en `ConvocatoriaController`
7. `ConvocatoriaController` delega en `ConvocatoriaRepository.eliminarPorId(id)`
8. La vista navega → `:CONVOCATORIAS_ABIERTAS` con `abrirConvocatorias()`

### flujo alternativo: cancelación

Si el coordinador cancela, `EliminarConvocatoriaView` navega de vuelta a `:CONVOCATORIA_ABIERTA` con `cancelar()` sin ningún cambio en los datos.

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cargar datos para confirmación|`ConvocatoriaController`|`cargarConvocatoriaParaEliminacion(id) : Convocatoria`|
|Acceder a la convocatoria por id|`ConvocatoriaRepository`|`obtenerPorId(id) : Convocatoria`|
|Eliminar convocatoria del sistema|`ConvocatoriaController`|`eliminarConvocatoria(id) : void`|
|Persistir la eliminación|`ConvocatoriaRepository`|`eliminarPorId(id) : void`|
|Navegar al listado de convocatorias|`EliminarConvocatoriaView`|`abrirConvocatorias()`|
|Cancelar sin cambios|`EliminarConvocatoriaView`|`cancelar()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación de la confirmación e interacción con el coordinador
- **Control**: Solo coordinación del proceso de eliminación
- **Entidad**: Solo datos y gestión de la persistencia

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `eliminarConvocatoria()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`ConvocatoriaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EliminarConvocatoriaView`), lógica de aplicación (`ConvocatoriaController`) y datos (`Convocatoria`, `ConvocatoriaRepository`).

## referencias

- [Análisis relacionado: abrirConvocatoria()](abrirConvocatoria.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
