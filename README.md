<h1 align="center">FUNIBER GIPF — Plataforma Interna de Investigación</h1>

<div align="center">

[![](https://img.shields.io/badge/-Inicio-0A3B64?style=for-the-badge&logo=github&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/README.md)
[![](https://img.shields.io/badge/-Modelo_del_Dominio-0A3B64?style=for-the-badge&logo=drawio&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/modeloDominio.md)
[![](https://img.shields.io/badge/-Actores_y_CdU-0A3B64?style=for-the-badge&logo=uml&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/casosDeUso.md)
[![](https://img.shields.io/badge/-Diagramas_de_Contexto-0A3B64?style=for-the-badge&logo=sitemap&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/diagramas/diagramasContexto.md)
[![](https://img.shields.io/badge/-Detalle_de_CdU-0A3B64?style=for-the-badge&logo=readme&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/casosDeUso/detalle/detalladoYPrototipos.md)
[![](https://img.shields.io/badge/-Análisis-0A3B64?style=for-the-badge&logo=databricks&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/analisis/analisis.md)
[![](https://img.shields.io/badge/-Diseño-0A3B64?style=for-the-badge&logo=figma&logoColor=white)](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/documents/dise%C3%B1o/dise%C3%B1o.md)

</div>

---

## ¿Qué hace el sistema?

FUNIBER GIPF conecta a los investigadores de la red FUNIBER con convocatorias de financiación y les permite colaborar en proyectos de investigación desde la propuesta hasta la entrega de resultados.

El sistema distingue dos roles:

- **Coordinador** — visión global: gestiona proyectos, investigadores, convocatorias, recompensas y publicaciones.
- **Investigador** — acceso a sus propios proyectos y entregables; puede publicar y responder publicaciones.

---

## Tecnologías

| Tecnología | Versión | Papel en el sistema |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.2.5 | Framework de aplicación web (MVC, inyección de dependencias) |
| Spring Data JPA + Hibernate | incluido en Boot | Capa de persistencia: mapeo objeto-relacional y acceso a datos |
| Spring Security | incluido en Boot | Autenticación de usuarios y control de acceso por rol |
| H2 | incluido en Boot | Base de datos embebida en archivo local; sin instalación externa |
| Thymeleaf | incluido en Boot | Motor de plantillas HTML renderizadas en el servidor |
| Lombok | incluido en Boot | Reducción de código repetitivo (getters, setters, constructores) |
| Maven | 3.x | Gestión de dependencias y herramienta de build |

---

## Requisitos y ejecución

### 1. Java 17 (JDK)

| Sistema | Comando de instalación |
|---|---|
| **Windows** | `winget install Microsoft.OpenJDK.17` |
| **Linux — Debian / Ubuntu** | `sudo apt install openjdk-17-jdk` |
| **Linux — Fedora / RHEL** | `sudo dnf install java-17-openjdk-devel` |
| **macOS** | `brew install openjdk@17` |

Verificar: `java -version` → debe mostrar `openjdk 17.x.x`

### 2. Maven

| Sistema | Comando de instalación |
|---|---|
| **Windows** | `winget install Apache.Maven` |
| **Linux — Debian / Ubuntu** | `sudo apt install maven` |
| **Linux — Fedora / RHEL** | `sudo dnf install maven` |
| **macOS** | `brew install maven` |

Verificar: `mvn -version` → debe mostrar `Apache Maven 3.x.x`

### 3. Clonar y ejecutar

```bash
git clone <url-del-repositorio>
cd <nombre-de-la-carpeta>
mvn spring-boot:run
```

Abrir en el navegador: **http://localhost:8080**

> La base de datos se crea y puebla automáticamente en el primer arranque. No se requiere ninguna configuración adicional.

### Credenciales de prueba

| Usuario | Contraseña | Rol |
|---|---|---|
| `coordinador` | `coordinador` | Coordinador |
| `investigador` | `investigador` | Investigador |

---

## Estructura del repositorio

| Carpeta / Archivo | Contenido |
|---|---|
| [`src/`](src/) | Código fuente Java: controllers, services, models, repositories y configuración de seguridad |
| [`documents/`](documents/) | Documentación del proyecto: análisis, diseño, registro de progreso y notas de revisión |
| [`modelosUML/`](modelosUML/) | Diagramas en formato PlantUML (`.puml`): análisis y diseño para cada caso de uso |
| [`images/`](images/) | Diagramas renderizados en SVG, referenciados desde los documentos |
| [`context/`](context/) | Modelo del dominio, casos de uso detallados, prototipos y ejemplos de referencia |
| [`conversations/`](conversations/) | Registro cronológico de sesiones de trabajo con IA durante el desarrollo |
| [`archivos/`](archivos/) | Archivos adjuntos subidos por los usuarios a través de la aplicación |
| [`pom.xml`](pom.xml) | Configuración de Maven: dependencias, plugins y versión de Java |
| [`QUE_HACE.md`](QUE_HACE.md) | Descripción inicial del sistema (primer commit, no se modifica) |
| [`conversation-log.md`](conversation-log.md) | Log de conversaciones con IA, requerido por el curso |
| [`documents/progreso.md`](documents/progreso.md) | Tabla de seguimiento del estado de cada caso de uso |
