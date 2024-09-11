package com.example.carcareapplication.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "cars",
    foreignKeys = [ForeignKey(
      entity = User::class,
       parentColumns = ["id"],
       childColumns = ["userId"],
       onDelete = ForeignKey.CASCADE // Optional: specify what happens on delete
     )]
)
data class Cars(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "userId")
    val userId: Int = 1,

    @ColumnInfo(name = "model")
    val model: String = "",

    @ColumnInfo(name = "productionYear")
    val productionYear: Int = 3000,

    @ColumnInfo(name = "mileage")
    val mileage: Int = 0,

    @ColumnInfo(name = "engine")
    val engine: String = "",

    @ColumnInfo(name = "fuelType")
    val fuelType: String = ""

)