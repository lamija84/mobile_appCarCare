package com.example.carcareapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carcareapplication.model.daos.CarDao
import com.example.carcareapplication.model.daos.ForumDao
import com.example.carcareapplication.model.daos.UserDao
import com.example.carcareapplication.model.models.CarInformation
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.ForumPostComment
import com.example.carcareapplication.model.models.ForumPosts
import com.example.carcareapplication.model.models.User

@Database(entities = [User::class, Cars::class, CarInformation::class, ForumPosts::class, ForumPostComment::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun carsDao(): CarDao
    abstract fun forumDao(): ForumDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "car-care-database-v3")
                    .build().also { Instance = it }
            }
        }
    }

}