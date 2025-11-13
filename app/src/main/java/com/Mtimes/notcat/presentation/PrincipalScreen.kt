package com.Mtimes.notcat.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.Mtimes.notcat.data.UserDB
import com.Mtimes.notcat.navigation.AppNavHost
import com.Mtimes.notcat.ui.theme.Pink40
import com.Mtimes.notcat.ui.theme.Pink80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    navController: NavHostController,
    dbHelper: UserDB
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                TopBar()


            }
        }
    ) { innerPadding ->


        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            dbHelper = dbHelper
        )

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopBar() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Pink80,
            titleContentColor = Pink40,
        ),

        navigationIcon = {
            IconButton(onClick = { Log.d("MyTabs", "Menu clicked") }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        title = {
            Text(
                "Expenses",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },

        actions = {


            IconButton(onClick = { Log.d("MyTabs", "Tab clicked")}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}


