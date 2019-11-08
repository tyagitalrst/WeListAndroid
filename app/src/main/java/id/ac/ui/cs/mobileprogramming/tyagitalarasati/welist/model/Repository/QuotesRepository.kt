package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.Repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao.QuotesDao
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database.QuotesDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.Quotes

class QuotesRepository(application: Application) {

        private var quotesDao: QuotesDao

        private lateinit var quotesOfTheDay: LiveData<Quotes>


        init {
            val database: QuotesDatabase = QuotesDatabase.getInstanceQuotes(
                application.applicationContext
            )!!

            quotesDao = database.quotesDao()
        }


        fun insertQuotes(quotes: Quotes) {
            val insertQuotesAsyncTask = InsertQuotesAsyncTask(
                quotesDao
            ).execute(quotes)
        }

        fun getQuotes(listId: Int): LiveData<Quotes> {
            quotesOfTheDay = quotesDao.getQuotes(listId)
            return quotesOfTheDay
        }


        private class InsertQuotesAsyncTask(val quotesDao: QuotesDao) : AsyncTask<Quotes, Unit, Unit>() {

            override fun doInBackground(vararg p0: Quotes?) {
                quotesDao.insertQuotes(p0[0]!!)
            }
        }

    }