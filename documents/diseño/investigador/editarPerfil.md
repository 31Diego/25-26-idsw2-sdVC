# editarPerfil — Diseño (Investigador)

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: editarPerfil()

## Propósito

Presentar al investigador un formulario editable con sus propios datos de perfil. No incluye el campo `rol` (solo el coordinador puede alterarlo, y solo al editar el perfil de otro investigador).

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/editarPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/editarPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| EditarPerfilView (azul) | `EditarPerfilController` `@Controller` | Recibe GET y POST en /perfil/editar |
| EditarPerfilController (amarillo) | `InvestigadorService` `@Service` | Recupera el investigador por username; lo actualiza sin tocar el rol |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | Ejecuta SELECT por username y UPDATE por id |
| Investigador (naranja) | `Investigador` `@Entity` | Tabla investigadores en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /perfil/editar | Muestra el formulario del propio perfil |
| POST | /perfil/editar | Guarda los cambios (sin modificar rol) |

## Decisiones de diseño

- El campo `rol` no aparece en el formulario: el investigador no puede cambiar su propio rol.
- El controller es compartido con el coordinador (`EditarPerfilController`); la plantilla `editar-perfil.html` oculta el campo `rol` cuando `esPropioPeril = true`.
- Tras guardar, redirige a `/perfil/opciones`.
