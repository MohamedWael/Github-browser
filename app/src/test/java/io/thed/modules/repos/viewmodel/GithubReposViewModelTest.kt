package io.thed.modules.repos.viewmodel

import io.thed.applevel.BASE_API
import io.thed.applevel.network.Retrofit
import io.thed.applevel.storage.AppDatabase
import io.thed.applevel.storage.StorageManager
import io.thed.applevel.storage.dao.RepoItemDao
import io.thed.applevel.storage.datasource.GithubReposDataSource
import io.thed.modules.repos.repository.GithubReposRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GithubReposViewModelTest {

    lateinit var viewModel: GithubReposViewModel
    lateinit var repo: GithubReposRepository
    @Before
    fun setUp() {
        repo = Mockito.mock(GithubReposRepository::class.java)
        Retrofit.init(BASE_API)
        val db = Mockito.mock(AppDatabase::class.java)
        Mockito.`when`(db.repoItemDao()).thenReturn(Mockito.mock(RepoItemDao::class.java))
        Mockito.`when`(db.repoItemDao().getAllUsersDataSource()).thenReturn(FakeReposDataSource())
        StorageManager.githubReposDataSource = GithubReposDataSource(db)
        Mockito.`when`(repo.getReposDataSource()).thenReturn(FakeReposDataSource())
        viewModel = GithubReposViewModel(repo)
    }

    @Test
    fun testIsLoadingMore() {
        viewModel.loadNextPage()
        assertTrue(viewModel.isLoadingMore.get() == true)
    }

    @Test
    fun testIsShowingProgress() {
        viewModel.getUserData()
        assertTrue(viewModel.showProgress.get() == true)
    }

    @Test
    fun testHideProgress() {
        viewModel.hideProgress()
        assertFalse(viewModel.showProgress.get() ?: true)
        assertFalse(viewModel.isLoadingMore.get() ?: true)
    }

    @Test
    fun testGetUserData() {
        viewModel.getUserData()
        assertTrue(viewModel.showProgress.get() ?: false)
        assertFalse(viewModel.isLoadingMore.get() ?: true)
        Mockito.verify(repo, Mockito.times(1)).getRepos(1)
    }

    @Test
    fun testLoadNextPage() {
        viewModel.loadNextPage()
        assertTrue(viewModel.isLoadingMore.get() ?: false)
        assertFalse(viewModel.showProgress.get() ?: true)
        Mockito.verify(repo, Mockito.times(1)).getRepos(2)
    }
}