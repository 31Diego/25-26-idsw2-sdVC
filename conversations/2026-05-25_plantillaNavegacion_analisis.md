# Sesión — 2026-05-25 · Plantilla navegación · análisis

## [~20:00] Duda sobre estados vs colaboraciones en los diagramas

**Prompt:** "he estado revisando los diferentes archivos de coordinador que hicimos el otro dia, y no entiendo muy bien porque en algunos diagramas la vista lleva a estados tipo 'PANEL_PRINCIPAL_ABIERTO' o 'CONVOCATORIAS_ABIERTAS', mientras que con otro caso de uso lleva a estados tipo ':Collaboration nombre de caso de uso', osea que diferencia hay entre esos casos de uso que te llevan a un u otro estado?"

**Resultado:** Explicación de los tres patrones de salida en los diagramas de colaboración:
- `:ESTADO_EN_MAYÚSCULAS` — el CU terminó su trabajo, la pantalla está lista (resultado propio) o es navegación de vuelta (sin lógica nueva)
- `:Collaboration NombreCasoDeUso` — el usuario lanza otro caso de uso completo que tiene su propia View + Controller + Repository

Regla práctica: si el destino **hace trabajo** (consulta datos, ejecuta lógica) → `:Collaboration`; si simplemente **muestra una pantalla** ya cargada o sin lógica → `:ESTADO`.

**Decisión:** Aceptada la explicación.

---

## [20:20] Generar plantilla visual de los tres tipos de salida

**Prompt:** "vale puedes generar un diagrama de ese estilo, que me sirva como plantilla?"

**Resultado:** Creado `context/ejemplos/analisis/plantillaNavegacion.puml` con los tres patrones comentados:
- Salida A: `:MI_ESTADO_ABIERTO` — resultado del CU
- Salida B: `:ESTADO_ANTERIOR` — volver atrás sin lógica
- Salida C: `:Collaboration OtroCasoDeUso` — lanzar otro CU completo

**Decisión:** Aceptado. El usuario lo usará para revisar y corregir los diagramas de coordinador ya generados.

---
