package com.artushock.home_work_5.users

import com.artushock.home_work_5.application.App.Companion.router
import com.artushock.home_work_5.data.GitHubUserRepository
import com.artushock.home_work_5.data.convertUserListToUserItemList
import com.artushock.home_work_5.user.UserScreen
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(private val repository: GitHubUserRepository) :
    MvpPresenter<UsersView>() {

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
                viewState.showUsers(convertUserListToUserItemList(usersList))
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })
    }
}