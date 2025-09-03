package ru.myapp.myapplication

import ru.myapp.myapplication.database.Dbo
import ru.myapp.myapplication.database.dbo.CarnationDbo
import ru.myapp.myapplication.database.dbo.DaisyDbo
import ru.myapp.myapplication.database.dbo.DandelionDbo
import ru.myapp.myapplication.database.dbo.LavenderDbo
import ru.myapp.myapplication.database.dbo.LilyDbo
import ru.myapp.myapplication.database.dbo.OrchidDbo
import ru.myapp.myapplication.database.dbo.PoppyDbo
import ru.myapp.myapplication.database.dbo.RoseDbo
import ru.myapp.myapplication.database.dbo.SunflowerDbo
import ru.myapp.myapplication.database.dbo.TulipDbo
import ru.myapp.myapplication.model.Bouquet
import kotlin.random.Random

object BouquetGenerator {

    fun generateBouquet(): Bouquet {
        val bouquetSize = Random.nextInt(1, 100)
        val flowersList = mutableListOf<Dbo>()

        repeat(bouquetSize) {
            flowersList.add(returnRandomFlower())
        }

        return Bouquet(bouquetSize, flowersList.toList())
    }

    private fun returnRandomFlower(): Dbo {
        val randomInt = Random.nextInt(0, 100)

        return when (randomInt) {
            in 0..10 -> DandelionDbo(color = "yellow")
            in 11..20 -> CarnationDbo(color = "red")
            in 21..30 -> DaisyDbo(color = "pink")
            in 31..40 -> LavenderDbo(color = "blue")
            in 41..50 -> LilyDbo(color = "white")
            in 51..60 -> OrchidDbo(color = "white")
            in 61..70 -> PoppyDbo(color = "red")
            in 71..80 -> TulipDbo(color = "blue")
            in 81..90 -> SunflowerDbo(color = "yellow")
            else -> RoseDbo(color = "red")
        }
    }

}