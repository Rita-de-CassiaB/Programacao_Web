package sptech.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/herois")

class HeroiController {

    val herois = mutableListOf<Heroi>(
        Heroi("electabuzz", "raios", 12, 60, true ),
        Heroi("blazikem", "fogo e luta", 19, 120, true ),
        Heroi("swampert", "terra e água", 16, 112, true )
    )

    @GetMapping("/{indice}")
    fun indice(@PathVariable indice:Int):Heroi ? {

        if (indice>=0 && indice <= herois.size -1){
            return herois[indice]
        }else{
            return null
        }
    }

    @GetMapping ("/cadastrar/{nome}/{habilidade}/{idade}/{forca}/{vivo}")
    fun cadastrar (@PathVariable nome:String,
                   @PathVariable habilidade:String,
                   @PathVariable idade:Int,
                   @PathVariable forca:Int,
                   @PathVariable vivo:Boolean
                    ):String {
        var novoHeroi = Heroi(nome, habilidade, idade, forca, vivo)
        herois.add(novoHeroi)
        return "Heroi $nome de habilidade $habilidade, idade $idade e força $forca cadastrado com sucesso"
    }

    @GetMapping()
    fun todos():List<Heroi>{
        return herois
    }

    @GetMapping ("/atualizar/{indice}/{nome}/{habilidade}/{idade}/{forca}/{vivo}")
    fun atualizar (@PathVariable indice: Int,
                   @PathVariable nome:String,
                   @PathVariable habilidade:String,
                   @PathVariable idade:Int,
                   @PathVariable forca:Int,
                   @PathVariable vivo:Boolean
    ):Heroi? {

        if (indice >= 0 && indice <= herois.size -1){
            var atualizarHeroi = Heroi(nome, habilidade, idade, forca, vivo)
            herois[indice] = atualizarHeroi

            return herois[indice]
        }else{
            return null
        }
    }

    @GetMapping("/remover/{indice}")
    fun remover(@PathVariable indice: Int):String{
        if (indice >= 0 && indice <= herois.size-1){
            herois.removeAt(indice)
            return "Heroi excluido"
        }else   {
            return "Tente novamente, heroi não encontrado!"
        }
    }
}
