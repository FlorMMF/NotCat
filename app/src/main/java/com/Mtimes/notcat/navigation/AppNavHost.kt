package com.Mtimes.notcat.navigation


import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Mtimes.notcat.data.UserDB
import com.Mtimes.notcat.presentation.LoginScreen
import com.Mtimes.notcat.presentation.PrincipalScreen
import com.Mtimes.notcat.presentation.RegisterScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    dbHelper: UserDB,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.login.route,
        modifier = modifier
    ) {
        composable(Screen.login.route) {
            LoginScreen(
                navController = navController, onEntrar ={ correo, contra, context ->
                    if (dbHelper.checkEmail(correo) && dbHelper.checkPass(contra)) {
                        navController.navigate(Screen.principal.route) {
                            launchSingleTop = true
                        }
                    } else  {
                        Toast.makeText(context, "Correo ya registrado", Toast.LENGTH_SHORT).show()
                    }
            })
        }

        composable(Screen.register.route) {
            RegisterScreen(navController = navController,  onRegistrar = { nomUsuario, correo, contra, confirmContra, context ->
                if (dbHelper.checkUser(nomUsuario)) {
                    Toast.makeText(context, "Usuario ya registrado", Toast.LENGTH_SHORT).show()
                } else if (dbHelper.checkEmail(correo)) {
                    Toast.makeText(context, "Correo ya registrado", Toast.LENGTH_SHORT).show()
                } else {

                    val id = dbHelper.addUser(nomUsuario, correo, contra)
                    Log.d("RegisterActivity", "Intentando registrar usuario: $nomUsuario, ID devuelto = $id")
                    if (id != -1L) {
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        Log.d("DB", " Usuario insertado correctamente con ID: $id")
                    } else {
                        Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                        Log.e("DB", "Error al insertar usuario (insert devolvió -1)")

                    }
                }
            })

        }

        composable(Screen.principal.route){
            PrincipalScreen(navController, dbHelper)
        }
    }
}