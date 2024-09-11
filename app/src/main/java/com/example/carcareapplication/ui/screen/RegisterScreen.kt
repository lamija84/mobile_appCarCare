package com.example.carcareapplication.ui.screen

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

object RegisterDestination: NavigationDestination {
    override val route = "register"
    override val title = "Register"
}

@Composable
fun RegisterScreen(
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToLogin: () -> Unit,
    navigateToLanding: () -> Unit
) {

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var checkEmail by remember { // u prijevodu, isEmailOk, po defaultu je Ok(true)
        mutableStateOf(true)
    }
    var password by remember {
        mutableStateOf("")
    }
    var repeatPassword by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var checkPassword by remember {
        mutableStateOf(true) //u prijevodu isPasswordOk, po defaultu true
    }
    var nameError by remember {
        mutableStateOf(false) // is name wrong, po defaultu false
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
                .padding(horizontal = 20.dp)
                .padding(top = 100.dp)
                .padding(bottom = 20.dp),
            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Register", fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF6EC6F7)
            )

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange =
                    {
                        firstName = it;
                        viewModel.updateUiState(detailsState.copy(firstName = it))
                    },
                    label = { Text(text = "First name")},
                    enabled = true,
                    placeholder = { Text(text = "John")},
                    isError = nameError && firstName.isEmpty(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6EC6F7),
                        unfocusedBorderColor = Color(0xFF111111),
                        //errorBorderColor = Color.Magenta,
                        focusedLabelColor = Color(0xFF6EC6F7),
                        unfocusedLabelColor = Color(0xFF111111),
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange =
                    {
                        lastName = it;
                        viewModel.updateUiState(detailsState.copy(lastName = it))
                    },
                    enabled = true,
                    label = { Text(text = "Last name")},
                    placeholder = { Text(text = "Doe")},
                    isError = nameError && lastName.isEmpty(),
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6EC6F7),
                        unfocusedBorderColor = Color(0xFF111111),
                        //errorBorderColor = Color.Magenta,
                        focusedLabelColor = Color(0xFF6EC6F7),
                        unfocusedLabelColor = Color(0xFF111111),
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email ,
                onValueChange =
                {
                    email = it;
                    viewModel.updateUiState(detailsState.copy(email = it))
                },
                enabled = true,
                label = { Text(text = "Email")},
                placeholder = { Text(text = "example@example.com")},
                isError = !checkEmail,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6EC6F7),
                    unfocusedBorderColor = Color(0xFF111111),
                    //errorBorderColor = Color.Magenta,
                    focusedLabelColor = Color(0xFF6EC6F7),
                    unfocusedLabelColor = Color(0xFF111111),
                )
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = password ,
                onValueChange =
                {
                    password = it;
                    viewModel.updateUiState(detailsState.copy(password = it))
                },
                enabled = true,
                label = { Text(text = "Password")},
                visualTransformation = if(showPassword) {
                    VisualTransformation.None
                }else{
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
                    imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6EC6F7),
                    unfocusedBorderColor = Color(0xFF111111),
                    //errorBorderColor = Color.Magenta,
                    focusedLabelColor = Color(0xFF6EC6F7),
                    unfocusedLabelColor = Color(0xFF111111),
                )
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                enabled = true,
                label = { Text(text = "Repeat password")},
                visualTransformation = PasswordVisualTransformation(),
                isError = !checkPassword,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done),
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
                    checkEmail = checkEmailForRegister(email);
                    checkPassword = password == repeatPassword && !(password.isEmpty() || repeatPassword.isEmpty());
                    nameError = firstName.isEmpty() || lastName.isEmpty()

                    if(checkEmail && checkPassword && !nameError) {
                        coroutineScope.launch {
                            if(viewModel.register()) {
                                Log.d("register", viewModel.usersUiState.toString())
                                navigateToLogin()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_1000),
                    contentColor = colorResource(id = R.color.blue_50)
                ),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Create account", fontSize = 22.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { navigateToLanding() },
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(R.color.blue_1000)
                ),
                // border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                border = BorderStroke(2.dp, color = colorResource(id = R.color.blue_1000)),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Back", fontFamily = FontFamily.Monospace, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = colorResource(
                    id = R.color.blue_1000
                ))
            }
        }
    }
}
fun checkEmailForRegister(email: String): Boolean{
    return EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
    //RegisterScreen()
}