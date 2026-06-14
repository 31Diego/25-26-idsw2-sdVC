# FUNIBER GIPF > editarPerfil > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `editarPerfil()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para que el coordinador modifique los datos de su perfil.

## diagrama de colaboración

<div align=center>

|![Análisis: editarPerfil()](../../../images/analisis/coordinador/editarPerfil-analisis.svg)|
|-|
|Código fuente: [editarPerfil.puml](../../../modelosUML/analisis/coordinador/editarPerfil.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### EditarPerfilView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `editarPerfil()` desde `:OPCIONES_PERFIL_ABIERTO`
- Solicitar al controlador los datos actuales del perfil mediante `obtenerPerfil() : Investigador`
- Mostrar el formulario de edición con los datos actuales
- Notificar al controlador los cambios del campo mediante `modificarCampos(datos) : void`
- Solicitar al controlador el guardado mediante `guardarPerfil(datos) : Investigador`
- Navegar de vuelta a las opciones de perfil

**Colaboraciones**:
- **Entrada**: Desde `:OPCIONES_PERFIL_ABIERTO` con `editarPerfil()`
- **Control**: Se comunica con `PerfilController` mediante `obtenerPerfil()`, `modificarCampos(datos)` y `guardarPerfil(datos)`
- **Salida**: Transita a `:OPCIONES_PERFIL_ABIERTO` con `abrirOpcionesPerfil()`

### clases de control

#### PerfilController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `obtenerPerfil()` y delegar en el repositorio la obtención del investigador
- Recibir `modificarCampos(datos)` para procesar cambios en tiempo real
- Recibir `guardarPerfil(datos)` y delegar la actualización al repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `EditarPerfilView`
- **Repositorio**: Delega en `InvestigadorRepository` mediante `obtenerPorId(id) : Investigador` y `actualizar(investigador) : Investigador`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Recuperar el investigador por id mediante `obtenerPorId(id) : Investigador`
- Persistir los cambios en el perfil mediante `actualizar(investigador) : Investigador`

**Colaboraciones**:
- **Control**: Responde a `PerfilController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del perfil del coordinador a editar

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:OPCIONES_PERFIL_ABIERTO`
2. El coordinador solicita editar perfil: `EditarPerfilView` recibe `editarPerfil()`
3. `EditarPerfilView` invoca `obtenerPerfil()` en `PerfilController`
4. `PerfilController` delega en `InvestigadorRepository.obtenerPorId(id)` y obtiene un objeto `Investigador`
5. El formulario se muestra con los datos actuales
6. El coordinador modifica los campos: `EditarPerfilView` invoca `modificarCampos(datos) : void` en `PerfilController`
7. El coordinador confirma el guardado: `EditarPerfilView` invoca `guardarPerfil(datos)` en `PerfilController`
8. `PerfilController` delega en `InvestigadorRepository.actualizar(investigador)` y obtiene el objeto actualizado
9. La vista navega de vuelta → `:OPCIONES_PERFIL_ABIERTO` con `abrirOpcionesPerfil()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Obtener datos actuales del perfil|`PerfilController`|`obtenerPerfil() : Investigador`|
|Acceder al investigador por id|`InvestigadorRepository`|`obtenerPorId(id) : Investigador`|
|Notificar cambios en campos|`PerfilController`|`modificarCampos(datos) : void`|
|Guardar cambios del perfil|`PerfilController`|`guardarPerfil(datos) : Investigador`|
|Persistir actualización del perfil|`InvestigadorRepository`|`actualizar(investigador) : Investigador`|
|Volver a opciones de perfil|`EditarPerfilView`|`abrirOpcionesPerfil()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el coordinador
- **Control**: Solo coordinación de la obtención y persistencia del perfil
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
