package io.thed.applevel.storage.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.thed.applevel.storage.AppDatabase
import io.thed.modules.repos.response.RepoItem
import kotlinx.coroutines.*

class GithubReposDatasource(db: AppDatabase) : DataSource<RepoItem>(db) {
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

    override fun getPagedItems(): LiveData<PagedList<RepoItem>> {
        return database.repoItemDao().getAllUsers()
    }

    override fun getItem(): LiveData<RepoItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}