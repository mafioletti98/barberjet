package br.edu.up.barberjet.ui.theme.screens.login

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.barberjet.ui.theme.viewModel.AgendamentoViewModel


object LoginRotas {
    const val TELA_LOGIN_ROUTE = "login"
    const val TELA_REGISTRO_ROUTE = "registro"
    const val TELA_AGENDAMENTO_ROUTE = "agendamento"
    const val TELA_SERVICOS_ROUTE = "servicos"
    const val TELA_LISTA_AGENDAMENTOS_ROUTE = "listagem"

}

@Composable
fun LoginNavHost(drawerState: DrawerState, viewModel: AgendamentoViewModel) {
    val navCtrlBottomNav = rememberNavController()  // Controlador de navegação

    NavHost(
        navController = navCtrlBottomNav,
        startDestination = LoginRotas.TELA_LOGIN_ROUTE
    ) {
        composable(LoginRotas.TELA_LOGIN_ROUTE) {
            LoginScreen(navController = navCtrlBottomNav)
        }
        composable(LoginRotas.TELA_REGISTRO_ROUTE) {
            TelaRegistro(drawerState = drawerState)
        }
        composable(LoginRotas.TELA_AGENDAMENTO_ROUTE) {
            TelaDeAgendamento(drawerState = drawerState, viewModel = viewModel, navController = navCtrlBottomNav)  // Tela de Agendamento
        }
        composable(LoginRotas.TELA_LISTA_AGENDAMENTOS_ROUTE) {
            TelaListaAgendamentos(drawerState = drawerState, viewModel = viewModel, navController = navCtrlBottomNav)  // Tela de Listagem de Agendamentos
        }
    }
}
