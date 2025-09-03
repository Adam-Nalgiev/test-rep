package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.DandelionDbo

@Dao
interface DandelionDao: FlowerDao {
    @Query("SELECT * from dandelion")
    suspend fun getDandelion(): List<DandelionDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: DandelionDbo)

    @Delete
    suspend fun delete(flower: DandelionDbo)
}