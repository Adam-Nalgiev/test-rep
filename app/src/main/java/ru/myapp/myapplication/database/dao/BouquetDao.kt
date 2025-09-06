package ru.myapp.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.myapp.myapplication.database.dbo.BouquetDbo
import ru.myapp.myapplication.database.dbo.BouquetFlowerDetail
import ru.myapp.myapplication.database.dbo.FlowerBouquetDbo

@Dao
interface BouquetDao {
    @Insert
    suspend fun insert(bouquetDbo: BouquetDbo)

    @Insert
    suspend fun insertFlowerBouquet(flowerBouquet: List<FlowerBouquetDbo>)

    @Query("SELECT * FROM bouquet")
    suspend fun getAllBouquet(): List<BouquetDbo>

    @Query("""
        SELECT f.*, fb.quantity
        FROM flower_bouquet fb
        INNER JOIN flower f ON fb.flower_id = f.flower_id
        WHERE fb.bouquet_id = :bouquetId
    """)
    suspend fun getBouquetComposition(bouquetId: Int): List<BouquetFlowerDetail>

    @Query("UPDATE bouquet SET is_available = 0 WHERE bouquet_id = :bouquetId")
    suspend fun markBouquetAsUnavailable(bouquetId: Int)
}