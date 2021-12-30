package com.artushock.home_work_5.user

import com.artushock.home_work_5.data.models.User
import com.artushock.home_work_5.data.repositories.UsersRepository
import com.artushock.home_work_5.data.repositories.UserConverter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(
    private val userLogin: String,
) : MvpPresenter<UserView>() {

    @Inject
    lateinit var repository: UsersRepository

    @Inject
    lateinit var userConverter: UserConverter

    override fun onFirstViewAttach() {
        showUserData(userLogin)
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