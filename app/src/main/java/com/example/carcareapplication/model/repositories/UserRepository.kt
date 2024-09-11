package com.example.carcareapplication.model.repositories

import com.example.carcareapplication.model.daos.UserDao
import com.example.carcareapplication.model.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao): BaseRepository<User> {
    override suspend fun insert(t: User) = userDao.insert(t)

    override suspend fun update(t: User) = userDao.update(t)

    override suspend fun delete(t: User) = userDao.delete(t)

    override fun getOneStream(id: Int): Flow<User?> = userDao.getUserById(id)

    fun getEmailStudent(email: String): Flow<User> = userDao.getUserByEmail(email)

    fun login(password: String, email: String): Flow<User?> = userDao.login(password, email)

    fun getUserCars(id: Int) = userDao.getUserCars(id)
}