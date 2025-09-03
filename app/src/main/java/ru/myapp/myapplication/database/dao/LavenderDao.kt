package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.LavenderDbo

@Dao
interface LavenderDao: FlowerDao {
    @Query("SELECT * from lavender")
    suspend fun getLavender(): List<LavenderDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: LavenderDbo)

    @Delete
    suspend fun delete(flower: LavenderDbo)
}