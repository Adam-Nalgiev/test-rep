package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.DaisyDbo

@Dao
interface DaisyDao: FlowerDao {

    @Query("SELECT * from daisy")
    suspend fun getDaisy(): List<DaisyDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: DaisyDbo)

    @Delete
    suspend fun delete(flower: DaisyDbo)
}