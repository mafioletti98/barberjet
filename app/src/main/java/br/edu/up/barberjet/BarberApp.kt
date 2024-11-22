package br.edu.up.barberjet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.up.barberjet.ui.theme.screens.login.LoginNavHost
import br.edu.up.barberjet.ui.theme.screens.login.TelaDeAgendamento
import br.edu.up.barberjet.ui.theme.screens.login.TelaListaAgendamentos
import br.edu.up.barberjet.ui.theme.screens.login.TelaRegistro
import br.edu.up.barberjet.ui.theme.screens.login.TelaServicos
import br.edu.up.barberjet.ui.theme.viewModel.AgendamentoViewModel
import kotlinx.coroutines.launch

object BarberRotas {
    const val TELA_LOGIN = "tela_login"
    const val TELA_CADASTRO = "tela_cadastro"
    const val TELA_AGENDAMENTO = "tela_agendamento"
    const val TELA_SERVICOS = "tela_servicos"
    const val TELA_LISTA_AGENDAMENTOS = "tela_lista_agendamentos"  // Nova rota
}

@Composable
fun BarberNavDrawer(viewModel: AgendamentoViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()  // Usando uma única instância do NavController

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)  // Passando o navController para o Drawer
        },
        content = {
            NavHost(
                navController = navController,  // Usando o mesmo NavController aqui
                startDestination = BarberRotas.TELA_LOGIN
            ) {
                composable(BarberRotas.TELA_LOGIN) {
                    LoginNavHost(drawerState, viewModel)
                }
                composable(BarberRotas.TELA_CADASTRO) {
                    TelaRegistro(drawerState)
                }
                composable(BarberRotas.TELA_AGENDAMENTO) {
                    TelaDeAgendamento(drawerState, viewModel = viewModel, navController)
                }
                composable(BarberRotas.TELA_SERVICOS) {
                    TelaServicos(drawerState)
                }
                composable(BarberRotas.TELA_LISTA_AGENDAMENTOS) {
                    TelaListaAgendamentos(drawerState, viewModel = viewModel, navController)  // Nova tela
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {
    val coroutineScope = rememberCoroutineScope()
    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: BarberRotas.TELA_LOGIN

    val ehLogin = rotaAtual == BarberRotas.TELA_LOGIN
    val ehCadastro = rotaAtual == BarberRotas.TELA_CADASTRO
    val ehAgendamento = rotaAtual == BarberRotas.TELA_AGENDAMENTO
    val ehServicos = rotaAtual == BarberRotas.TELA_SERVICOS
    val ehListaAgendamentos = rotaAtual == BarberRotas.TELA_LISTA_AGENDAMENTOS

    Column(
        modifier = Modifier
            .background(Color(0xFF070918))
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehLogin)),
            onClick = {
                navController.navigate(BarberRotas.TELA_LOGIN)
                coroutineScope.launch { drawerState.close() }
            }) {
            Image(
                imageVector = Icons.Default.Person, // Substitua pelo ImageVector que deseja usar
                contentDescription = "Login",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(getColorTexto(ehLogin)) // Define a cor do ícone
            )
            Text(text = "Login", fontSize = 20.sp, color = getColorTexto(ehLogin))
        }


        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehCadastro)),
            onClick = {
                navController.navigate(BarberRotas.TELA_CADASTRO)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_agenda),
                contentDescription = "Cadastro",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehCadastro)
            )
            Text(text = "Cadastro", fontSize = 20.sp, color = getColorTexto(ehCadastro))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehAgendamento)),
            onClick = {
                navController.navigate(BarberRotas.TELA_AGENDAMENTO)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                contentDescription = "Agendamento",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehAgendamento)
            )
            Text(text = "Agendamento", fontSize = 20.sp, color = getColorTexto(ehAgendamento))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehListaAgendamentos)),
            onClick = {
                navController.navigate(BarberRotas.TELA_LISTA_AGENDAMENTOS)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_recent_history),
                contentDescription = "Lista de Agendamentos",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehListaAgendamentos)
            )
            Text(text = "Lista de Agendamentos", fontSize = 20.sp, color = getColorTexto(ehListaAgendamentos))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(ehServicos)),
            onClick = {
                navController.navigate(BarberRotas.TELA_SERVICOS)
                coroutineScope.launch { drawerState.close() }
            }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_preferences),
                contentDescription = "Serviços",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehServicos)
            )
            Text(text = "Serviços", fontSize = 20.sp, color = getColorTexto(ehServicos))
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) Color.Yellow else Color(0xFF000624)
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) Color.Black else Color.Yellow
}
