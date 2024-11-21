package br.edu.up.barberjet.ui.theme.screens.login

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.barberjet.ui.theme.screens.BarberTopBar
import br.edu.up.barberjet.ui.theme.themes.Amarelo
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import br.edu.up.barberjet.model.schedule.Agendamento
import br.edu.up.barberjet.ui.theme.themes.DarkGrey
import br.edu.up.barberjet.ui.theme.viewModel.AgendamentoViewModel
import java.util.*

@Composable
fun TelaDeAgendamento(drawerState: DrawerState, viewModel: AgendamentoViewModel, navController: NavController) {
    val context = LocalContext.current

    var selectedDate by remember { mutableStateOf("Selecione a Data") }
    var selectedTime by remember { mutableStateOf("Selecione o Horário") }
    var fullName by remember { mutableStateOf("") } // Para o nome completo
    var email by remember { mutableStateOf("") } // Para o email

    val calendar = Calendar.getInstance()

    Scaffold(
        topBar = { BarberTopBar(drawerState = drawerState) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Tela de Agendamentos",
                    fontSize = 32.sp,
                    color = Amarelo,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Nome Completo") },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    singleLine = true
                )

                // Botão de Seleção de Data
                Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Button(
                        onClick = {
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    selectedDate = "$dayOfMonth/${month + 1}/$year"
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Amarelo)
                    ) {
                        Text(text = selectedDate, color = Color.Black)
                    }
                }

                // Botão de Seleção de Horário
                Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Button(
                        onClick = {
                            TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    selectedTime = String.format("%02d:%02d", hour, minute)
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true
                            ).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Amarelo)
                    ) {
                        Text(text = selectedTime, color = Color.Black)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                        Button(
                            onClick = {
                                val agendamento = Agendamento()
                                agendamento.date = selectedDate
                                agendamento.horaMin = selectedTime
                                agendamento.nomeCompleto = fullName
                                agendamento.email = email

                                viewModel.gravar(agendamento)

                                navController.navigate(LoginRotas.TELA_LISTA_AGENDAMENTOS_ROUTE)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Amarelo)
                        ) {
                            Text(text = "Confirmar", color = Color.Black)
                        }
                    }

                    Box(modifier = Modifier.weight(1f).padding(8.dp)) {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = Amarelo)
                        ) {
                            Text(text = "Cancelar", color = Color.Black)
                        }
                    }
                }
            }
        }
    )
}
