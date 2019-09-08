package io.thed.applevel.storage.datasource

import androidx.lifecycle.LiveData
import io.thed.applevel.storage.AppDatabase
import io.thed.modules.repos.response.RepoItem

class GithubReposDataSource(db: AppDatabase) : DataSource<RepoItem>(db) {
    override fun insertItem(data: RepoItem) {
        doAsync { database.repoItemDao().insert(data) }
    }

    override fun insertAll(data: List<RepoItem>) {
        doAsync { database.repoItemDao().insertAll(*data.toTypedArray()) }
    }

    override fun update(data: RepoItem) {
        doAsync { database.repoItemDao().update(data) }
    }

    override fun delete(data: RepoItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll() {
        doAsync { database.repoItemDao().deleteAll() }
    }

    override fun getItems(): LiveData<List<RepoItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getItemsDataSource(): androidx.paging.DataSource.Factory<Int, RepoItem> {
        return database.repoItemDao().getAllUsersDataSource()
    }

    override fun getItem(): LiveData<RepoItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}