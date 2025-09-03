package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.LilyDbo

@Dao
interface LilyDao: FlowerDao {

    @Query("SELECT * from lily")
    suspend fun getLily(): List<LilyDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: LilyDbo)

    @Delete
    suspend fun delete(flower: LilyDbo)
}