package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bouquet")
data class BouquetDbo(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bouquet_id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean = true,

    @ColumnInfo(name = "decor")
    val decor: String
)