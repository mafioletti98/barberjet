package br.edu.up.barberjet.model.schedule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendamentoDAO{

    @Query("select * from agendamento")
    fun listarAgendamentos(): Flow<List<Agendamento>>

    @Query("select * from agendamento where id = :idx")
    suspend fun buscarAgendamentoId(idx: Int): Agendamento

    @Upsert
    suspend fun gravarAgendamento(agendamento: Agendamento)

    @Delete
    suspend fun excluirAgendamento(agendamento: Agendamento)

}