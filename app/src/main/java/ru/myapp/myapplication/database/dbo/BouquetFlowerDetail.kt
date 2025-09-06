package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class BouquetFlowerDetail(
    @Embedded
    val flower: FlowerDbo,

    @ColumnInfo(name = "quantity")
    val quantity: Int
)