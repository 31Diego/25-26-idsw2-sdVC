# FUNIBER GIPF > eliminarRecompensa > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `eliminarRecompensa()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador elimine una recompensa del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: eliminarRecompensa()](../../../images/analisis/coordinador/eliminarRecompensa-analisis.svg)|
|-|
|Código fuente: [eliminarRecompensa.puml](../../../modelosUML/analisis/coordinador/eliminarRecompensa.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EliminarRecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `eliminarRecompensa()` desde `:RECOMPENSA_ABIERTA`
- Solicitar al controlador los datos de la recompensa a eliminar mediante `cargarRecompensaParaEliminacion(id) : Recompensa`
- Mostrar la pantalla de confirmación con los datos de la recompensa
- Solicitar al controlador la eliminación definitiva mediante `eliminarRecompensa(id) : void`
- Navegar al listado de recompensas tras la eliminación

**Colaboraciones**:
- **Entrada**: Desde `:RECOMPENSA_ABIERTA` con `eliminarRecompensa()`
- **Control**: Se comunica con `RecompensaController` mediante `cargarRecompensaParaEliminacion(id)` y `eliminarRecompensa(id)`
- **Salida**: Transita a `:RECOMPENSAS_ABIERTAS` con `abrirRecompensas()`

### clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `cargarRecompensaParaEliminacion(id)` y delegar en el repositorio la obtención de la recompensa
- Recibir `eliminarRecompensa(id)` y delegar la eliminación al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EliminarRecompensaView`
- **Repositorio**: Delega en `RecompensaRepository` mediante `obtenerPorId(id) : Recompensa` y `eliminarPorId(id) : void`

### clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar una recompensa por id mediante `obtenerPorId(id) : Recompensa`
- Eliminar una recompensa del sistema mediante `eliminarPorId(id) : void`

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`
- **Entidad**: Gestiona instancias de `Recompensa`

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de la recompensa mostrados en la confirmación de eliminación

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:RECOMPENSA_ABIERTA`
2. El coordinador solicita eliminar recompensa: `EliminarRecompensaView` recibe `eliminarRecompensa()`
3. `EliminarRecompensaView` invoca `cargarRecompensaParaEliminacion(id)` en `RecompensaController`
4. `RecompensaController` delega en `RecompensaRepository.obtenerPorId(id)` y obtiene un objeto `Recompensa`
5. La pantalla de confirmación se muestra con los datos de la recompensa
6. El coordinador confirma: `EliminarRecompensaView` invoca `eliminarRecompensa(id) : void` en `RecompensaController`
7. `RecompensaController` delega en `RecompensaRepository.eliminarPorId(id)`
8. La vista navega → `:RECOMPENSAS_ABIERTAS` con `abrirRecompensas()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cargar datos para confirmación|`RecompensaController`|`cargarRecompensaParaEliminacion(id) : Recompensa`|
|Acceder a la recompensa por id|`RecompensaRepository`|`obtenerPorId(id) : Recompensa`|
|Eliminar recompensa del sistema|`RecompensaController`|`eliminarRecompensa(id) : void`|
|Persistir la eliminación|`RecompensaRepository`|`eliminarPorId(id) : void`|
|Navegar al listado de recompensas|`EliminarRecompensaView`|`abrirRecompensas()`|

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

- **Origen**: Caso de uso detallado `eliminarRecompensa()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`RecompensaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EliminarRecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

## referencias

- [Especificación detallada: eliminarRecompensa()](../../../context/casosDeUso/detalle/coordinador/eliminarRecompensa/eliminarRecompensa.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
