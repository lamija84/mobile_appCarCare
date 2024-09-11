package com.example.carcareapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcareapplication.model.repositories.UserRepository
import com.example.carcareapplication.ui.screen.ProfileDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository, savedStateHandle: SavedStateHandle): ViewModel() {
    /**
     * Holds current item ui state
     */

    // OVAJ USER ID KORISTIMO ISPOD U INIT GET ONE STREAM DA UZMEMO PODATKE OD USERA KOJI SE LOG-INUJE
    private val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    var usersUiState by mutableStateOf(UsersUiState())
        private set

    init {
        viewModelScope.launch {
            usersUiState = userRepository.getOneStream(userId)
                .filterNotNull()
                .first()
                .toUsersUiState(true)
        }
    }
    suspend fun updateUser() {
        userRepository.update(usersUiState.usersDetails.toUsers())
    }

    //FOR LOGOUT
    fun clearUi(){
        usersUiState = UsersUiState()
    }

    fun updateUiState(usersDetails: UsersDetails) {
        usersUiState =
            UsersUiState(usersDetails = usersDetails, isEntryValid = false)
    }
}