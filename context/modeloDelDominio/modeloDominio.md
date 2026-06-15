

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

# Modelo del dominio

## Objetivo

#### El objetivo de este proyecto es diseñar un solo sistema en donde:
#### 1. Se pueda llevar una gestión de los proyectos asociados con los investigadores, en donde entre otras cosas se podrán revisar los entregables de un proyecto.
#### 2. Estén registradas las diferentes entidades de la red de Funiber (investigador, coordinador, etc), los cuales podrán interactuar entre ellos.


## Vocabulario del proyecto

#### En base a nuestra primera reunión definimos los términos clave del proyecto y los explicamos, a continuación los términos mas importantes del vocabulario

---

###  Estados de un proyecto

| **Término** | **Definición** | **Notas / Ejemplo** |
|--------------|----------------|----------------------|
| **Entidad convocante** | Organización o institución que emite una convocatoria. | Puede ser la Unión Europea, el Ministerio de Ciencia, el Gobierno de Cantabria, etc. |
| **Convocatoria** | Anuncio oficial de una oportunidad de financiación o participación en un proyecto de investigación. | Ejemplo: “Convocatoria Horizonte Europa 2025”. |
| **Propuesta** | Siguiente fase de una convocatoria, en donde se concretan los detalles de la misma | Ejemplo: Investigación sobre enfermedad rara.|
| **Proyecto** | Última fase de una convocatoria, en donde se tienen unos objetivos, un grupo de investigadores/docentes, una fecha de entrega y una documentación del mismo| ----- |

---

###  Usuarios y Roles

| **Término** | **Definición** | **Notas / Ejemplo** |
|--------------|----------------|----------------------|
| **Investigador / Docente** | Usuario registrado que busca, participa o coordina proyectos de investigación. | Incluye personal académico afiliado a FUNIBER. |
| **Perfil de investigador** | Conjunto de datos que describen la especialización, intereses, experiencia y criterios de elegibilidad del usuario. | Se utiliza para filtrar y recomendar convocatorias. |
| **Coordinador** | Rol o proceso encargado de revisar que la información (convocatorias, proyectos, documentación) sea correcta antes de su publicación. | Puede ser un rol administrativo dentro del sistema. |
| **Antena** | Rol o proceso encargado de estar al tanto de nuevas convocatorias | Puede ser un rol administrativo dentro del sistema (coordinador y antena están en el mismo nivel de permisos dentro del sistema)|

---



## Diagramas de clases

#### A continuación el diagrama de clases principal del proyecto, en donde se resalta que la entidad principal es proyecto.

<div align=center>

|![Diagrama de clases principal](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/clases/diagramaClasesProyectoRed.svg)|
|:-:|
|código fuente pendiente de añadir a este repositorio| 
</div>


#### De este diagrama elaboramos dos subdiagramas, los cuales reflejan mejor las necesidades de nuestro sistema y en donde cada uno tiene un contexto muy diferenciado del otro, los cuales son: la gestión de proyectos para el primer diagrama y la red de investigadores para el segundo diagrama.


<div align=center>

|![Diagrama de clases: gestión de proyectos](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/clases/diagramaClasesProyecto.svg)|![Diagrama de clases: red de investigadores](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/clases/diagramaClasesRed.svg)|
|:-:|:-:|
|código fuente pendiente| código fuente pendiente|
</div>


#### De estos dos diagramas elaboramos los siguientes diagramas de estados de las entidades principales de cada uno de ellos, es decir proyecto e investigador/docente, sin olvidarnos de los dos diferentes contextos que puede tener cada entidad.



## Diagramas de estado

#### En los siguientes diagramas de estado se modelan los diferentes comportamientos que puede tener un proyecto.

<div align=center>

|![Estado proyectos: gestión](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/estados/diagramaEstadosProyectoGestion.svg)|![Estado proyectos: red](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/estados/diagramaEstadosProyectoRed.svg)|
|:-:|:-:|
|código fuente pendiente| código fuente pendiente|
</div>


#### En los siguientes diagramas de estado se modelan los diferentes comportamientos que puede tener un investigador.

<div align=center>

|![Estado investigador: gestión](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/estados/diagramaEstadosInvestigadorGestion.svg)|![Estado investigador: red](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/estados/diagramaEstadosInvestigadorRed.svg)|
|:-:|:-:|
|código fuente pendiente| código fuente pendiente|
</div>



## Diagrama de objetos

#### En el siguiente diagrama se implementa una instancia del sistema de gestión de proyectos, en donde hay varios investigadores participando en un proyecto en donde destacan varios aspectos del proyecto como los entregables o las recompensas del mismo hacia los investigadores.

<div align=center>

|![Diagrama de objetos: proyecto](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/objetos/diagramaObjetosProyecto.svg)|
|:-:|
|código fuente pendiente| 
</div>


#### En el siguiente diagrama se implementa una instancia del sistema de red de investigadores, en donde de nuevo hay varios investigadores participando en un proyecto pero en este caso destacan aspectos relacionados con los investigadores como los perfiles de los mismos.

<div align=center>

|![Diagrama de objetos: investigador](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/objetos/diagramaObjetosInvestigador.svg)|
|:-:|
|código fuente pendiente| 
</div>



#### Por último el diagrama de objetos unificado, basado en el diagrama de clases de la red de investigadores + gestión de proyectos


<div align=center>

|![Diagrama de objetos: unificado](https://github.com/31Diego/25-26-idsw2-sdVC/blob/main/context/modeloDelDominio/imagenes/objetos/diagramaObjetosInvestigadorProyecto.svg)|
|:-:|
|código fuente pendiente| 
</div>



