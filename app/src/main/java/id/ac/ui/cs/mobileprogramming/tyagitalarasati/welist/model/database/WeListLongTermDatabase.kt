package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao.WeListLongTermDao
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm

@Database(entities = [WeListLongTerm::class], version = 1)
abstract class WeListLongTermDatabase: RoomDatabase() {

    abstract fun weListLongTermDao(): WeListLongTermDao

    companion object {
        private var INSTANCE: WeListLongTermDatabase? = null

        fun getInstanceLong(context: Context): WeListLongTermDatabase? {
            if (INSTANCE == null) {
                synchronized(WeListLongTermDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WeListLongTermDatabase::class.java, "welistlong_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}