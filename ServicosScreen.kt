package br.edu.up.barberjet.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.barberjet.R
import br.edu.up.barberjet.ui.theme.screens.BarberTopBar
import br.edu.up.barberjet.ui.theme.themes.Amarelo
import br.edu.up.barberjet.ui.theme.themes.Preto

@Composable
fun TelaServicos(drawerState: DrawerState) {
    Scaffold(modifier = Modifier.background(Color.Black),
        topBar = { BarberTopBar(drawerState = drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
//                    .padding(16.dp)
                    .background(Color.Black),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "  Serviços Disponíveis",
                    fontSize = 24.sp,
                    color = Amarelo,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                BoxService(
                    serviceName = "Corte de Cabelo",
                    serviceDescription = "Corte moderno e estiloso para todos os tipos de cabelo.",
                    imageResource = R.drawable.ic_servico_corte
                )

                Spacer(modifier = Modifier.height(16.dp))

                BoxService(
                    serviceName = "Barba",
                    serviceDescription = "Aparação e modelagem de barba com produtos de qualidade.",
                    imageResource = R.drawable.ic_servico_barba
                )

                Spacer(modifier = Modifier.height(16.dp))

                BoxService(
                    serviceName = "Tintura",
                    serviceDescription = "Tintura profissional para um visual renovado.",
                    imageResource = R.drawable.ic_servico_tintura
                )
            }
        }
    )
}

@Composable
fun BoxService(serviceName: String, serviceDescription: String, imageResource: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = serviceName,
                    fontSize = 18.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = serviceDescription,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

