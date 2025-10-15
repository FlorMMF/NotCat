package com.Mtimes.notcat.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.Mtimes.notcat.R

@Composable
fun RegisterScreen(navController: NavHostController){
    var nomUsuario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var confirmCorreo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmContrasena by remember { mutableStateOf("") }

    val errorMessage by remember { mutableStateOf("") }

    val errorText by remember(correo, confirmCorreo){
        when {
            correo.isEmpty() && confirmCorreo.isEmpty() -> mutableStateOf("")
            correo == confirmCorreo -> mutableStateOf("")
            else -> mutableStateOf("Los correos no coinciden.")
        }
    }

    val esValido = correo.isNotEmpty() && confirmCorreo.isNotEmpty() && errorText.isEmpty()



    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registrarse",color = Color(0xCCC7719B), fontSize = 24.sp, fontWeight = FontWeight.Bold)


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nomUsuario,
                onValueChange = { nomUsuario= it},
                label = { Text("Nombre de Usuario")}
            )

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") }
            )

            OutlinedTextField(
                value = confirmCorreo,
                onValueChange = { confirmCorreo = it},
                label = { Text("Confirmar Correo")}
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                value = confirmContrasena,
                onValueChange = { confirmContrasena = it},
                label = { Text("Confirmar Contraseña")}
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilledTonalButton(
                onClick = {
                    println("Se ha registrado exitosamente")

                },
                enabled = esValido,

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xCCC7719B),
                    contentColor = Color.White
                )
            ){
                Text("Registrar")
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}