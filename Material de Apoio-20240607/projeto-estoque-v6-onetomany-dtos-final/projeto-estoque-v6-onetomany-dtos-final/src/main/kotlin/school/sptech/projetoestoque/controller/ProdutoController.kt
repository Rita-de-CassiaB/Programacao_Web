package school.sptech.projetoestoque.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetoestoque.dominio.Produto
import school.sptech.projetoestoque.dto.ProdutoContabilResponse
import school.sptech.projetoestoque.dto.ProdutoFinanceiroResponse
import school.sptech.projetoestoque.dto.ProdutoResponse
import school.sptech.projetoestoque.dto.ProdutoSimplesResponse
import school.sptech.projetoestoque.service.ProdutoService

@RestController
@RequestMapping("/produtos")
class ProdutoController(
    // criando como parâmetros do construtor, não é necessário o uso do @Autowired
    val produtoService: ProdutoService
) {

    @GetMapping("/simples/{id}")
    fun getSimples(@PathVariable id:Int) : ResponseEntity<ProdutoSimplesResponse> {
        val dto = produtoService.getSimples(id)
        return ResponseEntity.status(200).body(dto)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id:Int) : ResponseEntity<ProdutoResponse> {
        val dto = produtoService.get(id)
        return ResponseEntity.status(200).body(dto)
    }

    @GetMapping("/simples")
    fun listarSimples() : ResponseEntity<List<ProdutoSimplesResponse>> {
        val dtos = produtoService.getListaSimples()
        return ResponseEntity.status(200).body(dtos)
    }

    @GetMapping("/contabil")
    fun listarContabil() : ResponseEntity<List<ProdutoContabilResponse>> {
        val dtos = produtoService.getListaContabil()
        return ResponseEntity.status(200).body(dtos)
    }

/*
//    @PostMapping
    fun criarNovo(@RequestBody @Valid novoProduto: ProdutoCadastroRequest) : ResponseEntity<Produto> {
        // Verificando se o fabricante do produto existe, caso não, retorna 404
        if (!fabricanteRepository.existsById(novoProduto.fabricante!!.id)) {
            return ResponseEntity.status(404).build()
        }

        val produto = mapper.map(novoProduto, Produto::class.java)

        // salva o produto vinculando um produto a um fabricante
        produtoRepository.save(produto)

        return ResponseEntity.status(201).body(produto)
    }*/

    @PostMapping
    fun criar(@RequestBody @Valid novoProduto: Produto) :
            ResponseEntity<ProdutoResponse> {
        val salvo = produtoService.salvar(novoProduto)
        return ResponseEntity.status(201).body(salvo)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: Int,
        @RequestBody @Valid produto: Produto
    ) : ResponseEntity<ProdutoResponse> {
        produto.id = id
        val salvo = produtoService.salvar(produto)
        return ResponseEntity.status(200).body(salvo)
    }

    @GetMapping
    fun listar() : ResponseEntity<List<ProdutoResponse>> {
        val produtos = produtoService.getLista()
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/financeiro")
    fun listarFinanceiro() :
            ResponseEntity<List<ProdutoFinanceiroResponse>> {
        val produtos = produtoService.getListaFinanceiro()
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-fabricante/{fabricanteId}")
    fun buscarPorIdFabricante(@PathVariable fabricanteId: Int):
            ResponseEntity<List<Produto>> {

        val produtos = produtoService.getListaPorFabricante(fabricanteId)
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-qtd")
    fun buscarPorIdFabricante(
        @RequestParam qtdMin: Int, // @RequestParam utilizado para fazer buscas personalizadas que sejam diferentes do id
        @RequestParam qtdMax: Int):
            ResponseEntity<List<Produto>> {

        val produtos = produtoService.getListaPorQuantidade(qtdMin, qtdMax)
        return ResponseEntity.status(200).body(produtos)
    }
}