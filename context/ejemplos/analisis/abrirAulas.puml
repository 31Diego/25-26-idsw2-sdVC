@startuml abrirAulas-analisis
skinparam linetype polyline

rectangle #CDEBA5 ":Sistema Disponible" as SistemaDisponible
package abrirAulas as "abrirAulas()" {
    rectangle #629EF9 ListarAulasView
    rectangle #b5bd68 AulasController
    rectangle #F2AC4E AulaRepository
    rectangle #F2AC4E Aula
}

rectangle #CDEBA5 ":Sistema Disponible" as SistemaDisponibleSalida
rectangle #CDEBA5 ":Collaboration CrearAula" as CrearAula
rectangle #CDEBA5 ":Collaboration EditarAula" as EditarAula
rectangle #CDEBA5 ":Collaboration EliminarAula" as EliminarAula

SistemaDisponible -r-> ListarAulasView: abrirAulas()

ListarAulasView -d-> AulasController: listarAulas() : List<Aula>

AulasController --> AulaRepository: obtenerTodos() : List<Aula>

AulaRepository -- Aula

ListarAulasView --> AulasController: filtrarAulas(criterio) : List<Aula>

AulasController --> AulaRepository: buscarPorCriterio(criterio) : List<Aula>

ListarAulasView ..> SistemaDisponibleSalida: completarGestion()
ListarAulasView ..> CrearAula: crearAula()
ListarAulasView ..> EditarAula: editarAula()
ListarAulasView ..> EliminarAula: eliminarAula()

@enduml