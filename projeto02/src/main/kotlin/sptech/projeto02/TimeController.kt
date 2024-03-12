package sptech.projeto02

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Time

@RestController
@RequestMapping("/times")
class TimeController {

    val times = mutableListOf<TimeFutebol>(
        TimeFutebol("aaa",10,1,0,5.5),
        TimeFutebol("bbb", 4,7,2,698.20),
        TimeFutebol("ccc", 0,4,10, 6859596.58)

    )
    @GetMapping("/primeiro")
    fun primeiro():TimeFutebol {
        return times.first()
    }

    @GetMapping()
    fun todos():List<TimeFutebol>{
        return times
    }

    @GetMapping("/novo/{nome}/{vit}/{emp}/{derr}/{patr}")
    fun novo( @PathVariable nome:String,
              @PathVariable vit:Int,
              @PathVariable emp:Int,
              @PathVariable derr:Int,
              @PathVariable patr:Double ):String {
        val novoTime = TimeFutebol(nome,vit, emp, derr, patr)
        times.add(novoTime)
        return "Time $nome cadastrado com sucesso"
    }

    @GetMapping("/{posicao}")
    fun posicao(@PathVariable posicao:Int):TimeFutebol ?{
        /*
        Valores válidos para a "posicao":
        0..(tamanho-1)
        3 -> 0..2
        5 -> 0..4
         */
        if (posicao >= 0 && posicao <= (times.size - 1)) {
            return times[posicao]
        } else {
            return null
        }
    }
}



