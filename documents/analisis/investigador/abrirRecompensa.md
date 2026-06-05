# FUNIBER GIPF > abrirRecompensa > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirRecompensa()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el detalle de una recompensa al Investigador en modo consulta.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirRecompensa()](../../../images/analisis/investigador/abrirRecompensa-analisis.svg)|
|-|
|Código fuente: [abrirRecompensa.puml](../../../modelosUML/analisis/investigador/abrirRecompensa.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### RecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el detalle completo de la recompensa al Investigador
- Mostrar información: nombre, descripción, puntos, condiciones
- Navegar de vuelta al listado de recompensas

**Colaboraciones**:
- **Entrada**: Recibe `abrirRecompensa(id)` desde `:RECOMPENSAS_ABIERTAS`
- **Control**: Se comunica con `RecompensaController`
- **Salida**: Navega a `:RECOMPENSA_ABIERTA` y `:RECOMPENSAS_ABIERTAS`

### clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del detalle de la recompensa
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `RecompensaView`
- **Repositorio**: Delega el acceso a datos a `RecompensaRepository`

### clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas
- Proporcionar método para obtener una recompensa por identificador

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`
- **Entidad**: Gestiona instancias de `Recompensa`

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de una recompensa
- Encapsular atributos: nombre, descripción, puntos, condiciones

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:RECOMPENSAS_ABIERTAS` → `RecompensaView.abrirRecompensa(id)`
2. **Obtención de datos**: `RecompensaView` → `RecompensaController.obtenerRecompensa(id)` : `Recompensa`
3. **Acceso a datos**: `RecompensaController` → `RecompensaRepository.obtenerPorId(id)` : `Recompensa`
4. **Presentación**: `RecompensaView` → `:RECOMPENSA_ABIERTA.recompensaMostrada()`
5. **Navegación**: El Investigador puede volver al listado de recompensas

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle de la recompensa|`RecompensaView`|Coordina con `RecompensaController.obtenerRecompensa(id)`|
|Datos completos de la recompensa|`Recompensa`|Encapsula todos los atributos|
|Acceso a datos|`RecompensaRepository`|`obtenerPorId(id)`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y obtención del detalle
- **Entidad**: Solo datos y reglas de negocio de la recompensa

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

- [Especificación detallada: abrirRecompensa()](../../../context/casosDeUso/detalle/investigador/abrirRecompensa/abrirRecompensa.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
