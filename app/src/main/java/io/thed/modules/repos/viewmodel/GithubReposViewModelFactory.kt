package io.thed.modules.repos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.thed.modules.repos.repository.GithubReposRepositoryImpl

class GithubReposViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubReposViewModel(GithubReposRepositoryImpl()) as T
    }
}