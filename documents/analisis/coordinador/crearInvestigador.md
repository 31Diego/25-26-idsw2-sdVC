# FUNIBER GIPF > crearInvestigador > Análisis

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Análisis
- **Disciplina**: Análisis y Diseño
- **Versión**: 1.0
- **Fecha**: 2026-05-23
- **Autor**: Diego Martínez

## propósito

Análisis de colaboración del caso de uso `crearInvestigador()` mediante el patrón MVC, identificando las clases de análisis, sus responsabilidades y colaboraciones necesarias para registrar un nuevo investigador en la plataforma.

## diagrama de colaboración

<div align=center>

|![Análisis: crearInvestigador()](/images/analisis/crearInvestigador-analisis.svg)|
|-|
|Código fuente: [crearInvestigador.puml](crearInvestigador.puml)|

</div>

## clases de análisis identificadas

### clases de vista (boundary)

#### CrearInvestigadorView
**Estereotipo**: Vista (Boundary)  
**Responsabilidades**:
- Presentar el formulario de creación de investigador al Coordinador
- Capturar los datos del nuevo investigador
- Invocar el guardado en el controlador
- Navegar a la lista de investigadores o al panel principal

**Colaboraciones**:
- **Entrada**: Recibe `crearInvestigador()` desde `:INVESTIGADORES_ABIERTOS`
- **Control**: Se comunica con `InvestigadorController`
- **Salida**: Navega a `:INVESTIGADORES_ABIERTOS` o `:PANEL_PRINCIPAL_ABIERTO`

### clases de control

#### InvestigadorController
**Estereotipo**: Control  
**Responsabilidades**:
- Coordinar el proceso de creación del nuevo investigador
- Validar los datos recibidos del formulario
- Persistir el nuevo investigador a través del repositorio

**Colaboraciones**:
- **Vista**: Responde a solicitudes de `CrearInvestigadorView`
- **Repositorio**: Delega la persistencia a `InvestigadorRepository`

### clases de entidad (entity)

#### InvestigadorRepository
**Estereotipo**: Entidad  
**Responsabilidades**:
- Abstraer el acceso a datos de investigadores
- Proporcionar método para crear un nuevo investigador

**Colaboraciones**:
- **Control**: Responde a `InvestigadorController`
- **Entidad**: Gestiona instancias de `Investigador`

#### Investigador
**Estereotipo**: Entidad  
**Responsabilidades**:
- Representar la información del nuevo investigador
- Encapsular atributos: nombre, apellidos, correo, área, institución

**Colaboraciones**:
- **Repositorio**: Es gestionado por `InvestigadorRepository`

## flujo de colaboración

### secuencia de operaciones

1. **Inicio**: `:INVESTIGADORES_ABIERTOS` → `CrearInvestigadorView.crearInvestigador()`
2. **Captura**: El Coordinador rellena el formulario con los datos del investigador
3. **Guardado**: `CrearInvestigadorView` → `InvestigadorController.guardarInvestigador(datos)` : `Investigador`
4. **Persistencia**: `InvestigadorController` → `InvestigadorRepository.crear(investigador)` : `Investigador`
5. **Finalización**: `CrearInvestigadorView` → `:INVESTIGADORES_ABIERTOS.abrirInvestigadores()`

## correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Método/Colaboración|
|-|-|-|
|Presentar formulario de creación|`CrearInvestigadorView`|Captura datos del nuevo investigador|
|Persistir nuevo investigador|`InvestigadorController`|`guardarInvestigador(datos)` → `InvestigadorRepository.crear()`|
|Confirmar creación|`CrearInvestigadorView`|→ `:INVESTIGADORES_ABIERTOS`|

## características del análisis

### separación de responsabilidades MVC

- **Vista**: Solo presentación del formulario e interacción con el Coordinador
- **Control**: Solo coordinación de la validación y persistencia
- **Entidad**: Solo datos y reglas de negocio del investigador

### agnóstico tecnológicamente

- No especifica tecnología de interfaz de usuario
- No asume implementación específica de base de datos
- Mantiene independencia de frameworks

### trazabilidad completa

- **Origen**: Caso de uso detallado `crearInvestigador()`
- **Destino**: Base para diseño arquitectónico
- **Conexión**: Diagrama de estados → Análisis de colaboración

## patrones aplicados

### repository pattern
`InvestigadorRepository` abstrae el acceso a datos, permitiendo diferentes implementaciones sin afectar al controlador.

### mvc pattern
Separación clara entre presentación (`CrearInvestigadorView`), lógica de aplicación (`InvestigadorController`) y datos (`Investigador`, `InvestigadorRepository`).

## referencias

- [Especificación detallada: crearInvestigador()](../../../context/casosDeUso/detalle/coordinador/crearInvestigador/crearInvestigador.md)
- [Modelo del dominio](../../../context/modeloDelDominio/modeloDominio.md)
