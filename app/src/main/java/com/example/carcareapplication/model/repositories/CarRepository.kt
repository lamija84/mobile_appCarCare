package com.example.carcareapplication.model.repositories

import com.example.carcareapplication.model.daos.CarDao
import com.example.carcareapplication.model.daos.ForumDao
import com.example.carcareapplication.model.models.CarInformation
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.ForumPosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CarRepository(private val carDao: CarDao): BaseRepository<Cars> {
    override suspend fun insert(t: Cars) = carDao.insert(t)

    fun insertCar(t: Cars) = carDao.insertCar(t)

    suspend fun insertCarInformation(t: CarInformation) = carDao.insertCarInformation(t)

    override suspend fun update(t: Cars) = carDao.update(t)

    override suspend fun delete(t: Cars) = carDao.delete(t)

    override fun getOneStream(id: Int): Flow<Cars?> = carDao.getCarById(id)

    fun getCarsByUserId(id: Int): Flow<List<Cars>> = carDao.getCarByUserId(id)

    suspend fun getAllCars(): List<Cars> = withContext(Dispatchers.IO) {
        carDao.getAllCars()
    }

    fun getCarInformation(id: Int): Flow<CarInformation> = carDao.getCarInformation(id)
}