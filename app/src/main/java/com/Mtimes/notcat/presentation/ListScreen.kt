package com.Mtimes.notcat.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.Mtimes.notcat.data.UserDB
import com.Mtimes.notcat.navigation.Screen


@Composable
fun ListScreen(navController: NavHostController, dbHelper: UserDB, userId: Long){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ExtendedFloatingActionButton(
            onClick = {

                navController.navigate(Screen.addList.route) {
                    launchSingleTop = true
                }
            },
            containerColor = com.Mtimes.notcat.ui.theme.Pink80,
            contentColor = MaterialTheme.colorScheme.onPrimary,

            icon = { Icon(Icons.Filled.Edit, "Añadir") },
            text = { Text(text = "Añadir lista") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}

@Composable
fun addListScreen (){}

@Composable
fun listItemScreen (navController: NavHostController, dbHelper: UserDB) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(com.Mtimes.notcat.ui.theme.Pink40, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .padding(22.dp)

    ){
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text =,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = ,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            onClick = {

            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Income",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

}

@Composable
fun editListScreen () {}

@Composable
fun editItemScreen () {}

fun ReminderScreen(){}