package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.OrchidDbo

@Dao
interface OrchidDao: FlowerDao {

    @Query("SELECT * from orchid")
    suspend fun getOrchid(): List<OrchidDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: OrchidDbo)

    @Delete
    suspend fun delete(flower: OrchidDbo)
}