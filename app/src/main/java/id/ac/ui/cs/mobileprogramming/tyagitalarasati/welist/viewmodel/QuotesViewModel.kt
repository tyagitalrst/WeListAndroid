package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.Repository.QuotesRepository
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.Quotes

class QuotesViewModel(application: Application) : AndroidViewModel(application) {
    private var repositoryQuotes: QuotesRepository = QuotesRepository(application)

    private lateinit var quotesOfTheDay: LiveData<Quotes>


    fun insertQuotes(quotes: Quotes) {
        repositoryQuotes.insertQuotes(quotes)
    }


    fun getQuotes(listId: Int): LiveData<Quotes> {
        quotesOfTheDay = repositoryQuotes.getQuotes(listId)
        return quotesOfTheDay
    }
}