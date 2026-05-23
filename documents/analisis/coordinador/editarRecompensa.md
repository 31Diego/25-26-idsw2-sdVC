# FUNIBER GIPF > editarRecompensa > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarRecompensa()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para modificar los datos de una recompensa existente.

## diagrama de colaboración

<div align=center>

|![Análisis: editarRecompensa()](/images/analisis/editarRecompensa-analisis.svg)|
|-|
|Código fuente: [editarRecompensa.puml](editarRecompensa.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarRecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición con los datos actuales de la recompensa
- Recuperar los datos actuales a través del controlador
- Capturar los cambios del Coordinador
- Invocar el guardado en el controlador
- Navegar de vuelta a la recompensa tras la edición

**Colaboraciones**:
- **Entrada**: Recibe `editarRecompensa()` desde `:RECOMPENSA_ABIERTA`
- **Control**: Se comunica con `RecompensaController`
- **Salida**: Navega a `:RECOMPENSA_ABIERTA`

### clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención y persistencia de los datos de la recompensa
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarRecompensaView`
- **Repositorio**: Delega operaciones a `RecompensaRepository`

### clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas
- Proporcionar métodos para obtener y actualizar una recompensa

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`
- **Entidad**: Gestiona instancias de `Recompensa`

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de una recompensa
- Encapsular atributos editables: título, descripción, tipo, valor, condiciones

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:RECOMPENSA_ABIERTA` → `EditarRecompensaView.editarRecompensa()`
2. **Carga**: `EditarRecompensaView` → `RecompensaController.obtenerRecompensa(id)` : `Recompensa`
3. **Acceso a datos**: `RecompensaController` → `RecompensaRepository.obtenerPorId(id)` : `Recompensa`
4. **Edición**: El Coordinador modifica los datos
5. **Guardado**: `EditarRecompensaView` → `RecompensaController.guardarRecompensa(datos)` : `Recompensa`
6. **Persistencia**: `RecompensaController` → `RecompensaRepository.actualizar(recompensa)` : `Recompensa`
7. **Finalización**: `EditarRecompensaView` → `:RECOMPENSA_ABIERTA.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos actuales|`EditarRecompensaView`|Coordina con `RecompensaController.obtenerRecompensa(id)`|
|Modificar recompensa|`EditarRecompensaView`|Captura cambios en el formulario|
|Persistir cambios|`RecompensaController`|`guardarRecompensa(datos)` → `RecompensaRepository.actualizar()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la obtención y persistencia
- **Entidad**: Solo datos y reglas de negocio de la recompensa

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `editarRecompensa()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`RecompensaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EditarRecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

## referencias

- [Especificación detallada: editarRecompensa()](../../../context/casosDeUso/detalle/coordinador/editarRecompensa/editarRecompensa.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
