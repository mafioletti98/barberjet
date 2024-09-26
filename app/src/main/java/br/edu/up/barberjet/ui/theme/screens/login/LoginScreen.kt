package br.edu.up.barberjet.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import br.edu.up.barberjet.ui.theme.themes.Amarelo
import br.edu.up.barberjet.ui.theme.themes.DarkGrey
import br.edu.up.barberjet.ui.theme.themes.Preto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Login", color = Amarelo)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Preto)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
//                .padding(16.dp)
                .background(Color.Black),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tela de Login",
                fontSize = 24.sp,
                color = Preto,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            var username by remember { mutableStateOf("") }
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nome de Usuário", color = Amarelo) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.White,
                    containerColor = DarkGrey
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
                    containerColor = DarkGrey
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )

            Button(
                onClick = { navController.navigate(LoginRotas.TELA_AGENDAMENTO_ROUTE) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = "Entrar", color = Amarelo)
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = {
                navController.navigate(LoginRotas.TELA_REGISTRO_ROUTE)
            }) {
                Text(text = "Não tem uma conta? Registre-se aqui", color = Amarelo)
            }
        }
    }
}
