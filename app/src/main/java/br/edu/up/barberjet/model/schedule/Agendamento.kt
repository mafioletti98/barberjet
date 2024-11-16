package br.edu.up.barberjet.model.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Agendamento(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var date: String,
    var horaMin: String,
){
    constructor() : this(null, "", "")
}