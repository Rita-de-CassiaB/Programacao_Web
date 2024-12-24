package school.sptech.projetoestoque.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetoestoque.dominio.Produto
import school.sptech.projetoestoque.dto.ProdutoSimplesResponse
import school.sptech.projetoestoque.repository.FabricanteRepository
import school.sptech.projetoestoque.repository.ProdutoRepository
import java.util.*

class ProdutoServiceTest {

    lateinit var produtoRepository: ProdutoRepository
    lateinit var fabricanteRepository: FabricanteRepository
    lateinit var service: ProdutoService

    @BeforeEach
    fun iniciar() {
        produtoRepository = mock(ProdutoRepository::class.java)
        fabricanteRepository = mock(FabricanteRepository::class.java)
        service = ProdutoService(produtoRepository, fabricanteRepository)
    }

    @DisplayName(
    "Se houver produto na tabela, deve retornar os mesmos elementos")
    @Test
    fun getLista() {
        val listaEsperada = listOf(
            Produto(id = 1, nome = "mel"),
            Produto(id = 2, nome = "aveia")
        )

        `when`(produtoRepository.findAll())
                    .thenReturn(listaEsperada)

        val resultado = service.getLista()

        assertEquals(listaEsperada.size, resultado.size)

        assertEquals(listaEsperada, resultado)
    }

    @DisplayName(
    "Se a tabela estiver vazia, deve lançar uma exceção")
    @Test
    fun getListaVazia() {
        /*
Todo MOCK tem valores padrão para o retorno de métodos:
- Números: 0
- Boolean: false
- Coleção (ex: lista): coleção (ou lista) vazia
- QQ outra coisa: null
         */

        // código opcional para deixar EXPLÍCITO que a tabela está vazia
        `when`(produtoRepository.findAll()).thenReturn(listOf())

        val excecao = assertThrows(ResponseStatusException::class.java) {
            service.getLista()
        }

        assertEquals(204, excecao.statusCode.value())
    }

/*
Crie um cenário de teste para o método getSimples()
para o caso de o id do produto existir na tabela
 */
    @DisplayName(
"Se houver o id no banco, deve retornar o produtosimples correto")
    @Test
    fun getSimples() {
        val idExistente = 10
        val esperado = Produto(id = idExistente, nome = "mel")

        `when`(produtoRepository.existsById(idExistente))
            .thenReturn(true)
        `when`(produtoRepository.findById(idExistente))
            .thenReturn(Optional.of(esperado))

        val resultado = service.getSimples(idExistente)

        assertEquals(esperado.id, resultado.id)
        assertEquals(esperado.nome, resultado.nome)
    }







    @Test
    fun getListaConceito() {
        val listaEsperada = listOf(
            Produto(id = 1, nome = "mel"),
            Produto(id = 2, nome = "aveia")
        )

        val resultado = listOf(
            service.mapper.map(
                Produto(id = 1, nome = "mel"),
                ProdutoSimplesResponse::class.java
            ),
            service.mapper.map(
                Produto(id = 2, nome = "aveia"),
                ProdutoSimplesResponse::class.java
            ),
        )

        listaEsperada.forEachIndexed { i, produto ->
            assertEquals(produto.id, resultado[i].id)
            assertEquals(produto.nome, resultado[i].nome)
        }
    }
}
