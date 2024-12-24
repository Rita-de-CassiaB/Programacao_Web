package school.sptech.projetoestoque.service

import org.springframework.stereotype.Service
import school.sptech.projetoestoque.dominio.Cliente
import school.sptech.projetoestoque.dominio.Produto

@Service
class CalculadoraService {

    fun calcularPrecoVenda(precoCompra:Double): Double {

        if (precoCompra > 0.0) {
            val porcentagem = when (precoCompra) {
                in 0.0..10.0 -> 1.15
                in 10.01..100.0 -> 1.10
                else -> 1.07
            }

            val precoVenda = precoCompra * porcentagem

            return precoVenda
        } else {
            throw IllegalArgumentException("Peço de venda deve ser >= 0. Usado: $precoCompra")
        }
    }

    fun calcularPrecoVendaFinal(produto: Produto, cliente: Cliente):Double {

        var clientePontos = cliente.pontos
        var porcentagem = 0.0

        when(clientePontos){
            in 0..500 -> porcentagem = produto.preco!!*0.01
            in 501..5000 -> porcentagem = produto.preco!!*0.03
            else -> porcentagem = produto.preco!!*0.05
        }

        val precoFinal = calcularPrecoVenda(produto.preco!!) - porcentagem
        return precoFinal
    }
}