package com.Mtimes.notcat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.Mtimes.notcat.data.UserDB
import com.Mtimes.notcat.navigation.AppNavHost
import com.Mtimes.notcat.presentation.PrincipalScreen
import com.Mtimes.notcat.ui.theme.NotCatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotCatTheme {
                val dbHelper = UserDB(applicationContext, null)
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize())
                {
                   /* AppNavHost(//vista al LoginScreen
                        navController = navController, dbHelper = dbHelper
                    )*/
                    PrincipalScreen(//para poder ver la Principal Screen porque no funciona Preview
                        navController = navController, dbHelper = dbHelper
                    )
                }
            }
        }
    }
}
