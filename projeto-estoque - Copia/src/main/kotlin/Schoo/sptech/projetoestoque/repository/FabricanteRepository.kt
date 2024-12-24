package Schoo.sptech.projetoestoque.repository

import Schoo.sptech.projetoestoque.domain.Fabricante
import org.springframework.data.jpa.repository.JpaRepository

interface FabricanteRepository:JpaRepository<Fabricante, Int> {
}