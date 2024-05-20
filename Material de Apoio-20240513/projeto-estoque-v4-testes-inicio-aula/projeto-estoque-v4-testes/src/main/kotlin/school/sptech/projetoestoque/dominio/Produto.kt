package school.sptech.projetoestoque.dominio

import jakarta.persistence.*

@Entity
data class Produto (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    var nome: String?,

    var qtdEstoque: Int? = null,

    var preco:Double? = null,

    @field:ManyToOne
    var fabricante: Fabricante? = null
) {

    constructor(
        paramId: Int,
        paramNome: String
    ):
    this(id = paramId, nome = paramNome)

    constructor(
        paramId: Int,
        paramNome: String,
        paramFabricante: Fabricante
    ):
    this(id = paramId, nome = paramNome, fabricante = paramFabricante)

    constructor(
        paramId: Int,
        paramNome: String,
        paramQtdEstoque: Int,
        paramPreco: Double
    ):
    this(id = paramId, nome = paramNome,
         qtdEstoque = paramQtdEstoque, preco = paramPreco)


    constructor(paramPreco: Double):
            this(id=null, nome = null, qtdEstoque = null, preco = paramPreco)

}





