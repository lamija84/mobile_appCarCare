package com.example.carcareapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.carcareapplication.model.repositories.UserRepository
import kotlinx.coroutines.flow.first

class LoginRegistrationViewModel(private val userRepository: UserRepository): ViewModel() {
    /**
     * Holds current item ui state
     */
    var usersUiState by mutableStateOf(UsersUiState())
        private set

    suspend fun register(): Boolean{
        if(validateInput()){
            userRepository.insert(usersUiState.usersDetails.toUsers())
            return true
        }else return false
    }

    private suspend fun validateInput(uiState: UsersDetails = usersUiState.usersDetails): Boolean {
        return with(uiState) {
            checkEmail()
        }
    }

    private suspend fun checkEmail(): Boolean{
        return userRepository.getEmailStudent(usersUiState.usersDetails.email).first()
            ?.toUsersUiState()?.usersDetails?.email?.isEmpty() ?: true
    }

    suspend fun login(): Boolean {
        val res = userRepository.login(usersUiState.usersDetails.password, usersUiState.usersDetails.email)
            .first()
            ?.toUsersUiState(true)

        if(res != null){
            usersUiState = res
            return true
        }else{
            return false
        }
    }

    fun updateUiState(usersDetails: UsersDetails) {
        usersUiState =
            UsersUiState(usersDetails = usersDetails, isEntryValid = false)
    }
}