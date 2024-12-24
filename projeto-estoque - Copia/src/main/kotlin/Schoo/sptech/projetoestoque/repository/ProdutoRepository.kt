package Schoo.sptech.projetoestoque.repository

import Schoo.sptech.projetoestoque.domain.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProdutoRepository: JpaRepository<Produto, Int> {
    fun findByFabricanteId(id:Int): List<Produto>

    @Query("SELECT p FROM Produto p WHERE p.fabricante.id = :id")
    fun buscarPorFabricanteID(id: Int): List<Produto>

    fun findByQtdEstoqueBetween(qtdMin: Int, qtdMax: Int): List<Produto>




}