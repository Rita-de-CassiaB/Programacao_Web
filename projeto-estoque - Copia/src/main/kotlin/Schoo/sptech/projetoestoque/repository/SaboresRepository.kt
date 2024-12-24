package Schoo.sptech.projetoestoque.repository

import Schoo.sptech.projetoestoque.domain.Sabores
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SaboresRepository:JpaRepository<Sabores, Int> {
    fun findTop3ByOrderByPrecoBaseDesc():List<Sabores>

        @Query("select s.foto from Sabores s where s.codigo = ?1")
    fun findFotoByCodigo(codigo:Int):ByteArray
}