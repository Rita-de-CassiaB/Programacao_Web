package school.sptech.projetoestoque.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetoestoque.dominio.Produto
import school.sptech.projetoestoque.dto.ProdutoContabilResponse
import school.sptech.projetoestoque.repository.FabricanteRepository
import school.sptech.projetoestoque.repository.ProdutoRepository
import java.util.*

class ProdutoServiceTest {

    lateinit var produtoRepository: ProdutoRepository
    lateinit var fabricanteRepository: FabricanteRepository
    lateinit var service: ProdutoService

    @BeforeEach
        fun iniciar(){
        produtoRepository = mock(ProdutoRepository::class.java)
        fabricanteRepository = mock(FabricanteRepository::class.java)
        service = ProdutoService(produtoRepository, fabricanteRepository)
    }

    @Test
    fun getLista() {
        val listaEsperada = listOf(
            Produto(id = 1, nome = "mel"),
            Produto(id = 2, nome = "aveia")
        )
        `when` (produtoRepository.findAll()).thenReturn(listaEsperada)

        val resultado = service.getLista()

        assertEquals(listaEsperada.size, resultado.size)
    }

    @DisplayName("Se a tabela estiver vazia, deve lançar uma excecao")
    @Test
    fun getListaVazia() {

        `when`(produtoRepository.findAll()).thenReturn(listOf())

        val excecao = assertThrows(ResponseStatusException::class.java) {
            service.getLista()
        }

        assertEquals(204, excecao.statusCode.value())

    }
    @DisplayName("Se o id existir")
    @Test
    fun getSimples(){
        val idExistente = 10
        val idEsperado = Produto(id = idExistente, nome = "mel")


        `when`(produtoRepository.existsById(idExistente)).thenReturn(true)
        `when`(produtoRepository.findById(idExistente)).thenReturn(Optional.of(idEsperado))

        val resultado = service.getSimples(idExistente)

        assertEquals(idEsperado.id, resultado.id)
        assertEquals(idEsperado.nome, resultado.nome)

    }

    @DisplayName("lista contabil vazia")
    @Test
    fun getListaContabilVazia() {
        val excecao = assertThrows(ResponseStatusException::class.java) {
            service.getListaContabil()
        }
        assertEquals(204, excecao.statusCode.value())
    }

    @DisplayName("lista contabil")
    @Test
    fun getListaContabil(){

        val listaEsperada = listOf(
            Produto(id = 1, nome = "mel"),
            Produto(id = 2, nome = "aveia"),
            Produto(id = 3, nome = "Maçã"),
        )

        val listaContabilResponse:MutableList<ProdutoContabilResponse> = mutableListOf()

        listaEsperada.forEachIndexed{ index, produto ->
            listaContabilResponse.add (
                service.mapper.map(produto, ProdutoContabilResponse::class.java)
            )
        }
        `when`(produtoRepository.findContabil()).thenReturn(listaEsperada)

            val resultado = service.getListaContabil()

        assertEquals(listaContabilResponse,resultado)

    }
}