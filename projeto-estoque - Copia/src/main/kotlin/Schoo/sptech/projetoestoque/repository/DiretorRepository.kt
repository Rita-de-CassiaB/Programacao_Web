package Schoo.sptech.projetoestoque.repository

import Schoo.sptech.projetoestoque.domain.Diretor
import org.springframework.data.jpa.repository.JpaRepository

interface DiretorRepository: JpaRepository<Diretor, Int> {
}