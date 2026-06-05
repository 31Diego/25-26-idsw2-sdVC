# abrirOpcionesPerfil — Diseño (Investigador)

## Información del artefacto

- **Proyecto**: FUNIBER GIPF
- **Fase RUP**: Elaboración
- **Disciplina**: Diseño
- **Actor**: Investigador
- **Caso de uso**: abrirOpcionesPerfil()

## Propósito

Mostrar al investigador las opciones disponibles sobre su propio perfil: editar y solicitar eliminación.

## Diagrama de secuencia

![Diagrama de diseño](../../../images/diseño/investigador/abrirOpcionesPerfil-diseño.svg)

[Código PlantUML](../../../modelosUML/diseño/investigador/abrirOpcionesPerfil.puml)

## Participantes

| Análisis | Spring Boot | Rol |
|---|---|---|
| OpcionesPerfilView (azul) | `OpcionesPerfilController` `@Controller` | Recibe GET /perfil/opciones; carga el investigador y devuelve opciones-perfil.html |
| OpcionesPerfilController (amarillo) | `InvestigadorService` `@Service` | Recupera el investigador por username desde el Authentication |
| InvestigadorRepository (naranja) | `InvestigadorRepository` JpaRepository | Ejecuta SELECT por username |
| Investigador (naranja) | `Investigador` `@Entity` | Tabla investigadores en H2 |

## Rutas

| Método | URL | Acción |
|---|---|---|
| GET | /perfil/opciones | Muestra las opciones del propio perfil |

## Decisiones de diseño

- El investigador obtiene sus datos mediante `findByUsername(username)` usando el username del `Authentication` — no depende del ID en la URL.
- `esPropioPeril = true`: el template oculta el botón de cambiar rol y el enlace de vuelta al investigador.
- El controller es compartido con el coordinador (`OpcionesPerfilController`); la vista adapta las opciones mostradas según el rol y `esPropioPeril`.
