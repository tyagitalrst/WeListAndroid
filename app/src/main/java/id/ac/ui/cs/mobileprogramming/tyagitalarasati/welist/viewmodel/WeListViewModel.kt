package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.Repository.WeListRepository
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm

class WeListViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: WeListRepository = WeListRepository(application)

    private var allWeList: LiveData<List<WeList>> = repository.getAllWeList()
    private var allLongWeList: LiveData<List<WeListLongTerm>> = repository.getAllWeListLongTerm()


    private lateinit var detailWeList: LiveData<WeList>
    private lateinit var detailWeListLong: LiveData<WeListLongTerm>

    fun insert(weList: WeList) {
        repository.insert(weList)
    }

    fun deleteAllList() {
        repository.deleteList()
    }

    fun detailList(listId: Int): LiveData<WeList> {
        detailWeList = repository.detailList(listId)
        return detailWeList
    }

    fun getAllWeList(): LiveData<List<WeList>> {
        return allWeList
    }


    fun insertLongTerm(weListLong: WeListLongTerm) {
        repository.insertLongTerm(weListLong)
    }

    fun deleteAllLongTerm() {
        repository.deleteLongTermList()
    }


    fun detailLongTermList(listId: Int): LiveData<WeListLongTerm> {
        detailWeListLong = repository.detailLongTermList(listId)
        return detailWeListLong
    }

    fun getAllWeListLongTerm(): LiveData<List<WeListLongTerm>> {
        return allLongWeList
    }
}
