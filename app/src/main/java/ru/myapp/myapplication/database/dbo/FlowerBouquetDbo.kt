package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "flower_bouquet",
    primaryKeys = ["bouquet_id", "flower_id"],
    foreignKeys = [
        ForeignKey(
            entity = BouquetDbo::class,
            parentColumns = ["bouquet_id"],
            childColumns = ["bouquet_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FlowerDbo::class,
            parentColumns = ["flower_id"],
            childColumns = ["flower_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class FlowerBouquetDbo(
    @ColumnInfo(name = "bouquet_id")
    val bouquetId: Int,

    @ColumnInfo(name = "flower_id")
    val flowerId: Int,

    @ColumnInfo(name = "quantity")
    val quantity: Int
)
