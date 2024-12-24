package school.sptech.projetoestoque.dto

data class ProdutoResponse(
    var id: Int? = null,
    var nome: String? = null,

    var qtdEstoque: Int? = null,
    var preco:Double? = null,

    var fabricante: FabricanteSimplesResponse? = null
)
