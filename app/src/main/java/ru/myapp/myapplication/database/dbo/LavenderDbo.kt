package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.myapp.myapplication.database.Dbo
import kotlin.random.Random

@Entity(tableName = "lavender")
data class LavenderDbo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: Int = Random.nextInt(100),

    @ColumnInfo(name = "color")
    override val color: String,

    override val name: String = "Лаванда",

    @ColumnInfo(name = "country")
    override val country: String = "Holland",
): Dbo