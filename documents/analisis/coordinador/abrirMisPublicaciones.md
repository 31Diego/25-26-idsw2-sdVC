# FUNIBER GIPF > abrirMisPublicaciones > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirMisPublicaciones()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para listar las publicaciones propias del Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirMisPublicaciones()](/images/analisis/abrirMisPublicaciones-analisis.svg)|
|-|
|Código fuente: [abrirMisPublicaciones.puml](abrirMisPublicaciones.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### MisPublicacionesView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar la lista de publicaciones del Coordinador
- Ofrecer acceso a una publicación propia concreta y a la creación de nuevas
- Navegar de vuelta al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `abrirMisPublicaciones()` desde `:PANEL_PRINCIPAL_ABIERTO`
- **Control**: Se comunica con `PublicacionController`
- **Salida**: Navega a `:MIS_PUBLICACIONES_ABIERTAS` y colaboraciones `AbrirMiPublicacion`, `CrearPublicacion`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención de las publicaciones del Coordinador
- Filtrar por autor las publicaciones del repositorio
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `MisPublicacionesView`
- **Repositorio**: Delega el acceso a datos a `PublicacionRepository`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones
- Proporcionar método para obtener publicaciones por autor

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de una publicación propia
- Encapsular atributos: título, resumen, autores, fecha, área temática

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:PANEL_PRINCIPAL_ABIERTO` → `MisPublicacionesView.abrirMisPublicaciones()`
2. **Listado**: `MisPublicacionesView` → `PublicacionController.obtenerMisPublicaciones()` : `List<Publicacion>`
3. **Acceso a datos**: `PublicacionController` → `PublicacionRepository.obtenerPorAutor(id)` : `List<Publicacion>`
4. **Presentación**: `MisPublicacionesView` → `:MIS_PUBLICACIONES_ABIERTAS.publicacionesCargadas()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Listar publicaciones propias|`MisPublicacionesView`|Coordina con `PublicacionController.obtenerMisPublicaciones()`|
|Abrir publicación propia|`MisPublicacionesView`|→ Colaboración `AbrirMiPublicacion`|
|Crear nueva publicación|`MisPublicacionesView`|→ Colaboración `CrearPublicacion`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación e interacción con el Coordinador
- **Control**: Solo coordinación y filtrado por autor
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirMisPublicaciones()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`MisPublicacionesView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: abrirMisPublicaciones()](../../../context/casosDeUso/detalle/coordinador/abrirMisPublicaciones/abrirMisPublicaciones.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
