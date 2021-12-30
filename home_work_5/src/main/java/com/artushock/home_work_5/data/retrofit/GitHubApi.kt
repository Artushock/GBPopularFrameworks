package com.artushock.home_work_5.data.retrofit

import com.artushock.home_work_5.data.models.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("/users")
    fun fetchUsers(): Single<List<User>>

    @GET("/users/{login}")
    fun fetchUserDataByLogin(@Path("login") login: String): Single<User>
}