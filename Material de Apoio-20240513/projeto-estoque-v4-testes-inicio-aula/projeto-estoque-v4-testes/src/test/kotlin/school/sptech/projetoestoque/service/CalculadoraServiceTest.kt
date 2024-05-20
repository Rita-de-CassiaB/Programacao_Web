package school.sptech.projetoestoque.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource
import school.sptech.projetoestoque.dominio.Cliente
import school.sptech.projetoestoque.dominio.Produto

class CalculadoraServiceTest {

    val service = CalculadoraService()

    @Test
    fun `O valor de venda deve ser o valor de compra + 15%`() {
        val resultado = service.calcularPrecoVenda(5.0)
        assertEquals(5.75, resultado)

        val resultado2 = service.calcularPrecoVenda(10.0)
        assertEquals(11.50, resultado2)

    }

    @Test
    @DisplayName("O valor de venda deve ser o valor de compra + 10%")
    fun calcularPrecoVendaEntre10e100() {
        val resultado = service.calcularPrecoVenda(50.0)
        assertEquals(55.0, resultado)

        val resultado2 = service.calcularPrecoVenda(100.0)
        assertEquals(110.0, resultado2)
    }

    @ParameterizedTest  (name = "Para o valor de compra {0} o valor de venda deve ser {1}")
    @CsvFileSource(resources = ["/cesarios-precos.csv"])
    fun calcularPrecoVenda(precoCompra:Double):Double{
        val porcentagem = when (precoCompra) {
            in 0.0..10.0 -> 1.15
            in 10.01..100.0 -> 1.10
            else -> 1.07
        }
        val precoVenda = precoCompra + porcentagem
        return precoVenda
    }

    @ParameterizedTest
    @CsvFileSource( resources = ["/cenarios-exercicios.csv"])
    fun calcularPrecoVendaFinal(precoCompra: Double, clientePontos: Int,resultado: Double){

        var produto = Produto(paramPreco = precoCompra)
        var cliente = Cliente(paramPontos = clientePontos)

        var precoFinal = service.calcularPrecoVendaFinal(produto, cliente)
        assertEquals(resultado, precoFinal, 0.001)
    }




}