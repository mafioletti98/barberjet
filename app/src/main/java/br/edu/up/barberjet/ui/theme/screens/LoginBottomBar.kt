package br.edu.up.barberjet.ui.theme.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Defina suas rotas como constantes
object BarberRotas {
    const val TELA_LOGIN_ROUTE = "tela_login"
    const val TELA_AGENDAMENTO_ROUTE = "tela_agendamento"
    const val TELA_SERVIÇOS_ROUTE = "tela_servicos"
}

@Composable
fun BarberAppBottomBar(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    NavigationBar(containerColor = Color(0xFF1B4E74)) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                navController.navigate(BarberRotas.TELA_LOGIN_ROUTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Login",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Login") }
        )

        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                navController.navigate(BarberRotas.TELA_AGENDAMENTO_ROUTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Agendamento",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Agendamento") }
        )

        NavigationBarItem(
            selected = selectedIndex == 2,  // Verifica se a aba de serviços está selecionada
            onClick = {
                selectedIndex = 2  // Atualiza o índice selecionado
                navController.navigate(BarberRotas.TELA_SERVIÇOS_ROUTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Serviços",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Serviços") }
        )
    }
}
