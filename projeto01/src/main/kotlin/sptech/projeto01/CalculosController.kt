package sptech.projeto01

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/calculos")
class CalculosController {

    /* URI:} /calculos/somar/9/15 -> A soma entre ... 24.0
     Neste EndPoint temos 2 path params (num1 e num2)
     {num1} -> associado automaticamente ao parâmetro num1 por terem o mesmo nome
     essa conexão so aconteceu por conta da anotação com path variable
     */
    @GetMapping("/somar/{num1}/{num2}")
    fun somar(@PathVariable num1:Double, @PathVariable num2:Double): String {
        return "A soma entre $num1 e $num2 é ${num1+num2}"
    }
    @GetMapping("/resultado/{nota1}/{nota2}")
    fun resultado (@PathVariable nota1:Double, @PathVariable nota2:Double): String {
        var media = (nota1 + nota2)/2
        if (media >= 6){
            return "Parabéns aprovação"
        }else{
            return "Infelizmente reprovação"
        }
    }
}