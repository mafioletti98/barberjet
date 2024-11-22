package br.edu.up.barberjet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
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
        val isOnline = isNetworkAvailable(this)

        val localRepository = LocalAgendamentoRepository(db.agendamentoDao())
        val remoteRepository = RemoteAgendamentoRepository()

        var viewModel = AgendamentoViewModel(localRepository)
        //Log.d("isOnline", isOnline.toString())

        if(isOnline){
            viewModel = AgendamentoViewModel(remoteRepository)
        }

        setContent {
            BarberNavDrawer(viewModel)
        }
    }

    // Metodo para verificar conexão
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}