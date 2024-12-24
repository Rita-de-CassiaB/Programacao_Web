package Schoo.sptech.projetoestoque.domain

import org.springframework.web.bind.annotation.PathVariable

enum class Tamanhos
    (val descricao:String,) {
    PEQUENO ("pequena"),
    MEDIO("media"),
    GRANDE ("grande")
}



