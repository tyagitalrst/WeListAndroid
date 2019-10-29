package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [WeList::class], version = 1)
abstract class WeListDatabase : RoomDatabase() {

    abstract fun weListDao(): WeListDao

    companion object {
        private var INSTANCE: WeListDatabase? = null

        fun getInstance(context: Context): WeListDatabase? {
            if (INSTANCE == null) {
                synchronized(WeListDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WeListDatabase::class.java, "welist_database"
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
                PopulateDbAsyncTask(INSTANCE)
                    .execute()
            }
        }
    }
}



class PopulateDbAsyncTask(db: WeListDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val weListDao = db?.weListDao()

    override fun doInBackground(vararg p0: Unit?) {
        weListDao?.insert(WeList("Title 1", "Notes 1", "Price 1", "Link 1"))
        weListDao?.insert(WeList("Title 2", "Notes 2", "Price 2", "Link 2"))
        weListDao?.insert(WeList("Title 3", "Notes 3", "Price 3", "Link 3"))
    }
}