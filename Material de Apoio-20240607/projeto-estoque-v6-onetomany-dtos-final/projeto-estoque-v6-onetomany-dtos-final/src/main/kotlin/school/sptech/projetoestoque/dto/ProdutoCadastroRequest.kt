package school.sptech.projetoestoque.dto

import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import school.sptech.projetoestoque.dominio.Fabricante

// class ProdutoCadastroParameter {
data class ProdutoCadastroRequest(
    @field:Size(min = 3)
    var nome: String,

    @field:PositiveOrZero
    var qtdEstoque: Int? = null,

    @field:PositiveOrZero
    var preco:Double? = null,
    var fabricante: Fabricante? = null
)