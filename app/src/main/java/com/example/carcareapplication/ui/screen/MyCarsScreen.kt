package com.example.carcareapplication.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carcareapplication.R
import com.example.carcareapplication.model.MyCarList
import com.example.carcareapplication.model.MyCars
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.ui.screen.navigation.NavigationDestination
import com.example.carcareapplication.ui.screen.navigation.UserBottomBar
import com.example.carcareapplication.viewModel.AddGetCarsViewModel
import com.example.carcareapplication.viewModel.AppViewModelProvider

object CarDestination: NavigationDestination {
    override val route = "car"
    override val title = "Car"
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MyCarsScreenWithBottomBar(
    navigateToCars: () -> Unit,
    navigateToAddCar: () -> Unit,
    navigateToUpdateCar: () -> Unit,
    navigateToForum: () -> Unit,
    navigateToProfile: () -> Unit
) {
    Scaffold(
        bottomBar = { UserBottomBar(onCarsClick = navigateToCars, onForumClick = navigateToForum, onProfileClick = navigateToProfile) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddCar() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add car")
            }
        }
    ) {
        MyCarsScreen(navigateToAddCar, navigateToUpdateCar)
    }
}

@Composable
fun MyCarsScreen(
    navigateToAddCar: () -> Unit,
    navigateToUpdateCar: () -> Unit,
    viewModel: AddGetCarsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
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
                .padding(bottom = 70.dp),
            //.background(color = Color.Cyan), // Added padding to the top and bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(text = "My cars", fontSize = 32.sp, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(24.dp))

           // CarCard(makeModel = "Audi RS6", year = 2013, mileage = 210000, engine = "4.2 TFSI", fuelType = "Petrol")

           // CarCard(makeModel = "Audi RS6", year = 2013, mileage = 210000, engine = "4.2 TFSI", fuelType = "Petrol")

            //LazyColumn {
            //    items(MyCarList.myCars) {
            //        car -> CarCardV2(car = car, navigateToUpdateCar)
            //    }
            //}

            LazyColumn {
                items(viewModel.carList) {
                        car -> CarCardV2(car = car, navigateToUpdateCar)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CarCardV2(car: Cars, navigateToUpdateCar: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            ,
        colors = CardDefaults.cardColors(
            //containerColor = colorResource(id = R.color.blue_50),
            containerColor = Color(0xFFE7F6FE)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        border = BorderStroke(0.5.dp, colorResource(id = R.color.black))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Basic information
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Text("Make and Model")
                Text(car.model, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Production year")
                Text(car.productionYear.toString())
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mileage")
                Text(car.mileage.toString() + "km")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Engine")
                Text(car.engine)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fuel type")
                Text(car.fuelType)
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "View Details",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.blue_800)
            )

            // Expanded information (if card is expanded)
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Small Service")
                    Text("186000km")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("DPF Replacement")
                    Text("160000km")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Oil Change")
                    Text("121345km")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Brake Fluid Replacement", color = colorResource(id = R.color.clr_error), fontWeight = FontWeight.SemiBold)
                    Text("13000km", color = colorResource(id = R.color.clr_error), fontWeight = FontWeight.SemiBold)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Timing Belt Replacement")
                    Text("97050km")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Wheel Alignment", color = colorResource(id = R.color.clr_danger), fontWeight = FontWeight.SemiBold)
                    Text("183000km", color = colorResource(id = R.color.clr_danger), fontWeight = FontWeight.SemiBold)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Baterry Replacement")
                    Text("72500km")
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Fuel Filter Replacement")
                    Text("213000km")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navigateToUpdateCar() },
                    modifier = Modifier
                        .fillMaxWidth(),
                    //.padding(horizontal = 32.dp, vertical = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(R.color.black)
                    ),
                    // border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                    border = BorderStroke(2.dp, colorResource(id = R.color.black)),
                    contentPadding = PaddingValues(vertical = 6.dp)
                ) {
                    Text(text = "Update", fontSize = 18.sp)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
@Preview(showBackground = true)
fun MyCarsScreenPreview() {
    //MyCarsScreen()
}