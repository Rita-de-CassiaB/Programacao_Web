package sptech.projeto01

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
/*
O RestController transforma a classe em uma REST Controlller, ou seja, uma classe que pode abrigar EndPoints
O @ é uma anotação, ou seja, uma meta-programação (dedução - pequeno símbolo que traz características, ele é interpretado pelo SPRING)
*/
@RestController
@RequestMapping("/frases") // indica que todos os endpoits começarão com /frases
class FrasesController {

    /*
    GetMapping -> esta anotação indica que o metodo é um EndPoint ou Chamada
    "/bom-dia" -> URI (Universal Resource Identifier)
    A URI completa deste EndPoint é "/frases/bom-dia"
   */

    @GetMapping("/bom-dia") // adiciona o /bom-dia ao /frases por estar dentro da função que é regida por ele
    fun bomDia():String{
        return "Bom dia, flor do dia!"
    }

    @GetMapping("/idade-maior-idade")
    fun idadeMaiorIdade():Int {
        return 18
    }

    @GetMapping("/sistema-online")
    fun sistemaOnline (): Boolean{
        return true
    }
}

// Endereço localhost:8080/frases/bom-dia
// (O caminho que foi criado com o Request Mapping)