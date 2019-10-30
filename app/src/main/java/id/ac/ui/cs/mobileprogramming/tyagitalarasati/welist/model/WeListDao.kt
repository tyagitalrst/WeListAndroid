package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeListDao {

    @Insert
    fun insert(weList: WeList)

    @Query("DELETE FROM welist_table WHERE id=:listId")
    fun deleteList(listId: Int)

    @Query("SELECT * FROM welist_table WHERE id=:listId")
    fun detailList(listId: Int): LiveData<WeList>

    @Query("SELECT * FROM welist_table")
    fun getAllWeList(): LiveData<List<WeList>>
}