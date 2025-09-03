package ru.myapp.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.myapp.myapplication.database.dao.CarnationDao
import ru.myapp.myapplication.database.dao.DaisyDao
import ru.myapp.myapplication.database.dao.DandelionDao
import ru.myapp.myapplication.database.dao.LavenderDao
import ru.myapp.myapplication.database.dao.LilyDao
import ru.myapp.myapplication.database.dao.OrchidDao
import ru.myapp.myapplication.database.dao.PoppyDao
import ru.myapp.myapplication.database.dao.RoseDao
import ru.myapp.myapplication.database.dao.SunflowerDao
import ru.myapp.myapplication.database.dao.TulipDao
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

@Database(
    entities = [
        RoseDbo::class,
        CarnationDbo::class,
        DaisyDbo::class,
        DandelionDbo::class,
        LavenderDbo::class,
        LilyDbo::class,
        OrchidDbo::class,
        PoppyDbo::class,
        SunflowerDbo::class,
        TulipDbo::class
    ],
    version = 2
)
abstract class FlowersDatabase : RoomDatabase() {

    abstract fun getCarnationDao(): CarnationDao

    abstract fun getDaisyDao(): DaisyDao

    abstract fun getDandelionDao(): DandelionDao

    abstract fun getLavenderDao(): LavenderDao

    abstract fun getLilyDao(): LilyDao

    abstract fun getOrchidDao(): OrchidDao

    abstract fun getPoppyDao(): PoppyDao

    abstract fun getRoseDao(): RoseDao

    abstract fun getSunflowerDao(): SunflowerDao

    abstract fun getTulipDao(): TulipDao

    companion object {
        private var instance: FlowersDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE carnation ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE daisy ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE dandelion ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE lavender ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE lily ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE orchid ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE poppy ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE rose ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE sunflower ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
                db.execSQL("ALTER TABLE tulip ADD COLUMN country TEXT NOT NULL DEFAULT 'Holland'")
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