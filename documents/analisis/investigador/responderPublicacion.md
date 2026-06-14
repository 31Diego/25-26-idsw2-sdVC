# FUNIBER GIPF > responderPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `responderPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador publique una respuesta en una publicación existente.

## diagrama de colaboración

<div align=center>

|![Análisis: responderPublicacion()](../../../images/analisis/investigador/responderPublicacion-investigador-analisis.svg)|
|-|
|Código fuente: [responderPublicacion.puml](../../../modelosUML/analisis/investigador/responderPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### ResponderPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `responderPublicacion()` desde `:PUBLICACION_ABIERTA`
- Solicitar el guardado de la respuesta mediante `guardarRespuesta(datos) : Publicacion`
- Mostrar el formulario de respuesta al investigador
- Transitar a `:PUBLICACION_ABIERTA` al finalizar

**Colaboraciones**:
- **Entrada**: Desde `:PUBLICACION_ABIERTA` con `responderPublicacion()`
- **Control**: Se comunica con `PublicacionController` mediante `guardarRespuesta(datos) : Publicacion`
- **Salida**: Transita a `:PUBLICACION_ABIERTA` con `respuestaEnviada()`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `guardarRespuesta(datos)` y delegar la actualización de la publicación en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `ResponderPublicacionView`
- **Repositorio**: Delega en `PublicacionRepository` mediante `actualizar(publicacion) : Publicacion`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Actualizar la publicación con la respuesta añadida mediante `actualizar(publicacion) : Publicacion`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la publicación incluyendo la nueva respuesta añadida

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:PUBLICACION_ABIERTA`
2. El investigador solicita responder: `ResponderPublicacionView` recibe `responderPublicacion()`
3. El investigador escribe su respuesta y confirma
4. `ResponderPublicacionView` invoca `guardarRespuesta(datos) : Publicacion` en `PublicacionController`
5. `PublicacionController` delega en `PublicacionRepository.actualizar(publicacion)` y obtiene la `Publicacion` actualizada
6. La vista transita a `:PUBLICACION_ABIERTA` con `respuestaEnviada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Guardar la respuesta|`PublicacionController`|`guardarRespuesta(datos) : Publicacion`|
|Persistir la publicación con la respuesta|`PublicacionRepository`|`actualizar(publicacion) : Publicacion`|
|Confirmar envío de la respuesta|`ResponderPublicacionView`|`respuestaEnviada()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el investigador
- **Control**: Solo coordinación del guardado de la respuesta
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `responderPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`ResponderPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: responderPublicacion()](../../../context/casosDeUso/detalle/investigador/responderPublicacion/responderPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
