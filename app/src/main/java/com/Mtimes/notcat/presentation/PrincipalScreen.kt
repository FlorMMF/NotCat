package com.Mtimes.notcat.presentation
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.Mtimes.notcat.R
import com.Mtimes.notcat.data.UserDB
import com.Mtimes.notcat.navigation.Screen

// Pantalla raíz que incluye drawer + scaffold + NavHost
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PrincipalScreen(navController: NavHostController, dbHelper: UserDB) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        ModalNavigationDrawer (
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(navController = navController)
            }
        ) {

            Scaffold(
                topBar = {
                    TopBar(
                        onMenuClick = {
                            //
                            scope.launch { drawerState.open() }
                        }

                    )
                }
            ) { innerPadding ->

                numList(navController, innerPadding)
                // Aquí pones tu NavHost; recuerda aplicar innerPadding

            }
        }
    }
}
///*************************************************************************************************
@Composable
fun DrawerContent(navController: NavHostController) {
    ModalDrawerSheet {
        Text("Menú principal", modifier = Modifier.padding(16.dp))
        Divider()
        NavigationDrawerItem(
            label = { Text("Recordatorios") },
            selected = false,
            onClick = {
                navController.navigate(Screen.reminder.route) { launchSingleTop = true }
            }
        )
        Divider()
        NavigationDrawerItem(
            label = { Text("Listas") },
            selected = false,
            onClick = {
                navController.navigate(Screen.lists.route) { launchSingleTop = true }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onMenuClick: () -> Unit

) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        title = { Text("NotCat") },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Cuenta")
            }
        }
    )
}

@Composable
fun numList(navController: NavHostController, paddingValues: PaddingValues){
    Card(
        modifier = Modifier.size(width = 100.dp, height = 100.dp)
    ){
        Text(text = "\tNúmero de  Listas",
            fontSize = 10.sp,
            textAlign = TextAlign.Start
        )

    }
}

@Composable
fun ToDo(){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ){
        Text(text = "Por hacer",//deberia salir el nombre de la tarea
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(text = "Tengo que ir a hacer mandado despues de recoger al niño a la escuela",//descripccion de la tarea
            modifier = Modifier.padding (8.dp),
            textAlign = TextAlign.Justify,
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrincipalScreenPreview() {
    PrincipalScreen(
        navController = rememberNavController()


    )
}






