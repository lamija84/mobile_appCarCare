package com.example.carcareapplication.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "carInformation",
    foreignKeys = [ForeignKey(
        entity = Cars::class,
        parentColumns = ["id"],
        childColumns = ["carId"],
        onDelete = ForeignKey.CASCADE // Optional: specify what happens on delete
    )]
)
data class CarInformation(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "carId")
    val carId: Int? = null,

    @ColumnInfo(name = "service")
    val service: Int? = null,

    @ColumnInfo(name = "timingChain")
    val timingChain: Int? = null,

    @ColumnInfo(name = "oilChange")
    val oilChange: Int? = null,

    @ColumnInfo(name = "tireRotation")
    val tireRotation: Int? = null,

    @ColumnInfo(name = "DPFCleanup")
    val DPFCleanup: Int? = null,

    @ColumnInfo(name = "brakes")
    val brakes: Int? = null,

    @ColumnInfo(name = "airFilter")
    val airFilter: Int? = null,

    @ColumnInfo(name = "fuelFilter")
    val fuelFilter: Int? = null,

)