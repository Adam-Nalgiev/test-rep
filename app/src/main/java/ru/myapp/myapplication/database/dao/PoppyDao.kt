package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.PoppyDbo

@Dao
interface PoppyDao: FlowerDao {

    @Query("SELECT * from poppy")
    suspend fun getPoppy(): List<PoppyDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: PoppyDbo)

    @Delete
    suspend fun delete(flower: PoppyDbo)
}