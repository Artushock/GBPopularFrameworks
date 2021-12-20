package com.artushock.home_work_5.user

import com.artushock.home_work_5.data.GitHubUser
import com.artushock.home_work_5.data.GitHubUserRepository
import com.artushock.home_work_5.data.convertUserToUserDetail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val repository: GitHubUserRepository,
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        showUserData(userLogin)
    }

    private fun showUserData(userLogin: String) {
        repository.getUserDataByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user: GitHubUser ->
                viewState.showUserDetail(convertUserToUserDetail(user))
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })
    }

}