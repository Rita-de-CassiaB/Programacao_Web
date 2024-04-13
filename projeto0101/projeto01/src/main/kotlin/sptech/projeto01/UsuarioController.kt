package sptech.projeto01

import org.apache.coyote.Response
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/usuarios")
class UsuarioController {
    val listaUsuarios:MutableList<Usuario> = mutableListOf()

    @PostMapping
    fun cadastrar(@RequestBody usuario: Usuario):ResponseEntity <Usuario> {
        listaUsuarios.add(usuario)
        return ResponseEntity.status(201).body(usuario)
    }

//    @PostMapping("/autenticacao/{indice}")
//    fun autenticar(@PathVariable indice: Int,
//                   @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
//        if (true && usuario.senha !=null && usuario.ativo = true){
//             usuario.autenticado = true
//            return ResponseEntity.status(201).body(usuario)
//        }else if(usuario.login != null && usuario.senha !=null && usuario.ativo = false){
//            return ResponseEntity.status(401).body(usuario) // "Quem é vc"
//        }
//        return ResponseEntity.status(403).body(usuario) // "Aqui você não pode"
//    }

    @DeleteMapping("/{indice}")
    fun deletar(@PathVariable indice: Int): ResponseEntity<Void>{
        if (existeUsuario(indice)){
            listaUsuarios.removeAt(indice)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()

    }

    @GetMapping
    fun retornarUsuarios():
            ResponseEntity<MutableList<Usuario>> {
        if (listaUsuarios.size < 1 ){
            return ResponseEntity.status(204).build()
        }else {
            return ResponseEntity.status(200).body(listaUsuarios)
        }
    }

    @GetMapping("/{indice}")
    fun retornarPorIndice(@PathVariable indice:Int): ResponseEntity<Usuario> {
        if(existeUsuario(indice)){
            return ResponseEntity.status(200).body(listaUsuarios[indice])
        } else  {
            return ResponseEntity.status(404).build()
        }
    }

    fun existeUsuario(indice: Int):Boolean{
        return indice >= 0 && indice < listaUsuarios.size
    }

    @PatchMapping("/{indice}")
    fun patch (@PathVariable indice:Int,
               @RequestBody atualizacaoUsuario: PatchUsuario): ResponseEntity<Usuario> {
       try {
           val usuario = listaUsuarios[indice]
           usuario.senha = atualizacaoUsuario.senha
           usuario.dicaSenha = atualizacaoUsuario.dicaSenha
           return ResponseEntity.status(200).body(usuario)
       } catch (exception:Exception){
           return ResponseEntity.status(404).build()
       }
    }


}