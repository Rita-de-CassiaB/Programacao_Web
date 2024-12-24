package Schoo.sptech.projetoestoque.controller

import Schoo.sptech.projetoestoque.domain.Produto
import Schoo.sptech.projetoestoque.repository.FabricanteRepository
import Schoo.sptech.projetoestoque.repository.ProdutoRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produtos")
class ProdutoController (
    val produtoRepository:ProdutoRepository,
    val fabricanteRepository: FabricanteRepository
){
    @PostMapping
    fun criar(@RequestBody @Valid novoProduto:Produto):ResponseEntity<Produto>{

        if (!fabricanteRepository.existsById(novoProduto.fabricante.id)){
            return ResponseEntity.status(404).build()
        }
        val produtoSalvo = produtoRepository.save(novoProduto)
        return ResponseEntity.status(201).body(produtoSalvo)
    }

    @GetMapping
    fun listar():ResponseEntity<List<Produto>>{
        val produtos = produtoRepository.findAll()

        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build()

        }
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-fabricante/{fabricanteId}")
    fun buscarPorIdFabricante(@PathVariable fabricanteId:Int):
            ResponseEntity<List<Produto>> {
        val produtos = produtoRepository.findByFabricanteId(fabricanteId)

        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }

    @GetMapping("/filtro-qtdEstoque")
    fun buscarPorQtdEstoque(@RequestParam qtdMin:Int, @RequestParam qtdMax:Int):
            ResponseEntity<List<Produto>> {
        val produtos =
            produtoRepository.findByQtdEstoqueBetween(qtdMin, qtdMax)

        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }
}