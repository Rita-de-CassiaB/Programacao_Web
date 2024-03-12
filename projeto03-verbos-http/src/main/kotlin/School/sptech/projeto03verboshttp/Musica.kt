package School.sptech.projeto03verboshttp

import java.time.LocalDate

class Musica (
    var nome:String,
    var artista:String,
    var dataAdicao: LocalDate = LocalDate.now()
) {
}