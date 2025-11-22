package com.Mtimes.notcat.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.Mtimes.notcat.presentation.LoginScreen
import com.Mtimes.notcat.presentation.RegisterScreen
import com.Mtimes.notcat.presentation.ReminderScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.login.route,
        modifier = modifier
    ) {
        composable(Screen.login.route) {
            LoginScreen(
                navController = navController,
            )
        }

        composable(Screen.register.route) {
            RegisterScreen(navController = navController,  onRegistrar = { _, _, _, _, _ ->})

        }

        composable(
            route = "reminder/{userId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getInt("userId") ?: -1

            ReminderScreen(
                navController = navController,
                UserID = userId,
                onSaveReminder = { _, _, _, _, _, _, _ ->})
        }
    }
}