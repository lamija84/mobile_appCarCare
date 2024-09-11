package com.example.carcareapplication.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.carcareapplication.UserApplication

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            LoginRegistrationViewModel(
                userApplication().container.userRepository
            )
        }
        initializer {
            UserViewModel(
                userApplication().container.userRepository,
                this.createSavedStateHandle()
            )
        }
        initializer {
            AddGetCarsViewModel(
                userApplication().container.carRepository
            )
        }
        initializer {
            AddGetForumPostsViewModel(
                userApplication().container.forumPostRepository
            )
        }
        initializer {
            AddGetCarInformationViewModel(
                userApplication().container.carRepository
            )
        }
    }
}

fun CreationExtras.userApplication(): UserApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UserApplication)