package Schoo.sptech.projetoestoque.repository

import Schoo.sptech.projetoestoque.domain.Diretor
import Schoo.sptech.projetoestoque.domain.Filme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FilmeRepository: JpaRepository<Filme, Int> {

    fun findByTituloContains(titulo:String):List<Filme>
    fun findByDiretorNacionalidade(nacionalidade:String):List<Filme>
    fun findByGeneroAndDiretor(genero:String, diretor:Diretor):List<Filme>
//    fun findByDiretorNascimentoBetweenDataInicioAndDataFim( dataInicio: Int, dataFim:Int ):List<Filme>
}