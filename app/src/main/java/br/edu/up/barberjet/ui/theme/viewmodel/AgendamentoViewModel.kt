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
    private val _agendamentosBackup = MutableStateFlow<List<Agendamento>>(emptyList())
    val agendamentos: StateFlow<List<Agendamento>> get() = _agendamentos

    init {
        viewModelScope.launch {
            repository.listarAgendamentos().collectLatest { listaDeAgendamentos ->
                _agendamentos.value = listaDeAgendamentos
                _agendamentosBackup.value = listaDeAgendamentos
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

    fun editar(agendamento: Agendamento) {
        viewModelScope.launch {
            repository.editarAgendamento(agendamento)
            val updatedAgendamentos = _agendamentos.value.map {
                if (it.id == agendamento.id) agendamento else it
            }
            _agendamentos.value = updatedAgendamentos
        }
    }

    fun buscarPorNomeCompleto(nomeCompleto: String) {
        viewModelScope.launch {
            if(!nomeCompleto.isEmpty()){
                val updatedAgendamentos = _agendamentosBackup.value.filter {
                    it.nomeCompleto == nomeCompleto
                }
                _agendamentos.value = updatedAgendamentos
            } else {
                _agendamentos.value = _agendamentosBackup.value
            }
        }
    }
}