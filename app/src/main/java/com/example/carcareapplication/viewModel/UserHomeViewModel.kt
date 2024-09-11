package com.example.carcareapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.repositories.CarRepository
import com.example.carcareapplication.model.repositories.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


// DA LI OVDJE TREBA DA NAPRAVIMO DA DOLE carRepository RETURNA SAMO OD ODREDJENOG USERA AUTA?
class UserHomeViewModel(private val carRepository: CarRepository): ViewModel() {

    //val homeUiState: StateFlow<HomeUiState> =
    //    carRepository.getAllCars()
    //        .map {
    //            HomeUiState(it)
    //            // StudentsDetails(id = it.id, name = it.name, surname = it.surname, dob = it.dob.toString(), enrolmentYear = it.enrolmentYear?.value.toString(), studentID = it.studentID.toString(), email = it.email, password = it.password)
    //        }.stateIn(
    //            scope = viewModelScope,
    //            started = SharingStarted.WhileSubscribed(5000L),
    //            initialValue = HomeUiState()
    //        )
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val carList: List<Cars> = listOf())