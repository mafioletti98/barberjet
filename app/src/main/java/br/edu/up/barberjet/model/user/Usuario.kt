package br.edu.up.barberjet.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val nomeUsu: String,
    val nome: String,
    val email: String,
    val endereco: String,
    val cep: String,
    val senha: String
){
    constructor() : this(null, "", "", "", "","", "")
}