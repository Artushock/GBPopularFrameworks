package com.artushock.home_work_5.users

import com.artushock.home_work_5.data.GitHubUserRepository
import com.artushock.home_work_5.data.UserConverter
import com.artushock.home_work_5.user.UserScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter :
    MvpPresenter<UsersView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repository: GitHubUserRepository

    @Inject
    lateinit var userConverter: UserConverter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateUsersList()
    }

    fun showUserFragment(login: String) {
        router.navigateTo(UserScreen(login))
    }

    private fun updateUsersList() {
        repository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ usersList ->
                viewState.showUsers(userConverter.convertUserListToUserItemList(usersList))
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })
    }
}