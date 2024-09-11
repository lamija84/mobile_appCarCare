package com.example.carcareapplication.viewModel

import com.example.carcareapplication.model.models.User

// data class Users()

data class UsersDetails(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = ""
)

data class UsersUiState(
    val usersDetails: UsersDetails = UsersDetails(),
    val isEntryValid: Boolean = false
)

fun UsersDetails.toUsers(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)


fun User.toUserDetails() = UsersDetails(
    id = id,
    firstName = firstName ?: "john",
    lastName = lastName ?: "doe",
    email = email ?: "example@gmail.com",
    password = password ?: "john123"
)


fun User.toUsersUiState(isEntryValid: Boolean = false): UsersUiState = UsersUiState(
    usersDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)
