package com.example.carcareapplication.viewModel

import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.User

// data class Cars()

data class CarDetails(
    val id: Int = 0,
    val userId: Int = 1,
    val model: String = "",
    val productionYear: Int = 2050,
    val mileage: Int = 0,
    val engine: String = "",
    val fuelType: String = "",

    )

data class CarUiState(
    val carDetails: CarDetails = CarDetails(),
    val isEntryValid: Boolean = false
)

fun CarDetails.toCars(): Cars = Cars(
    id = id,
    userId = userId,
    model = model,
    productionYear = productionYear,
    mileage = mileage,
    engine = engine,
    fuelType = fuelType

)


fun Cars.toCarDetails() = CarDetails(
    id = id,
    userId = userId ?: 1,
    model = model ?: "Mercedes Benz SLR",
    productionYear = productionYear ?: 2003,
    mileage = mileage ?: 0,
    engine = engine ?: "6.2 V12",
    fuelType = fuelType ?: "Petrol"
)


fun Cars.toCarUiState(isEntryValid: Boolean = false): CarUiState = CarUiState(
    carDetails = this.toCarDetails(),
    isEntryValid = isEntryValid
)
