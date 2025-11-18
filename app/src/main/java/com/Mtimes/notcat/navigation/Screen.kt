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
    object principal : Screen("principal_screen/{userId}", "Principal") {
        fun createRoute(userId: Long) = "principal_screen/$userId"
    }


    @Serializable
    object reminder : Screen (route = "reminder_screen", label = "Reminder")

    @Serializable
    object lists : Screen ("lists_screen/{userId}","Lists"){
        fun createRoute(userId: Long) = "lists_screen/$userId"
    }

    @Serializable
    object addList : Screen (route = "addList_screen", label = "AddList")

    @Serializable
    object listItem : Screen (route = "listItem_screen", label = "ListItem")

    @Serializable
    object editList : Screen (route = "editList_screen", label = "EditList")

    @Serializable
    object editItem : Screen (route = "editItem_screen", label = "EditItem")


}