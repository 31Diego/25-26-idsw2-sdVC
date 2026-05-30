# FUNIBER GIPF > editarPerfil > Diseño

## información del artefacto

- **Proyecto**: FUNIBER GIPF - Plataforma Interna de Investigación
- **Fase**: Diseño
- **Disciplina**: Análisis y Diseño
- **Actor**: Coordinador
- **Versión**: 1.0
- **Fecha**: 2026-05-30
- **Autor**: Diego Martínez

## propósito

Detallar el flujo para cargar el formulario de edición del perfil propio y persistir los cambios.

## diagrama de secuencia

<div align=center>

|![Diseño: editarPerfil()](/images/diseño/editarPerfil-diseño.svg)|
|-|
|Código fuente: [editarPerfil.puml](editarPerfil.puml)|

</div>

## participantes

| Participante | Tipo | Correspondencia análisis |
|-|-|-|
| `EditarPerfilController` | `@Controller` | `EditarPerfilView` |
| `PerfilService` | `@Service` | `PerfilController` (compartido con abrirOpcionesPerfil) |
| `InvestigadorRepository` | `JpaRepository<Investigador, Long>` | `InvestigadorRepository` |
| `Investigador` | `@Entity` | `Investigador` |

## flujos

### cargar formulario
1. Coordinador pulsa "Editar perfil" desde `/perfil`.
2. `GET /perfil/editar` → `PerfilService.obtenerPerfil()` → carga datos actuales.
3. Thymeleaf pre-rellena el formulario con los datos del `Investigador`.

### guardar cambios
1. Coordinador modifica los campos y pulsa "Guardar".
2. `POST /perfil` con los datos del formulario.
3. `PerfilService.guardarPerfil(datos)` obtiene el perfil actual, actualiza sus campos y llama a `save()`.
4. H2 ejecuta `UPDATE investigadores SET ... WHERE id = ?`.
5. Redirect a `GET /perfil` (patrón PRG).

## decisiones de diseño

- **`PerfilService` compartido**: `abrirOpcionesPerfil` y `editarPerfil` comparten el mismo servicio — corresponde al único `PerfilController` del análisis.
- **PRG (Post/Redirect/Get)**: tras guardar, redirect a `/perfil` para evitar reenvío del formulario al recargar.

## referencias

- [Análisis: editarPerfil()](../../analisis/coordinador/editarPerfil.md)
