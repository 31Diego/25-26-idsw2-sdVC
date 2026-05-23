# FUNIBER GIPF > abrirPanelPrincipal > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `abrirPanelPrincipal()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para presentar el panel principal del Coordinador y ofrecer acceso a todas las funcionalidades del sistema.

## diagrama de colaboración

<div align=center>

|![Análisis: abrirPanelPrincipal()](/images/analisis/abrirPanelPrincipal-analisis.svg)|
|-|
|Código fuente: [abrirPanelPrincipal.puml](abrirPanelPrincipal.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### PanelPrincipalView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Mostrar el panel principal del Coordinador
- Ofrecer acceso a las diferentes secciones del sistema: proyectos, investigadores, convocatorias, recompensas, publicaciones, perfil, carga de trabajo
- Permitir la navegación entre todas las funcionalidades principales
- Gestionar el cierre de sesión

**Colaboraciones**:
- **Entrada**: Recibe `abrirPanelPrincipal()` desde múltiples estados (investigadores, carga de trabajo, perfil, publicaciones, convocatorias, recompensas, proyectos)
- **Control**: Se comunica con `PanelController`
- **Salida**: Navega a todas las colaboraciones principales del sistema

### clases de control

#### PanelController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar la carga del panel principal
- Inicializar el estado de la sesión del Coordinador

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `PanelPrincipalView`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: Desde cualquier estado → `PanelPrincipalView.abrirPanelPrincipal()`
2. **Carga**: `PanelPrincipalView` → `PanelController.cargarPanel()` : `void`
3. **Presentación**: `PanelPrincipalView` → `:PANEL_PRINCIPAL_ABIERTO.panelMostrado()`
4. **Navegación**: `PanelPrincipalView` ofrece acceso a proyectos, investigadores, convocatorias, recompensas, publicaciones, perfil, carga de trabajo y cierre de sesión

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Mostrar panel principal|`PanelPrincipalView`|Coordina con `PanelController.cargarPanel()`|
|Acceder a proyectos|`PanelPrincipalView`|→ Colaboración `AbrirProyectos`|
|Acceder a investigadores|`PanelPrincipalView`|→ Colaboración `AbrirInvestigadores`|
|Acceder a convocatorias|`PanelPrincipalView`|→ Colaboración `AbrirConvocatorias`|
|Acceder a recompensas|`PanelPrincipalView`|→ Colaboración `AbrirRecompensas`|
|Acceder a publicaciones|`PanelPrincipalView`|→ Colaboraciones `AbrirPublicaciones` / `AbrirMisPublicaciones`|
|Acceder a perfil|`PanelPrincipalView`|→ Colaboración `AbrirOpcionesPerfil`|
|Acceder a carga de trabajo|`PanelPrincipalView`|→ Colaboración `AbrirOpcionesCargaTrabajo`|
|Cerrar sesión|`PanelPrincipalView`|→ Colaboración `CerrarSesion`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del panel e interacción con el Coordinador
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
Separación clara entre presentación (`PanelPrincipalView`) y lógica de aplicación (`PanelController`). El panel actúa como hub de navegación hacia el resto del sistema.

## referencias

- [Especificación detallada: abrirPanelPrincipal()](../../../context/casosDeUso/detalle/coordinador/abrirPanelPrincipal/abrirPanelPrincipal.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
