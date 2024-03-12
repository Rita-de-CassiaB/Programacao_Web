package sptech.projeto02

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/esportes")
class EsportesController {
    val esportes = mutableListOf(
        "futebol", "tênis", "volei", "basquete", "xadrez"
    )

    /* Exemplos de URI p teste
    /esportes/pesquisa?termo=te
    /esportes/pesquisa?termo=s
    /esportes/pesquisa?termo=x
    /esportes/pesquisa?termo=www

    Aqui usamos uma Query Param (ou request param) - "Parâmetro de Requisição"
    */
    @GetMapping("/pesquisa")
    fun pesquisar(@RequestParam termo:String):List<String> {
        return esportes.filter {it.contains(termo)}
    }

    /*
    /esportes/filtro
    2 parâmetros de requisição: "termo" e "tamanho"
    ex: termo "x" e tamanho 10 -> lista vazia,
    pq "xadrez" tem menos de 10 letras
    MAS termo "x" e tamanho 3 -> ["xadrez"]

    /esportes/filtro?termo=x&tamanho=10
    teria o mesmo resultado de...
    /esportes/filtro?tamanho=10&termo=x
     */

    @GetMapping("/filtro")
    fun filtrar(@RequestParam termo:String, @RequestParam tamanho:Int):List<String>{
        return esportes.filter {
            it.contains(termo)&& it.length >= tamanho
        }
    }

}