package br.edu.up.barberjet.model.schedule

import kotlinx.coroutines.flow.Flow

class LocalAgendamentoRepository(
    private val dao : AgendamentoDAO
) : IAgendamentoRepository {

    override fun listarAgendamentos(): Flow<List<Agendamento>> {
        return dao.listarAgendamentos()
    }

    override suspend fun buscarAgendamentoId(idx: Int): Agendamento {
        return dao.buscarAgendamentoId(idx)
    }

    override suspend fun gravarAgendamento(agendamento: Agendamento) {
        dao.gravarAgendamento(agendamento)
    }

    override suspend fun excluirAgendamento(agendamento: Agendamento) {
        dao.excluirAgendamento(agendamento)
    }

}
