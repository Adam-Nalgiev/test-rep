package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "inventory",
    foreignKeys = [
        ForeignKey(
            entity = FlowerDbo::class,
            parentColumns = ["flower_id"],
            childColumns = ["flower_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class InventoryDbo(
    @PrimaryKey
    @ColumnInfo(name = "flower_id")
    val flowerId: Int,

    @ColumnInfo(name = "quantity")
    val quantity: Int
)
