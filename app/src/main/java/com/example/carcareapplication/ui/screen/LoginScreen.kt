package com.example.carcareapplication.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS

import androidx.compose.material3.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcareapplication.R
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination
import com.example.carcareapplication.viewModel.AppViewModelProvider
import com.example.carcareapplication.viewModel.LoginRegistrationViewModel
import kotlinx.coroutines.launch


//import com.example.carcareapplication.ui.theme.poppinsFontFamily
import com.example.carcareapplication.ui.screen.navigation.UserBottomBar

object LoginDestination: NavigationDestination {
    override val route = "login"
    override val title = "Login"
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun LoginScreenWithBottomBar(
    navigateToLanding: () -> Unit,
    navigateToProfilePage: (Int) -> Unit
) {
    Scaffold(
        bottomBar = {UserBottomBar()}
    ) {
        LoginScreen(navigateToLanding = navigateToLanding, navigateToProfilePage = navigateToProfilePage)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToLanding: () -> Unit,
    navigateToProfilePage: (Int) -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var checkPassword by remember {
        mutableStateOf(true)
    }
    var checkEmail by remember {
        mutableStateOf(true)
    }

    var loginTest by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()
    var uiState = viewModel.usersUiState
    var detailsState = uiState.usersDetails

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blue_50))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth()
                .padding(vertical = 100.dp, horizontal = 20.dp),
            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = "Login", fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
               fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF6EC6F7)
            )

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = email ,
                onValueChange =
                {
                    email = it
                    viewModel.updateUiState(detailsState.copy(email = it))
                },
                enabled = true,
                label = { Text(text = "Email")},
                placeholder = { Text(text = "example@example.com")},
                isError = !checkEmail,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6EC6F7),
                    unfocusedBorderColor = Color(0xFF111111),
                    //errorBorderColor = Color.Magenta,
                    focusedLabelColor = Color(0xFF6EC6F7),
                    unfocusedLabelColor = Color(0xFF111111),
                )
            )

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = password ,
                onValueChange =
                {
                    password = it
                    viewModel.updateUiState(detailsState.copy(password = it))
                },
                label = { Text(text = "Password")},
                visualTransformation = if(showPassword) {
                    VisualTransformation.None   // showPassword je u pocetku false tj ne vidimo tekst i ako je nakon
                    // i nakon recomposition-a i dalje false ostaje tekst nevidljiv, nista se ne desava koristenjem VisualTransformation
                }
                else {
                    PasswordVisualTransformation()
                },
                isError = !checkPassword,
                trailingIcon = {  Icon(
                    painter = if(showPassword){
                        painterResource(id = R.drawable.eye)
                    }
                    else{
                        painterResource(id = R.drawable.eye_crossed)
                    },
                    contentDescription = "",
                    tint = Color(0xFF111111),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = { showPassword = !showPassword }) )

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6EC6F7),
                    unfocusedBorderColor = Color(0xFF111111),
                    //errorBorderColor = Color.Magenta,
                    focusedLabelColor = Color(0xFF6EC6F7),
                    unfocusedLabelColor = Color(0xFF111111),
                )
            )

            Spacer(Modifier.height(60.dp))

            Button(
                onClick = {
                    checkEmail = checkEmail(email)
                    checkPassword = checkPassword(password)

                    if(checkEmail && checkPassword) {
                        coroutineScope.launch {
                            Log.d("pre login", viewModel.usersUiState.toString())
                            if(viewModel.login()) {
                                Log.d("login", viewModel.usersUiState.toString())
                                loginTest = "Login successful"
                                navigateToProfilePage(viewModel.usersUiState.usersDetails.id)
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_1000)
                ),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Login", fontSize = 22.sp, color = colorResource(id = R.color.blue_50), fontWeight = FontWeight.Medium, fontFamily = FontFamily.Monospace)
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {navigateToLanding()},
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.blue_1000)
                ),
                // border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                border = BorderStroke(2.dp, color = colorResource(id = R.color.black)),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Back", fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, fontSize = 22.sp, color = colorResource(
                    id = R.color.blue_1000
                ))
            }
        }
    }
}
fun checkEmail(email: String): Boolean{
    return EMAIL_ADDRESS.matcher(email).matches()
}

fun checkPassword(password: String): Boolean {
    return password.length >= 3
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    //LoginScreen()
}