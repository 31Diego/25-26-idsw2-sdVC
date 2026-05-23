# FUNIBER GIPF > crearPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para registrar una nueva publicación del Coordinador.

## diagrama de colaboración

<div align=center>

|![Análisis: crearPublicacion()](/images/analisis/crearPublicacion-analisis.svg)|
|-|
|Código fuente: [crearPublicacion.puml](crearPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de creación de publicación al Coordinador
- Capturar los datos de la nueva publicación: título, resumen, contenido, área temática
- Invocar el guardado en el controlador
- Navegar a la lista de publicaciones propias o al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `crearPublicacion()` desde `:MIS_PUBLICACIONES_ABIERTAS`
- **Control**: Se comunica con `PublicacionController`
- **Salida**: Navega a `:MIS_PUBLICACIONES_ABIERTAS` o `:PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de creación de la nueva publicación
- Validar los datos recibidos del formulario
- Persistir la nueva publicación a través del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearPublicacionView`
- **Repositorio**: Delega la persistencia a `PublicacionRepository`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones
- Proporcionar método para crear una nueva publicación

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de la nueva publicación
- Encapsular atributos: título, resumen, contenido, autores, fecha, área temática

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:MIS_PUBLICACIONES_ABIERTAS` → `CrearPublicacionView.crearPublicacion()`
2. **Captura**: El Coordinador rellena el formulario con los datos de la publicación
3. **Guardado**: `CrearPublicacionView` → `PublicacionController.guardarPublicacion(datos)` : `Publicacion`
4. **Persistencia**: `PublicacionController` → `PublicacionRepository.crear(publicacion)` : `Publicacion`
5. **Finalización**: `CrearPublicacionView` → `:MIS_PUBLICACIONES_ABIERTAS.abrirMisPublicaciones()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de creación|`CrearPublicacionView`|Captura datos de la nueva publicación|
|Persistir nueva publicación|`PublicacionController`|`guardarPublicacion(datos)` → `PublicacionRepository.crear()`|
|Confirmar creación|`CrearPublicacionView`|→ `:MIS_PUBLICACIONES_ABIERTAS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
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

- [Especificación detallada: crearPublicacion()](../../../context/casosDeUso/detalle/coordinador/crearPublicacion/crearPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
