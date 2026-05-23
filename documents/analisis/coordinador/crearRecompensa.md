# FUNIBER GIPF > crearRecompensa > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearRecompensa()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para registrar una nueva recompensa en el sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: crearRecompensa()](/images/analisis/crearRecompensa-analisis.svg)|
|-|
|Código fuente: [crearRecompensa.puml](crearRecompensa.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearRecompensaView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de creación de recompensa al Coordinador
- Capturar los datos de la nueva recompensa
- Invocar el guardado en el controlador
- Navegar a la lista de recompensas tras la creación

**Colaboraciones**:
- **Entrada**: Recibe `crearRecompensa()` desde `:RECOMPENSAS_ABIERTAS`
- **Control**: Se comunica con `RecompensaController`
- **Salida**: Navega a `:RECOMPENSAS_ABIERTAS`

### clases de control

#### RecompensaController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de creación de la nueva recompensa
- Validar los datos recibidos del formulario
- Persistir la nueva recompensa a través del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearRecompensaView`
- **Repositorio**: Delega la persistencia a `RecompensaRepository`

### clases de entidad (entity)

#### RecompensaRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de recompensas
- Proporcionar método para crear una nueva recompensa

**Colaboraciones**:
- **Control**: Responde a `RecompensaController`
- **Entidad**: Gestiona instancias de `Recompensa`

#### Recompensa
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de la nueva recompensa
- Encapsular atributos: título, descripción, tipo, valor, condiciones

**Colaboraciones**:
- **Repositorio**: Es gestionado por `RecompensaRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:RECOMPENSAS_ABIERTAS` → `CrearRecompensaView.crearRecompensa()`
2. **Captura**: El Coordinador rellena el formulario
3. **Guardado**: `CrearRecompensaView` → `RecompensaController.guardarRecompensa(datos)` : `Recompensa`
4. **Persistencia**: `RecompensaController` → `RecompensaRepository.crear(recompensa)` : `Recompensa`
5. **Finalización**: `CrearRecompensaView` → `:RECOMPENSAS_ABIERTAS.abrirRecompensas()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de creación|`CrearRecompensaView`|Captura datos de la nueva recompensa|
|Persistir nueva recompensa|`RecompensaController`|`guardarRecompensa(datos)` → `RecompensaRepository.crear()`|
|Confirmar creación|`CrearRecompensaView`|→ `:RECOMPENSAS_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la validación y persistencia
- **Entidad**: Solo datos y reglas de negocio de la recompensa

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `crearRecompensa()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`RecompensaRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`CrearRecompensaView`), lógica de aplicación (`RecompensaController`) y datos (`Recompensa`, `RecompensaRepository`).

## referencias

- [Especificación detallada: crearRecompensa()](../../../context/casosDeUso/detalle/coordinador/crearRecompensa/crearRecompensa.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
