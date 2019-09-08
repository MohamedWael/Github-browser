package io.thed.modules.repos.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.thed.applevel.network.ErrorHandler
import io.thed.modules.repos.repository.GithubReposRepository
import io.thed.modules.repos.repository.GithubReposRepositoryImpl
import io.thed.modules.repos.repository.ITEMS_PER_PAGE
import io.thed.modules.repos.response.RepoItem

class GithubReposViewModel(private val repository: GithubReposRepository) : ViewModel() {

    val isLoadingMore = ObservableField(false)
    val showProgress = ObservableField(false)

    val errorHandler: LiveData<ErrorHandler>
        get() = repository.liveErrorHandler

    var page = 1

    val liveRepoList: LiveData<PagedList<RepoItem>> = LivePagedListBuilder(
        repository.getReposDataSource(),
        PagedList.Config.Builder().setInitialLoadSizeHint(15).setPageSize(ITEMS_PER_PAGE).setPrefetchDistance(
            12
        ).setEnablePlaceholders(
            false
        ).build()
    ).setBoundaryCallback(object : PagedList.BoundaryCallback<RepoItem>() {
        override fun onItemAtEndLoaded(itemAtEnd: RepoItem) {
            super.onItemAtEndLoaded(itemAtEnd)
            loadNextPage()
        }
    }).build()

    fun hideProgress() {
        showProgress.set(false)
        isLoadingMore.set(false)
    }

    fun getUserData() {
        showProgress.set(page == 1)
        isLoadingMore.set(page > 1)
        repository.getRepos(page)
    }

    fun loadNextPage() {
        page++
        getUserData()
        Log.d("getUserData", "Page: $page")
    }
}
