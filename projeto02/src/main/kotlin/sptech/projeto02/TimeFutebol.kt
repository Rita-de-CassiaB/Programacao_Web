package sptech.projeto02

import com.fasterxml.jackson.annotation.JsonIgnore

class TimeFutebol (
    var nome:String,
    var vitorias:Int,
    var empates:Int,
    var derrotas:Int,
    @JsonIgnore var patrimônio:Double
) {
    /*
    Métodos que começam com get/is fazem parte do padrão JavaBean
    Como são um padrão, servem para prover propriedades para um JSON

    Quando criamos uma propriedade gerada a partir de uma informação,
    Dizemos que criamos um...
    Campo Virtual; Campo Calculado; Atributo Virtual; Atributo Calculado
     */

    fun getPontos():Int{
        return vitorias*3+ empates
    }

    fun isInvicto(): Boolean {
        return derrotas ==0
    }


}