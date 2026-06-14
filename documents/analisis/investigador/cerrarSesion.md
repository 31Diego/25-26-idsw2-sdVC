# FUNIBER GIPF > cerrarSesion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `cerrarSesion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para finalizar la sesión activa del investigador.

## diagrama de colaboración

<div align=center>

|![Análisis: cerrarSesion()](../../../images/analisis/investigador/cerrarSesion-investigador-analisis.svg)|
|-|
|Código fuente: [cerrarSesion.puml](../../../modelosUML/analisis/investigador/cerrarSesion.puml)|

</div>

## clases de análisis identificadas

### clases de control

#### SesionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `cerrarSesion()` desde `:PANEL_PRINCIPAL_ABIERTO` e invalidar la sesión activa del investigador
- Transitar al estado de sesión cerrada

**Colaboraciones**:
- **Entrada**: Desde `:PANEL_PRINCIPAL_ABIERTO` con `cerrarSesion()`
- **Salida**: Transita a `:SESION_CERRADA` con `sesionCerrada()`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PANEL_PRINCIPAL_ABIERTO`
2. El investigador solicita cerrar sesión: `SesionController` recibe `cerrarSesion()`
3. `SesionController` invalida la sesión activa y transita a `:SESION_CERRADA` con `sesionCerrada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cerrar la sesión activa|`SesionController`|`cerrarSesion()`|
|Transitar a sesión cerrada|`SesionController`|`sesionCerrada()`|

## características del análisis

### separación de responsabilidades MVC

- **Control**: Coordina el cierre de sesión sin necesidad de vista propia; la acción se dispara directamente desde el panel principal y no requiere acceso a datos persistentes

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `cerrarSesion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### mvc pattern
Solo interviene la clase de control `SesionController`. No se requiere vista propia ni entidad.

## referencias

- [Especificación detallada: cerrarSesion()](../../../context/casosDeUso/detalle/investigador/cerrarSesion/cerrarSesion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
