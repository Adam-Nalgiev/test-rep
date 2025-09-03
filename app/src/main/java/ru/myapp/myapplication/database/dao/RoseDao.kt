package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.RoseDbo

@Dao
interface RoseDao: FlowerDao {

    @Query("SELECT * from rose")
    suspend fun getRoses(): List<RoseDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: RoseDbo)

    @Delete
    suspend fun delete(flower: RoseDbo)
}