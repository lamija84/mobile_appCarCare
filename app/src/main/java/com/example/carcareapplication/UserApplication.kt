package com.example.carcareapplication

import android.app.Application
import com.example.carcareapplication.model.AppContainer
import com.example.carcareapplication.model.AppDataContainer

class UserApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}