package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao.WeListDao


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
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
