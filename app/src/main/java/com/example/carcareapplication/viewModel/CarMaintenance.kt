package com.example.carcareapplication.viewModel

import com.example.carcareapplication.model.models.CarInformation
import com.example.carcareapplication.model.models.Cars

//data class CarMaintenance()

data class CarMaintenanceDetails(
    val id: Int = 0,
    val carId: Int = 0,
    val service: Int = 0,
    val timingChain: Int = 0,
    val oilChange: Int = 0,
    val tireRotation: Int = 0,
    val DPFCleanup: Int = 0,
    val brakes: Int = 0,
    val airFilter: Int = 0,
    val fuelFilter: Int = 0,
)

data class CarMaintenanceUiState(
    val carMaintenanceDetails: CarMaintenanceDetails = CarMaintenanceDetails(),
    val isEntryValid: Boolean = false
)

fun CarMaintenanceDetails.toCarMaintenance(): CarInformation = CarInformation(
    id = id,
    carId = carId,
    service = service,
    timingChain = timingChain,
    oilChange = oilChange,
    tireRotation = tireRotation,
    DPFCleanup = DPFCleanup,
    brakes = brakes,
    airFilter = airFilter,
    fuelFilter = fuelFilter,
)

fun CarInformation.toCarMaintenanceDetails(): CarMaintenanceDetails = CarMaintenanceDetails(
    id = id,
    carId = carId ?: 1,
    service = service ?: 1,
    timingChain = timingChain ?: 1,
    oilChange = oilChange ?: 1,
    tireRotation = tireRotation ?: 1,
    DPFCleanup = DPFCleanup ?: 1,
    brakes = brakes ?: 1,
    airFilter = airFilter ?: 1,
    fuelFilter = fuelFilter ?: 1,
)

fun CarInformation.toCarMaintenanceUiState(isEntryValid: Boolean = false): CarMaintenanceUiState = CarMaintenanceUiState(
    carMaintenanceDetails = this.toCarMaintenanceDetails(),
    isEntryValid = isEntryValid
)