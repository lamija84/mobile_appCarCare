package com.example.carcareapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.ForumPosts
import com.example.carcareapplication.model.repositories.CarRepository
import kotlinx.coroutines.launch

class AddGetCarsViewModel(private val carRepository: CarRepository): ViewModel() {
    var carsUiState by mutableStateOf(CarUiState())
    private set

    var carList = mutableStateListOf<Cars>()
        private set

    init {
        fetchCars()
    }

    private fun fetchCars() {
        viewModelScope.launch {
            val cars = carRepository.getAllCars()
            carList.addAll(cars)
        }
    }

    suspend fun addCar(): Boolean {
        carRepository.insert(carsUiState.carDetails.toCars())
        carList.add(carsUiState.carDetails.toCars())
        return true
    }

    suspend fun insertCar(): Boolean {
        carRepository.insertCar(carsUiState.carDetails.toCars())
        return true
    }

    /*
    suspend fun validateUser(uiState: CarDetails = carsUiState.carDetails): Boolean {
        return with(uiState) {
            checkUser()
        }
    }

    suspend fun checkUser(): Boolean {
        return carRepository.
    }
    */

    fun updateUiState(carDetails: CarDetails) {
        carsUiState = CarUiState(carDetails = carDetails, isEntryValid = false)
    }

}