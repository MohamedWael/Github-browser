package io.thed.applevel.storage.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.thed.applevel.storage.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class DataSource<Data>(val database: AppDatabase) {

    abstract fun insertItem(data: Data)

    abstract fun insertAll(data: List<Data>)

    abstract fun update(data: Data)

    abstract fun delete(data: Data)

    abstract fun deleteAll()

    abstract fun getItems(): LiveData<List<Data>>

    abstract fun getPagedItems(): LiveData<PagedList<Data>>

    abstract fun getItem(): LiveData<Data>

    fun <T> doAsync(action: () -> T) {
        GlobalScope.launch(Dispatchers.IO) {
            action()
        }
    }

    fun throwUnsupportedOperationException() {
        throw UnsupportedOperationException("this method is not implemented and may not be used")
    }
}
