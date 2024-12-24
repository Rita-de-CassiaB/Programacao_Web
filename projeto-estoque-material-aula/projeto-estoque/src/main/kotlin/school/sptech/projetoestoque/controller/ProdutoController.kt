package school.sptech.projetoestoque.controller

import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetoestoque.dominio.Produto
import school.sptech.projetoestoque.repository.FabricanteRepository
import school.sptech.projetoestoque.repository.ProdutoRepository

@RestController
@RequestMapping("/produtos")
class ProdutoController(
    // criando as repository como parâmetros do construtor, não é necessário o uso do @Autowired
    val produtoRepository: ProdutoRepository,
    val fabricanteRepository: FabricanteRepository
) {

    @PostMapping
    fun criar(@RequestBody @Valid novoProduto: Produto) : ResponseEntity<Produto> {
        // Verificando se o fabricante do produto existe, caso não, retorna 404
        if (!fabricanteRepository.existsById(novoProduto.fabricante.id)) {
            return ResponseEntity.status(404).build()
        }
        // salva o produto vinculando um produto a um fabricante
        val produtoSalvo = produtoRepository.save(novoProduto)
        return ResponseEntity.status(201).body(produtoSalvo)
    }

    @GetMapping
    fun listar() : ResponseEntity<List<Produto>> {
        val produtos = produtoRepository.findAll()

        if (produtos.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-fabricante/{fabricanteId}")
    fun buscarPorIdFabricante(@PathVariable fabricanteId: Int):
            ResponseEntity<List<Produto>> {

        val produtos =
            produtoRepository.findByFabricanteId(fabricanteId)

        if (produtos.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-qtd")
    fun buscarPorIdFabricante(
        @RequestParam qtdMin: Int, // @RequestParam utilizado para fazer buscas personalizadas que sejam diferentes do id
        @RequestParam qtdMax: Int):
            ResponseEntity<List<Produto>> {

        val produtos =
            produtoRepository.findByQtdEstoqueBetween(qtdMin, qtdMax)

        if (produtos.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }
}