package school.sptech.projetoestoque.service

import org.springframework.stereotype.Service
import school.sptech.projetoestoque.dominio.Cliente
import school.sptech.projetoestoque.dominio.Produto

@Service
class CalculadoraService {

    fun calcularPrecoVenda(precoCompra: Double): Double {
        var precoVenda = precoCompra;

        when (precoCompra) {
            in 0.0..10.0 -> precoVenda += precoCompra * 0.15;
            in 10.01..100.0 -> precoVenda += precoCompra * 0.1;
            else -> precoVenda += precoCompra * 0.07;
        }

        return precoVenda;
    }

    fun calcularPrecoVendaFinal(produto: Produto, cliente: Cliente): Double {

        var clientePontos = cliente.pontos
        var porcentagemDesconto = 0.0

        when (clientePontos) {
            in 0..500 -> porcentagemDesconto = produto.preco!! * 0.01
            in 501..5000 -> porcentagemDesconto = produto.preco!! * 0.03
            else -> porcentagemDesconto = produto.preco!! * 0.05
        }

        var valorFinal = calcularPrecoVenda(produto.preco!!) - porcentagemDesconto

        return valorFinal

    }
}

