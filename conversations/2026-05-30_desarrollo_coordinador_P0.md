# Sesión — 2026-05-30 · Desarrollo · coordinador · P0

## [~16:15] Orientación inicial

**Prompt:** "buenas claude"

**Resultado:** Estado del proyecto: análisis coordinador completo, diseño iniciado (abrirConvocatoria, abrirConvocatorias), stack decidido (Spring Boot + Thymeleaf + H2).

**Decisión:** Continuar con desarrollo de los casos de uso de ayer y avanzar P0.

---

## [~16:15] Desarrollo de abrirConvocatoria y abrirConvocatorias

**Prompt:** "quiero hacer el desarrollo de los dos diseños de los casos de uso de ayer"

**Resultado:** Generados pom.xml, GipfApplication, application.properties, Convocatoria.java, ConvocatoriaRepository, ConvocatoriasService, ConvocatoriasController, convocatorias.html, convocatoria.html.

**Decisión:** Aceptado. Después el usuario señaló que ConvocatoriaDTO no estaba en el análisis — eliminado. Regla guardada en memoria: ceñirse estrictamente a las clases identificadas en el análisis.

---

## [~16:30] Explicaciones de stack y tecnología

**Prompt:** Varias preguntas: qué es pom.xml, qué es dto, diferencia Spring+Thymeleaf vs Spring+Angular, ventajas e inconvenientes.

**Resultado:** Añadidas secciones a `documents/stack.md`: comparativa Thymeleaf/React/Angular, cuándo elegir cada uno.

**Decisión:** Aceptado.

---

## [~16:30] Prueba de la aplicación

**Prompt:** "como lo pruebo" / "estoy en vscode"

**Resultado:** Instrucciones para VS Code: Extension Pack for Java, Run desde GipfApplication.java. La app arrancó correctamente.

**Decisión:** Funcionando.

---

## [~17:00] Guía de pruebas

**Prompt:** "puedes hacerme un md de como deberia hacer las pruebas"

**Resultado:** Creado `documents/pruebas/guiaPruebas.md` con tabla de casos de prueba por caso de uso y plantilla para nuevos.

**Decisión:** Aceptado.

---

## [~17:30] Decisión sobre convocatorias — sin crear, sin eliminar

**Prompt:** Reflexión sobre el diseño: convocatorias se importan, no se crean manualmente; no se eliminan para mantener histórico.

**Resultado:** Validadas ambas decisiones. Se acordó implementar `importarConvocatoria` como formulario manual (opción A).

**Decisión:** Aceptado.

---

## [~17:45] DataLoader con datos de demo

**Prompt:** "montalo" (data.sql / datos de demo)

**Resultado:** Expandido `DataLoader.java` con 5 convocatorias de demo (3 abiertas, 2 cerradas) y perfil del usuario admin.

**Decisión:** Aceptado.

---

## [~18:00] iniciarSesion — diseño y desarrollo

**Prompt:** "vamos con la A" (P0 en orden)

**Resultado:** Diseño (`iniciarSesion.puml`, `iniciarSesion.md`) y desarrollo: Usuario.java, UsuarioRepository, AutenticacionService, SecurityConfig, IniciarSesionController, login.html, DataLoader actualizado. Añadido Spring Security al pom.xml.

**Decisión:** Aceptado. Probado: login funciona con admin/1234.

---

## [~18:30] cerrarSesion + abrirPanelPrincipal

**Prompt:** "vete haciendo esos"

**Resultado:** Diseño y desarrollo de ambos. CerrarSesionController, cerrar-sesion.html, PanelPrincipalService, PanelPrincipalController, panel.html con menú completo de navegación.

**Decisión:** Aceptado. Probado: login → panel → cerrar sesión funciona.

---

## [~19:00] abrirOpcionesPerfil + editarPerfil

**Prompt:** "vale"

**Resultado:** Creada entidad Investigador.java (@OneToOne con Usuario), InvestigadorRepository, PerfilService, OpcionesPerfilController, EditarPerfilController, opciones-perfil.html, editar-perfil.html. DataLoader actualizado con perfil del admin.

**Decisión:** Aceptado.

---

## [~19:30] abrirOpcionesCargaTrabajo + editarCargaTrabajo

**Prompt:** "si, hazlo y lo probamos todo"

**Resultado:** Añadidos campos `disponibilidad` y `cargaTrabajo` a Investigador. Creados CargaTrabajoService, CargaTrabajoController, EditarCargaTrabajoController, carga-trabajo.html, editar-carga-trabajo.html. InvestigadorRepository con `buscarPorCriterio`. DataLoader actualizado.

**Decisión:** P0 completo.

---
