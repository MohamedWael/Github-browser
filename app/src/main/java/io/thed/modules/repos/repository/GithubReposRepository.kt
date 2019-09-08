package io.thed.modules.repos.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.thed.applevel.network.ErrorHandler
import io.thed.modules.repos.response.RepoItem

interface GithubReposRepository {

    val liveErrorHandler: MutableLiveData<ErrorHandler>

    fun getReposDataSource(): DataSource.Factory<Int, RepoItem>

    fun getRepos(page: Int = 1)
}