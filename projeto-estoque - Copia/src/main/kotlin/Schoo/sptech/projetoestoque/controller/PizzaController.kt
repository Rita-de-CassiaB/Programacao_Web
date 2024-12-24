package Schoo.sptech.projetoestoque.controller

import Schoo.sptech.projetoestoque.domain.Pizza
import Schoo.sptech.projetoestoque.domain.Tamanhos
import Schoo.sptech.projetoestoque.repository.PizzaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pizzas")
class PizzaController (
    val pizzaRepository: PizzaRepository
){
    @GetMapping
    fun getPizzas(): ResponseEntity<List<Pizza>>{
        var pizzas = pizzaRepository.findAll()

        if (pizzas.isEmpty()){
            return ResponseEntity.status(204).build()
        }

        for (pizza in pizzas){
            pizza.setPreco(pizza.tamanho)
        }

        return ResponseEntity.status(200).body(pizzas)
    }

    @GetMapping("/filtro-sabor/{sabores}")
    fun filtroSabores (
        @PathVariable sabores: String
    ):ResponseEntity<List<Pizza>>{
        var pizzas = pizzaRepository.findBySaboresNomeContains(sabores)

        if (pizzas.isEmpty()){
            return ResponseEntity.status(204).build()
        }

        for (pizza in pizzas){
            pizza.setPreco(pizza.tamanho)
        }

        return ResponseEntity.status(200).body(pizzas)
    }

    @GetMapping("/filtro-sabor-tamanho/{sabores}/{tamanho}")
    fun filtroSaborTamanho(
        @PathVariable sabores: String,
        @PathVariable tamanho: Tamanhos
    ):ResponseEntity<List<Pizza>>{
        var pizzas = pizzaRepository.findBySaboresNomeContainsAndTamanho(sabores,tamanho)

        if (pizzas.isEmpty()){
            return ResponseEntity.status(204).build()
        }

        for (pizza in pizzas){
            pizza.setPreco(pizza.tamanho)
        }

        return ResponseEntity.status(200).body(pizzas)

    }

}