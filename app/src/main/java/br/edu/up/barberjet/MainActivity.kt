package br.edu.up.barberjet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.up.barberjet.model.schedule.AgendamentoDatabase.Companion.abrirAgendamentoBancoDeDados
import br.edu.up.barberjet.model.schedule.LocalAgendamentoRepository
import br.edu.up.barberjet.model.schedule.RemoteAgendamentoRepository
import br.edu.up.barberjet.ui.theme.viewModel.AgendamentoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = abrirAgendamentoBancoDeDados(this)

        val localRepository = LocalAgendamentoRepository(db.agendamentoDao())
        val remoteRepository = RemoteAgendamentoRepository()

        val viewModel = AgendamentoViewModel(remoteRepository)

        setContent {
            BarberNavDrawer(viewModel)
        }
    }
}