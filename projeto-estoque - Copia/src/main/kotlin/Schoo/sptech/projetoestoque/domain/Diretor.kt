package Schoo.sptech.projetoestoque.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Diretor(
    @field:Id
    @field:GeneratedValue(strategy =  GenerationType.IDENTITY)
    var id: Int,

    @field:Size(min = 3)
    var nome:String,

    @field:NotBlank
    var nacionalidade:String,

    @field:Past @field:NotNull
    var dataNascimento:LocalDate

) {
}