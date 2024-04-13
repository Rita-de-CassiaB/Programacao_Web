package sptech.projetoclientes

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clientes")
class ClienteController {

    val clientes = mutableListOf<Cliente>()

    @PostMapping
    fun post(@RequestBody @Valid novoCliente: Cliente) {
        clientes.add(novoCliente)
    }

    @GetMapping
    fun get():List<Cliente> {
        return clientes
    }

}