package Schoo.sptech.projetoestoque.controller

import Schoo.sptech.projetoestoque.domain.Diretor
import Schoo.sptech.projetoestoque.repository.DiretorRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/diretores")
class DiretorController {

    val diretorRepository: DiretorRepository

    constructor(diretorRepository: DiretorRepository) {
        this.diretorRepository = diretorRepository
    }

    @PostMapping
    fun post(@RequestBody @Valid novoDiretor: Diretor): ResponseEntity<Diretor> {
        val diretorSalvo = diretorRepository.save(novoDiretor)

        return ResponseEntity.status(201).body(diretorSalvo)
    }
}