# FUNIBER GIPF > crearPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el investigador publique un nuevo mensaje en el sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: crearPublicacion()](../../../images/analisis/investigador/crearPublicacion-investigador-analisis.svg)|
|-|
|Código fuente: [crearPublicacion.puml](../../../modelosUML/analisis/investigador/crearPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `crearPublicacion()` desde `:MIS_PUBLICACIONES_ABIERTAS`
- Solicitar la validación de datos mediante `validarDatos(datos) : boolean`
- Solicitar el guardado de la publicación mediante `guardarPublicacion(datos) : Publicacion`
- Mostrar el formulario de creación al investigador
- Transitar a `:MIS_PUBLICACIONES_ABIERTAS` o `:PANEL_PRINCIPAL_ABIERTO` al finalizar

**Colaboraciones**:
- **Entrada**: Desde `:MIS_PUBLICACIONES_ABIERTAS` con `crearPublicacion()`
- **Control**: Se comunica con `PublicacionController` mediante `validarDatos(datos) : boolean` y `guardarPublicacion(datos) : Publicacion`
- **Salida**: Transita a `:MIS_PUBLICACIONES_ABIERTAS` (`abrirMisPublicaciones()`) o `:PANEL_PRINCIPAL_ABIERTO` (`abrirPanelPrincipal()`)

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `validarDatos(datos)` y verificar la corrección de los datos del formulario
- Recibir `guardarPublicacion(datos)` y delegar la persistencia en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearPublicacionView`
- **Repositorio**: Delega en `PublicacionRepository` mediante `crear(publicacion) : Publicacion`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Persistir una nueva publicación mediante `crear(publicacion) : Publicacion`

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos de la nueva publicación a persistir

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:MIS_PUBLICACIONES_ABIERTAS`
2. El investigador solicita crear una publicación: `CrearPublicacionView` recibe `crearPublicacion()`
3. El investigador rellena el formulario y confirma
4. `CrearPublicacionView` invoca `validarDatos(datos) : boolean` en `PublicacionController`
5. `CrearPublicacionView` invoca `guardarPublicacion(datos) : Publicacion` en `PublicacionController`
6. `PublicacionController` delega en `PublicacionRepository.crear(publicacion)` y obtiene la `Publicacion` persistida
7. La vista transita con `abrirMisPublicaciones()` o `abrirPanelPrincipal()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Validar datos del formulario|`PublicacionController`|`validarDatos(datos) : boolean`|
|Guardar la publicación|`PublicacionController`|`guardarPublicacion(datos) : Publicacion`|
|Persistir nueva publicación|`PublicacionRepository`|`crear(publicacion) : Publicacion`|
|Volver a mis publicaciones|`CrearPublicacionView`|`abrirMisPublicaciones()`|
|Volver al panel principal|`CrearPublicacionView`|`abrirPanelPrincipal()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el investigador
- **Control**: Solo coordinación de la validación y persistencia
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `crearPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`CrearPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: crearPublicacion()](../../../context/casosDeUso/detalle/investigador/crearPublicacion/crearPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
