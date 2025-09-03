package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.myapp.myapplication.database.FlowerDao
import ru.myapp.myapplication.database.dbo.TulipDbo

@Dao
interface TulipDao: FlowerDao {

    @Query("SELECT * from tulip")
    suspend fun getTulip(): List<TulipDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flower: TulipDbo)

    @Delete
    suspend fun delete(flower: TulipDbo)
}