package ru.myapp.myapplication.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.myapp.myapplication.database.Dbo
import kotlin.random.Random

@Entity(tableName = "tulip")
data class TulipDbo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: Int = Random.nextInt(100),

    @ColumnInfo(name = "color")
    override val color: String,

    override val name: String = "Тюльпан",

    @ColumnInfo(name = "country")
    override val country: String = "Holland",
): Dbo