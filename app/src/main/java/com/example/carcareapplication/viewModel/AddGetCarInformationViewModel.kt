package com.example.carcareapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.carcareapplication.model.repositories.CarRepository

class AddGetCarInformationViewModel(private val carRepository: CarRepository): ViewModel() {
    var carMaintenanceUiState by mutableStateOf(CarMaintenanceUiState())
    private set

            suspend fun addCarInformation(): Boolean {
        carRepository.insertCarInformation(carMaintenanceUiState.carMaintenanceDetails.toCarMaintenance())
        return true
    }

    fun updateUiState(carMaintenanceDetails: CarMaintenanceDetails) {
        carMaintenanceUiState = CarMaintenanceUiState(carMaintenanceDetails = carMaintenanceDetails, isEntryValid = false)
    }

}