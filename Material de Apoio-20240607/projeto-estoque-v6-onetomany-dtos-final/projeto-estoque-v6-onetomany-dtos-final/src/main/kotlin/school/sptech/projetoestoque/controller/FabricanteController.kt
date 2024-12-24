package school.sptech.projetoestoque.controller

import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import school.sptech.projetoestoque.dominio.Fabricante
import school.sptech.projetoestoque.dto.FabricanteResponse
import school.sptech.projetoestoque.dto.ProdutoResponse
import school.sptech.projetoestoque.repository.FabricanteRepository

@RestController
@RequestMapping("/fabricantes")
class FabricanteController(
    val fabricanteRepository: FabricanteRepository,
    val mapper: ModelMapper = ModelMapper()
) {

    @PostMapping
    fun criar(@RequestBody @Valid novoFabricante: Fabricante) :
            ResponseEntity<Fabricante> {

        val fabricanteSalvo = fabricanteRepository.save(novoFabricante)
        return ResponseEntity.status(201).body(fabricanteSalvo)
    }

    @GetMapping
    fun listar(): ResponseEntity<List<FabricanteResponse>> {
        val fabricantes = fabricanteRepository.findAll()

        if(fabricantes.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        val dtos = fabricantes.map {
            mapper.map(it, FabricanteResponse::class.java)
        }

        return ResponseEntity.status(200).body(dtos)
    }
}