package School.sptech.projeto03verboshttp

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/musicas")
class MusicaController {
    val listaMusicas =  mutableListOf(
        Musica("Cheia de Manias", "Raça Negra"))

    @GetMapping// utilização de GET
    fun listar(): ResponseEntity<List<Musica>> {
        if (listaMusicas.isEmpty()){
       return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaMusicas)

    }

    @GetMapping ("/{indice}") // utilização de GET
    fun porIndice(@PathVariable indice:Int): Musica ? {
        if (existeMusica(indice)) {
            return listaMusicas[indice]
        }
        return null
    }

    @PostMapping //utilização de post em cadastro
    fun cadastrar(@RequestBody musica:Musica):Musica {
        listaMusicas.add(musica)
        return musica
    }

    @PutMapping("/{indice}")
    fun atualizar (@PathVariable indice:Int,
                   @RequestBody musica:Musica
    ): Musica? {
        if (existeMusica(indice)) {
            listaMusicas.set(indice, musica)
            return listaMusicas[indice]
        }
        return null
    }

    @DeleteMapping("{indice}")
    fun deletar (@PathVariable indice: Int):Musica ?{
        if (existeMusica(indice)){
            listaMusicas.removeAt(indice)
        }
        return null
    }

    fun existeMusica(indice:Int):Boolean{
        return indice >= 0 && indice < listaMusicas.size
    }
}