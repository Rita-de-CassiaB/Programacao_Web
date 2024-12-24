package school.sptech.projetoestoque.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class ProdutoFinanceiroResponse(
    var id: Int? = null,
    var nome: String? = null,

    @JsonIgnore
    var qtdEstoque: Int? = null,

    @JsonIgnore
    var preco:Double? = null
    ) {

    // isso fará com que o JSON tenha uma propriedade "valorEmEstoque"
    fun getValorEmEstoque(): Double {
        return qtdEstoque!! * preco!!
    }
}

