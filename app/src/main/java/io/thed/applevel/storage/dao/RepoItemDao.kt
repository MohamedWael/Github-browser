package io.thed.applevel.storage.dao

import androidx.paging.DataSource
import androidx.room.*
import io.thed.modules.repos.response.RepoItem


@Dao
interface RepoItemDao {

    @Query("SELECT * from github_repo")
    fun getAllUsersDataSource(): DataSource.Factory<Int, RepoItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repos: RepoItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(repo: RepoItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RepoItem)

    @Query("DELETE FROM github_repo")
    fun deleteAll()
}