package com.artushock.home_work_5.user

import android.util.Log
import com.artushock.home_work_5.data.models.User
import com.artushock.home_work_5.data.models.UserReposEntity
import com.artushock.home_work_5.data.repositories.UserConverter
import com.artushock.home_work_5.data.repositories.UsersReposRepository
import com.artushock.home_work_5.data.repositories.UsersRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter : MvpPresenter<UserView>() {

    @Inject
    lateinit var repository: UsersRepository

    @Inject
    lateinit var userConverter: UserConverter

    @Inject
    lateinit var usersReposRepository: UsersReposRepository

    private lateinit var userLogin: String

    fun init(login: String) {
        userLogin = login
    }

    override fun onFirstViewAttach() {
        showUserData(userLogin)
        logUserRepos(userLogin)
    }

    private fun logUserRepos(userLogin: String) {
        usersReposRepository.getUserRepos(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userRepos: List<UserReposEntity> ->
                for (repo in userRepos) {
                    Log.d("123123123",
                        "$userLogin repos\n " +
                                "id: ${repo.id} " +
                                "login: ${repo.login} " +
                                "name: ${repo.name} " +
                                "private: ${repo.private} " +
                                "htmlUrl: ${repo.htmlUrl} " +
                                "description: ${repo.description} " +
                                "createdAt: ${repo.createdAt} " +
                                "updatedAt: ${repo.updatedAt} " +
                                "pushedAt: ${repo.pushedAt} " +
                                "size: ${repo.size} " +
                                "forks: ${repo.forks} " +
                                "watchers: ${repo.watchers}")
                }
            }, {
                Log.d("123123123", "$userLogin error: ${it.message}")
            })
    }

    private fun showUserData(userLogin: String) {
        repository.getUserDataByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user: User ->
                viewState.showUserDetail(userConverter.convertUserToUserDetail(user))
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })
    }

}