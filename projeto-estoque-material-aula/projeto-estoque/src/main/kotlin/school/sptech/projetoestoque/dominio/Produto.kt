package school.sptech.projetoestoque.dominio

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Entity
data class Produto (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @field:Size(min = 3)
    var nome: String,

    @field:Positive
    var qtdEstoque: Int,

    @field:Positive
    var preco:Double,

    @field:ManyToOne
    var fabricante: Fabricante
)