package com.artushock.home_work_5.data.repositories

import com.artushock.home_work_5.data.models.User
import com.artushock.home_work_5.data.retrofit.GitHubApi
import com.artushock.home_work_5.data.room.UserDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersRepositoryImpl
@Inject constructor(
    private val api: GitHubApi,
    private val dao: UserDao,
) : UsersRepository {


    override fun getUsers(): Single<List<User>> {
        return dao.getUsers()
            .flatMap {
                if (it.isEmpty()) {
                    api.fetchUsers()
                        .map { resultFromServer ->
                            dao.saveUser(resultFromServer)
                            resultFromServer
                        }
                } else {
                    Single.just(it)
                }
            }
    }

    override fun getUserDataByLogin(login: String): Single<User> =
        dao.getUserByLogin(login)
}