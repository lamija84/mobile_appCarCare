package com.example.carcareapplication.ui.screen

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcareapplication.R
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination
import com.example.carcareapplication.viewModel.AddGetCarsViewModel
import com.example.carcareapplication.viewModel.AppViewModelProvider
import kotlinx.coroutines.launch


object AddCarDestination: NavigationDestination {
    override val route = "addCar"
    override val title = "Add Car"
}

@Composable
fun AddCarScreen(
    viewModel: AddGetCarsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToCars: () -> Unit,
) {
    // -> treba novi icon umjesto ovog koji stoji na prazno polje <- //

    // stavit cemo da su sva polja obavezna
    var model by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var engine by remember { mutableStateOf("") }
    var fuel by remember { mutableStateOf("") }
    var mileage by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) } // po defaultu nijedno polje ne prikazuje error

    val coroutineScope = rememberCoroutineScope()
    var uiState = viewModel.carsUiState
    var detailsState = uiState.carDetails

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
                .padding(top = 20.dp)
                .padding(bottom = 20.dp),
            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = "Add new car", fontSize = 32.sp, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = model ,
                onValueChange =
                {
                    model = it
                    viewModel.updateUiState(detailsState.copy(model = it))
                },
                enabled = true,
                label = { Text(text = "Make and Model")},
                placeholder = { Text(text = "Audi S7")},
                isError = showError && model.isEmpty(), // ako je error true i ako je polje prazno, eror je true ako je nakon unosa polje ostalo prazno, namjerno sam ostavio i uslov ovdje da je model.isEmpty(), moze i bez toga ovdje
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
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

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = year ,
                onValueChange =
                {
                    year = it
                    viewModel.updateUiState(detailsState.copy(productionYear = it.toInt()))
                },
                enabled = true,
                label = { Text(text = "Production year")},
                placeholder = { Text(text = "2013")},
                isError = showError && year.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
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
                value = mileage ,
                onValueChange =
                {
                    mileage = it
                    viewModel.updateUiState(detailsState.copy(mileage = it.toInt()))
                },
                enabled = true,
                label = { Text(text = "Mileage")},
                placeholder = { Text(text = "100000")},
                isError = showError && mileage.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
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
                value = engine ,
                {
                    engine = it
                    viewModel.updateUiState(detailsState.copy(engine = it))
                },
                label = { Text(text = "Engine")},
                placeholder = { Text(text = "4.2 TFSI")},
                isError = showError && engine.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
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

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = fuel ,
                {
                    fuel = it
                    viewModel.updateUiState(detailsState.copy(fuelType = it))
                },
                label = { Text(text = "Fuel type")},
                placeholder = { Text(text = "Petrol")},
                isError = showError && fuel.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
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

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = {
                    showError = model.isEmpty() || year.isEmpty() || engine.isEmpty() || fuel.isEmpty();
                    if(!showError) {
                        coroutineScope.launch {
                            if(viewModel.addCar()) {
                                Log.d("adding new car", viewModel.carsUiState.toString())
                                navigateToCars()
                            }
                            //viewModel.insertCar()
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
                Text(text = "Confirm", fontSize = 22.sp)
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { navigateToCars() },
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.blue_1000)
                ),
                // border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                border = BorderStroke(2.dp, colorResource(id = R.color.blue_1000)),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Back", fontSize = 22.sp)
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AddCarScreenPreview() {
    //AddCarScreen()
}