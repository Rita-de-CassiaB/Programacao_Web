package school.sptech.projetoestoque.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import school.sptech.projetoestoque.dominio.Produto

interface ProdutoRepository : JpaRepository<Produto, Int> {

    /*
A instrução JPQL abaixo funciona APENAS SE
a classe Produto tiver um construtor que espere apenas por id e nome
    */
    @Query("SELECT new Produto(p.id, p.nome, p.fabricante) FROM Produto p")
    fun findSimples(): List<Produto>

    /*
        SELECT p FROM PRODUTO p JOIN FABRICANTE f
        on p.fk_fabricante = f.id where p.fk_fabricante = ?

        findBy = SELECT p FROM Produto
        FabricanteId = WHERE p.fabricante.id = ?
        -SELECT p FROM Produto p WHERE p.fabricante.id = ?-

        Dynamic Finders
     */
    fun findByFabricanteId(id: Int) : List<Produto>

    @Query("SELECT p FROM Produto p WHERE p.fabricante.id = :id")
    fun buscaPorFabricanteId(id: Int) : List<Produto>

    fun findByQtdEstoqueBetween(qtdMin: Int, qtdMax: Int): List<Produto>

    @Query("SELECT p FROM Produto p WHERE p.qtdEstoque >= :qtdMin AND p.qtdEstoque <= :qtdMax")
    fun buscaEntreDuasQuantidades(qtdMin: Int, qtdMax: Int)

    @Query("SELECT new Produto(p.id,p.nome,p.qtdEstoque,p.preco) FROM Produto p ")
    fun buscarContabil(): List<Produto>

}
