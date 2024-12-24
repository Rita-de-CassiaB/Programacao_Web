package school.sptech.projetoestoque.dto

class ProdutoContabilResponse (
    var id:Int? = null,
    var nome:String? = null,
    var qtdEstoque:Int? = null,
    var preco:Double? = null
) {

    fun getValorTotalEmEstoque():Double{
        return qtdEstoque!!*preco!!;
    }

    fun isEsgotado():Boolean{
        return qtdEstoque!! == 0
    }

}