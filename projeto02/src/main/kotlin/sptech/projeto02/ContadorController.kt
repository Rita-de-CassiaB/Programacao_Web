package sptech.projeto02

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contador")
class ContadorController {

    /*
    Por padrão, toda Rest Controller é um SINGLETON
    */
    var contador = 0

    @GetMapping
    fun informarContagem():String {
        contador++
        return "Você já chamou este EndPoint $contador vezes"
    }


}