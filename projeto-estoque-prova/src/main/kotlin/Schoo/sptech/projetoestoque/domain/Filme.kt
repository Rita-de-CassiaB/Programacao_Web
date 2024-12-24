package Schoo.sptech.projetoestoque.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import java.time.LocalDate
import java.util.Date

@Entity
data class Filme (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @field:NotNull
    var titulo:String,

    var genero:String,

    @field:Past
    var anoLancamento: Int,

    var sinopse:String,

    @field:ManyToOne var diretor: Diretor

) {
}