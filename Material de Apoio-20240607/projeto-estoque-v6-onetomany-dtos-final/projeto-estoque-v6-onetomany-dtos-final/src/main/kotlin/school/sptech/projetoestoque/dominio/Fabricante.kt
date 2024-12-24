package school.sptech.projetoestoque.dominio

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
data class Fabricante (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @field:Size(min = 3)
    var nome: String?,

    @field:OneToMany(mappedBy = "fabricante")
    var produtos: List<Produto>? = null
)

