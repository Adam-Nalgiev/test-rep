package ru.myapp.myapplication.database.dao

import androidx.room.Query

interface InventoryDao {
    @Query("UPDATE inventory SET quantity = quantity - :amount WHERE flower_id = :flowerId")
    suspend fun decreaseFlowerQuantity(flowerId: Int, amount: Int)


    @Query("""
        SELECT COUNT(*) = 0 as has_enough
        FROM flower_bouquet fb
        INNER JOIN inventory i ON fb.flower_id = i.flower_id
        WHERE fb.bouquet_id = :bouquetId AND i.quantity < fb.quantity
    """)
    suspend fun hasEnoughFlowersForBouquet(bouquetId: Int): Boolean
}