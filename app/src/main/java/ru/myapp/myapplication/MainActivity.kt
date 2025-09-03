package ru.myapp.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myapp.myapplication.database.Dbo
import ru.myapp.myapplication.database.FlowersDatabase
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
import ru.myapp.myapplication.databinding.ActivityMainBinding
import ru.myapp.myapplication.model.Bouquet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FlowersDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = FlowersDatabase.getInstance(this)

        lifecycleScope.launch(Dispatchers.IO) {
            fillDb()
        }

        //да, я поленился создать бОльший список букетов
        val firstBouquet = BouquetGenerator.generateBouquet()
        val secondBouquet = BouquetGenerator.generateBouquet()
        val thirdBouquet = BouquetGenerator.generateBouquet()

        with(binding) {
            bouquet1.text = "Размер: ${firstBouquet.flowerCount}\nCостоит из: \n   ${getBouquetDescription(firstBouquet)}"
            bouquet2.text = "Размер: ${secondBouquet.flowerCount}\nCостоит из: \n   ${getBouquetDescription(secondBouquet)}"
            bouquet3.text = "Размер: ${thirdBouquet.flowerCount}\nCостоит из: \n   ${getBouquetDescription(thirdBouquet)}"

            bouquet1.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    removeFlowers(firstBouquet)
                    fillDb() //чтобы без цветов не остаться
                }
                Toast.makeText(this@MainActivity, "Куплено!", Toast.LENGTH_LONG).show()
            }
            bouquet2.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    removeFlowers(secondBouquet)
                    fillDb()
                }
                Toast.makeText(this@MainActivity, "Куплено!", Toast.LENGTH_LONG).show()
            }
            bouquet3.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    removeFlowers(thirdBouquet)
                    fillDb()
                }
                Toast.makeText(this@MainActivity, "Куплено!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getBouquetDescription(bouquet: Bouquet): String {
        val flowersGroup = bouquet.bouquet.groupingBy { it.name }.eachCount()
        val stringBuilder = StringBuilder()

        flowersGroup.forEach {
            stringBuilder.append("${it.key}: ${it.value}, ")
        }

        return stringBuilder.toString()
    }

    private suspend fun fillDb() {
        repeat(10) {
            db.getCarnationDao().insert(CarnationDbo(color = "red"))
            db.getDaisyDao().insert(DaisyDbo(color = "pink"))
            db.getDandelionDao().insert(DandelionDbo(color = "yellow"))
            db.getLavenderDao().insert(LavenderDbo(color = "blue"))
            db.getLilyDao().insert(LilyDbo(color = "white"))
            db.getOrchidDao().insert(OrchidDbo(color = "white"))
            db.getPoppyDao().insert(PoppyDbo(color = "red"))
            db.getRoseDao().insert(RoseDbo(color = "red"))
            db.getSunflowerDao().insert(SunflowerDbo(color = "yellow"))
            db.getTulipDao().insert(TulipDbo(color = "blue"))
        }
    }

    private suspend fun removeFlowers(bouquet: Bouquet) {
        bouquet.bouquet.forEach {
            when (it) {
                is CarnationDbo -> { db.getCarnationDao().delete(it) }
                is DaisyDbo -> { db.getDaisyDao().delete(it) }
                is DandelionDbo -> { db.getDandelionDao().delete(it) }
                is LavenderDbo -> { db.getLavenderDao().delete(it) }
                is LilyDbo -> { db.getLilyDao().delete(it) }
                is OrchidDbo -> { db.getOrchidDao().delete(it) }
                is PoppyDbo -> { db.getPoppyDao().delete(it) }
                is RoseDbo -> { db.getRoseDao().delete(it) }
                is SunflowerDbo -> { db.getSunflowerDao().delete(it) }
                is TulipDbo -> { db.getTulipDao().delete(it) }
            }
        }
    }
//  А зачем?
//    private fun saveBouquet(){}

}