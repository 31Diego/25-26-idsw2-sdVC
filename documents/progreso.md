# Progreso — FUNIBER GIPF

## Leyenda

| Símbolo | Significado |
|:---:|---|
| ✅ | Completo |
| ⚠️ | Código implementado, **falta el `.md` de desarrollo** |
| ⬜ | Pendiente |

> La columna **Doc. Desarrollo** apunta a `documents/desarrollo/{actor}/{cu}.md`.  
> Ninguno existe todavía — todos los casos con código muestran ⚠️.

---

## Coordinador

### P0 — Base del sistema

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `iniciarSesion` | ✅ | ✅ | ✅ | ⚠️ |
| `cerrarSesion` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirPanelPrincipal` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirOpcionesPerfil` | ✅ | ✅ | ✅ | ⚠️ |
| `editarPerfil` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirOpcionesCargaTrabajo` | ✅ | ✅ | ✅ | ⚠️ |
| `editarCargaTrabajo` | ✅ | ✅ | ✅ | ⚠️ |

---

### P1 — Proyectos y entregables

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirProyectos` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirProyecto` | ✅ | ✅ | ✅ | ⚠️ |
| `crearProyecto` | ✅ | ✅ | ✅ | ⚠️ |
| `editarProyecto` | ✅ | ✅ | ✅ | ⚠️ |
| `eliminarProyecto` | ✅ | ✅ | ✅ | ⚠️ |
| `agregarInvestigador` | ✅ | ✅ | ✅ | ⚠️ |
| `eliminarInvestigador` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirEntregables` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirEntregable` | ✅ | ✅ | ✅ | ⚠️ |
| `crearEntregable` | ✅ | ✅ | ✅ | ⚠️ |
| `editarEntregable` | ✅ | ✅ | ✅ | ⚠️ |
| `eliminarEntregable` | ✅ | ✅ | ✅ | ⚠️ |

---

### P1 — Publicaciones

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirPublicaciones` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `responderPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `editarPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `eliminarPublicacion` | ✅ | ✅ | ✅ | ⚠️ |

---

### P1 — Mis publicaciones

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirMisPublicaciones` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirMiPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `crearPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `editarMiPublicacion` | ✅ | ✅ | ✅ † | ⚠️ |
| `eliminarMiPublicacion` | ✅ | ✅ | ✅ † | ⚠️ |

---

### P1 — Convocatorias

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirConvocatorias` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirConvocatoria` | ✅ | ✅ | ✅ | ⚠️ |
| `importarConvocatoria` | ✅ | ✅ | ✅ | ⚠️ |
| `eliminarConvocatoria` | ✅ | ✅ | ✅ | ⚠️ |

---

### P2 — Recompensas

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirRecompensas` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirRecompensa` | ✅ | ✅ | ✅ | ⚠️ |
| `crearRecompensa` | ✅ | ✅ | ✅ | ⚠️ |
| `editarRecompensa` | ✅ | ✅ | ✅ | ⚠️ |
| `eliminarRecompensa` | ✅ | ✅ | ✅ | ⚠️ |

---

### P2 — Investigadores

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirInvestigadores` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirInvestigador` | ✅ | ✅ | ✅ | ⚠️ |
| `crearInvestigador` | ✅ | ✅ | ✅ | ⚠️ |

---

### P2 — Gestión de perfil

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `solicitarEliminacionPerfil` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirSolicitudesEliminacionPerfil` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirSolicitudEliminacionPerfil` | ✅ | ✅ | ✅ | ⚠️ |

---

## Investigador

> Los casos marcados con `†` usan el mismo controlador/template que el coordinador (lógica compartida por rol).

### P0 — Base del sistema

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `iniciarSesion` | ✅ | ✅ | ✅ † | ⚠️ |
| `cerrarSesion` | ✅ | ✅ | ✅ † | ⚠️ |
| `abrirPanelPrincipal` | ✅ | ✅ | ✅ † | ⚠️ |
| `abrirOpcionesPerfil` | ✅ | ✅ | ✅ | ⚠️ |
| `editarPerfil` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirOpcionesCargaTrabajo` | ✅ | ✅ | ✅ | ⚠️ |
| `editarCargaTrabajo` | ✅ | ✅ | ✅ | ⚠️ |

---

### P1 — Proyectos propios y entregables

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirProyectos` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirProyecto` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirEntregables` | ✅ | ✅ | ✅ † | ⚠️ |
| `abrirEntregable` | ✅ | ✅ | ✅ † | ⚠️ |
| `crearEntregable` | ✅ | ✅ | ✅ † | ⚠️ |
| `editarEntregable` | ✅ | ✅ | ✅ † | ⚠️ |
| `eliminarEntregable` | ✅ | ✅ | ✅ † | ⚠️ |

---

### P1 — Publicaciones

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirPublicaciones` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `responderPublicacion` | ✅ | ✅ | ✅ | ⚠️ |

---

### P1 — Mis publicaciones

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirMisPublicaciones` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirMiPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `crearPublicacion` | ✅ | ✅ | ✅ | ⚠️ |
| `editarPublicacion` | ✅ | ✅ | ✅ † | ⚠️ |
| `eliminarPublicacion` | ✅ | ✅ | ✅ † | ⚠️ |

---

### P2 — Recompensas

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirRecompensas` | ✅ | ✅ | ✅ | ⚠️ |
| `abrirRecompensa` | ✅ | ✅ | ✅ | ⚠️ |

---

### P2 — Investigadores (consulta)

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `abrirInvestigadores` | ✅ | ✅ | ✅ † | ⚠️ |
| `abrirInvestigador` | ✅ | ✅ | ✅ † | ⚠️ |

---

### P2 — Gestión de perfil

| Caso de uso | Análisis | Diseño | Código | Doc. Desarrollo |
|---|:---:|:---:|:---:|:---:|
| `solicitarEliminacionPerfil` | ✅ | ✅ | ✅ | ⚠️ |
