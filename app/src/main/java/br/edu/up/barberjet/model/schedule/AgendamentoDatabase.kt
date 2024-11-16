package br.edu.up.barberjet.model.schedule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Agendamento::class], version = 1)
abstract class AgendamentoDatabase: RoomDatabase(){

    abstract fun agendamentoDao(): AgendamentoDAO

    companion object{
        fun abrirAgendamentoBancoDeDados(context: Context): AgendamentoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AgendamentoDatabase::class.java, "agendamento.db"
            ).build()
        }
    }
}
