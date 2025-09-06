package ru.myapp.myapplication.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.myapp.myapplication.database.dao.BouquetDao
import ru.myapp.myapplication.database.dao.InventoryDao
import ru.myapp.myapplication.database.dbo.BouquetDbo
import java.lang.Exception

class Repository {

    suspend fun getAvailableBouquets(dao: BouquetDao): List<BouquetDbo> {
        return dao.getAllBouquet()
    }

    suspend fun buyBouquet(bouquetDao: BouquetDao, inventoryDao: InventoryDao, bouquetId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val isEnough = inventoryDao.hasEnoughFlowersForBouquet(bouquetId)

                if (!isEnough){
                    return@withContext false
                }

                val composition = bouquetDao.getBouquetComposition(bouquetId)

                composition.forEach { item ->
                    inventoryDao.decreaseFlowerQuantity(
                        item.flower.id,
                        item.quantity
                    )
                }

                markBouquetUnavailableIdNeeded(inventoryDao, bouquetDao, bouquetId)

                true
            } catch (e: Exception) {
                false
            }
        }
    }

    private suspend fun markBouquetUnavailableIdNeeded(inventoryDao: InventoryDao, bouquetDao: BouquetDao, bouquetId: Int){
        val isEnough = inventoryDao.hasEnoughFlowersForBouquet(bouquetId)
        if (!isEnough){
            bouquetDao.markBouquetAsUnavailable(bouquetId)
        }
    }
}