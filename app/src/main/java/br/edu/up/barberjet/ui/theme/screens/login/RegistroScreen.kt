package br.edu.up.barberjet.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.barberjet.BarberRotas
import br.edu.up.barberjet.ui.theme.screens.BarberTopBar
import br.edu.up.barberjet.ui.theme.themes.Amarelo
import br.edu.up.barberjet.ui.theme.themes.Preto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaRegistro(drawerState: DrawerState) {
    Scaffold(
        topBar = { BarberTopBar(drawerState = drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Tela de Registro", fontSize = 24.sp, color = Amarelo)


                var username by remember { mutableStateOf("") }
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nome de Usuário", color = Amarelo) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Preto
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default
                )

                var nomeCompleto by remember { mutableStateOf("") }
                TextField(
                    value = nomeCompleto,
                    onValueChange = { nomeCompleto = it },
                    label = { Text("Nome Completo", color = Amarelo) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Preto
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default
                )

                var email by remember { mutableStateOf("") }
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail:", color = Amarelo) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Preto
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default
                )

                var endereco by remember { mutableStateOf("") }
                TextField(
                    value = endereco,
                    onValueChange = { endereco = it },
                    label = { Text("Endereço:", color = Amarelo) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Preto
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default
                )

                var cep by remember { mutableStateOf("") }
                TextField(
                    value = cep,
                    onValueChange = { cep = it },
                    label = { Text("CEP:", color = Amarelo) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Preto
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default
                )

                var password by remember { mutableStateOf("") }
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha", color = Amarelo) },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Preto
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default
                )

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Preto)
                ) {
                    Text(text = "Entrar", color = Amarelo)
                }
            }


        }
    )
}
