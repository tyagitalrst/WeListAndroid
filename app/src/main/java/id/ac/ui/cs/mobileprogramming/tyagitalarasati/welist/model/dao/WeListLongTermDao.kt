package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm

@Dao
interface WeListLongTermDao {

    @Insert
    fun insertLongTerm(weListLong: WeListLongTerm)

    @Query("DELETE FROM welistlong_table")
    fun deleteAllLongTerm()

    @Query("SELECT * FROM welistlong_table WHERE id=:listId")
    fun detailLongTermList(listId: Int): LiveData<WeListLongTerm>

    @Query("SELECT * FROM welistlong_table")
    fun getAllWeListLongTerm(): LiveData<List<WeListLongTerm>>
}