# FUNIBER GIPF > editarPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el Investigador modifique sus datos personales.

## diagrama de colaboración

<div align=center>

|![Análisis: editarPerfil()](../../../images/analisis/investigador/editarPerfil-analisis.svg)|
|-|
|Código fuente: [editarPerfil.puml](../../../modelosUML/analisis/investigador/editarPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de edición prellenado con los datos actuales del Investigador
- Capturar los cambios introducidos (nombre, email, username, contraseña)
- Invocar el guardado en el controlador
- Navegar de vuelta a las opciones del perfil tras guardar

**Colaboraciones**:
- **Entrada**: Recibe `editarPerfil()` desde `:OPCIONES_PERFIL_ABIERTO`
- **Control**: Se comunica con `PerfilController`
- **Salida**: Navega a `:OPCIONES_PERFIL_ABIERTO`

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga del perfil actual para prellenar el formulario
- Validar y persistir los cambios introducidos por el Investigador

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarPerfilView`
- **Repositorio**: Delega el acceso a datos a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para obtener y actualizar el perfil

**Colaboraciones**:
- **Control**: Responde a `PerfilController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información editable del perfil
- Encapsular atributos: nombre, email, username, contraseña

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:OPCIONES_PERFIL_ABIERTO` → `EditarPerfilView.editarPerfil()`
2. **Carga del perfil**: `EditarPerfilView` → `PerfilController.obtenerPerfil()` : `Investigador`
3. **Acceso a datos**: `PerfilController` → `InvestigadorRepository.obtenerPorId(id)` : `Investigador`
4. **Edición**: El Investigador modifica los campos del formulario
5. **Guardado**: `EditarPerfilView` → `PerfilController.guardarPerfil(datos)` : `Investigador`
6. **Persistencia**: `PerfilController` → `InvestigadorRepository.actualizar(investigador)` : `Investigador`
7. **Finalización**: `EditarPerfilView` → `:OPCIONES_PERFIL_ABIERTO.abrirOpcionesPerfil()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Prellenar formulario con datos actuales|`PerfilController`|`obtenerPerfil()` → `InvestigadorRepository.obtenerPorId(id)`|
|Capturar cambios del Investigador|`EditarPerfilView`|Formulario con nombre, email, username, contraseña|
|Persistir cambios|`PerfilController`|`guardarPerfil(datos)` → `InvestigadorRepository.actualizar()`|
|Confirmar edición|`EditarPerfilView`|→ `:OPCIONES_PERFIL_ABIERTO`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Investigador
- **Control**: Solo coordinación de la carga y persistencia del perfil
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

- [Especificación detallada: editarPerfil()](../../../context/casosDeUso/detalle/investigador/editarPerfil/editarPerfil.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
