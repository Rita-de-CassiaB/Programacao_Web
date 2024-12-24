package Schoo.sptech.projetoestoque.controller


import Schoo.sptech.projetoestoque.domain.Diretor
import Schoo.sptech.projetoestoque.domain.Filme
import Schoo.sptech.projetoestoque.repository.DiretorRepository
import Schoo.sptech.projetoestoque.repository.FilmeRepository
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/filmes")
class FilmeController (
    val filmeRepository: FilmeRepository,
    val diretorRepository: DiretorRepository
){
// buscando todos
    @GetMapping
    fun get():ResponseEntity<List<Filme>>{
        val filmes = filmeRepository.findAll()

        if(filmes.isEmpty()){
            return ResponseEntity.status(204).build()

        }
        return  ResponseEntity.status(200).body(filmes)
    }

    // buscando por id
    @GetMapping("/{id}")
    fun get(@PathVariable id:Int):ResponseEntity<Filme>{
        if (filmeRepository.existsById(id)){
            var filme = filmeRepository.findById(id).get()
            return ResponseEntity.status(200).body(filme)
        }
        return ResponseEntity.status(400).build()
    }

    // buscando por parte do titulo
    @GetMapping("filtro-titulo/{titulo}")
    fun filtroNome(@RequestParam titulo:String):ResponseEntity<List<Filme>>{
        val filmes = filmeRepository.findByTituloContains(titulo)

        if(filmes.isNotEmpty()){
            return ResponseEntity.status(200).body(filmes)
    }
        return ResponseEntity.status(204).build()

}
    // buscando por parte do titulo
    @GetMapping("filtro-nacionalidade-diretor/{nacionalidade}")
    fun filtronacionalidade(@RequestParam nacionalidade:String):ResponseEntity<List<Filme>> {
        val filmes = filmeRepository.findByDiretorNacionalidade(nacionalidade)

        if (filmes.isNotEmpty()) {
            return ResponseEntity.status(200).body(filmes)
        }
        return ResponseEntity.status(204).build()
    }
    // buscando por diretor e genero
    @GetMapping("filtro-genero-diretor/{genero}/{diretor}")
    fun filtrogenerodiretor(
        @PathVariable genero:String,
        @RequestBody diretor: Diretor):
            ResponseEntity<List<Filme>> {
        var filmes = filmeRepository.findByGeneroAndDiretor(genero, diretor)

        if(filmes.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(filmes)
    }

//    @GetMapping("/filtro-nascimento-diretor/{dataInicio}/{dataFim}")
//    fun filtronascimento(
//        @RequestParam dataInicio:Int,
//        @RequestParam dataFim:Int):
//            ResponseEntity<List<Filme>>{
//        var filmes = filmeRepository.findByDiretorNascimentoBetweenDataInicioAndDataFim(dataInicio, dataFim)
//
//        if(filmes.isEmpty()) {
//            return ResponseEntity.status(204).build()
//        }
//        return ResponseEntity.status(200).body(filmes)
//    }

}