package sptech.projeto.clientes

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate

data class Cliente(
   @field:NotBlank @field:Size(min = 3, max = 5) var codigo:String,
    var nome:String,
    @field:NotNull var salario:Double?,
    @field:Past var nascimento:LocalDate,
    @field:Future var validadeCadastro:LocalDate,
    @field:PositiveOrZero var filhos:Int,
 //@field:min(0) var filhos:Int
    @field:Email var email:String,
    @field:CPF var cpf:String
) {
}