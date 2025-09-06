package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.myapp.myapplication.database.dbo.FlowerDbo

@Dao
interface FlowerDao {
    @Insert
    suspend fun insert(flower: FlowerDbo)

    @Query("SELECT * FROM flower")
    suspend fun getAllFlowers(): List<FlowerDbo>
}