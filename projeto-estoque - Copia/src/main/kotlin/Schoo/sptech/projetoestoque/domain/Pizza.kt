package Schoo.sptech.projetoestoque.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Positive

@Entity
data class Pizza (
    @field:Id
    @field:GeneratedValue(strategy =  GenerationType.IDENTITY)
    var codigo: Int?,
    @field:ManyToOne var sabores: Sabores,
    var tamanho: Tamanhos,
    var preco:Double?,
    ) {

    fun setPreco(tamanho: Tamanhos) {

        when (tamanho) {
            Tamanhos.PEQUENO -> {
                preco = sabores.precoBase
            }

            Tamanhos.MEDIO -> {
                preco = sabores.precoBase * 1.5
            }

            Tamanhos.GRANDE -> {
                preco = sabores.precoBase * 2
            }
        }
    }
}