package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.Quotes

@Dao
interface QuotesDao {

    @Insert
    fun insertQuotes(quotes: Quotes)

    @Query("SELECT * FROM quotes_table WHERE id=:listId")
    fun getQuotes(listId: Int): LiveData<Quotes>

}