# FUNIBER GIPF > iniciarSesion > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `iniciarSesion()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para autenticar al Coordinador y abrir el panel principal.

## diagrama de colaboración

<div align=center>

|![Análisis: iniciarSesion()](/images/analisis/iniciarSesion-analisis.svg)|
|-|
|Código fuente: [iniciarSesion.puml](iniciarSesion.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### IniciarSesionView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de inicio de sesión al Coordinador
- Capturar las credenciales introducidas (usuario y contraseña)
- Invocar la autenticación en el controlador
- Mostrar mensaje de error si las credenciales son incorrectas
- Redirigir al panel principal si la autenticación es correcta

**Colaboraciones**:
- **Entrada**: Recibe `iniciarSesion()` desde `:SESION_CERRADA`
- **Control**: Se comunica con `AutenticacionController`
- **Salida**: Navega a `:PANEL_PRINCIPAL_ABIERTO` o se mantiene en sí misma si falla

### clases de control

#### AutenticacionController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de autenticación del Coordinador
- Validar las credenciales contra el repositorio de usuarios
- Devolver resultado de autenticación (verdadero/falso)

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `IniciarSesionView`
- **Repositorio**: Delega la verificación de credenciales a `UsuarioRepository`

### clases de entidad (entity)

#### UsuarioRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de usuarios
- Proporcionar método para buscar usuario por credenciales

**Colaboraciones**:
- **Control**: Responde a `AutenticacionController`
- **Entidad**: Gestiona instancias de `Usuario`

#### Usuario
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información de un usuario del sistema
- Encapsular atributos: nombre de usuario, contraseña, rol

**Colaboraciones**:
- **Repositorio**: Es gestionado por `UsuarioRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:SESION_CERRADA` → `IniciarSesionView.iniciarSesion()`
2. **Autenticación**: `IniciarSesionView` → `AutenticacionController.autenticar(usuario, contrasena)` : `boolean`
3. **Acceso a datos**: `AutenticacionController` → `UsuarioRepository.buscarPorCredenciales(usuario, contrasena)` : `Usuario`
4. **Éxito**: `IniciarSesionView` → `:PANEL_PRINCIPAL_ABIERTO.sesionIniciada()`
5. **Fallo**: `IniciarSesionView` → muestra error de credenciales incorrectas

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de acceso|`IniciarSesionView`|Captura usuario y contraseña|
|Validar credenciales|`AutenticacionController`|`autenticar(usuario, contrasena)`|
|Acceso a datos de usuarios|`UsuarioRepository`|`buscarPorCredenciales(usuario, contrasena)`|
|Navegar al panel principal|`IniciarSesionView`|→ `:PANEL_PRINCIPAL_ABIERTO`|
|Informar de error en credenciales|`IniciarSesionView`|`credencialesIncorrectas()`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la lógica de autenticación
- **Entidad**: Solo datos y reglas de negocio del usuario

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
`UsuarioRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`IniciarSesionView`), lógica de aplicación (`AutenticacionController`) y datos (`Usuario`, `UsuarioRepository`).

## referencias

- [Especificación detallada: iniciarSesion()](../../../context/casosDeUso/detalle/coordinador/iniciarSesion/iniciarSesion.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
