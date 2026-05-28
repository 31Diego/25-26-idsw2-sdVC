# Sesión — 2026-05-28 · Tecnología · stack

## [~inicio] Orientación inicial

**Prompt:** "buenas claude"

**Resultado:** Lectura de `conversation-log.md` y `priorizacionCasosDeUso.md`. Estado del proyecto: análisis coordinador completo (46 SVGs), pendiente análisis investigador y fase diseño.

**Decisión:** Continuar hacia diseño del coordinador.

---

## [~medio] Consulta sobre tecnología

**Prompt:** "vale, primero quiero proceder con el diseño de los casos de uso de coordinador, después el desarrollo, etc" + "me refería más a la tecnología, para este proyecto no tengo que escalarlo tanto, tan solo tiene que funcionar, en localhost si es más sencillo, mira el ejemplo dentro de context-ejemplos-diseño"

**Resultado:** Leído `context/ejemplos/diseño/abrirAulas.md` y `abrirAulas.puml`. Stack identificado del ejemplo: React (frontend, puerto 5173), FastAPI + Uvicorn (backend, puerto 8000), SQLAlchemy (ORM), Pydantic (schemas/DTOs), Bearer Token JWT (auth), SQLite (base de datos localhost).

**Decisión:** Stack aceptado para el proyecto.

---

## [~final] Profundización en tecnología

**Prompt:** "podemos profundizar más en la tecnología"

**Resultado:** Explicación detallada del stack:
- FastAPI: endpoints REST con decoradores, Swagger UI en `/docs`
- SQLAlchemy: ORM, mapeo clases→tablas
- SQLite: archivo único `funiber.db`, sin instalación de servidor
- Pydantic: DTOs de entrada/salida
- React + Vite: bundler moderno, hot reload
- JWT: flujo login → token → `Authorization: Bearer <token>` → `Depends(get_current_user)`
- Estructura de carpetas recomendada: `backend/` con `models/`, `schemas/`, `routers/`, `repositories/` + `frontend/` con `pages/`, `components/`, `api/`
- Tabla de correspondencia diagrama diseño ↔ código real

**Decisión:** Usuario va a investigar más sobre la tecnología por su cuenta antes de continuar.

---
