package com.Mtimes.notcat.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.Mtimes.notcat.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ReminderScreen(navController: NavHostController, onSaveReminder: (String, String, String, String, Int, Int, android.content.Context) -> Unit){
    val context = LocalContext.current
    val datePickerState = rememberDatePickerState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var repeat by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    val esValido by derivedStateOf {
        title.isNotEmpty() &&
                description.isNotEmpty() &&
                date.isNotEmpty() &&
                time.isNotEmpty() &&
                repeat.isNotEmpty()
        //errorText.isEmpty()
    }


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
            Text("Crear Recordatorio",color = Color(0xCCC7719B), fontSize = 24.sp, fontWeight = FontWeight.Bold)


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it},
                label = { Text("Titulo") }
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") }
            )

                    //esto esta mal xd deberia ser un calendario

            FilledTonalButton(
                onClick = {showDialog= true},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xCCC7719B),
                    contentColor = Color.White
                )
            ){
                Text("Elegir fecha")
            }

                if (showDialog) {
                    DatePickerDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            TextButton(onClick = {
                                datePickerState.selectedDateMillis?.let {
                                    val format = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
                                    date = format.toString()
                                }
                                showDialog = false
                            }) {
                                Text("Confirmar")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Cancelar")
                            }
                        }
                    ) {
                        // DatePicker interno — se adapta automáticamente al Locale del sistema
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false,
                            colors = DatePickerDefaults.colors(
                                selectedDayContainerColor = Color(0xCCBF7172),
                                selectedDayContentColor = Color(0xCCFDD7D4)
                            )
                        )
                    }
                }


                    //nose si ocupa ser uno diferente segun yo puedes sacar el tiempo del calendario ahorita checo
            OutlinedTextField(
                value = time,
                onValueChange = { time = it},
                label = { Text("Tiempo") }
            )

            OutlinedTextField(
                value = repeat,
                onValueChange = { repeat = it},
                label = { Text("Repetir") }
            )


                    Spacer(modifier = Modifier.height(16.dp))

            FilledTonalButton(
                onClick = {
                    onSaveReminder("a",title,description,date,time.toInt(),repeat.toInt(),context)
                    //println("Se ha registrado exitosamente")

                },
                enabled = esValido,

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xCCC7719B),
                    contentColor = Color.White
                )
            ){
                Text("Guardar")
            }


        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReminderScreenPreview() {
    ReminderScreen(navController = rememberNavController(), onSaveReminder = { _, _, _, _, _, _, _ ->})
}
