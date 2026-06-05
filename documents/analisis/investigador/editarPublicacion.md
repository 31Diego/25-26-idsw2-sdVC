# FUNIBER GIPF > editarPublicacion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarPublicacion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador modifique el contenido de una publicación propia.

## diagrama de colaboración

<div align=center>

|![Análisis: editarPublicacion()](../../../images/analisis/investigador/editarPublicacion-analisis.svg)|
|-|
|Código fuente: [editarPublicacion.puml](../../../modelosUML/analisis/investigador/editarPublicacion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarPublicacionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición prellenado con los datos actuales de la publicación
- Capturar los cambios introducidos por el Investigador
- Invocar el guardado en el controlador
- Navegar de vuelta al detalle de la publicación

**Colaboraciones**:
- **Entrada**: Recibe `editarPublicacion()` desde `:MI_PUBLICACION_ABIERTA`
- **Control**: Se comunica con `PublicacionController`
- **Salida**: Navega a `:MI_PUBLICACION_ABIERTA`

### clases de control

#### PublicacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga de la publicación actual para prellenar el formulario
- Validar y persistir los cambios introducidos

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarPublicacionView`
- **Repositorio**: Delega el acceso a datos a `PublicacionRepository`

### clases de entidad (entity)

#### PublicacionRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de publicaciones
- Proporcionar métodos para obtener y actualizar una publicación

**Colaboraciones**:
- **Control**: Responde a `PublicacionController`
- **Entidad**: Gestiona instancias de `Publicacion`

#### Publicacion
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información editable de la publicación propia
- Encapsular atributos: título, contenido

**Colaboraciones**:
- **Repositorio**: Es gestionado por `PublicacionRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:MI_PUBLICACION_ABIERTA` → `EditarPublicacionView.editarPublicacion()`
2. **Carga de publicación**: `EditarPublicacionView` → `PublicacionController.obtenerPublicacion(id)` : `Publicacion`
3. **Acceso a datos**: `PublicacionController` → `PublicacionRepository.obtenerPorId(id)` : `Publicacion`
4. **Edición**: El Investigador modifica los campos
5. **Guardado**: `EditarPublicacionView` → `PublicacionController.guardarPublicacion(datos)` : `Publicacion`
6. **Persistencia**: `PublicacionController` → `PublicacionRepository.actualizar(publicacion)` : `Publicacion`
7. **Finalización**: `EditarPublicacionView` → `:MI_PUBLICACION_ABIERTA.edicionFinalizada()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Prellenar formulario|`PublicacionController`|`obtenerPublicacion(id)` → `PublicacionRepository.obtenerPorId(id)`|
|Capturar cambios|`EditarPublicacionView`|Formulario prellenado|
|Persistir cambios|`PublicacionController`|`guardarPublicacion(datos)` → `PublicacionRepository.actualizar()`|
|Confirmar edición|`EditarPublicacionView`|→ `:MI_PUBLICACION_ABIERTA`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Investigador
- **Control**: Solo coordinación de la carga y persistencia
- **Entidad**: Solo datos y reglas de negocio de la publicación

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `editarPublicacion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`PublicacionRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EditarPublicacionView`), lógica de aplicación (`PublicacionController`) y datos (`Publicacion`, `PublicacionRepository`).

## referencias

- [Especificación detallada: editarPublicacion()](../../../context/casosDeUso/detalle/investigador/editarPublicacion/editarPublicacion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
