package io.thed.applevel.network

import io.reactivex.Observable
import io.thed.applevel.REPOS
import io.thed.applevel.USERS
import io.thed.modules.repos.response.UserReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RestCleint {

    @GET("$USERS/{userName}/$REPOS")
    fun getGithubRepos(@Path("userName") userName: String, @QueryMap page: Map<String, String>): Observable<UserReposResponse>
}