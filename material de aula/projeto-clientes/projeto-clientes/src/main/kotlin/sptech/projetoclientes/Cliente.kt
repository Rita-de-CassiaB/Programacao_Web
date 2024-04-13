package sptech.projetoclientes

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import org.hibernate.validator.constraints.br.TituloEleitoral
import java.time.LocalDate

data class Cliente(
        /*
NotBlank -> rejeita null, "" e "       "
Usado APENAS para String. NÃO USAR para outros tipos!
         */
  @field:NotBlank @field:Size(min = 3, max = 5) var codigo:String,
  var nome:String,
        /*
Para campos obrigatórios que não são String, usamos NotNull
         */
  @field:NotNull @field:DecimalMin("1050.00") var salario:Double?,
  @field:Past var nascimento:LocalDate,
  @field:Future var validadeCadastro:LocalDate,
  @field:PositiveOrZero var filhos:Int,
  // @field:Min(0) var filhos:Int
  @field:Email var email:String,
  @field:CPF var cpf:String,
  @field:CNPJ var cnpj:String,
  @field:TituloEleitoral var tituloEleitor:String,
)
