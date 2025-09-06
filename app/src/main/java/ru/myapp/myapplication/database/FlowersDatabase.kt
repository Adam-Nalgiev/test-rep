package ru.myapp.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.myapp.myapplication.database.dao.FlowerDao
import ru.myapp.myapplication.database.dbo.BouquetDbo
import ru.myapp.myapplication.database.dbo.FlowerBouquetDbo
import ru.myapp.myapplication.database.dbo.FlowerDbo
import ru.myapp.myapplication.database.dbo.InventoryDbo

@Database(
    entities = [
        FlowerDbo::class,
        BouquetDbo::class,
        FlowerBouquetDbo::class,
        InventoryDbo::class
    ],
    version = 2,
    exportSchema = true
)
abstract class FlowersDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao

    companion object {
        @Volatile
        private var instance: FlowersDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE flower ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE bouquet ADD COLUMN decor TEXT NOT NULL DEFAULT 'None'")
            }
        }

        fun getInstance(context: Context): FlowersDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    FlowersDatabase::class.java,
                    "flowers database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                instance = newInstance
                newInstance
            }
        }
    }
}