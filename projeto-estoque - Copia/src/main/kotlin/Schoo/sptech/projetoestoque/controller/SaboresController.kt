package Schoo.sptech.projetoestoque.controller
import Schoo.sptech.projetoestoque.domain.Sabores
import Schoo.sptech.projetoestoque.repository.SaboresRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sabores")
class SaboresController (
    val saboresRepository: SaboresRepository
){
    @PostMapping
    fun criar(@RequestBody novoSabor: Sabores):
            ResponseEntity<Sabores> {

        val saborSalvo = saboresRepository.save(novoSabor)
        return ResponseEntity.status(201).body(saborSalvo)
    }

    @GetMapping
    fun listar():ResponseEntity<List<Sabores>> {
        val sabores = saboresRepository.findAll()

        if(sabores.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(sabores)
    }

    @GetMapping("/top-3-caros")
    fun filtrartop3Caros(): ResponseEntity<List<Sabores>> {
        var lista = saboresRepository.findTop3ByOrderByPrecoBaseDesc()

        if (lista.isEmpty()){
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(200).body(lista)

    }

    @PatchMapping(value = ["/imagem/{codigo}"], consumes = ["image/jpeg","image/png" ])
    fun atualizarFoto(
        @PathVariable("codigo") codigo: Int,
        @RequestBody novafoto:ByteArray
    ):ResponseEntity<Void>{

        var sabores = saboresRepository.findById(codigo).get()

        if (sabores == null){
            return ResponseEntity.status(404).build()
        }else{
            sabores.foto = novafoto

            saboresRepository.save(sabores)

            return ResponseEntity.status(204).build()
        }

    }

    @GetMapping(value = ["/imagem/{codigo}"],produces = ["image/jpeg"])
    fun getImagem(
        @PathVariable codigo:Int
    ):ResponseEntity<Void>{
        saboresRepository.findFotoByCodigo(codigo)
        return ResponseEntity.status(200).build()

    }
}