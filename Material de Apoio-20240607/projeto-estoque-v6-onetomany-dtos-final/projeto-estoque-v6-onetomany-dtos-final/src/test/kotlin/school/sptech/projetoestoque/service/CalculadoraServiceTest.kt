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

    /*
    Esta anotação indica que o método contém um ou mais testes
     */
    @Test
    @DisplayName("O valor de venda dever ser o valor de compra +15%")
    fun calcularPrecoVendaMenorIgual10() {

        val resultado = service.calcularPrecoVenda(5.00)

        /*
Aqui fazemos uma ASSERÇÃO (ou Verificação)
Estamos verificando a igualdade entre 2 valores
O 1o valor: valor ESPERADO
O 2o valor: valor do teste
         */
        assertEquals(5.75, resultado)

        val resultado2 = service.calcularPrecoVenda(10.00)
        assertEquals(11.50, resultado2)
    }

    @Test
    fun `O valor de venda dever ser o valor de compra +10%`() {

        val resultado = service.calcularPrecoVenda(50.00)
        assertEquals(55.0, resultado)

        val resultado2 = service.calcularPrecoVenda(100.00)
        assertEquals(110.00, resultado2)
    }

/*
@ParameterizedTest -> permite a execução de vários cenários

@CsvSource -> permite a indicação, no código, de vários valores CSV
 */
//    @ParameterizedTest
    @ParameterizedTest(name =
    "Para o valor de compra {0} o valor de venda deve ser {1}")
    @CsvFileSource(resources = ["/cenarios-precos.csv"])
 /*@CsvSource(value =
        [
        "5, 5.75",
        "7, 8.05",
        "11, 12.1",
        "50, 55",
        "100.01, 107.0107",
        "200, 214"
        ]
    )*/
    fun calcularPrecoVenda(precoCompra:Double, resultado:Double) {
        val resultadoTeste = service.calcularPrecoVenda(precoCompra)

        assertEquals(resultado, resultadoTeste, 0.001)
        // se usarmos um 3o argumento numérico no assertEquals
        // indicamos a tolerância de erro matemática
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/resolucao-exercicio1.csv"])
    fun calcularPrecoVendaFinal(
        precoCompra: Double,
        pontosCliente: Int,
        precoVendaEsperado: Double
    ) {
        // todos os atributos de Produto devem ter null como valor padrão
        val produto = Produto(preco = precoCompra)
        val cliente = Cliente(pontos = pontosCliente)

        val resultado = service.calcularPrecoVendaFinal(produto, cliente)

        assertEquals(precoVendaEsperado, resultado, 0.001)
    }

//    @DisplayName("deve haver uma exceção em caso de preço negativo")
//    @Test
    fun precoVendaCompraNegativo() {
        val vendaNegativo1 = -0.01
        val vendaNegativo2 = -100.0

        /*
assertThrows -> verifica se uma determinada Exceção
é lançada durante a execução do bloco de código abaixo dela
         */
        assertThrows(IllegalArgumentException::class.java)
        { service.calcularPrecoVenda(vendaNegativo1) }

        val excecao = assertThrows(IllegalArgumentException::class.java)
        { service.calcularPrecoVenda(vendaNegativo2) }

        assertEquals(
            "Preço de venda deve ser >= 0. Usado: $vendaNegativo2",
            excecao.message)
    }
 }



