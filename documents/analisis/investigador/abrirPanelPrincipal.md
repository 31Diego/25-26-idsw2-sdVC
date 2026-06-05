# FUNIBER GIPF > abrirPanelPrincipal > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-06-05
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirPanelPrincipal()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el panel principal del Investigador y ofrecer acceso a sus funcionalidades.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirPanelPrincipal()](../../../images/analisis/investigador/abrirPanelPrincipal-analisis.svg)|
|-|
|Código fuente: [abrirPanelPrincipal.puml](../../../modelosUML/analisis/investigador/abrirPanelPrincipal.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### PanelPrincipalView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Mostrar el panel principal del Investigador
- Ofrecer acceso a las secciones disponibles: proyectos, publicaciones, mis publicaciones, recompensas, investigadores, perfil, carga de trabajo
- Permitir la navegación entre todas las funcionalidades
- Gestionar el cierre de sesión

**Colaboraciones**:
- **Entrada**: Recibe `abrirPanelPrincipal()` desde múltiples estados (proyectos, publicaciones, mis publicaciones, recompensas, investigadores, opciones de perfil, carga de trabajo)
- **Control**: Se comunica con `PanelController`
- **Salida**: Navega a todas las colaboraciones del sistema disponibles para el Investigador

### clases de control

#### PanelController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga del panel principal
- Inicializar el estado de la sesión del Investigador

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `PanelPrincipalView`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: Desde cualquier estado → `PanelPrincipalView.abrirPanelPrincipal()`
2. **Carga**: `PanelPrincipalView` → `PanelController.cargarPanel()` : `void`
3. **Presentación**: `PanelPrincipalView` → `:PANEL_PRINCIPAL_ABIERTO.panelMostrado()`
4. **Navegación**: `PanelPrincipalView` ofrece acceso a proyectos, publicaciones, mis publicaciones, recompensas, investigadores, perfil, carga de trabajo y cierre de sesión

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar panel principal|`PanelPrincipalView`|Coordina con `PanelController.cargarPanel()`|
|Acceder a proyectos|`PanelPrincipalView`|→ Colaboración `AbrirProyectos`|
|Acceder a publicaciones|`PanelPrincipalView`|→ Colaboraciones `AbrirPublicaciones` / `AbrirMisPublicaciones`|
|Acceder a recompensas|`PanelPrincipalView`|→ Colaboración `AbrirRecompensas`|
|Acceder a investigadores|`PanelPrincipalView`|→ Colaboración `AbrirInvestigadores`|
|Acceder a perfil|`PanelPrincipalView`|→ Colaboración `AbrirOpcionesPerfil`|
|Acceder a carga de trabajo|`PanelPrincipalView`|→ Colaboración `AbrirOpcionesCargaTrabajo`|
|Cerrar sesión|`PanelPrincipalView`|→ Colaboración `CerrarSesion`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del panel e interacción con el Investigador
- **Control**: Solo coordinación de la carga del panel

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `abrirPanelPrincipal()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### mvc pattern
Separación clara entre presentación (`PanelPrincipalView`) y lógica de aplicación (`PanelController`). El panel actúa como hub de navegación hacia el resto del sistema. El Investigador no tiene acceso a convocatorias.

## referencias

- [Especificación detallada: abrirPanelPrincipal()](../../../context/casosDeUso/detalle/investigador/abrirPanelPrincipal/abrirPanelPrincipal.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
