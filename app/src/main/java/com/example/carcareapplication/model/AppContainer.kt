package com.example.carcareapplication.model

import android.content.Context
import com.example.carcareapplication.model.repositories.CarRepository
import com.example.carcareapplication.model.repositories.ForumPostRepository
import com.example.carcareapplication.model.repositories.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val carRepository: CarRepository
    val forumPostRepository: ForumPostRepository
}
class AppDataContainer(private val context: Context): AppContainer {

    override val userRepository: UserRepository by lazy {
        UserRepository(AppDatabase.getDatabase(context).userDao())
    }

    override val carRepository: CarRepository by lazy {
        CarRepository(AppDatabase.getDatabase(context).carsDao())
    }

    override val forumPostRepository: ForumPostRepository by lazy {
        ForumPostRepository(AppDatabase.getDatabase(context).forumDao())
    }
}