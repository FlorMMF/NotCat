package com.Mtimes.notcat.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen (
    val route: String,
    val label: String
){
    @Serializable
    object login : Screen(route = "login_screen", label = "Login")

    @Serializable
    object register : Screen(route = "register_screen", label = "Register")

    @Serializable
    object principal : Screen (route = "principal_screen", label = "Prinicipal")

    @Serializable
    object reminder : Screen (route = "reminder_screen", label = "Reminder")

    @Serializable
    object lists : Screen (route = "lists_screen", label = "Lists")
}