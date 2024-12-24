package school.sptech.projetoestoque.service

import org.modelmapper.ModelMapper
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import school.sptech.projetoestoque.dominio.Produto
import school.sptech.projetoestoque.dto.ProdutoContabilResponse
import school.sptech.projetoestoque.dto.ProdutoSimplesResponse
import school.sptech.projetoestoque.repository.FabricanteRepository
import school.sptech.projetoestoque.repository.ProdutoRepository

@Service
class ProdutoService (
    // criando as repository como parâmetros do construtor, não é necessário o uso do @Autowired
    val produtoRepository: ProdutoRepository,
    val fabricanteRepository: FabricanteRepository,
    val mapper: ModelMapper = ModelMapper()
) {

    fun validarLista(lista: List<*>) {
        if (lista.isEmpty()){
            throw ResponseStatusException(HttpStatusCode.valueOf(204))
        }
    }

    fun getListaSimpes():List<ProdutoSimplesResponse>?{
        val lista = produtoRepository.findSimples()
        validarLista(lista)

        val dtos = lista.map {
            mapper.map(
                it,
                ProdutoSimplesResponse::class.java
            )
        }
        return dtos
    }

    fun getListaContabil():List<ProdutoContabilResponse>?{
        val lista = produtoRepository.buscarContabil()
        validarLista(lista)

        val dtos = lista.map {
            mapper.map(
                it,
                ProdutoContabilResponse::class.java
            )
        }
        return dtos
    }

    fun salvar(produto:Produto){
        if (!fabricanteRepository.existsById(produto.fabricante!!.id)) {
            throw ResponseStatusException(
                HttpStatusCode.valueOf(404)
            )
        }
        produtoRepository.save(produto)
    }

    fun getProdutosByMinMax(qtdMin:Int,qtdMax:Int):List<Produto>{
        val produtos = produtoRepository.findByQtdEstoqueBetween(qtdMin, qtdMax)
        validarLista(produtos)
        return produtos
    }

    fun getProdutosByFabricante(id:Int):List<Produto>{
        var produtos = produtoRepository.findByFabricanteId(id);
        validarLista(produtos)
        return produtos
    }

    fun getProdutos():List<Produto>{
        var produtos = produtoRepository.findAll()
        validarLista(produtos)
        return produtos

    }

    fun validarExists(id:Int){
        if (!produtoRepository.existsById(id)) {
            throw ResponseStatusException(
                HttpStatusCode.valueOf(404)
            )
        }
    }

    fun getProdutoSimples(id:Int):ProdutoSimplesResponse{

        validarExists(id)

        val produto = produtoRepository.findById(id).get()

        val dto = mapper.map(
            produto,
            ProdutoSimplesResponse::class.java
        ) // ATENÇÃO AQUI!!!
        return dto
    }

}