package sptech.projeto01

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/medicamentos")
class MedicamentoController {

    val listaMedicamento:MutableList<Medicamento> = mutableListOf()

    @PostMapping
    fun cadastrar(@RequestBody medicamento: Medicamento): ResponseEntity<Medicamento> {
        if(medicamento.codigo != null){
            if (medicamento.codigo!!.length == 3){
                if (medicamento.valorUnd >= 0.01){
                    if (medicamento.quantidade >= 0){
                        listaMedicamento.add(medicamento)
                        return ResponseEntity.status(201).body(medicamento)
                    }
                    else{
                        throw ResponseStatusException(
                            HttpStatusCode.valueOf(400),"A quantidade deve ser um número positivo")
                    }
                }else{
                    throw ResponseStatusException(
                        HttpStatusCode.valueOf(400),"Valor por Unidade deve ser maior ou igual a 0.01")
                }
            }else{
                throw ResponseStatusException(
                    HttpStatusCode.valueOf(400),"Código deve ter extamente 3 digitos")
            }
        }else{
            throw ResponseStatusException(
                HttpStatusCode.valueOf(400),"Código não pode estar vazio")

        }
    }

    fun validar(medicamento:Medicamento): ResponseEntity<String> {

        return ResponseEntity.status(400).body("Deu erro")

    }

    //    @GetMapping
//    fun retornarMedicamentos():ResponseEntity<MutableList<Medicamento>>{
//
//        if (listaMedicamento.size < 1){
//            return ResponseEntity.status(204).build()
//        }else{
//            return ResponseEntity.status(200).body(listaMedicamento)
//        }
//
//    }
    @GetMapping
    fun retornarMedicamentos(): ResponseEntity<MutableList<Medicamento>> {
        if (listaMedicamento.size < 1){
            return ResponseEntity.status(204).build()
        }else {
            return ResponseEntity.status(200).body(listaMedicamento)
        }
    }

    @GetMapping("/{indice}")
    fun pegarporIndice(@PathVariable indice:Int): ResponseEntity<Medicamento> {
        if (verificarIndice(indice)){
            return ResponseEntity.status(200).body(listaMedicamento[indice])
        }else{
            return ResponseEntity.status(404).build()
        }
    }

    fun verificarIndice(indice:Int):Boolean{
        if(indice >= 0 && indice <= (listaMedicamento.size-1)){
            return true
        }else{
            return false
        }
    }

    @PatchMapping("/{indice}")
    fun patch(
        @PathVariable indice: Int,
        @RequestBody atualizacao: PathMedicamento
    ): ResponseEntity<Medicamento> {
        try {
            val medicamento = listaMedicamento[indice]
            medicamento.valorUnd = atualizacao.novoValorUnd
            medicamento.quantidade += atualizacao.quantidadeEntrada
            return ResponseEntity.status(200).body(medicamento)
        } catch (exception:Exception) {
            return ResponseEntity.status(404).build()
        }
    }

}