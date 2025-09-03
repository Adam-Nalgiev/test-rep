package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.SunflowerDbo

@Dao
interface SunflowerDao: FlowerDao {

    @Query("SELECT * from sunflower")
    suspend fun getSunflower(): List<SunflowerDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: SunflowerDbo)

    @Delete
    suspend fun delete(flower: SunflowerDbo)
}