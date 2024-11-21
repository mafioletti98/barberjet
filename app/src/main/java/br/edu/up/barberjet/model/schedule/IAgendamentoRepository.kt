package br.edu.up.barberjet.model.schedule

import kotlinx.coroutines.flow.Flow

interface IAgendamentoRepository {
    fun listarAgendamentos(): Flow<List<Agendamento>>
    suspend fun buscarAgendamentoId(idx: Int): Agendamento?
    suspend fun gravarAgendamento(agendamento: Agendamento)
    suspend fun excluirAgendamento(agendamento: Agendamento)
    suspend fun editarAgendamento(agendamento: Agendamento)
}
