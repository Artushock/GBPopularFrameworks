package com.artushock.home_work_5.data.repositories

import com.artushock.home_work_5.data.models.User
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(): Single<List<User>>
    fun getUserDataByLogin(login: String): Single<User>
}