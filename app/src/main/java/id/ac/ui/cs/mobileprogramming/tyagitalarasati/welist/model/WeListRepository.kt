package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class WeListRepository(application: Application) {

    private var weListDao: WeListDao

    private var allWeList: LiveData<List<WeList>>

    private lateinit var detailList: LiveData<WeList>

    init {
        val database: WeListDatabase = WeListDatabase.getInstance(
            application.applicationContext
        )!!
        weListDao = database.weListDao()
        allWeList = weListDao.getAllWeList()
    }

    fun insert(weList: WeList) {
        val insertWeListAsyncTask = InsertWeListAsyncTask(weListDao).execute(weList)
    }

    fun deleteList(listId: Int) {
        val deleteWeListAsyncTask = DeleteWeListAsyncTask(weListDao).execute(listId)
    }


    fun detailList(listId: Int): LiveData<WeList> {
        detailList = weListDao.detailList(listId)
        return detailList
    }

    fun getAllWeList(): LiveData<List<WeList>> {
        return allWeList
    }

    private class InsertWeListAsyncTask(val weListDao: WeListDao) : AsyncTask<WeList, Unit, Unit>() {

        override fun doInBackground(vararg p0: WeList?) {
            weListDao.insert(p0[0]!!)
        }
    }


    private class DeleteWeListAsyncTask(val weListDao: WeListDao) : AsyncTask<Int, Unit, Unit>() {

        override fun doInBackground(vararg p0: Int?) {
            weListDao.deleteList(p0[0]!!)
        }
    }


}