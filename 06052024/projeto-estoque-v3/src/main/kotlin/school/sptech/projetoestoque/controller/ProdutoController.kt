package school.sptech.projetoestoque.controller

import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetoestoque.dominio.Produto
import school.sptech.projetoestoque.dto.ProdutoContabilResponse
import school.sptech.projetoestoque.dto.ProdutoSimplesResponse
import school.sptech.projetoestoque.repository.FabricanteRepository
import school.sptech.projetoestoque.repository.ProdutoRepository
import school.sptech.projetoestoque.service.ProdutoService

@RestController
@RequestMapping("/produtos")
class ProdutoController(
    // criando as repository como parâmetros do construtor, não é necessário o uso do @Autowired
    val produtoService: ProdutoService
) {

    @GetMapping("/simples/{id}")
    fun getSimples(@PathVariable id:Int) : ResponseEntity<ProdutoSimplesResponse> {
        var produto = produtoService.getProdutoSimples(id)
        return ResponseEntity.status(200).build()
    }

    @GetMapping("/simples")
    fun listarSimples() : ResponseEntity<List<ProdutoSimplesResponse>> {
        val dtos = produtoService.getListaSimpes()
        return ResponseEntity.status(200).body(dtos)
    }


    @PostMapping
    fun criar(@RequestBody @Valid novoProduto: Produto) : ResponseEntity<Produto> {
        produtoService.salvar(novoProduto)
        return ResponseEntity.status(201).body(novoProduto)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id:Int, @RequestBody @Valid produto: Produto) : ResponseEntity<Produto> {
        produto.id = id
        produtoService.salvar(produto)
        return ResponseEntity.status(200).body(produto)
    }

    @GetMapping
    fun listar() : ResponseEntity<List<Produto>> {
        val produtos = produtoService.getProdutos()
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-fabricante/{fabricanteId}")
    fun buscarPorIdFabricante(@PathVariable fabricanteId: Int):ResponseEntity<List<Produto>> {
        val produtos = produtoService.getProdutosByFabricante(fabricanteId)
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-qtd")
    fun filtrarPorQtdMinMax(@RequestParam qtdMin: Int,@RequestParam qtdMax: Int):ResponseEntity<List<Produto>> {
        var produtos = produtoService.getProdutosByMinMax(qtdMin, qtdMax)
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/contabil")
    fun listarContabil() : ResponseEntity<List<ProdutoContabilResponse>> {
        val dtos = produtoService.getListaContabil()
        return ResponseEntity.status(200).body(dtos)
    }

}

