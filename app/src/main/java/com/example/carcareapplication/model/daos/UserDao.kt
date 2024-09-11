package com.example.carcareapplication.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): Flow<User>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): Flow<User>

    @Query("SELECT * FROM user WHERE password = :password AND email = :email")
    fun login(password: String, email: String): Flow<User>

    @Query("SELECT * FROM cars JOIN user ON user.id = userId WHERE user.id = :id")
    fun getUserCars(id: Int): Flow<List<Cars>>
}