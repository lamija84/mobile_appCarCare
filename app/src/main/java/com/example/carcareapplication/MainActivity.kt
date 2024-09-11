package com.example.carcareapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.carcareapplication.model.AppDatabase
import com.example.carcareapplication.ui.screen.AddCarScreen
import com.example.carcareapplication.ui.screen.AddForumPostScreen
import com.example.carcareapplication.ui.screen.ForumScreen
import com.example.carcareapplication.ui.screen.ForumScreenPreview
import com.example.carcareapplication.ui.screen.LandingScreen
import com.example.carcareapplication.ui.screen.LoginScreen
import com.example.carcareapplication.ui.screen.MyCarsScreenPreview
import com.example.carcareapplication.ui.screen.ProfileScreen
import com.example.carcareapplication.ui.screen.RegisterScreen
import com.example.carcareapplication.ui.screen.UpdateCarScreenPreview
import com.example.carcareapplication.ui.screen.navigation.UserNavHost
import com.example.carcareapplication.ui.theme.CarCareApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            CarCareApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //LandingScreen()
                    //LoginScreen()
                    //RegisterScreen(this)
                    //AddCarScreen()
                    //ForumScreen()
                    //UpdateCarScreenPreview()
                    //ForumScreenPreview()
                    //MyCarsScreenPreview()
                    //ProfileScreen()
                    //AddForumPostScreen()

                    UserNavHost(navController = rememberNavController(), context = this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarCareApplicationTheme {
        Greeting("Android")
    }
}