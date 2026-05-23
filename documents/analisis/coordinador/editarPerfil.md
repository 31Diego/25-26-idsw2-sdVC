# FUNIBER GIPF > editarPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para modificar los datos del perfil de un investigador.

## diagrama de colaboración

<div align=center>

|![Análisis: editarPerfil()](/images/analisis/editarPerfil-analisis.svg)|
|-|
|Código fuente: [editarPerfil.puml](editarPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición del perfil con los datos actuales
- Recuperar los datos actuales del perfil a través del controlador
- Capturar los cambios introducidos por el Coordinador
- Invocar el guardado de datos en el controlador
- Volver a las opciones de perfil al finalizar

**Colaboraciones**:
- **Entrada**: Recibe `editarPerfil()` desde `:OPCIONES_PERFIL_ABIERTO`
- **Control**: Se comunica con `PerfilController`
- **Salida**: Navega a `:OPCIONES_PERFIL_ABIERTO`

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la obtención del perfil actual para su edición
- Validar y persistir los datos modificados del perfil
- Servir como intermediario entre la vista y el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarPerfilView`
- **Repositorio**: Delega operaciones de datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener un investigador por identificador
- Persistir los cambios en el perfil del investigador

**Colaboraciones**:
- **Control**: Responde a `PerfilController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información completa de un investigador
- Encapsular atributos editables del perfil
- Mantener la integridad de los datos

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:OPCIONES_PERFIL_ABIERTO` → `EditarPerfilView.editarPerfil()`
2. **Carga de datos**: `EditarPerfilView` → `PerfilController.obtenerPerfil()` : `Investigador`
3. **Acceso a datos**: `PerfilController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Edición**: El Coordinador modifica los datos en el formulario
5. **Guardado**: `EditarPerfilView` → `PerfilController.guardarPerfil(datos)` : `Investigador`
6. **Persistencia**: `PerfilController` → `InvestigadorRepository.actualizar(investigador)` : `Investigador`
7. **Finalización**: `EditarPerfilView` → `:OPCIONES_PERFIL_ABIERTO.abrirOpcionesPerfil()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar datos actuales del perfil|`EditarPerfilView`|Coordina con `PerfilController.obtenerPerfil()`|
|Permitir modificación de datos|`EditarPerfilView`|Captura cambios en el formulario|
|Persistir cambios|`PerfilController`|`guardarPerfil(datos)` → `InvestigadorRepository.actualizar()`|
|Volver a opciones de perfil|`EditarPerfilView`|→ `:OPCIONES_PERFIL_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la obtención y persistencia de datos
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `editarPerfil()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`EditarPerfilView`), lógica de aplicación (`PerfilController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: editarPerfil()](../../../context/casosDeUso/detalle/coordinador/editarPerfil/editarPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
