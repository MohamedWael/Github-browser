package io.thed.modules.repos.viewmodel

import androidx.paging.DataSource
import io.thed.modules.repos.response.RepoItem

class FakeReposDataSource : DataSource.Factory<Int, RepoItem>(){
    override fun create(): DataSource<Int, RepoItem> {
        return FakeReposDataSource() as DataSource<Int, RepoItem>
    }
}