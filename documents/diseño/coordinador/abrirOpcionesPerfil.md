# FUNIBER GIPF > abrirOpcionesPerfil > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## propósito

Detallar el flujo para recuperar y mostrar el perfil del coordinador autenticado.

## diagrama de secuencia

<div align=center>

|![Diseño: abrirOpcionesPerfil()](/images/diseño/abrirOpcionesPerfil-diseño.svg)|
|-|
|Código fuente: [abrirOpcionesPerfil.puml](abrirOpcionesPerfil.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `OpcionesPerfilController` | `@Controller` | `OpcionesPerfilView` |
| `PerfilService` | `@Service` | `PerfilController` |
| `InvestigadorRepository` | `JpaRepository<Investigador, Long>` | `InvestigadorRepository` |
| `Investigador` | `@Entity` | `Investigador` |

## flujo principal

1. Coordinador hace click en "Mi perfil" desde el panel.
2. `OpcionesPerfilController` recibe `GET /perfil`.
3. Llama a `PerfilService.obtenerPerfil()`, que obtiene el usuario actual de Spring Security y busca su `Investigador` por `findByUsuario()`.
4. H2 ejecuta `SELECT * FROM investigadores WHERE usuario_id = ?`.
5. El controlador añade el `Investigador` al `Model` y retorna `opciones-perfil.html`.

## decisiones de diseño

- **Sin ID en la URL**: `obtenerPerfil()` obtiene el usuario de `SecurityContextHolder` — el coordinador solo puede ver su propio perfil.
- **`Investigador` vinculado a `Usuario`**: relación `@OneToOne`. El perfil y las credenciales son entidades separadas.

## referencias

- [Análisis: abrirOpcionesPerfil()](../../analisis/coordinador/abrirOpcionesPerfil.md)
