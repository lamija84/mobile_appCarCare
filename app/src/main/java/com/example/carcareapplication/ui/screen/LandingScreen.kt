package com.example.carcareapplication.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carcareapplication.R
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination

// import com.example.carcareapplication.ui.theme.poppinsFontFamily


object LandingDestination: NavigationDestination {
    override val route = "landing"
    override val title = "Landing"
}


@Composable
fun LandingScreen(
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit
) {
    //al mainFontFamily = FontFamily(mainFont)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFDDF2FD),
                        Color(0xFF6EC6F7),
                        Color(0xFF0B80C1),
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 120.dp)
                .padding(bottom = 20.dp),
            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Temp logo",
                modifier = Modifier.size(110.dp)
            )

            Text(text = "CarCare",
                fontSize = 62.sp,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF0B80C1)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Your all-in-one platform for effortless car maintenance",
                fontSize = 20.sp,

                // fontFamily = mainFontFamily,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color(0xFF444444)
            )

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { navigateToLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_1000),
                    contentColor = colorResource(id = R.color.blue_100)
                ),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Login",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Monospace, fontWeight =FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(30.dp))

            Text(text = "Dont have an account?",
                fontFamily = FontFamily.Monospace, fontWeight =FontWeight.W600, fontSize = 18.sp )

            Spacer(Modifier.height(5.dp))

            Button(
                onClick = { navigateToRegister() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x00ffffff), // prve dvije vrijednosti su transarency
                    ),
                border = BorderStroke(2.dp, colorResource(id = R.color.blue_1000)),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Register",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight =FontWeight.Bold,
                    color = colorResource(id = R.color.blue_1000))
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun LandingScreenPreview() {
    //LandingScreen()
}