package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao.QuotesDao
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.data.PopulateDbQuotesAsyncTask
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.Quotes


@Database(entities = [Quotes::class], version = 1)
abstract class QuotesDatabase: RoomDatabase() {
    abstract fun quotesDao(): QuotesDao

    companion object {
        private var INSTANCE: QuotesDatabase? = null

        fun getInstanceQuotes(context: Context): QuotesDatabase? {
            if (INSTANCE == null) {
                synchronized(QuotesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuotesDatabase::class.java, "quotes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return INSTANCE
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbQuotesAsyncTask(INSTANCE)
                    .execute()
            }
        }
    }

}




