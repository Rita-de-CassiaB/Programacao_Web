package school.sptech.projetoestoque.dto

data class FabricanteResponse(
    var id: Int? = null,

    var nome: String? = null,

    var produtos: List<ProdutoSimplesSemFabricanteResponse>? = null
)
