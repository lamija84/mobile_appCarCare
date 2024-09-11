
package com.example.carcareapplication.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carcareapplication.R
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination

// import com.example.carcareapplication.ui.theme.poppinsFontFamily


object UpdateCarDestination: NavigationDestination {
    override val route = "updateCar"
    override val title = "Update Car"
}

@Composable
fun UpdateCarScreen(
    navigateToCars: () -> Unit
) {
    var makeModel by remember{ mutableStateOf("") }
    var year by remember{ mutableStateOf("") }
    var engine by remember{ mutableStateOf("") }
    var fuel by remember{ mutableStateOf("") }
    var battery by remember{ mutableStateOf("") }
    var smallService by remember{ mutableStateOf("") }
    var oil by remember{ mutableStateOf("") }
    var tires by remember{ mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.blue_50))
            .verticalScroll(rememberScrollState()) // da se moze skrolati, bez ovoga pola stvari ode ispod i ne vide se
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 60.dp)
                .padding(bottom = 20.dp),

            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = "Update car information",
                fontSize = 32.sp,
                //fontFamily = poppinsFontFamily, ruzno izgleda
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = makeModel ,
                onValueChange = { makeModel = it },
                label = { Text(text = "Make and Model")},
                placeholder = { Text(text = "Audi S7")},
                isError = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next)
            )

            Spacer(Modifier.height(15.dp))


            OutlinedTextField(
                value = year ,
                onValueChange = { year  = it },
                label = { Text(text = "Production year")},
                placeholder = { Text(text = "2013")},
                isError = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                    )
            )

            Spacer(Modifier.height(15.dp))

            OutlinedTextField(
                value = engine ,
                onValueChange = { engine  = it },
                label = { Text(text = "Engine")},
                placeholder = { Text(text = "4.2 TFSI")},
                isError = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next)
            )

            Spacer(Modifier.height(15.dp))

            OutlinedTextField(
                value = fuel ,
                onValueChange = { fuel = it },
                label = { Text(text = "Fuel type")},
                placeholder = { Text(text = "Petrol")},
                isError = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next)
            )

            Spacer(Modifier.height(40.dp))

            //Text(text = "Maintenance Information")

            //Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Timing chain")},
                    isError = false,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = smallService,
                    onValueChange = { smallService = it },
                    label = { Text(text = "Service")},
                    isError = false,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = oil,
                    onValueChange = { oil = it },
                    label = { Text(text = "Oil Change")},

                    isError = false,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = tires,
                    onValueChange = { tires = it },
                    label = { Text(text = "Tire Rotation")},
                    placeholder = { Text(text = "Doe")},
                    isError = false,
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = oil,
                    onValueChange = { oil = it },
                    label = { Text(text = "DPF Cleanup")},

                    isError = false,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = tires,
                    onValueChange = { tires = it },
                    label = { Text(text = "Brakes")},
                    placeholder = { Text(text = "Doe")},
                    isError = false,
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = oil,
                    onValueChange = { oil = it },
                    label = { Text(text = "Air Filter")},

                    isError = false,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = tires,
                    onValueChange = { tires = it },
                    label = { Text(text = "Fuel Filter")},
                    placeholder = { Text(text = "Doe")},
                    isError = false,
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { navigateToCars() },
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_1000),
                    contentColor = colorResource(R.color.blue_50)
                ),
                // border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                //border = BorderStroke(2.dp, colorResource(id = R.color.black)),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(text = "Update", fontSize = 22.sp)
            }
            // Spacer(Modifier.height(30.dp))
        }
    }
}



@Composable
@Preview(showBackground = true)
fun UpdateCarScreenPreview() {
    //UpdateCarScreen()
}
