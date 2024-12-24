package Schoo.sptech.projetoestoque.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@Entity
data class Sabores (
    @field:Id
    @field:GeneratedValue(strategy =  GenerationType.IDENTITY)
    var codigo: Int?,

    var nome: String?,

    var precoBase:Double,

    @JsonIgnore @field:Column(length = 30*1024*1024) var foto:ByteArray?
){
}