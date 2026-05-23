# FUNIBER GIPF > cerrarSesion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `cerrarSesion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para finalizar la sesión activa del Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: cerrarSesion()](/images/analisis/cerrarSesion-analisis.svg)|
|-|
|Código fuente: [cerrarSesion.puml](cerrarSesion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CerrarSesionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Mostrar confirmación de cierre de sesión al Coordinador
- Invocar la confirmación de cierre en el controlador
- Redirigir a la pantalla de sesión cerrada o cancelar el cierre

**Colaboraciones**:
- **Entrada**: Recibe `cerrarSesion()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Control**: Se comunica con `SesionController`
- **Salida**: Navega a `:SESION_CERRADA` o vuelve a `:PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### SesionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de cierre de sesión
- Confirmar la intención del Coordinador antes de proceder
- Invalidar la sesión activa

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CerrarSesionView`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `CerrarSesionView.cerrarSesion()`
2. **Confirmación**: `CerrarSesionView` → `SesionController.confirmarCierre()` : `boolean`
3. **Cierre**: `CerrarSesionView` → `:SESION_CERRADA.sesionCerrada()`
4. **Cancelación**: `CerrarSesionView` → `:PANEL_PRINCIPAL_ABIERTO.cierreCancelado()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Solicitar confirmación de cierre|`CerrarSesionView`|Muestra diálogo de confirmación|
|Procesar cierre de sesión|`SesionController`|`confirmarCierre()`|
|Redirigir al estado cerrado|`CerrarSesionView`|→ `:SESION_CERRADA`|
|Cancelar cierre|`CerrarSesionView`|→ `:PANEL_PRINCIPAL_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del diálogo e interacción con el Coordinador
- **Control**: Solo coordinación del proceso de cierre de sesión

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `cerrarSesion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### mvc pattern
Separación clara entre presentación (`CerrarSesionView`) y lógica de aplicación (`SesionController`). No se requiere entidad ya que no se accede a datos persistentes.

## referencias

- [Especificación detallada: cerrarSesion()](../../../context/casosDeUso/detalle/coordinador/cerrarSesion/cerrarSesion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
