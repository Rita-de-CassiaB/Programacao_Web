package school.sptech.projetoestoque.dto

data class ProdutoContabilResponse(
    var id: Int? = null,
    var nome: String? = null,
    var qtdEstoque: Int? = null,
    var preco: Double? = null
) {
    // no JSON teremos uma propriedade "valorTotalEstoque"
    fun getValorTotalEstoque():Double {
        return qtdEstoque!! * preco!!
    }

    // no JSON teremos uma propriedade "esgotado"
    fun isEsgotado():Boolean {
        return qtdEstoque!! == 0
    }
}
