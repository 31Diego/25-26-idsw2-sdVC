# FUNIBER GIPF > cerrarSesion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `cerrarSesion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para finalizar la sesión activa del Investigador.

## diagrama de colaboración

<div align=center>

|![Análisis: cerrarSesion()](../../../images/analisis/investigador/cerrarSesion-analisis.svg)|
|-|
|Código fuente: [cerrarSesion.puml](../../../modelosUML/analisis/investigador/cerrarSesion.puml)|

</div>

## clases de análisis identificadas

### clases de control

#### SesionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de cierre de sesión
- Invalidar la sesión activa del Investigador
- Redirigir al estado de sesión cerrada

**Colaboraciones**:
- **Entrada**: Recibe `cerrarSesion()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Salida**: Navega a `:SESION_CERRADA`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `SesionController.cerrarSesion()`
2. **Cierre**: `SesionController` invalida la sesión activa
3. **Redirección**: `SesionController` → `:SESION_CERRADA.sesionCerrada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Cerrar la sesión activa|`SesionController`|`cerrarSesion()`|
|Redirigir al estado cerrado|`SesionController`|→ `:SESION_CERRADA`|

## características del análisis

### separación de responsabilidades MVC

- **Control**: Coordina el cierre de sesión sin necesidad de vista propia — la acción se dispara directamente desde el panel principal

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `cerrarSesion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### mvc pattern
Solo interviene la clase de control `SesionController`. No se requiere vista propia (acción directa desde el panel) ni entidad (no se accede a datos persistentes).

## referencias

- [Especificación detallada: cerrarSesion()](../../../context/casosDeUso/detalle/investigador/cerrarSesion/cerrarSesion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
