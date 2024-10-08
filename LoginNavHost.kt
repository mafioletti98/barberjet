package br.edu.up.barberjet.ui.theme.screens.login

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


object LoginRotas {
    const val TELA_LOGIN_ROUTE = "login"
    const val TELA_REGISTRO_ROUTE = "registro"
    const val TELA_AGENDAMENTO_ROUTE = "agendamento"
    const val TELA_SERVICOS_ROUTE = "servicos"

}

@Composable
fun LoginNavHost(drawerState: DrawerState) {
    val navCtrlBottomNav = rememberNavController()
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
            TelaDeAgendamento(drawerState = drawerState)
        }
        composable(LoginRotas.TELA_SERVICOS_ROUTE) {
            TelaServicos(drawerState = drawerState)
        }
    }
}
