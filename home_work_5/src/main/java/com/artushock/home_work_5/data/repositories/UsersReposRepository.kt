package com.artushock.home_work_5.data.repositories

import com.artushock.home_work_5.data.models.UserReposEntity
import io.reactivex.rxjava3.core.Single

interface UsersReposRepository {
    fun getUserRepos(login: String): Single<List<UserReposEntity>>
}