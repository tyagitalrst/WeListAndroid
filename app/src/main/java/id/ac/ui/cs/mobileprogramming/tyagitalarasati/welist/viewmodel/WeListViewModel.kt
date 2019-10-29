package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.WeListRepository

class WeListViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: WeListRepository = WeListRepository(application)
    private var allWeList: LiveData<List<WeList>> = repository.getAllWeList()
    private lateinit var detailWeList: LiveData<WeList>

    fun insert(weList: WeList) {
        repository.insert(weList)
    }

    fun deleteList(listId: Int) {
        repository.deleteList(listId)
    }

    fun detailList(listId: Int): LiveData<WeList> {
        detailWeList = repository.detailList(listId)
        return detailWeList
    }

    fun getAllWeList(): LiveData<List<WeList>> {
        return allWeList
    }
}
