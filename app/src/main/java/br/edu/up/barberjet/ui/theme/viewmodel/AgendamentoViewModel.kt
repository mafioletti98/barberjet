package br.edu.up.barberjet.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.barberjet.model.schedule.Agendamento
import br.edu.up.barberjet.model.schedule.IAgendamentoRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AgendamentoViewModel(private val repository : IAgendamentoRepository) : ViewModel() {
    private val _agendamentos = MutableStateFlow<List<Agendamento>>(emptyList())
    val agendamentos: StateFlow<List<Agendamento>> get() = _agendamentos

    init {
        viewModelScope.launch {
            repository.listarAgendamentos().collectLatest { listaDeAgendamentos ->
                _agendamentos.value = listaDeAgendamentos
            }
        }
    }

    fun excluir(agendamento: Agendamento) {
        viewModelScope.launch {
            repository.excluirAgendamento(agendamento)
        }
    }

    suspend fun buscarPorId(agendamentoId: Int): Agendamento? {
        return withContext(Dispatchers.IO){
            repository.buscarAgendamentoId(agendamentoId)
        }
    }

    fun gravar(agendamento: Agendamento) {
        viewModelScope.launch {
            repository.gravarAgendamento(agendamento)
        }
    }


}