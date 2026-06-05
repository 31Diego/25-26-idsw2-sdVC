# FUNIBER GIPF > abrirMiPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirMiPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el detalle de una publicación propia al Investigador y ofrecer opciones de gestión.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirMiPublicacion()](../../../images/analisis/investigador/abrirMiPublicacion-analisis.svg)|
|-|
|Código fuente: [abrirMiPublicacion.puml](../../../modelosUML/analisis/investigador/abrirMiPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### MiPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el detalle de la publicación propia al Investigador
- Mostrar contenido, fecha y respuestas recibidas
- Ofrecer opciones de gestión: editar y eliminar
- Navegar de vuelta al listado de mis publicaciones

**Colaboraciones**:
- **Entrada**: Recibe `abrirMiPublicacion(id)` desde `:MIS_PUBLICACIONES_ABIERTAS`
- **Control**: Se comunica con `PublicacionController`
- **Salida**: Navega a `:MI_PUBLICACION_ABIERTA` y colaboraciones `EditarPublicacion`, `EliminarPublicacion`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del detalle de la publicación propia
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `MiPublicacionView`
- **Repositorio**: Delega el acceso a datos a `PublicacionRepository`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones
- Proporcionar método para obtener una publicación por identificador

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de una publicación propia
- Encapsular atributos: título, contenido, autor, fecha, respuestas

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:MIS_PUBLICACIONES_ABIERTAS` → `MiPublicacionView.abrirMiPublicacion(id)`
2. **Obtención de datos**: `MiPublicacionView` → `PublicacionController.obtenerPublicacion(id)` : `Publicacion`
3. **Acceso a datos**: `PublicacionController` → `PublicacionRepository.obtenerPorId(id)` : `Publicacion`
4. **Presentación**: `MiPublicacionView` → `:MI_PUBLICACION_ABIERTA.publicacionMostrada()`
5. **Navegación**: El Investigador puede editar, eliminar o volver al listado

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar detalle de la publicación propia|`MiPublicacionView`|Coordina con `PublicacionController.obtenerPublicacion(id)`|
|Datos completos|`Publicacion`|Encapsula todos los atributos|
|Acceso a datos|`PublicacionRepository`|`obtenerPorId(id)`|
|Editar publicación|`MiPublicacionView`|→ Colaboración `EditarPublicacion`|
|Eliminar publicación|`MiPublicacionView`|→ Colaboración `EliminarPublicacion`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Investigador
- **Control**: Solo coordinación y obtención del detalle
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirMiPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`MiPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: abrirMiPublicacion()](../../../context/casosDeUso/detalle/investigador/abrirMiPublicacion/abrirMiPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
