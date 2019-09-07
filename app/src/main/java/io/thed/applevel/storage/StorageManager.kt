package io.thed.applevel.storage

import android.content.Context
import io.thed.applevel.storage.datasource.GithubReposDataSource

object StorageManager {

    private lateinit var githubReposDataSource: GithubReposDataSource

    fun init(context: Context) {
        val db = AppDatabase.getDatabase(context)
        githubReposDataSource = GithubReposDataSource(db)
    }
}