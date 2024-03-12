package sptech.projeto01

class Usuario (
    var login:String,
    var senha:String,
    var nome:String,
    var dicaSenha:String,
    var ativo:Boolean,
    var autenticado:Boolean = false,
    var acessos:Int = 0
) {
}