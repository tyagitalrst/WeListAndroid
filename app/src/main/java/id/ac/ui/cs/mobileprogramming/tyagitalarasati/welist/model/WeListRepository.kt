package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeList
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao.WeListDao
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.dao.WeListLongTermDao
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database.WeListDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.database.WeListLongTermDatabase
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.model.entity.WeListLongTerm

class WeListRepository(application: Application) {

    private var weListDao: WeListDao
    private var weListLongTermDao: WeListLongTermDao

    private var allWeList: LiveData<List<WeList>>
    private var allLongWeList: LiveData<List<WeListLongTerm>>

    private lateinit var detailList: LiveData<WeList>
    private lateinit var detailListLong: LiveData<WeListLongTerm>

    init {
        val database: WeListDatabase = WeListDatabase.getInstance(
            application.applicationContext
        )!!

        val longDatabase: WeListLongTermDatabase = WeListLongTermDatabase.getInstanceLong(
            application.applicationContext
        )!!
        weListDao = database.weListDao()
        weListLongTermDao = longDatabase.weListLongTermDao()

        allWeList = weListDao.getAllWeList()
        allLongWeList = weListLongTermDao.getAllWeListLongTerm()
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

    fun insertLongTerm(weListLong: WeListLongTerm) {
        val insertWeListLongAsyncTask = InsertWeListLongAsyncTask(weListLongTermDao).execute(weListLong)
    }

    fun deleteLongTermList(listId: Int) {
        val deleteWeListLongAsyncTask = DeleteWeListLongAsyncTask(weListLongTermDao).execute(listId)
    }


    fun detailLongTermList(listId: Int): LiveData<WeListLongTerm> {
        detailListLong = weListLongTermDao.detailLongTermList(listId)
        return detailListLong
    }

    fun getAllWeListLongTerm(): LiveData<List<WeListLongTerm>> {
        return allLongWeList
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



    private class InsertWeListLongAsyncTask(val weListLongDao: WeListLongTermDao) : AsyncTask<WeListLongTerm, Unit, Unit>() {

        override fun doInBackground(vararg p0: WeListLongTerm?) {
            weListLongDao.insertLongTerm(p0[0]!!)
        }
    }


    private class DeleteWeListLongAsyncTask(val weListLongDao: WeListLongTermDao) : AsyncTask<Int, Unit, Unit>() {

        override fun doInBackground(vararg p0: Int?) {
            weListLongDao.deleteLongTermList(p0[0]!!)
        }
    }


}