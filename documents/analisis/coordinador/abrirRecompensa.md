# FUNIBER GIPF > abrirRecompensa > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirRecompensa()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador consulte el detalle de una recompensa concreta y acceda a las opciones de edición o eliminación.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirRecompensa()](../../../images/analisis/coordinador/abrirRecompensa-analisis.svg)|
|-|
|Código fuente: [abrirRecompensa.puml](../../../modelosUML/analisis/coordinador/abrirRecompensa.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### RecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `abrirRecompensa(id)` desde `:RECOMPENSAS_ABIERTAS`
- Solicitar al controlador los datos de la recompensa mediante `obtenerRecompensa(id) : Recompensa`
- Mostrar el detalle de la recompensa al coordinador
- Ofrecer las opciones de editar, eliminar o volver al listado

**Colaboraciones**:
- **Entrada**: Desde `:RECOMPENSAS_ABIERTAS` con `abrirRecompensa(id)`
- **Control**: Se comunica con `RecompensaController` mediante `obtenerRecompensa(id) : Recompensa`
- **Salida**: Transita a `:RECOMPENSA_ABIERTA` (`recompensaMostrada()`), a `:Collaboration EditarRecompensa` (`editarRecompensa()`), a `:Collaboration EliminarRecompensa` (`eliminarRecompensa()`) o a `:RECOMPENSAS_ABIERTAS` (`abrirRecompensas()`)

### clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir la petición `obtenerRecompensa(id)` desde la vista
- Delegar la recuperación de la recompensa al repositorio mediante `obtenerPorId(id)`

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `RecompensaView`
- **Repositorio**: Delega el acceso a datos a `RecompensaRepository` mediante `obtenerPorId(id) : Recompensa`

### clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar una recompensa concreta por su identificador mediante `obtenerPorId(id) : Recompensa`

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`
- **Entidad**: Gestiona instancias de `Recompensa`

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de una recompensa del sistema

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema llega al estado `:RECOMPENSAS_ABIERTAS`
2. El coordinador selecciona una recompensa: `RecompensaView` recibe `abrirRecompensa(id)`
3. `RecompensaView` invoca `obtenerRecompensa(id)` en `RecompensaController`
4. `RecompensaController` delega en `RecompensaRepository.obtenerPorId(id)` y obtiene un objeto `Recompensa`
5. `RecompensaView` muestra el detalle → estado `:RECOMPENSA_ABIERTA` con `recompensaMostrada()`
6. Desde la vista el coordinador puede:
   - Editar la recompensa → `:Collaboration EditarRecompensa` con `editarRecompensa()`
   - Eliminar la recompensa → `:Collaboration EliminarRecompensa` con `eliminarRecompensa()`
   - Volver al listado → `:RECOMPENSAS_ABIERTAS` con `abrirRecompensas()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle de una recompensa|`RecompensaView`|`abrirRecompensa(id)`|
|Recuperar la recompensa por id|`RecompensaController`|`obtenerRecompensa(id) : Recompensa`|
|Acceder a datos de la recompensa|`RecompensaRepository`|`obtenerPorId(id) : Recompensa`|
|Navegar a editar recompensa|`RecompensaView`|`editarRecompensa()`|
|Navegar a eliminar recompensa|`RecompensaView`|`eliminarRecompensa()`|
|Volver al listado de recompensas|`RecompensaView`|`abrirRecompensas()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el coordinador
- **Control**: Solo coordinación y recuperación del objeto `Recompensa`
- **Entidad**: Solo datos y reglas de negocio de las recompensas

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirRecompensa()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`RecompensaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`RecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

## referencias

- [Especificación detallada: abrirRecompensa()](../../../context/casosDeUso/detalle/coordinador/abrirRecompensa/abrirRecompensa.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
