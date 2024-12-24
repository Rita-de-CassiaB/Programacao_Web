package Schoo.sptech.projetoestoque.repository

import Schoo.sptech.projetoestoque.domain.Pizza
import Schoo.sptech.projetoestoque.domain.Tamanhos
import org.springframework.data.jpa.repository.JpaRepository

interface PizzaRepository:JpaRepository<Pizza, Int> {
    fun findBySaboresNomeContains(sabor:String):List<Pizza>
    fun findBySaboresNomeContainsAndTamanho(sabor:String,tamanho:Tamanhos):List<Pizza>
}