package br.edu.up.barberjet.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.barberjet.model.schedule.Agendamento
import br.edu.up.barberjet.ui.theme.themes.Amarelo
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.navigation.NavController
import br.edu.up.barberjet.ui.theme.viewModel.AgendamentoViewModel

@Composable
fun TelaListaAgendamentos(drawerState: DrawerState, viewModel: AgendamentoViewModel, navController: NavController) {
    val agendamentos by viewModel.agendamentos.collectAsState()
    var nomeCompleto by remember { mutableStateOf("") }

    // Estado para gerenciar a edição e exclusão
    var agendamentoParaEditar by remember { mutableStateOf<Agendamento?>(null) }
    var agendamentoParaExcluir by remember { mutableStateOf<Agendamento?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = nomeCompleto,
                onValueChange = { nomeCompleto = it },
                placeholder = { Text("Nome Completo") },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                singleLine = true
            )
            Button(
                onClick = {
                    viewModel.buscarPorNomeCompleto(nomeCompleto)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Amarelo)
            ) {
                Text(text = "Buscar", color = Color.Black)
            }
        }

        Text(
            text = "Agendamentos",
            fontSize = 32.sp,
            color = Amarelo,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        agendamentos.forEach { agendamento ->
            AgendamentoItem(
                agendamento = agendamento,
                onEditClick = { agendamentoParaEditar = agendamento },
                onDeleteClick = { agendamentoParaExcluir = agendamento }
            )
        }

        // Diálogo para editar agendamento
        agendamentoParaEditar?.let { agendamento ->
            EditAgendamentoDialog(
                agendamento = agendamento,
                onDismiss = { agendamentoParaEditar = null },
                onSave = { updatedAgendamento ->
                    viewModel.editar(updatedAgendamento)
                    agendamentoParaEditar = null
                }
            )
        }

        // Diálogo para confirmar exclusão
        agendamentoParaExcluir?.let { agendamento ->
            ConfirmDeleteDialog(
                agendamento = agendamento,
                onDismiss = { agendamentoParaExcluir = null },
                onConfirm = {
                    viewModel.excluir(agendamento)
                    agendamentoParaExcluir = null
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                navController.popBackStack()  // Navegar de volta para a tela anterior
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Amarelo)
        ) {
            Text(text = "Voltar para Agendamentos", color = Color.Black)
        }
    }
}


@Composable
fun EditAgendamentoDialog(
    agendamento: Agendamento,
    onDismiss: () -> Unit,
    onSave: (Agendamento) -> Unit
) {
    var date by remember { mutableStateOf(agendamento.date) }
    var horaMin by remember { mutableStateOf(agendamento.horaMin) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Editar Agendamento") },
        text = {
            Column {
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Data") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = horaMin,
                    onValueChange = { horaMin = it },
                    label = { Text("Horário") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedAgendamento = agendamento.copy(date = date, horaMin = horaMin)
                onSave(updatedAgendamento)
            }) {
                Text(text = "Salvar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Composable
fun ConfirmDeleteDialog(
    agendamento: Agendamento,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Confirmar Exclusão") },
        text = {
            Text(text = "Você tem certeza que deseja excluir o agendamento${agendamento.nomeCompleto}?")
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = "Confirmar", color = Color.White)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}


@Composable
fun AgendamentoItem(
    agendamento: Agendamento,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.DarkGray, RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Informações do agendamento
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Email: ${agendamento.email}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Nome Completo: ${agendamento.nomeCompleto}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Data: ${agendamento.date}",
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = "Hora: ${agendamento.horaMin}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Ícones de editar e excluir
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Editar",
                    tint = Amarelo
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Excluir",
                    tint = Color.Red
                )
            }
        }
    }
}
