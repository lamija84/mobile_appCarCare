package com.example.carcareapplication.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.carcareapplication.model.models.CarInformation
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.ForumPosts
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert
    suspend fun insert(car: Cars)
    @Update
    suspend fun update(car: Cars)
    @Delete
    suspend fun delete(car: Cars)
    @Insert
    suspend fun insertCarInformation(carInformation: CarInformation)

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Cars>

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(id: Int): Flow<Cars>

    @Query("SELECT * FROM cars WHERE userId = :id")
    fun getCarByUserId(id: Int): Flow<List<Cars>>

    @Query("SELECT * FROM carInformation JOIN cars ON cars.id = carId WHERE carId = :id")
    fun getCarInformation(id: Int): Flow<CarInformation>

    fun insertCar(car: Cars) {
        insertCarQuery(car.userId, car.model, car.productionYear, car.engine, car.fuelType)
    }

    @Query("INSERT INTO cars(userId, model, productionYear, engine, fuelType) VALUES(:userId, :model, :productionYear, :engine, :fuelType)")
    fun insertCarQuery(userId: Int, model: String, productionYear: Int, engine: String, fuelType: String)

    @Query("INSERT INTO carInformation(carId, service, timingChain, oilChange, tireRotation, DPFCleanup, brakes, airFilter, fuelFilter) VALUES(1, 0, 0, 0, 0, 0, 0, 0, 0)")
    fun insertCarInformation()
}