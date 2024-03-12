package sptech.projeto02

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping ("/pokemon")
class PokemonController {
    val pokemons = mutableListOf("a", "b", "c")
    fun pokemon(): Int {
        return pokemons.size
    }

    @GetMapping ("/novo/{nome}")
    fun novo (@PathVariable nome:String): String {
        var novoPokemon = nome
        pokemons.add(novoPokemon)
    return "Pokemon $novoPokemon cadastrado com sucesso"
    }

    @GetMapping("/recuperar/{indice}")
    fun recuperar (@PathVariable indice:Int):String {
        if (indice >= 0 && indice <= (pokemons.size-1) )
            return pokemons[indice]
    }

}