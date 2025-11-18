package com.Mtimes.notcat.navigation


import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.Mtimes.notcat.data.UserDB
import com.Mtimes.notcat.presentation.ListScreen
import com.Mtimes.notcat.presentation.LoginScreen
import com.Mtimes.notcat.presentation.PrincipalScreen
import com.Mtimes.notcat.presentation.RegisterScreen
import com.Mtimes.notcat.presentation.ReminderScreen
import com.Mtimes.notcat.presentation.addListScreen

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
                    val userId = dbHelper.checkUser(correo, contra)

                    if (userId != -1L) {
                        navController.navigate("${Screen.principal.route}/$userId")

                    } else {
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show()
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

        composable(
            route = Screen.principal.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.LongType }
            )
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getLong("userId") ?: -1L

            PrincipalScreen(
                navController = navController,
                dbHelper = dbHelper,
                userId = userId
            )
        }


        composable(Screen.reminder.route){
            ReminderScreen()
        }

        composable(route = Screen.lists.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.LongType }
            )
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getLong("userId") ?: -1L

            ListScreen(
                navController = navController,
                dbHelper = dbHelper,
                userId = userId
            )
        }

        composable(Screen.addList.route) {
            addListScreen()
        }

    }
}