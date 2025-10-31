package com.Mtimes.notcat.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Mtimes.notcat.presentation.LoginScreen
import com.Mtimes.notcat.presentation.RegisterScreen

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
    }
}