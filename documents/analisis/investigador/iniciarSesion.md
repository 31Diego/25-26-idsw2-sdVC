# FUNIBER GIPF > iniciarSesion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `iniciarSesion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para autenticar al investigador y abrir el panel principal.

## diagrama de colaboración

<div align=center>

|![Análisis: iniciarSesion()](../../../images/analisis/investigador/iniciarSesion-investigador-analisis.svg)|
|-|
|Código fuente: [iniciarSesion.puml](../../../modelosUML/analisis/investigador/iniciarSesion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### IniciarSesionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Recibir la solicitud `iniciarSesion()` desde `:SESION_CERRADA`
- Solicitar la autenticación al controlador mediante `autenticar(usuario, contrasena) : boolean`
- Mostrar el formulario de inicio de sesión al investigador
- Transitar a `:PANEL_PRINCIPAL_ABIERTO` si las credenciales son correctas
- Mostrar error y permanecer si las credenciales son incorrectas

**Colaboraciones**:
- **Entrada**: Desde `:SESION_CERRADA` con `iniciarSesion()`
- **Control**: Se comunica con `AutenticacionController` mediante `autenticar(usuario, contrasena) : boolean`
- **Salida**: Transita a `:PANEL_PRINCIPAL_ABIERTO` (`sesionIniciada()`) o se mantiene en sí misma (`credencialesIncorrectas()`)

### clases de control

#### AutenticacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Recibir `autenticar(usuario, contrasena)` y delegar la verificación de credenciales en el repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `IniciarSesionView`
- **Repositorio**: Delega en `InvestigadorRepository` mediante `buscarPorCredenciales(usuario, contrasena) : Investigador`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Buscar un investigador por sus credenciales mediante `buscarPorCredenciales(usuario, contrasena) : Investigador`

**Colaboraciones**:
- **Control**: Responde a `AutenticacionController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar los datos del investigador autenticado

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. El sistema está en `:SESION_CERRADA`
2. El investigador accede al formulario: `IniciarSesionView` recibe `iniciarSesion()`
3. El investigador introduce sus credenciales y confirma
4. `IniciarSesionView` invoca `autenticar(usuario, contrasena) : boolean` en `AutenticacionController`
5. `AutenticacionController` delega en `InvestigadorRepository.buscarPorCredenciales(usuario, contrasena)` y obtiene un `Investigador` o nulo
6. Si las credenciales son correctas: la vista transita a `:PANEL_PRINCIPAL_ABIERTO` con `sesionIniciada()`
7. Si las credenciales son incorrectas: la vista se mantiene en sí misma con `credencialesIncorrectas()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Autenticar al investigador|`AutenticacionController`|`autenticar(usuario, contrasena) : boolean`|
|Buscar investigador por credenciales|`InvestigadorRepository`|`buscarPorCredenciales(usuario, contrasena) : Investigador`|
|Abrir panel principal|`IniciarSesionView`|`sesionIniciada()`|
|Informar de credenciales incorrectas|`IniciarSesionView`|`credencialesIncorrectas()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el investigador
- **Control**: Solo coordinación de la lógica de autenticación
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `iniciarSesion()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`IniciarSesionView`), lógica de aplicación (`AutenticacionController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: iniciarSesion()](../../../context/casosDeUso/detalle/investigador/iniciarSesion/iniciarSesion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
