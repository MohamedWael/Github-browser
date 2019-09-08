package io.thed.modules.repos.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.thed.applevel.ITEMS_PER_PAGE
import io.thed.applevel.PAGE
import io.thed.applevel.PER_PAGE
import io.thed.applevel.network.ErrorHandler
import io.thed.applevel.network.RestCleint
import io.thed.applevel.network.networkservice.network
import io.thed.applevel.storage.StorageManager
import io.thed.modules.repos.response.RepoItem
import io.thed.modules.repos.response.UserReposResponse


class GithubReposRepositoryImpl : GithubReposRepository {

    override val liveErrorHandler: MutableLiveData<ErrorHandler> = MutableLiveData()
    override val liveIsLastItem: MutableLiveData<Boolean> = MutableLiveData()

    override fun getReposDataSource(): DataSource.Factory<Int, RepoItem> {
        return StorageManager.githubReposDataSource.getItemsDataSource()
    }

    override fun getRepos(page: Int) {

        network<UserReposResponse> {
            execute(
                createRestClient(RestCleint::class.java).getGithubRepos(
                    "JakeWharton",
                    mapOf(Pair(PAGE, page.toString()), Pair(PER_PAGE, ITEMS_PER_PAGE.toString()))
                ), {
                    liveIsLastItem.value = it.size == 0
                    StorageManager.githubReposDataSource.insertAll(it)
                }, {
                    liveErrorHandler.value = it
                })
        }
    }
}