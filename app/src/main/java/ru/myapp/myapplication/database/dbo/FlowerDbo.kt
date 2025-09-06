package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flower")
data class FlowerDbo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("flower_id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "color")
    val color: String?,

    @ColumnInfo(name = "country")
    val country: String
)
